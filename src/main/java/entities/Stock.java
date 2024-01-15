package entities;

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
            System.out.println("Producto con ID '" + productId + "' eliminado del stock.");
        } else {
            System.out.println("No se encontró el producto con ID '" + productId + "' en el stock.");
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
    public void mostrarStockConCantidades() {
        Catalogue catalogue = Catalogue.getInstance();

        System.out.println("\n--- Stock with product quantities ---");
        for (Map.Entry<Integer, Integer> entry : stockItems.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = catalogue.getProductById(productId);

            if (product != null) {
                System.out.println("- Product: " + product.getName() + " - Quantity: " + quantity);
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
    public void guardarShop() throws IOException {
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
