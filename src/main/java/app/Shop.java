package app;

import entities.Product;
import entities.Stock;
import entities.Ticket;
import interfaces.Ipersistence;


import java.util.ArrayList;
import java.util.List;

public class Shop {

    private static Shop instance;
    private String name;
    private List<Stock> stockProducts = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private int id = 0;
    private static int nextId = 1;

    private Shop() {

    }

    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public List<Ticket> getTickets() {
        return tickets;
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

    public int getId() {
        return id;
    }

    public Stock encontrarStock(Product product) {
        for (Stock stock : stockProducts) {
            if (stock.findStockItem(product) != null) {
                return stock;
            }
        }
        return null;
    }

    public List<Stock> getStockProducts() {
        return stockProducts;
    }

    //TODO: listar los productos con sus cantidades en stock en base al id del producto

}

