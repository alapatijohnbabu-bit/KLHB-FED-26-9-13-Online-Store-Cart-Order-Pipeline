import java.util.ArrayList;
import java.util.List;

public class StoreWeek09 {
    static class Product {
        private final String name;
        private final double price;
        private int stock;
        public Product(String name, double price, int stock) {
            this.name = name; this.price = price; this.stock = stock;
        }
        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getStock() { return stock; }
        public boolean sell(int quantity) {
            if (quantity <= 0 || quantity > stock) return false;
            stock -= quantity; return true;
        }
        public String toString() { return name + " | ₹" + price + " | stock: " + stock; }
    }
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Notebook", 55.0, 10));
        products.add(new Product("Pen", 15.0, 20));
        System.out.println("Week 9: Classes, objects and encapsulation");
        for (Product product : products) System.out.println(product);
        System.out.println("Selling 2 pens: " + products.get(1).sell(2));
        System.out.println(products.get(1));
    }
}
