package entities;

import app.Shop;
import interfaces.Ipersistence;
import persistence.CatalogoBD;
import persistence.StockDB;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Stock implements Ipersistence {

    private static Stock instance;
    private StockDB stockDB;

    private Map<Integer, Integer> stockItems;

    private Stock() {
        this.stockItems = new HashMap<>();
        stockDB = new StockDB(this);
    }

    public static Stock getInstance() {
        if (instance == null) {
            instance = new Stock();
        }
        return instance;
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
                // Reducir la cantidad en stock
                stockItems.put(productId, currentQuantity - quantity);
            } else {
                System.out.println("Error: No hay suficiente stock disponible para el producto: " + product.getName());
            }
        } else {
            System.out.println("Error: Producto no encontrado en el stock.");
        }
    }



    @Override
    public void guardarCatalogo(String catalogoFileName) throws IOException {

    }

    @Override
    public void guardarStock(String stockFileName) throws IOException {
        StockDB stock = new StockDB(this);
        stock.guardarBd(stockFileName);
    }


    @Override
    public void guardarTicket(String ticketFileName) throws IOException {

    }

    @Override
    public void leerCatalogo(String catalogoFileName) throws IOException {

    }

    @Override
    public void leerStock(String stockFileName) throws IOException {
        StockDB stockDB1 = StockDB.getInstance();
        stockDB1.leerBd(stockFileName);
    }

    @Override
    public void leerTicket(String ticketFileName) throws IOException {

    }
}
