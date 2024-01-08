package entities;

public class Product {
    private String name;
    private int id;
    private String tipo;
    private static int contador = 1;
    private double price;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setId(int id) {
        id = id;
        if (contador < id) {
            contador = id;
        }

    }

    public int getId() {
        return id;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.id = contador++;
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
