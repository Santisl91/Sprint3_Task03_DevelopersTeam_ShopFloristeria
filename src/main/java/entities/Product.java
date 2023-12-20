package entities;

public class Product {
    private String name;
    private int id;
    private String tipo;
    private static int contador = 1;
    private double price;

    public String getTipo() {
		return tipo;
	}

  public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setId(int _id) {
		id = _id;
		if (contador < _id) {
			contador = _id;
		}
		
	}
	public int getId() {
		return id;
	}

	public Product(String name) {
        this.name = name;
        this.id = contador++;
        
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
