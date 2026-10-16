import java.util.Scanner;

/** Week 5: Methods and reusable calculations for the online store. */
public class StoreWeek05 {
    static double calculateSubtotal(double price, int quantity) {
        if (price < 0 || quantity < 0) throw new IllegalArgumentException("Price and quantity cannot be negative.");
        return price * quantity;
    }

    static double calculateDiscount(double subtotal, double discountPercent) {
        if (subtotal < 0 || discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Invalid subtotal or discount percentage.");
        return subtotal * discountPercent / 100.0;
    }

    static void printReceipt(String product, int quantity, double subtotal, double discount) {
        System.out.println("\n--- Store Receipt ---");
        System.out.println("Product: " + product);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Subtotal: ₹%.2f%n", subtotal);
        System.out.printf("Discount: ₹%.2f%n", discount);
        System.out.printf("Total: ₹%.2f%n", subtotal - discount);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Product name: ");
        String product = input.nextLine();
        System.out.print("Unit price: ");
        double price = input.nextDouble();
        System.out.print("Quantity: ");
        int quantity = input.nextInt();
        System.out.print("Discount percentage (0-100): ");
        double percent = input.nextDouble();
        try {
            double subtotal = calculateSubtotal(price, quantity);
            double discount = calculateDiscount(subtotal, percent);
            printReceipt(product, quantity, subtotal, discount);
        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
        }
        input.close();
    }
}
