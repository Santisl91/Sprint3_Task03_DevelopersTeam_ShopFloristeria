package entities;

import java.util.ArrayList;

public class Stock {
    private ArrayList<StockItem> items;

    private Stock() {
        this.items = new ArrayList<StockItem>();
    }
    public ArrayList<StockItem> getItems() {
        return this.items;
    }
    public void addStockItem(StockItem item) {
        //buscar si existe el producto en el stock
    }
    public void addQuantity(Product product, int quantity) {
        for (StockItem item : this.items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
                break;
            }
        }
    }
    public void removeStockItem(StockItem item) {
        this.items.remove(item);
    }
    public void removeQuantity(Product product, int quantity) {
        for (StockItem item : this.items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() - quantity);
                break;
            }
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
