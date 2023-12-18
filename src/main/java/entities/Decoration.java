package entities;

public class Decoration extends Product {

    private String material;
    public Decoration(String name, String material) {
        super(name);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Decoración: " + getName() + " - Material: " + material;
    }
}
