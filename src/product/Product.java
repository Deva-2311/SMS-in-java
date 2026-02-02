package product;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private int reorderLevel;

    // Constructor
    public Product(String id, String name, double price, int quantity, int reorderLevel) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    // Display product details
    @Override
    public String toString() {
        return "Product [ID=" + id +
               ", Name=" + name +
               ", Price=" + price +
               ", Quantity=" + quantity +
               ", ReorderLevel=" + reorderLevel + "]";
    }
}
