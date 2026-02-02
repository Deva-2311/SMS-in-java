package inventory;

import product.Product;
import persistence.ProductDAO;

public class InventoryManager {

    // ================= Increase Stock in Database =================
    public void increaseStock(String productId, int quantity) {
        Product p = ProductDAO.getProductById(productId);

        if (p == null) {
            System.out.println("❌ Product not found in database.");
            return;
        }

        p.setQuantity(p.getQuantity() + quantity);
        ProductDAO.updateProduct(p);

        System.out.println("Stock increased. New stock of " + p.getName() + ": " + p.getQuantity());
    }

    // ================= Reduce Stock in Database =================
    public boolean reduceStock(String productId, int quantity) {
        Product p = ProductDAO.getProductById(productId);

        if (p == null) {
            System.out.println("❌ Product not found in database.");
            return false;
        }

        if (p.getQuantity() >= quantity) {
            p.setQuantity(p.getQuantity() - quantity);
            ProductDAO.updateProduct(p);

            System.out.println("Stock reduced. New stock of " + p.getName() + ": " + p.getQuantity());
            return true;
        } else {
            System.out.println("❌ Not enough stock for " + p.getName());
            return false;
        }
    }

    // ================= Get Stock =================
    public int getStock(String productId) {
        Product p = ProductDAO.getProductById(productId);

        if (p == null) return -1;
        return p.getQuantity();
    }

    // ================= Check Stock =================
    public void checkStock(String productId) {
        Product p = ProductDAO.getProductById(productId);

        if (p == null) {
            System.out.println("❌ Product not found in database.");
            return;
        }

        System.out.println("Current stock of " + p.getName() + ": " + p.getQuantity());
    }
}
