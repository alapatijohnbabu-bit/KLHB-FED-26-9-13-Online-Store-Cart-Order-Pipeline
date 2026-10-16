/** Week 2: add selection and positive-quantity validation. */
public class StoreWeek02 {
    public static void main(String[] args) {
        int validQuantity = 2;
        int invalidQuantity = 0;

        checkQuantity(validQuantity);
        checkQuantity(invalidQuantity);
    }

    static void checkQuantity(int quantity) {
        if (quantity > 0) {
            System.out.println("Accepted quantity: " + quantity);
        } else {
            System.out.println("Rejected quantity: " + quantity + " (must be greater than zero)");
        }
    }
}
