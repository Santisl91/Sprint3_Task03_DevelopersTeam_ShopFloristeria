package entities;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private static Stock instance;

    private Stock() {
        // Constructor privado
    }

    public static Stock getInstance() {
        if (instance == null) {
            instance = new Stock();
        }
        return instance;
    }

    private int id;
    private List<StockItem> items = new ArrayList<>();

    public Stock(int id) {
        this.id = id;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<StockItem> getItems() {
        return items;
    }

    public void addStockItem(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            return;
        }

        StockItem existingItem = findStockItem(product);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // Si no existe, creemos un nuevo StockItem y agréguelo al stock
            StockItem newItem = new StockItem(product, quantity);
            items.add(newItem);
        }
    }

    public void displayAllStock() {
        System.out.println("Contenido del stock:");

        for (StockItem item : items) {
            System.out.println("Producto: " + item.getProduct().getName() +
                    ", Cantidad: " + item.getQuantity());
        }
    }

    public void removeStockItem(StockItem item) {
        items.remove(item);
    }

    public StockItem findStockItem(Product product) {
        if (product == null) {
            return null;
        }
        for (StockItem item : items) {
            if (item.getProduct().equals(product)) {
                return item;
            }
        }
        return null;
    }

}
