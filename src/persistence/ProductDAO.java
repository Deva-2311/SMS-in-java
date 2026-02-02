package persistence;

import product.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Insert new product
    public static void addProduct(Product p) {
        String sql = "INSERT INTO products (id, name, price, quantity, reorder_level) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, p.getId());
            stmt.setString(2, p.getName());
            stmt.setDouble(3, p.getPrice());
            stmt.setInt(4, p.getQuantity());
            stmt.setInt(5, p.getReorderLevel());

            stmt.executeUpdate();
            System.out.println("Product added to database!");

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

    }

    // Update existing product
    public static void updateProduct(Product p) {
        String sql = "UPDATE products SET name=?, price=?, quantity=?, reorder_level=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPrice());
            stmt.setInt(3, p.getQuantity());
            stmt.setInt(4, p.getReorderLevel());
            stmt.setString(5, p.getId());

            stmt.executeUpdate();
            System.out.println("Product updated!");

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
}

    }

   public static boolean deleteProduct(String id) {
    String sql = "DELETE FROM products WHERE id=?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setString(1, id);

        int rows = stmt.executeUpdate();

        return rows > 0; // true = deleted, false = not found

    } catch (SQLException e) {
        return false;
    }
}

    // Load a single product
    public static Product getProductById(String id) {
        String sql = "SELECT * FROM products WHERE id=?";
        Product product = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getInt("reorder_level")
                );
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return product;
    }

    // Load all products
    public static List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getInt("reorder_level")
                );
                list.add(p);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return list;
    }
}
