package entities;

import app.Shop;
import interfaces.Ipersistence;
import persistence.StockDb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Stock implements Ipersistence {

    private static Stock instance;
    private StockDb stockDB;

    private Map<Integer, Integer> stockItems;

    private Stock() {
        this.stockItems = new HashMap<>();
        stockDB = new StockDb(this);
    }

    public static Stock getInstance() {
        if (instance == null) {
            instance = new Stock();
        }
        return instance;
    }
    public void removeProductById(int productId) {
        if (stockItems.containsKey(productId)) {
            stockItems.remove(productId);
            System.out.println("Product ID '" + productId + "' removed from stock.");
        } else {
            System.out.println("Product ID not found '" + productId + "' in stock.");
        }
    }
    public void setStockItems(Map<Integer, Integer> stockItems) {
        this.stockItems = stockItems;
    }
    public Map<Integer, Integer> getItems() {
        return stockItems;
    }

    public void addStockItem(StockItem item) {
        if (item != null) {
            stockItems.put(item.getProductId(), item.getQuantity());
        }
    }

    public void reduceStock(Product product, int quantity) {
        int productId = product.getId();
        if (stockItems.containsKey(productId)) {
            int currentQuantity = stockItems.get(productId);

            if (currentQuantity >= quantity) {
                stockItems.put(productId, currentQuantity - quantity);
            } else {
                System.out.println("Error: There is not enough stock available for the product: " + product.getName());
            }
        } else {
            System.out.println("Error: Product not found in stock.");
        }
    }

    public static void showStockWithQuantities(Shop shop) {
        Stock stock = Stock.getInstance();
        Catalogue catalogue = Catalogue.getInstance();

        System.out.println("\n--- Stock with product quantities ---");
        for (Map.Entry<Integer, Integer> entry : stock.getItems().entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = catalogue.getProductById(productId);

            if (product != null) {
                System.out.println("- Product ID: " + productId + " - Product: " + product.getName() +
                        " - Quantity: " + quantity);
            } else {
                System.out.println("Product ID '" + productId + "' not found in the catalogue.");
            }
        }
    }


    @Override
    public void guardarStock(String stockFileName) throws IOException {
        StockDb stock = new StockDb(this);
        stock.guardarBd(stockFileName);
    }
    @Override
    public void leerStock(String stockFileName) throws IOException {
        StockDb stockDb1 = StockDb.getInstance();
        stockDb1.leerBd(stockFileName);
    }


    @Override
    public void guardarShop(Shop shop) throws IOException {
    }
    @Override
    public void guardarCatalogo(String catalogoFileName) throws IOException {
    }
    @Override
    public void guardarTicket(String ticketFileName) throws IOException {
    }
    @Override
    public void leerShop() throws IOException {
    }
    @Override
    public void leerCatalogo(String catalogoFileName) throws IOException {
    }
    @Override
    public void leerTicket(String ticketFileName) throws IOException {

    }
}
