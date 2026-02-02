package ui;

import java.util.Scanner;

import product.ProductManager;
import inventory.InventoryManager;
import order.OrderManager;
import report.ReportManager;
import utilities.InputHelper;

public class StockManagementApp {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            // Managers (JDBC based)
            ProductManager productManager = new ProductManager();
            InventoryManager inventoryManager = new InventoryManager();
            OrderManager orderManager = new OrderManager(inventoryManager);
            ReportManager reportManager = new ReportManager(inventoryManager);

            int choice;

            do {
                System.out.println("\n=== STOCK MANAGEMENT SYSTEM ===");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Increase Stock (Purchase Order)");
                System.out.println("6. Reduce Stock (Sales Order)");
                System.out.println("7. Check Stock Level");
                System.out.println("8. Create Purchase Order");
                System.out.println("9. Create Sales Order");
                System.out.println("10. View Orders");
                System.out.println("11. Stock Report");
                System.out.println("12. Low Stock Alert");
                System.out.println("13. Order Report");
                System.out.println("14. Summary Report");
                System.out.println("0. Exit");
                System.out.println("---------------------------------");

                choice = InputHelper.readInt(scanner, "Enter your choice: ");

                switch (choice) {
                    case 1 -> productManager.addProduct(scanner);
                    case 2 -> productManager.viewProducts();
                    case 3 -> productManager.updateProduct(scanner);
                    case 4 -> {
                    String delId = InputHelper.readNonEmptyString(scanner, "Enter Product ID: ");
                    productManager.deleteProduct(delId);
                    }

                    case 5 -> {
                        String incId = InputHelper.readNonEmptyString(scanner, "Enter Product ID: ");
                        int incQty = InputHelper.readInt(scanner, "Enter quantity to add: ");
                        orderManager.createPurchaseOrder(incId, incQty);
                    }
                    case 6 -> {
                        String decId = InputHelper.readNonEmptyString(scanner, "Enter Product ID: ");
                        int decQty = InputHelper.readInt(scanner, "Enter quantity to reduce: ");
                        orderManager.createSalesOrder(decId, decQty);
                    }
                    case 7 -> {
                        String stockId = InputHelper.readNonEmptyString(scanner, "Enter Product ID: ");
                        inventoryManager.checkStock(stockId);
                    }
                    case 8 -> {
                        String purId = InputHelper.readNonEmptyString(scanner, "Enter Product ID: ");
                        int purQty = InputHelper.readInt(scanner, "Enter quantity to purchase: ");
                        orderManager.createPurchaseOrder(purId, purQty);
                    }
                    case 9 -> {
                        String saleId = InputHelper.readNonEmptyString(scanner, "Enter Product ID: ");
                        int saleQty = InputHelper.readInt(scanner, "Enter quantity to sell: ");
                        orderManager.createSalesOrder(saleId, saleQty);
                    }
                    case 10 -> orderManager.viewOrders();
                    case 11 -> reportManager.stockReport();
                    case 12 -> {
                        int threshold = InputHelper.readInt(scanner, "Enter low stock threshold: ");
                        reportManager.lowStockReport(threshold);
                    }
                    case 13 -> reportManager.orderReport();
                    case 14 -> {
                        int summaryThreshold = InputHelper.readInt(scanner, "Enter low stock threshold: ");
                        reportManager.summaryReport(summaryThreshold);
                    }
                    case 0 -> System.out.println("Exiting... Data is already saved in MySQL.");
                    default -> System.out.println("Invalid choice! Try again.");
                }

            } while (choice != 0);
        }
    }
}
