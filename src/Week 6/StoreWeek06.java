import java.util.Scanner;

/** Week 6: One-dimensional arrays and linear product search. */
public class StoreWeek06 {
    public static void main(String[] args) {
        String[] products = {"Keyboard", "Mouse", "Headphones", "USB Cable", "Webcam"};
        double[] prices = {899.00, 499.00, 1499.00, 199.00, 2299.00};

        System.out.println("--- Product Catalogue ---");
        for (int i = 0; i < products.length; i++) {
            System.out.printf("%d. %-15s ₹%.2f%n", i + 1, products[i], prices[i]);
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Search product name: ");
        String search = input.nextLine().trim();
        int foundIndex = -1;
        for (int i = 0; i < products.length; i++) {
            if (products[i].equalsIgnoreCase(search)) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex >= 0) {
            System.out.printf("Found: %s — ₹%.2f%n", products[foundIndex], prices[foundIndex]);
        } else {
            System.out.println("Product not found.");
        }
        input.close();
    }
}
