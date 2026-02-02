package order;

public class Order {
    private String orderId;
    private String productId;
    private int quantity;
    private String type; // "Purchase" or "Sales"

    // Constructor
    public Order(String orderId, String productId, int quantity, String type) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.type = type;
    }

    public Order(String productId2, int quantity2, String string) {
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Display format
    @Override
    public String toString() {
        return "Order ID: " + orderId +
               " | Product ID: " + productId +
               " | Quantity: " + quantity +
               " | Type: " + type;
    }
}
