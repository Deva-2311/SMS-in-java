package persistence;

import product.Product;
import order.Order;

import java.util.List;

public class DataPersistence {

    // Load products from database
    public static List<Product> loadProducts() {
        return ProductDAO.getAllProducts();
    }

    // Save or update a single product
    public static void saveProduct(Product product) {
        if (ProductDAO.getProductById(product.getId()) == null) {
            ProductDAO.addProduct(product);    // insert
        } else {
            ProductDAO.updateProduct(product); // update
        }
    }

    // Save product list (bulk save)
    public static void saveProducts(List<Product> products) {
        for (Product p : products) {
            saveProduct(p);
        }
    }

    // Delete product
    public static void deleteProduct(String id) {
        ProductDAO.deleteProduct(id);
    }

    // Load orders from database
    public static List<Order> loadOrders() {
        return OrderDAO.getAllOrders();
    }

    // Save order
    public static void saveOrder(Order order) {
        OrderDAO.addOrder(order);
    }

    // Save list of orders
    public static void saveOrders(List<Order> orders) {
        for (Order o : orders) {
            saveOrder(o);
        }
    }

    // Convenience save-all method
    public static void saveAll(List<Product> products, List<Order> orders) {
        saveProducts(products);
        saveOrders(orders);
        System.out.println("All data saved to MySQL database.");
    }
}
