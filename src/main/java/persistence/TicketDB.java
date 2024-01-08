package persistence;

import com.fasterxml.jackson.databind.JsonNode;
import entities.Ticket;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketDB {
    private List<Ticket> ticketList;

    public TicketDB() {
        this.ticketList = new ArrayList<>();
    }
    public void leerBd() {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre("Ticket.txt");

        File file = new File(f.getNombre());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JsonNode jsonNode = (JsonNode) f.leer();

        if (jsonNode != null) {
            JsonNode ticketsNode = jsonNode.get(Ticket.class.toString());
            if (ticketsNode != null) {
                for (JsonNode prod : ticketsNode) {
                    String tipo = prod.get("tipo").asText();
                    if (tipo.equals(Ticket.class.toString())) {
                        Ticket tk = new Ticket(prod.get("id").asInt(),prod.get("quantity").asInt(), prod.get("totalPrice").asDouble());
                        if (!ticketList.contains(tk))
                            ticketList.add(tk);
                    }
                }
            } else {
                System.out.println("Error al leer el archivo JSON");
            }
        }
    }
    public void guardarBd() {
        ConexionFichero f = (ConexionFichero) FactoryBD.getConexionBD("TXT");
        f.setNombre("Ticket.txt");
        JSONObject jsonTicket = new JSONObject();
        jsonTicket.put(Ticket.class.toString(), ticketList);
        f.write(jsonTicket);
    }
}
