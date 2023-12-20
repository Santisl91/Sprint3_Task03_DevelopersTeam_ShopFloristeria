package entities;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Ticket> tickets;

    private Shop() {
        this.tickets = new ArrayList<Ticket>();
    }
    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
    public double getTotalStockPrice() {
        double total = 0;
        for (Ticket ticket : this.tickets) {
            total += ticket.getTotalPrice();
        }
        return total;
    }
    public double getTotalVentas() {
        double total = 0;
        for (Ticket ticket : this.tickets) {
            total += ticket.getTotalPrice();
        }
        return total;
    }
}
