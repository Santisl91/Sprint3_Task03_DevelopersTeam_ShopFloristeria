package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import interfaces.Ipersistence;
import persistence.CatalogoBD;

public class Catalogue implements Ipersistence {
    private static Catalogue instance;
    private List<Product> prodItems = new ArrayList<>();

    private Catalogue() {
        // Constructor privado para evitar la creación de instancias directas
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
        System.out.println("Catálogo:");
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
                System.out.println("Producto con ID '" + productId + "' eliminado del catálogo.");
                return; // Terminamos la iteración al encontrar y eliminar el producto
            }
        }

        System.out.println("No se encontró el producto con ID '" + productId + "' en el catálogo.");
    }
    public Product getProductById(int productId) {
        for (Product product : prodItems) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void leerCatalogo(String catalogoFileName) throws IOException {
        CatalogoBD cat = new CatalogoBD(this);
        cat.leerBd(catalogoFileName);
    }
    @Override
    public void guardarCatalogo(String catalogoFileName) throws IOException {
        CatalogoBD cat = new CatalogoBD(this);
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
