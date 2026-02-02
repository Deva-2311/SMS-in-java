package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import order.Order;

public class OrderDAO {

    // Insert an order
    public static void addOrder(Order order) {
        String sql = "INSERT INTO orders (order_id, product_id, quantity, type) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, order.getOrderId());
            stmt.setString(2, order.getProductId());
            stmt.setInt(3, order.getQuantity());
            stmt.setString(4, order.getType());

            stmt.executeUpdate();
            System.out.println("Order added to database!");

        } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
}

    }

    // Get all orders
    public static List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Order order = new Order(
                    rs.getString("order_id"),
                    rs.getString("product_id"),
                    rs.getInt("quantity"),
                    rs.getString("type")
                );
                list.add(order);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
}


        return list;
    }

    // Delete order
    public static void deleteOrder(String orderId) {
        String sql = "DELETE FROM orders WHERE order_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, orderId);
            stmt.executeUpdate();
            System.out.println("Order deleted!");

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

    }

    // Get one order
    public static Order getOrderById(String orderId) {
        String sql = "SELECT * FROM orders WHERE order_id=?";
        Order order = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order = new Order(
                    rs.getString("order_id"),
                    rs.getString("product_id"),
                    rs.getInt("quantity"),
                    rs.getString("type")
                );
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
}


        return order;
    }
}
