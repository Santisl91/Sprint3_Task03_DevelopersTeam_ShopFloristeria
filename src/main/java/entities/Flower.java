package entities;

public class Flower extends Product {

    private String color;
    public Flower(String name, String color) {
        super(name);
        this.color = color;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Flor: " + getName() + " - Color: " + color;
    }
}
