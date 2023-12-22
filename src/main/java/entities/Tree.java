package entities;

public class Tree extends Product {

	private String height;

	public Tree(String name, String height, double price) {
		super(name, price);
		this.height = height;
		this.setTipo(Tree.class.toString());
	}

	public Tree(int id, String name, String height, double price) {
		this(name, height, price);
		this.setId(id);
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "[id:" + getId() + ", height:" + height + ", Name:" + getName() + ", price:" + getPrice() + "]";
	}
}

