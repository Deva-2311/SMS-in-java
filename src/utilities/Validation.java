package utilities;

public class Validation {

    // Validate that a string is not empty
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Validate numeric input (positive integer)
    public static boolean isPositiveInteger(int number) {
        return number > 0;
    }

    // Validate price
    public static boolean isValidPrice(double price) {
        return price >= 0;
    }

    // Validate ID format (example: must start with letter, then digits)
    public static boolean isValidProductId(String id) {
        return id.matches("[A-Za-z]\\d+");
    }

    // Validate reorder level
    public static boolean isValidReorderLevel(int level) {
        return level >= 0;
    }
}
