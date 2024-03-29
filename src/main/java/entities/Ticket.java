package entities;
import java.time.LocalDate;

public class Ticket {
    private static int lastTicketId = 0;

    private int ticketId;
    private int productId;
    private String productName;
    private LocalDate ticketDate;
    private int quantity;
    private double totalPrice;

    public Ticket(int ticketId, int productId, String productName, LocalDate ticketDate, int quantity, double totalPrice ) {
        this.ticketId = ticketId;
        this.productId = productId;
        this.productName = productName;
        this.ticketDate = ticketDate;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
    public Ticket(int productId, String productName, int quantity, LocalDate ticketDate) {
        this.ticketId = generateTicketId();
        this.productId = productId;
        this.productName = productName;
        this.ticketDate = ticketDate;
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice();
    }

    private int generateTicketId() {
        lastTicketId++;
        return lastTicketId;
    }

    private double calculateTotalPrice() {
        double productPrice = Catalogue.getInstance().getProductPriceById(productId);
        return productPrice * quantity;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public LocalDate getTicketDate() {
        return ticketDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

