package product;

import java.util.List;
import java.util.Scanner;

import persistence.ProductDAO;
import utilities.InputHelper;
import utilities.Validation;

public class ProductManager {
    public void addProductFromGUI(String id, String name, double price, int qty, int reorder) {
    // Directly insert into DB using ProductDAO
    persistence.ProductDAO.addProduct(new product.Product(id, name, price, qty, reorder));
}

    // ================= Add New Products (Multiple at Once) =================
    public void addProduct(Scanner scanner) {
        int count = InputHelper.readInt(scanner, "How many products do you want to add? ");

        for (int i = 1; i <= count; i++) {
            System.out.println("\n--- Adding Product " + i + " of " + count + " ---");

            String id = InputHelper.readNonEmptyString(scanner, "Enter Product ID: ");

            // Check for duplicate ID (in DB)
            if (ProductDAO.getProductById(id) != null) {
                System.out.println("Product ID already exists in database! Enter a unique ID.");
                i--;
                continue;
            }

            // Validate format
            if (!Validation.isValidProductId(id)) {
                System.out.println("Invalid ID format! Example: P101");
                i--;
                continue;
            }

            String name = InputHelper.readNonEmptyString(scanner, "Enter Product Name: ");
            double price = InputHelper.readDouble(scanner, "Enter Price: ");

            if (!Validation.isValidPrice(price)) {
                System.out.println("Invalid price!");
                i--;
                continue;
            }

            int quantity = InputHelper.readInt(scanner, "Enter Quantity: ");
            int reorderLevel = InputHelper.readInt(scanner, "Enter Reorder Level: ");

            // Save directly to DB
            ProductDAO.addProduct(new Product(id, name, price, quantity, reorderLevel));
            System.out.println("Product " + id + " added to database!");
        }

        System.out.println("\n" + count + " product(s) added successfully!");
    }

    // ================= View All Products =================
    public void viewProducts() {
        List<Product> products = ProductDAO.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\n--- Product List ---");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    // ================= Update Product (DB Version) =================
    public void updateProduct(Scanner scanner) {
        String id = InputHelper.readNonEmptyString(scanner, "Enter Product ID to update: ");
        Product product = ProductDAO.getProductById(id);

        if (product == null) {
            System.out.println("Product not found in database!");
            return;
        }

        System.out.println("\nCurrent Details:");
        System.out.println(product);

        System.out.println("\nEnter new details (leave blank to keep current value):");

        // Update name
        System.out.print("Enter new name (" + product.getName() + "): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) product.setName(newName);

        // Update price
        System.out.print("Enter new price (" + product.getPrice() + "): ");
        String newPriceInput = scanner.nextLine().trim();
        if (!newPriceInput.isEmpty()) {
            try {
                double newPrice = Double.parseDouble(newPriceInput);
                if (Validation.isValidPrice(newPrice)) product.setPrice(newPrice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price entered, keeping old value.");
            }
        }

        // Update quantity
        System.out.print("Enter new quantity (" + product.getQuantity() + "): ");
        String newQtyInput = scanner.nextLine().trim();
        if (!newQtyInput.isEmpty()) {
            try {
                int newQty = Integer.parseInt(newQtyInput);
                if (Validation.isPositiveInteger(newQty)) product.setQuantity(newQty);
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity, keeping old value.");
            }
        }

        // Update reorder level
        System.out.print("Enter new reorder level (" + product.getReorderLevel() + "): ");
        String newReorderInput = scanner.nextLine().trim();
        if (!newReorderInput.isEmpty()) {
            try {
                int newReorder = Integer.parseInt(newReorderInput);
                if (Validation.isValidReorderLevel(newReorder)) product.setReorderLevel(newReorder);
            } catch (NumberFormatException e) {
                System.out.println("Invalid reorder level, keeping old value.");
            }
        }

        // Save to DB
        ProductDAO.updateProduct(product);
        System.out.println("Product updated successfully in database!");
    }

    // ================= Delete Product (DB Version) =================
   public void deleteProduct(String id) {
    if (ProductDAO.getProductById(id) == null) {
        System.out.println("Product not found in database!");
        return;
    }

    ProductDAO.deleteProduct(id);
    System.out.println("Product deleted successfully from database!");
}


}
