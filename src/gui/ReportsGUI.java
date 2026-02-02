package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import product.Product;
import order.Order;
import report.ReportManager;

public class ReportsGUI extends JFrame {

    private final ReportManager reportManager;
    private JTextArea outputArea;

    public ReportsGUI(ReportManager reportManager) {
        this.reportManager = reportManager;

        setTitle("Reports");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ThemeManager.styleFrame(this);

        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel header = new JLabel("REPORTS");
        ThemeManager.styleHeader(header);
        add(header, BorderLayout.NORTH);

        // Button Panel
        JPanel btnPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        ThemeManager.stylePanel(btnPanel);

        JButton btnStock = new JButton("Stock Report");
        JButton btnLowStock = new JButton("Low Stock");
        JButton btnOrders = new JButton("Order Report");
        JButton btnSummary = new JButton("Summary");

        ThemeManager.styleButton(btnStock);
        ThemeManager.styleButton(btnLowStock);
        ThemeManager.styleButton(btnOrders);
        ThemeManager.styleButton(btnSummary);

        btnPanel.add(btnStock);
        btnPanel.add(btnLowStock);
        btnPanel.add(btnOrders);
        btnPanel.add(btnSummary);

        add(btnPanel, BorderLayout.NORTH);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(outputArea);
        add(scroll, BorderLayout.CENTER);

        // Button Actions
        btnStock.addActionListener(e -> showStockReport());
        btnLowStock.addActionListener(e -> showLowStockReport());
        btnOrders.addActionListener(e -> showOrderReport());
        btnSummary.addActionListener(e -> showSummaryReport());

        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ================= STOCK REPORT =================
    private void showStockReport() {
        List<Product> products = reportManager.getAllProducts();

        outputArea.setText("=== STOCK REPORT ===\n\n");

        if (products.isEmpty()) {
            outputArea.append("No products found.\n");
            return;
        }

        for (Product p : products) {
            outputArea.append(String.format("%s | Price: %.2f | Qty: %d | Reorder: %d\n",
                p.getName(), p.getPrice(), p.getQuantity(), p.getReorderLevel()));
        }
    }


    // ================= LOW STOCK REPORT =================
    private void showLowStockReport() {
        String thresholdStr = JOptionPane.showInputDialog(this, "Enter low stock threshold:");
        if (thresholdStr == null) return; // Cancelled

        try {
            int threshold = Integer.parseInt(thresholdStr);

            List<Product> lowStock = reportManager.getLowStock(threshold);

            outputArea.setText("=== LOW STOCK REPORT ===\n\n");

            if (lowStock.isEmpty()) {
                outputArea.append("No products below threshold.\n");
                return;
            }

            for (Product p : lowStock) {
                outputArea.append(String.format("%s (ID: %s) → Qty: %d\n",
                    p.getName(), p.getId(), p.getQuantity()));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid threshold!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= ORDER REPORT =================
    private void showOrderReport() {
        List<Order> orders = reportManager.getAllOrders();

        outputArea.setText("=== ORDER REPORT ===\n\n");

        if (orders.isEmpty()) {
            outputArea.append("No orders found.\n");
            return;
        }

        for (Order o : orders) {
            outputArea.append(o.toString() + "\n");
        }
    }

    // ================= SUMMARY REPORT =================
    private void showSummaryReport() {
        String thresholdStr = JOptionPane.showInputDialog(this, "Enter low stock threshold:");
        if (thresholdStr == null) return;

        try {
            int threshold = Integer.parseInt(thresholdStr);

            outputArea.setText("=== SUMMARY REPORT ===\n\n");

            List<Product> products = reportManager.getAllProducts();
            List<Order> orders = reportManager.getAllOrders();

            outputArea.append("--- PRODUCT STOCK SUMMARY ---\n");
            for (Product p : products) {
                outputArea.append(String.format("%s (ID: %s) → Qty: %d%s\n",
                    p.getName(), p.getId(), p.getQuantity(),
                    (p.getQuantity() <= threshold ? "  ⚠ LOW STOCK" : "")));
            }

            outputArea.append("\n--- ORDER SUMMARY ---\n");
            if (orders.isEmpty()) {
                outputArea.append("No orders found.\n");
            } else {
                for (Order o : orders) {
                    outputArea.append(o.toString() + "\n");
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
