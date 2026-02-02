package gui;

import javax.swing.*;
import java.awt.*;
import product.Product;
import persistence.ProductDAO;

public class UpdateProductGUI extends JFrame {

    private final JTextField txtId, txtName, txtPrice, txtQty, txtReorder;

    public UpdateProductGUI() {

        setTitle("Update Product");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("UPDATE PRODUCT");
        ThemeManager.styleHeader(header);
        add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        ThemeManager.stylePanel(form);

        txtId = new JTextField();
        txtName = new JTextField();
        txtPrice = new JTextField();
        txtQty = new JTextField();
        txtReorder = new JTextField();

        addField(form, "Product ID:", txtId);
        addField(form, "Name:", txtName);
        addField(form, "Price:", txtPrice);
        addField(form, "Quantity:", txtQty);
        addField(form, "Reorder Level:", txtReorder);

        JButton btnLoad = new JButton("Load");
        JButton btnUpdate = new JButton("Update");

        ThemeManager.styleButton(btnLoad);
        ThemeManager.styleButton(btnUpdate);

        form.add(btnLoad);
        form.add(btnUpdate);

        add(form, BorderLayout.CENTER);

        btnLoad.addActionListener(e -> loadProduct());
        btnUpdate.addActionListener(e -> updateProduct());

        setSize(450, 380);
        setLocationRelativeTo(null);
        ThemeManager.styleFrame(this);
        setVisible(true);
    }

    private void addField(JPanel panel, String label, JTextField field) {
        JLabel lbl = new JLabel(label);
        ThemeManager.styleLabel(lbl);
        panel.add(lbl);
        panel.add(field);
    }

    private void loadProduct() {
        Product p = ProductDAO.getProductById(txtId.getText().trim());
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Product not found!");
            return;
        }

        txtName.setText(p.getName());
        txtPrice.setText(String.valueOf(p.getPrice()));
        txtQty.setText(String.valueOf(p.getQuantity()));
        txtReorder.setText(String.valueOf(p.getReorderLevel()));
    }

    private void updateProduct() {
        try {
            Product p = new Product(
                txtId.getText().trim(),
                txtName.getText().trim(),
                Double.parseDouble(txtPrice.getText().trim()),
                Integer.parseInt(txtQty.getText().trim()),
                Integer.parseInt(txtReorder.getText().trim())
            );

            ProductDAO.updateProduct(p);
            JOptionPane.showMessageDialog(this, "Product updated successfully!");

        } catch (HeadlessException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
