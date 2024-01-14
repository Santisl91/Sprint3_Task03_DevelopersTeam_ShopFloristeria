package entities;
import java.util.Date;

public class Ticket {
    private static int lastTicketId = 0;

    private int ticketId;
    private int productId;
    private String productName;
    private Date ticketDate;
    private int quantity;
    private double totalPrice;

    public Ticket(int ticketId, int productId, String productName, Date ticketDate, int quantity, double totalPrice ) {
        this.ticketId = ticketId;
        this.productId = productId;
        this.productName = productName;
        this.ticketDate = ticketDate;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
    public Ticket(int productId, String productName, int quantity) {
        this.ticketId = generateTicketId();
        this.productId = productId;
        this.productName = productName;
        this.ticketDate = new Date();
        this.quantity = quantity;
        // Puedes calcular el precio total aquí o en otro método, dependiendo de tus necesidades
        this.totalPrice = calculateTotalPrice();
    }

    private int generateTicketId() {
        lastTicketId++;
        return lastTicketId;
    }

    private double calculateTotalPrice() {
        // Implementa la lógica para calcular el precio total, según tus necesidades
        // Puedes obtener el precio del producto desde otra fuente o utilizar una lógica específica
        return 0.0;  // Reemplaza esto con tu lógica real
    }

    // Métodos getter para acceder a los atributos del ticket

    public int getTicketId() {
        return ticketId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Date getTicketDate() {
        return ticketDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

