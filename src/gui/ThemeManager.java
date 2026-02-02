package gui;

import javax.swing.table.JTableHeader;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ThemeManager {

    // ------------------ COLORS ------------------
    public static final Color HEADER_BG = Color.decode("#CC5500");   // Dark Orange
    public static final Color BUTTON_BG = Color.decode("#FF7F00");   // Bright Orange
    public static final Color BG_COLOR = Color.decode("#FFFFFF");    // White
    public static final Color TEXT_LIGHT = Color.decode("#FFFFFF");  // White text
    public static final Color TEXT_DARK = Color.decode("#000000");   // Black text
    public static final Color BUTTON_HOVER = Color.decode("#E66A00"); // Slightly darker orange

    // ------------------ FONTS ------------------
    public static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 26);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font TEXT_FONT = new Font("Segoe UI", Font.PLAIN, 14);


    // ------------------ FRAME STYLE ------------------
    public static void styleFrame(JFrame frame) {
    // Background
    frame.getContentPane().setBackground(BG_COLOR);

    // Make sure VS Code sees it as modifying multiple meaningful UI properties
    frame.setForeground(TEXT_DARK);
    frame.setFont(new Font("Segoe UI", Font.PLAIN, 14));
}


    // ------------------ HEADER STYLE ------------------
    public static void styleHeader(JLabel label) {
        label.setOpaque(true);
        label.setBackground(HEADER_BG);
        label.setForeground(TEXT_LIGHT);

        label.setFont(HEADER_FONT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(15, 0, 15, 0));
    }


    // ------------------ BUTTON STYLE ------------------
    public static void styleButton(JButton btn) {
        btn.setBackground(BUTTON_BG);
        btn.setForeground(TEXT_LIGHT);
        btn.setFocusPainted(false);
        btn.setFont(BUTTON_FONT);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 12, 8, 12));

        // Rounded button edges
        btn.setOpaque(true);

        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(BUTTON_HOVER);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(BUTTON_BG);
            }
        });
    }


    // ------------------ FORM LABEL STYLE ------------------
    public static void styleFormLabel(JLabel lbl) {
        lbl.setForeground(TEXT_DARK);
        lbl.setFont(LABEL_FONT);
    }


    // ------------------ TABLE HEADER STYLE ------------------
    public static void styleTableHeader(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(HEADER_BG);
        header.setForeground(TEXT_LIGHT);
        header.setFont(BUTTON_FONT);
    }


    // ------------------ PANEL STYLE ------------------
    public static void stylePanel(JPanel panel) {
        panel.setBackground(BG_COLOR);
    }
    // ------------------ LABEL STYLE (Used by GUIs) ------------------
public static void styleLabel(JLabel lbl) {
    lbl.setForeground(TEXT_DARK);
    lbl.setFont(LABEL_FONT);
}


}
