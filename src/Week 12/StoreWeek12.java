import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StoreWeek12 {
    static class Product {
        private final String name;
        private final double price;
        Product(String name, double price) { this.name = name; this.price = price; }
        String getName() { return name; }
        double getPrice() { return price; }
    }
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Notebook", 55.0));
        products.add(new Product("Pen", 15.0));
        products.add(new Product("Water Bottle", 120.0));
        products.add(new Product("Pencil", 10.0));
        System.out.println("Week 12: Collections, lambdas and streams");
        List<Product> affordable = products.stream()
                .filter(product -> product.getPrice() <= 60.0)
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
        affordable.forEach(product -> System.out.printf("%s: ₹%.2f%n", product.getName(), product.getPrice()));
        double total = products.stream().mapToDouble(Product::getPrice).sum();
        System.out.printf("Total catalogue value: ₹%.2f%n", total);
    }
}
