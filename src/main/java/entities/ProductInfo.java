package entities;

public class ProductInfo {
    private Product product;
    private int quantity;

    public ProductInfo(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Nuevo método para obtener el nombre del producto
    public String getProductName() {
        return product.getName();
    }

    // Nuevo método para obtener el precio del producto
    public double getProductPrice() {
        return product.getPrice();
    }
}
