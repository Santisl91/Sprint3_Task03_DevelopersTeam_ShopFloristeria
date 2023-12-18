package entities;

public class Tree extends Product {

    private String height;

    public Tree(String name, String height) {
        super(name);
        this.height = height;
    }
    public String getHeight(){
        return height;
    }

    public void setSpecies(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Árbol: " + getName() + " - Especie: " + height;
    }

}
