package gui;

import inventory.InventoryManager;
import report.ReportManager;

public class RunApp {
    public static void main(String[] args) {

        // Create Inventory Manager
        InventoryManager inventoryManager = new InventoryManager();

        // Create Report Manager
        ReportManager reportManager = new ReportManager(inventoryManager);

        // Launch Main Dashboard
        new MainDashboard(inventoryManager, reportManager).setVisible(true);
    }
}
