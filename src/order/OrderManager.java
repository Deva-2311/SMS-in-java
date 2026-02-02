package order;

import java.util.List;

import inventory.InventoryManager;
import persistence.OrderDAO;
import persistence.ProductDAO;
import utilities.Validation;

public class OrderManager {

    private final InventoryManager inventoryManager;

    public OrderManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    // ================= Create Purchase Order =================
    public void createPurchaseOrder(String productId, int quantity) {

        if (!Validation.isPositiveInteger(quantity)) {
            System.out.println("Invalid quantity! Must be greater than 0.");
            return;
        }

        // Product exists?
        if (ProductDAO.getProductById(productId) == null) {
            System.out.println("Product ID not found in database!");
            return;
        }

        // Increase stock in DB
        inventoryManager.increaseStock(productId, quantity);

        // Create order with generated ID
        String orderId = generateOrderId();

        Order order = new Order(orderId, productId, quantity, "Purchase");
        OrderDAO.addOrder(order);

        System.out.println("Purchase Order created successfully! Order ID: " + orderId);
    }

    // ================= Create Sales Order =================
    public void createSalesOrder(String productId, int quantity) {

        if (!Validation.isPositiveInteger(quantity)) {
            System.out.println("Invalid quantity! Must be greater than 0.");
            return;
        }

        // Product exists?
        if (ProductDAO.getProductById(productId) == null) {
            System.out.println("Product ID not found in database!");
            return;
        }

        // Reduce stock in DB
        boolean success = inventoryManager.reduceStock(productId, quantity);

        if (!success) {
            System.out.println("Order failed! Not enough stock.");
            return;
        }

        // Create order with generated ID
        String orderId = generateOrderId();

        Order order = new Order(orderId, productId, quantity, "Sales");
        OrderDAO.addOrder(order);

        System.out.println("Sales Order created successfully! Order ID: " + orderId);
    }

    // ================= View All Orders (from DB) =================
    public void viewOrders() {
        List<Order> orders = OrderDAO.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        System.out.println("\n--- Order List ---");
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    // ================= Generate Unique Order ID =================
    private String generateOrderId() {
        return "O" + System.currentTimeMillis(); // unique using timestamp
    }
}
