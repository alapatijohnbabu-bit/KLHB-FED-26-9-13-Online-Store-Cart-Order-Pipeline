import java.util.Arrays;

/** Week 7: String validation, stock checks and sorting product records by price. */
public class StoreWeek07 {
    static boolean isValidProductName(String name) {
        return name != null && name.trim().length() >= 2 && name.trim().matches("[A-Za-z0-9][A-Za-z0-9 &_-]*");
    }

    static boolean canPurchase(int requestedQuantity, int availableStock) {
        return requestedQuantity > 0 && availableStock >= requestedQuantity;
    }

    public static void main(String[] args) {
        String[] products = {"Webcam", "Keyboard", "USB Cable", "Headphones", "Mouse"};
        double[] prices = {2299.00, 899.00, 199.00, 1499.00, 499.00};

        String sampleName = "USB Cable";
        System.out.println("Valid product name? " + isValidProductName(sampleName));
        System.out.println("Can purchase 2 from stock of 5? " + canPurchase(2, 5));

        // Sort product names and prices together by ascending price.
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = 0; j < prices.length - 1 - i; j++) {
                if (prices[j] > prices[j + 1]) {
                    double tempPrice = prices[j];
                    prices[j] = prices[j + 1];
                    prices[j + 1] = tempPrice;
                    String tempName = products[j];
                    products[j] = products[j + 1];
                    products[j + 1] = tempName;
                }
            }
        }

        System.out.println("\nProducts sorted by price (low to high):");
        for (int i = 0; i < products.length; i++) {
            System.out.printf("%-15s ₹%.2f%n", products[i], prices[i]);
        }
        System.out.println("Sorted prices: " + Arrays.toString(prices));
    }
}
