package app;

import entities.Product;
import entities.Stock;
import entities.StockItem;
import entities.Ticket;
import interfaces.Ipersistence;
//import persistence.ShopDB;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private static Shop instance;
    private String name;
    private List<Stock> stockProducts = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private int id = 0;
    private int nextTicketId = 1;

    private Shop() {

    }

    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }
    public int getNextTicketId() {
        int currentId = nextTicketId;
        nextTicketId++;
        return currentId;
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
            if (stock.findStockItemById(product.getId()) != null) {
                return stock;
            }
        }
        return null;
    }

    public Product getProductById(int productId) {
        for (Stock stock : stockProducts) {
            StockItem stockItem = stock.findStockItemById(productId);
            if (stockItem != null) {
                return stockItem.getProduct();
            }
        }
        return null;
    }
    public void displayAllStock() {
        System.out.println("Contenido completo del stock:");

        for (Stock stock : stockProducts) {
            stock.displayAllStock();
        }
    }

    public List<Stock> getStockProducts() {
        return stockProducts;
    }

}

