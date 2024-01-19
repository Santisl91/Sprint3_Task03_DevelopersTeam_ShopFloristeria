package dataBases;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Ticket;
import entities.TicketManager;
import menu.FactoryDb;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.FileConnection;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class TicketDb {

    private static TicketDb instance;
    private Ticket ticketDB;

    public TicketDb() {

    }
    public TicketDb(Ticket ticket){
        ticketDB = ticket;
    }
    public static TicketDb getInstance(){
        if (instance == null) {
            instance = new TicketDb();
        }
        return instance;
    }
    public void leerBd(String filePath) throws IOException {
        FileConnection f = (FileConnection) FactoryDb.getConexionBD("TXT");
        f.setNombre(filePath);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(f.getNombre()));

        if (rootNode != null && rootNode.has("tickets")) {
            JsonNode ticketNode = rootNode.get("tickets");

            Iterator<JsonNode> ticketIterator = ticketNode.elements();
            while (ticketIterator.hasNext()) {
                JsonNode ticketObject = ticketIterator.next();

                int ticketId = ticketObject.get("ticketId").asInt();
                int productId = ticketObject.get("productId").asInt();
                String productName = ticketObject.get("productName").asText();
                String dateString = ticketObject.get("ticketDate").asText();
                LocalDate ticketDate = LocalDate.parse(dateString);
                int quantity = ticketObject.get("quantity").asInt();
                double totalPrice = ticketObject.get("totalPrice").asDouble();

                Ticket ticket = new Ticket(ticketId, productId, productName, ticketDate, quantity, totalPrice);

                TicketManager.getInstance().addTicket(ticket);
            }
        }
    }

    public void guardarBd(String filePath) throws IOException {
        FileConnection f = (FileConnection) FactoryDb.getConexionBD("TXT");
        f.setNombre(filePath);
        List<Ticket> tickets = TicketManager.getInstance().getTickets();

        JSONObject jsonTickets = new JSONObject();
        JSONArray ticketArray = new JSONArray();

        for (Ticket ticket : tickets) {
            JSONObject ticketObject = new JSONObject();
            ticketObject.put("ticketId", ticket.getTicketId());
            ticketObject.put("productId", ticket.getProductId());
            ticketObject.put("productName", ticket.getProductName());
            ticketObject.put("ticketDate", ticket.getTicketDate());
            ticketObject.put("quantity", ticket.getQuantity());
            ticketObject.put("totalPrice", ticket.getTotalPrice());
            ticketArray.put(ticketObject);
        }
        jsonTickets.put("tickets", ticketArray);
        f.write(jsonTickets);
    }
}
