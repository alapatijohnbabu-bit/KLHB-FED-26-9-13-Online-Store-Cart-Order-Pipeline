/** Week 4: loops, selection, and a terminating menu-shaped workflow. */
public class StoreWeek04 {
    public static void main(String[] args) {
        int choice = 1;
        int stepsCompleted = 0;

        while (choice != 0) {
            switch (choice) {
                case 1:
                    System.out.println("1. View sample cart");
                    break;
                case 2:
                    System.out.println("2. Review sample total");
                    break;
                case 3:
                    System.out.println("3. Confirm review state");
                    break;
                default:
                    System.out.println("Unknown menu choice");
            }
            stepsCompleted++;

            // Fixed demonstration sequence; 0 terminates the loop.
            if (stepsCompleted == 1) choice = 2;
            else if (stepsCompleted == 2) choice = 3;
            else choice = 0;
        }

        System.out.println("Menu ended after " + stepsCompleted + " steps.");
    }
}
