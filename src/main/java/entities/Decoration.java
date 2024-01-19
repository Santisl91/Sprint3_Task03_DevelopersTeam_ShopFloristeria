package entities;

import java.io.FileWriter;
import java.io.IOException;

public class Decoration extends Product {
	private String material;

	public Decoration(String name, String material, double price) {
		super(name, price);
		this.material = material;
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
		this.material = material;
	}
	@Override
	public String toString() {
		return "[id:" + getId() + ", material:" + material + ", Name:" + getName() + ", price:" + getPrice() + "]";
	}
}
