package persistence;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Ticket;
import entities.TicketManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TicketDB {

    private static TicketDB instance;
    private Ticket ticketDB;

    public TicketDB() {

    }
    public TicketDB(Ticket ticket){
        ticketDB = ticket;
    }
    public static TicketDB getInstance(){
        if (instance == null) {
            instance = new TicketDB();
        }
        return instance;
    }

    public void leerBd(String filePath) throws IOException {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
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
                long creationDateMillis = ticketObject.get("ticketDate").asLong();
                Date ticketDate = new Date(creationDateMillis);
                int quantity = ticketObject.get("quantity").asInt();
                double totalPrice = ticketObject.get("totalPrice").asDouble();

                // Crear un objeto Ticket con los datos leídos
                Ticket ticket = new Ticket(ticketId, productId, productName, ticketDate, quantity, totalPrice);

                // Agregar el ticket al TicketManager
                TicketManager.getInstance().addTicket(ticket);
            }
        }
    }


    public void guardarBd(String filePath) throws IOException {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre(filePath);
        List<Ticket> tickets = TicketManager.getInstance().getTickets();

        JSONObject jsonTickets = new JSONObject();
        JSONArray ticketArray = new JSONArray();

        for (Ticket ticket : tickets) {
            JSONObject ticketObject = new JSONObject();
            ticketObject.put("ticketId", ticket.getTicketId());
            ticketObject.put("productId", ticket.getProductId());
            ticketObject.put("prductName", ticket.getProductName());
            ticketObject.put("ticketDate", ticket.getTicketDate());
            ticketObject.put("quantity", ticket.getQuantity());
            ticketObject.put("totalPrice", ticket.getTotalPrice());
            ticketArray.put(ticketObject);
        }
        jsonTickets.put("tickets", ticketArray);
        f.write(jsonTickets);
    }
}
