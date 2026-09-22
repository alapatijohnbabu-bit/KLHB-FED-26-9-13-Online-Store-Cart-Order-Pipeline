public class Customer {
    String customerId;
    String customerName;
    String address;

    // Constructor
    Customer(String customerId, String customerName, String address) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
    }

    void displayCustomer() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Address: " + address);
    }
}
