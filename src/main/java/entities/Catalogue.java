package entities;
import java.util.ArrayList;
import java.util.List;

public class Catalogue {
    private List<Product> items = new ArrayList<>();

    public void addItem(Product item) {
        items.add(item);
    }

    public void displayCatalog() {
        System.out.println("Catálogo:");
        for (Product item : items) {
            System.out.println(item);
        }
    }
}