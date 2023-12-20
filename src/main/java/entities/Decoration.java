package entities;

public class Decoration extends Product {

	private String material;

	public Decoration(String name, String material) {
		super(name);
		this.material = material;
		this.setTipo(Decoration.class.toString());
	}
	public Decoration(int id, String name, String material) {
		this(name, material);
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
		return "[id:" + getId() + ", material:" + material + ", Name:" + getName() + "]";
	}
}
