package gui;

import javax.swing.*;
import java.awt.*;
import product.Product;
import persistence.ProductDAO;

public class AddProductGUI extends JFrame {

    private final JTextField txtId, txtName, txtPrice, txtQty, txtReorder;

    public AddProductGUI() {
        setTitle("Add Product");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ThemeManager.styleFrame(this);

        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel header = new JLabel("ADD PRODUCT");
        ThemeManager.styleHeader(header);
        add(header, BorderLayout.NORTH);

        // Form Panel
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        ThemeManager.stylePanel(form);

        txtId = new JTextField();
        txtName = new JTextField();
        txtPrice = new JTextField();
        txtQty = new JTextField();
        txtReorder = new JTextField();

        addLabeledField(form, "Product ID:", txtId);
        addLabeledField(form, "Name:", txtName);
        addLabeledField(form, "Price:", txtPrice);
        addLabeledField(form, "Quantity:", txtQty);
        addLabeledField(form, "Reorder Level:", txtReorder);

        add(form, BorderLayout.CENTER);

        // Buttons
        JButton btnAdd = new JButton("Add");
        JButton btnClear = new JButton("Clear");

        ThemeManager.styleButton(btnAdd);
        ThemeManager.styleButton(btnClear);

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd);
        btnPanel.add(btnClear);
        ThemeManager.stylePanel(btnPanel);

        add(btnPanel, BorderLayout.SOUTH);

        // Action Listeners
        btnAdd.addActionListener(e -> addProduct());
        btnClear.addActionListener(e -> clearFields());

        pack();
        setSize(420, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addLabeledField(JPanel panel, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        ThemeManager.styleLabel(label);
        panel.add(label);
        panel.add(field);
    }

    private void addProduct() {
        try {
            Product p = new Product(
                txtId.getText().trim(),
                txtName.getText().trim(),
                Double.parseDouble(txtPrice.getText().trim()),
                Integer.parseInt(txtQty.getText().trim()),
                Integer.parseInt(txtReorder.getText().trim())
            );

            ProductDAO.addProduct(p);

            JOptionPane.showMessageDialog(this, "Product added successfully!");
            clearFields();
        } catch (HeadlessException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtQty.setText("");
        txtReorder.setText("");
    }
}
