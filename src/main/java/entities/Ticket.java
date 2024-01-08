package entities;

import app.Shop;
import interfaces.Ipersistence;
import persistence.TicketDB;

import java.util.ArrayList;
import java.util.Date;

public class Ticket implements Ipersistence {
    private int id;
    private static int contador = 0;

    private ArrayList<StockItem> venta;
    private Date date;
    private double totalPrice;

    public Ticket(int productId, int quantity, double totalPrice) {
        this.id = contador++;
        this.venta = new ArrayList<>();
        this.date = new Date();
        this.totalPrice = totalPrice;

        // Agregar el producto al ticket al momento de la creación
        addProductById(productId, quantity, Shop.getInstance());
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

    public void updateTotalPrice() {
        double total = 0;

        for (StockItem item : this.venta) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        this.totalPrice = total;
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

    public void addProductById(int productId, int quantity, Shop shop) {
        Product product = shop.getProductById(productId);

        if (product != null) {
            Stock stock = shop.encontrarStock(product);

            for (StockItem item : this.venta) {
                if (item.getProduct().equals(product)) {
                    item.setQuantity(item.getQuantity() + quantity);
                    updateTotalPrice();
                    if (stock != null) {
                        stock.reduceStock(product, quantity);
                    }
                    return;
                }
            }

            StockItem newItem = new StockItem(product, quantity);
            this.venta.add(newItem);
            updateTotalPrice();
            if (stock != null) {
                stock.reduceStock(product, quantity);
            }
        } else {
            System.out.println("Producto no encontrado con el ID: " + productId);
        }
    }

    @Override
    public void leerBd() {
        TicketDB ticket = new TicketDB();
        ticket.leerBd();
    }

    @Override
    public void guardarBd() {
        TicketDB ticket = new TicketDB();
        ticket.guardarBd();
    }
}
