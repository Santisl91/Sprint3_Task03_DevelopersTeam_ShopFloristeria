package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import interfaces.Ipersistence;
import persistence.CatalogueDb;

public class Catalogue implements Ipersistence {
    private static Catalogue instance;
    private List<Product> prodItems = new ArrayList<>();

    private Catalogue() {

    }
    public static Catalogue getInstance() {
        if (instance == null) {
            instance = new Catalogue();
        }
        return instance;
    }
    public List<Product> getItems() {
        return prodItems;
    }

    public void addItem(Product item) {
        prodItems.add(item);
    }

    public void displayCatalog() {
        System.out.println("Catalogue:");
        for (Product item : prodItems) {
            System.out.println(item);
        }
    }
    public void removeProductById(int productId) {
        Iterator<Product> iterator = prodItems.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId() == productId) {
                iterator.remove();
                System.out.println("Product with ID '" + productId + "' removed from catalogue.");
                return;
            }
        }
        System.out.println("Product ID not found '" + productId + "' in the catalogue.");
    }
    public Product getProductById(int productId) {
        for (Product product : prodItems) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
    public double getProductPriceById(int productId) {
        for (Product product : prodItems) {
            if (product.getId() == productId) {
                return product.getPrice();
            }
        }
        return 0.0;
    }
    @Override
    public void leerCatalogo(String catalogoFileName) throws IOException {
        CatalogueDb cat = new CatalogueDb(this);
        cat.leerBd(catalogoFileName);
    }
    @Override
    public void guardarCatalogo(String catalogoFileName) throws IOException {
        CatalogueDb cat = new CatalogueDb(this);
        cat.guardarBd(catalogoFileName);
    }
    @Override
    public void leerStock(String stockFileName) throws IOException {
    }
    @Override
    public void leerTicket(String ticketFileName) throws IOException {
    }
    @Override
    public void guardarShop() throws IOException {
    }
    @Override
    public void guardarStock(String stockFileName) throws IOException {
    }
    @Override
    public void guardarTicket(String ticketFileName) throws IOException {
    }
    @Override
    public void leerShop() throws IOException {

    }
}
