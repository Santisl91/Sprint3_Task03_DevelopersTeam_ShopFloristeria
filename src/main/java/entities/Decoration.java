package entities;

import java.io.FileWriter;
import java.io.IOException;

public class Decoration extends Product {
	private String material;
	private static final String WOOD = "wood";
	private static final String PLASTIC = "plastic";

	public Decoration(int id, String material, double price) {
		super("", price);
		setId(id);
		setMaterial(material);
		setTipo(Decoration.class.toString());
	}

	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		if (WOOD.equals(material) || PLASTIC.equals(material)) {
			this.material = material;
		} else {
			throw new IllegalArgumentException("Invalid material. Must be 'wood' or 'plastic'.");
		}
	}
	@Override
	public String toString() {
		return "[id:" + getId() + ", material:" + material + ", Name:" + getName() + ", price:" + getPrice() + "]";
	}
}
