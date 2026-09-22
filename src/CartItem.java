public class CartItem {
    Product product;
    int quantity;

    // Constructor
    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getItemTotal() {
        return product.price * quantity;
    }

    void displayItem() {
        System.out.println(product.productName + " x " + quantity
                + " = ₹" + getItemTotal());
    }
}
