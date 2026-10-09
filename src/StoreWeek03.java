/** Week 3: conditions, arithmetic, and a simple cart review decision. */
public class StoreWeek03 {
    public static void main(String[] args) {
        String item = "TraceTiles";
        int quantity = 2;
        double sampleUnitPrice = 120.0;
        double lineTotal = quantity * sampleUnitPrice;
        double reviewLimit = 200.0;

        String status;
        if (quantity <= 0) {
            status = "EMPTY";
        } else if (lineTotal >= reviewLimit) {
            status = "READY_FOR_REVIEW";
        } else {
            status = "BELOW_REVIEW_LIMIT";
        }

        System.out.println("Item: " + item);
        System.out.println("Line total: " + lineTotal + " sample units");
        System.out.println("Review status: " + status);
    }
}
