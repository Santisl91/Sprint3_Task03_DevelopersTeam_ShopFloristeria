package entities;

import interfaces.Ipersistence;
import dataBases.TicketDb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketManager implements Ipersistence {
    private static TicketManager instance;
    private List<Ticket> tickets;

    private TicketManager() {
        tickets = new ArrayList<>();
    }

    public static TicketManager getInstance() {
        if (instance == null) {
            instance = new TicketManager();
        }
        return instance;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public void leerTicket(String ticketFileName) throws IOException {
        TicketDb ticketDB = TicketDb.getInstance();
        ticketDB.leerBd(ticketFileName);
    }

    @Override
    public void guardarTicket(String ticketFileName) throws IOException {
        TicketDb ticketDb1 = TicketDb.getInstance();
        ticketDb1.guardarBd(ticketFileName);
    }

    @Override
    public void leerShop() throws IOException {
    }

    @Override
    public void guardarShop(Shop shop) throws IOException {
    }

    @Override
    public void guardarCatalogo(String catalogoFileName) throws IOException {
    }

    @Override
    public void guardarStock(String stockFileName) throws IOException {
    }

    @Override
    public void leerCatalogo(String catalogoFileName) throws IOException {
    }

    @Override
    public void leerStock(String stockFileName) throws IOException {
    }

}


