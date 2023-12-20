package entities;

public class Tree extends Product {

	private String height;

	public Tree(String name, String height) {
		super(name);
		this.height = height;
		this.setTipo(Tree.class.toString());
	}
	public Tree(int id, String name, String height) {
		this(name, height);
		this.setId(id);
	}

	public String getHeight() {
		return height;
	}

	public void setSpecies(String height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "[id:" + getId() + ", height:" + height + ", Name:" + getName() + "]";
	}

}
