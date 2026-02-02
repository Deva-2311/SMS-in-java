package report;

import java.util.List;

import product.Product;
import persistence.ProductDAO;
import persistence.OrderDAO;
import order.Order;
import inventory.InventoryManager;

public class ReportManager {

    private final InventoryManager inventoryManager;

    public ReportManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }
        public List<Product> getAllProducts() {
    return persistence.ProductDAO.getAllProducts();
}

public List<Product> getLowStock(int threshold) {
    List<Product> list = persistence.ProductDAO.getAllProducts();
    return list.stream().filter(p -> p.getQuantity() < threshold).toList();
}

public List<order.Order> getAllOrders() {
    return persistence.OrderDAO.getAllOrders();
}

    // ================= Stock Report =================
    public void stockReport() {
        System.out.println("\n=== STOCK REPORT ===");

        List<Product> products = ProductDAO.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (Product p : products) {
            int stock = inventoryManager.getStock(p.getId());
            System.out.println(p + " | Current Stock: " + stock);
        }
    }

    // ================= Low Stock Alert =================
    public void lowStockReport(int threshold) {
        System.out.println("\n=== LOW STOCK ALERT (Threshold = " + threshold + ") ===");

        List<Product> products = ProductDAO.getAllProducts();

        boolean found = false;
        for (Product p : products) {
            int stock = inventoryManager.getStock(p.getId());
            if (stock != -1 && stock < threshold) {
                System.out.println(p.getName() + " (ID: " + p.getId() + ") → Stock: " + stock);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No products below the threshold.");
        }
    }

    // ================= Order Report =================
    public void orderReport() {
        System.out.println("\n=== ORDER REPORT ===");

        List<Order> orders = OrderDAO.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        for (Order o : orders) {
            System.out.println(o);
        }
    }

    // ================= Summary Report =================
    public void summaryReport(int lowStockThreshold) {
        System.out.println("\n=== SUMMARY REPORT ===");

        List<Product> products = ProductDAO.getAllProducts();
        List<Order> orders = OrderDAO.getAllOrders();

        // --- Product & Stock Summary ---
        System.out.println("\n--- PRODUCTS & STOCK ---");

        for (Product p : products) {
            int stock = inventoryManager.getStock(p.getId());
            System.out.print(p.getName() + " (ID: " + p.getId() + ") → Stock: " + stock);

            if (stock != -1 && stock <= lowStockThreshold) {
                System.out.print(" ⚠ LOW STOCK");
            }

            System.out.println();
        }

        // --- Order Summary ---
        System.out.println("\n--- ORDERS ---");
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order o : orders) {
                System.out.println(o);
            }
        }
    }
}
