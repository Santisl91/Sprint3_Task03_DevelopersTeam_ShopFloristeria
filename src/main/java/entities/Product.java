package entities;

public class Product {
    private String name;
    private int id;
    private String type;
    private static int contador = 1;
    private double price;


    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.id = contador++;
    }

    public String getTipo() {
        return type;
    }

    public void setTipo(String type) {
        this.type= type;
    }

    public void setId(int id) {
        this.id = id;
        if (contador < id) {
            contador = id;
        }
    }

    public int getId() {
        return id;
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
