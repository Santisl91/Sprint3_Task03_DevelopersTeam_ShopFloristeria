package entities;

public class Product {
    private String name;
    private int id;
    private double price;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
