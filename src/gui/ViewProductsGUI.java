package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import product.Product;
import persistence.ProductDAO;
import java.util.List;

public class ViewProductsGUI extends JFrame {

    public ViewProductsGUI() {
        setTitle("View Products");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ThemeManager.styleFrame(this);

        List<Product> products = ProductDAO.getAllProducts();

        String[] cols = {"ID", "Name", "Price", "Quantity", "Reorder Level"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        for (Product p : products) {
            model.addRow(new Object[]{
                p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getReorderLevel()
            });
        }

        JTable table = new JTable(model);
        ThemeManager.styleTableHeader(table);

        add(new JScrollPane(table));

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
