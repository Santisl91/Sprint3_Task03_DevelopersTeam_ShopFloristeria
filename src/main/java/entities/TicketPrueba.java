package entities;



import java.util.Date;
import java.util.List;

public class TicketPrueba {
    private static int lastTicketId = 0;

    private int ticketId;
    private List<ProductInfo> productInfoList;
    private Date ticketDate;
    private double totalPrice;

    public TicketPrueba(int ticketId, List<ProductInfo> productInfoList, Date ticketDate) {
        this.ticketId = ticketId;
        this.productInfoList = productInfoList;
        this.ticketDate = ticketDate;
        this.totalPrice = calcularTotal();
    }

    public TicketPrueba(List<ProductInfo> productInfoList) {
        this.ticketId = generateTicketId();
        this.productInfoList = productInfoList;
        this.ticketDate = new Date();
        this.totalPrice = calcularTotal();

    }

    private int generateTicketId() {
        lastTicketId++;
        return lastTicketId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }

    public Date getTicketDate() {
        return ticketDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Método para calcular la suma total del precio de los productos multiplicado por la cantidad
    public double calcularTotal() {
        double total = 0.0;

        for (ProductInfo productInfo : productInfoList) {
            // Obtener el precio del producto desde ProductInfo
            double productPrice = productInfo.getProductPrice();

            // Calcular el subtotal para el producto actual
            double subtotal = productPrice * productInfo.getQuantity();

            // Sumar al total general
            total += subtotal;
        }

        return total;
    }
}
