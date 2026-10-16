import java.util.Scanner;

/** Week 8: Recursion for quantity totals and a recursive cart summary. */
public class StoreWeek08 {
    static int sumQuantities(int[] quantities, int index) {
        if (index >= quantities.length) return 0; // Base case
        return quantities[index] + sumQuantities(quantities, index + 1);
    }

    static double calculateCartTotal(double[] prices, int[] quantities, int index) {
        if (index >= prices.length || index >= quantities.length) return 0.0; // Base case
        return prices[index] * quantities[index] + calculateCartTotal(prices, quantities, index + 1);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Number of different cart items (1-10): ");
        int count = input.nextInt();
        if (count < 1 || count > 10) {
            System.out.println("Please enter a number from 1 to 10.");
            input.close();
            return;
        }

        double[] prices = new double[count];
        int[] quantities = new int[count];
        for (int i = 0; i < count; i++) {
            System.out.print("Item " + (i + 1) + " unit price: ");
            prices[i] = input.nextDouble();
            System.out.print("Item " + (i + 1) + " quantity: ");
            quantities[i] = input.nextInt();
            if (prices[i] < 0 || quantities[i] < 0) {
                System.out.println("Price and quantity cannot be negative.");
                input.close();
                return;
            }
        }
        System.out.println("Total units in cart: " + sumQuantities(quantities, 0));
        System.out.printf("Cart total: ₹%.2f%n", calculateCartTotal(prices, quantities, 0));
        input.close();
    }
}
