package entities;

import java.util.ArrayList;

public class Stock {
    private static Stock instance;
    private ArrayList<StockItem> items;

   private Stock() {
        this.items = new ArrayList<StockItem>();
    }
    public static Stock getInstance() {
        if (instance == null) {
            instance = new Stock();
        }
        return instance;
    }
    public ArrayList<StockItem> getItems() {
        return items;
    }
   /* public void addStockItem(StockItem item) {
    }*/
    public void addQuantity(Product product, int quantity) {
        StockItem item = findStockItem(product);
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            this.items.add(new StockItem(product, quantity));
        }
    }
    public void removeStockItem(StockItem item) {
        this.items.remove(item);
    }
    public void removeQuantity(Product product, int quantity) {
        StockItem item = findStockItem(product);
        if (item != null) {
            item.setQuantity(item.getQuantity() - quantity);
        }
    }
    public StockItem findStockItem(Product product) {
        for (StockItem item : this.items) {
            if (item.getProduct().equals(product)) {
                return item;
            }
        }
        return null;
    }
}
