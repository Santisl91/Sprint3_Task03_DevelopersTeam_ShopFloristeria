package entities;

public class Tree extends Product {

	private double height;

	public Tree(String name, double height, double price) {
		super(name, price);
		this.height = height;
		this.setTipo(Tree.class.toString());
	}

	public Tree(int id, String name, double height, double price) {
		this(name, height, price);
		this.setId(id);
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "[id:" + getId() + ", height:" + height + ", Name:" + getName() + ", price:" + getPrice() + "]";
	}
}

