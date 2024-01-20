package entities;

import java.io.FileWriter;
import java.io.IOException;

public class Decoration extends Product {
	private String material;
	private static final String WOOD = "madera";
	private static final String PLASTIC = "plástico";

	public Decoration(String name, String material, double price) {
		super(name, price);
		setMaterial(material);
		this.setTipo(Decoration.class.toString());
	}

	public Decoration(int id, String name, String material, double price) {
		this(name, material, price);
		this.setId(id);
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		if (WOOD.equals(material) || PLASTIC.equals(material)) {
			this.material = material;
		} else {
			throw new IllegalArgumentException("Invalid material. Must be 'madera' or 'plástico'.");
		}
	}
	@Override
	public String toString() {
		return "[id:" + getId() + ", material:" + material + ", Name:" + getName() + ", price:" + getPrice() + "]";
	}
}
