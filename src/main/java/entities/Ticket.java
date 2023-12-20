package entities;

import java.util.ArrayList;
import java.util.Date;

public class Ticket {
    private int id;
    private ArrayList<StockItem> venta;
    private Date date;
    private double totalPrice;

    public Ticket(int id, double totalPrice) {
        this.id = id;
        this.venta = new ArrayList<StockItem>();
        this.date = new Date();
        this.totalPrice = totalPrice;
    }
    public int getId() {
        return id;
    }
    public ArrayList<StockItem> getVenta() {
        return venta;
    }
    public Date getDate() {
        return date;
    }
    public double getTotalPrice() {
        double total = 0;
        for (StockItem item : this.venta) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        this.totalPrice = total;
        return total;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setVenta(ArrayList<StockItem> venta) {
        this.venta = venta;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
