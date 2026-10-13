import java.util.Scanner;

public class StoreWeek10 {
    static double calculateSubtotal(double price, int quantity) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero.");
        return price * quantity;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Week 10: Exception handling");
        try {
            System.out.print("Enter item price: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            System.out.printf("Subtotal: ₹%.2f%n", calculateSubtotal(price, quantity));
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
        } finally { scanner.close(); }
    }
}
