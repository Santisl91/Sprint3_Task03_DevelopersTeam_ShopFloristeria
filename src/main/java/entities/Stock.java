package entities;

import interfaces.Ipersistence;
import persistence.StockDB;
import java.util.ArrayList;
import java.util.List;

public class Stock implements Ipersistence {
    private int id;
    private List<StockItem> items;

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
        StockItem existingItem = findStockItem(product);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // Si no existe, creemos un nuevo StockItem y agréguelo al stock
            StockItem newItem = new StockItem(product, quantity);
            items.add(newItem);
        }
    }
    public void removeStockItem(StockItem item) {
        items.remove(item);
    }

    public StockItem findStockItem(Product product) {
        for (StockItem item : items) {
            if (item.getProduct().equals(product)) {
                return item;
            }
        }
        return null;
    }
    @Override
    public void leerBd() {
        StockDB stock = new StockDB(this);
        stock.leerBd();
    }
    @Override
    public void guardarBd() {
        StockDB stock = new StockDB(this);
        stock.guardarBd();
    }
}
