package entities;

import java.util.ArrayList;

public class Shop {
    private static Shop instance;
    private ArrayList<Ticket> tickets;

    private Shop() {
        this.tickets = new ArrayList<Ticket>();
    }
    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }
    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
    public double getTotalStockPrice() {
        double total = 0;
        for (StockItem item : Stock.getInstance().getItems()) {
            total += item.getProduct().getPrice() * item.getQuantity();
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
