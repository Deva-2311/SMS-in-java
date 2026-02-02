package gui;

import javax.swing.*;
import java.awt.*;

import inventory.InventoryManager;
import order.Order;
import persistence.ProductDAO;
import persistence.OrderDAO;

public class SalesOrderGUI extends JFrame {

    private JTextField txtProductId, txtQty;
    private final InventoryManager inventoryManager;

    public SalesOrderGUI(InventoryManager inventoryManager) {

        this.inventoryManager = inventoryManager;

        setTitle("Sales Order");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ThemeManager.styleFrame(this);

        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel header = new JLabel("SALES ORDER");
        ThemeManager.styleHeader(header);
        add(header, BorderLayout.NORTH);

        // Form panel
        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        ThemeManager.stylePanel(form);

        JLabel lblProductId = new JLabel("Product ID:");
        JLabel lblQty = new JLabel("Quantity:");
        ThemeManager.styleLabel(lblProductId);
        ThemeManager.styleLabel(lblQty);

        txtProductId = new JTextField();
        txtQty = new JTextField();

        form.add(lblProductId);
        form.add(txtProductId);
        form.add(lblQty);
        form.add(txtQty);

        add(form, BorderLayout.CENTER);

        // Buttons
        JButton btnCreate = new JButton("Create Order");
        JButton btnClear = new JButton("Clear");

        ThemeManager.styleButton(btnCreate);
        ThemeManager.styleButton(btnClear);

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnCreate);
        btnPanel.add(btnClear);
        ThemeManager.stylePanel(btnPanel);

        add(btnPanel, BorderLayout.SOUTH);

        // Actions
        btnClear.addActionListener(e -> {
            txtProductId.setText("");
            txtQty.setText("");
        });

        btnCreate.addActionListener(e -> createSalesOrder());

        setSize(420, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void createSalesOrder() {
        try {
            String productId = txtProductId.getText().trim();
            int qty = Integer.parseInt(txtQty.getText().trim());

            // Check if product exists
            if (ProductDAO.getProductById(productId) == null) {
                JOptionPane.showMessageDialog(this, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Reduce stock through InventoryManager
            boolean success = inventoryManager.reduceStock(productId, qty);
            if (!success) {
                JOptionPane.showMessageDialog(this, "Not enough stock!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create order record
            String orderId = "SO" + System.currentTimeMillis();
            Order order = new Order(orderId, productId, qty, "Sales");
            OrderDAO.addOrder(order);

            JOptionPane.showMessageDialog(this, "Sales Order Created Successfully!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
