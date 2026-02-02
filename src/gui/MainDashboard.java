package gui;

import javax.swing.*;
import java.awt.*;

import inventory.InventoryManager;
import report.ReportManager;

public class MainDashboard extends JFrame {

    public MainDashboard(InventoryManager inventoryManager, ReportManager reportManager) {

        setTitle("Stock Management Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //HEADER
        JLabel title = new JLabel("STOCK MANAGEMENT SYSTEM");
        ThemeManager.styleHeader(title);
        add(title, BorderLayout.NORTH);

        //CENTER BUTTON PANEL
        JPanel centerPanel = new JPanel(new GridLayout(5, 2, 15, 15));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        ThemeManager.stylePanel(centerPanel);

        JButton btnAddProduct = new JButton("Add Product");
        JButton btnViewProducts = new JButton("View Products");
        JButton btnUpdateProduct = new JButton("Update Product");
        JButton btnDeleteProduct = new JButton("Delete Product");
        JButton btnPurchaseOrder = new JButton("Purchase Order");
        JButton btnSalesOrder = new JButton("Sales Order");
        JButton btnReports = new JButton("Reports");
        JButton btnExit = new JButton("Exit");

        ThemeManager.styleButton(btnAddProduct);
        ThemeManager.styleButton(btnViewProducts);
        ThemeManager.styleButton(btnUpdateProduct);
        ThemeManager.styleButton(btnDeleteProduct);
        ThemeManager.styleButton(btnPurchaseOrder);
        ThemeManager.styleButton(btnSalesOrder);
        ThemeManager.styleButton(btnReports);
        ThemeManager.styleButton(btnExit);

        centerPanel.add(btnAddProduct);
        centerPanel.add(btnViewProducts);
        centerPanel.add(btnUpdateProduct);
        centerPanel.add(btnDeleteProduct);
        centerPanel.add(btnPurchaseOrder);
        centerPanel.add(btnSalesOrder);
        centerPanel.add(btnReports);
        centerPanel.add(btnExit);

        add(centerPanel, BorderLayout.CENTER);

        //BUTTON ACTIONS

        btnAddProduct.addActionListener(e -> new AddProductGUI().setVisible(true));
        btnViewProducts.addActionListener(e -> new ViewProductsGUI().setVisible(true));
        btnUpdateProduct.addActionListener(e -> new UpdateProductGUI().setVisible(true));
        btnDeleteProduct.addActionListener(e -> new DeleteProductGUI().setVisible(true));

        // These require inventory manager
        btnPurchaseOrder.addActionListener(e -> new PurchaseOrderGUI(inventoryManager).setVisible(true));
        btnSalesOrder.addActionListener(e -> new SalesOrderGUI(inventoryManager).setVisible(true));

        // Reports require report manager
        btnReports.addActionListener(e -> new ReportsGUI(reportManager).setVisible(true));

        btnExit.addActionListener(e -> System.exit(0));

        //WINDOW SETTINGS
        setMinimumSize(new Dimension(500, 600));
        setLocationRelativeTo(null);
        // ThemeManager.styleFrame(this); // Moved outside constructor
    }
}
