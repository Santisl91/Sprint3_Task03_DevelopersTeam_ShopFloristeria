package entities;

public class Flower extends Product {

	private String color;

	public Flower(String name, String color, double price) {
		super(name, price);
		this.color = color;
		this.setTipo(Flower.class.toString());
	}

	public Flower(int id, String name, String color, double price) {
		this(name, color, price);
		this.setId(id);
	}
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "[id:" + getId() + ", color:" + color + ", Name:" + getName() + ", price:" + getPrice() + "]";
	}
}
