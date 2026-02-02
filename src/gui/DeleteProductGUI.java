package gui;

import javax.swing.*;
import java.awt.*;
import persistence.ProductDAO;

public class DeleteProductGUI extends JFrame {

    private JTextField txtId;

    public DeleteProductGUI() {
        setTitle("Delete Product");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel header = new JLabel("DELETE PRODUCT");
        ThemeManager.styleHeader(header);
        add(header, BorderLayout.NORTH);

        // Form
        JPanel form = new JPanel(new GridLayout(1, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        ThemeManager.stylePanel(form);

        JLabel lblId = new JLabel("Enter Product ID:");
        ThemeManager.styleLabel(lblId);
        txtId = new JTextField();

        form.add(lblId);
        form.add(txtId);

        add(form, BorderLayout.CENTER);

        // Buttons
        JPanel btnPanel = new JPanel();
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        ThemeManager.styleButton(btnDelete);
        ThemeManager.styleButton(btnClear);

        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);
        ThemeManager.stylePanel(btnPanel);

        add(btnPanel, BorderLayout.SOUTH);

        // Actions
        btnClear.addActionListener(e -> txtId.setText(""));

        btnDelete.addActionListener(e -> {
            String id = txtId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Product ID!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete product ID: " + id + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) return;

            boolean deleted = ProductDAO.deleteProduct(id);
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                txtId.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Product not found or delete failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

            pack();
            pack();
            setSize(420, 200);
            setLocationRelativeTo(null);
            setVisible(true);
            ThemeManager.styleFrame(this);
        }
    }
