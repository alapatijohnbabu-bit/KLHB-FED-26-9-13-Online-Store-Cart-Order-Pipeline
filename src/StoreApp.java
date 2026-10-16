import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/** A classroom/demo cart app built with the Java concepts from the 12-week plan. */
public class StoreApp extends JFrame {
    private final Store store = new Store();
    private final Cart cart = new Cart("SAMPLE-CART-01");
    private final DefaultTableModel catalogueModel = new DefaultTableModel(new Object[]{"Item", "Sample unit price"}, 0) {
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private final DefaultTableModel cartModel = new DefaultTableModel(new Object[]{"Item", "Quantity", "Unit price", "Line total"}, 0) {
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable catalogueTable = new JTable(catalogueModel);
    private final JTable cartTable = new JTable(cartModel);
    private final JTextField searchField = new JTextField(12);
    private final JTextField quantityField = new JTextField("1", 5);
    private final JLabel totalLabel = new JLabel("Sample total: 0");
    private final JLabel statusLabel = new JLabel("Status: OPEN");

    StoreApp() {
        super("Online Store Cart & Order Pipeline");
        store.addProduct(new Product("TraceTiles", 120));
        store.addProduct(new Product("LoopCards", 80));
        store.addProduct(new Product("MethodMap", 160));
        store.addProduct(new Product("ArrayAtlas", 100));
        store.addProduct(new Product("StreamSheets", 140));
        buildWindow();
        refreshCatalogue(store.getProducts());
        refreshCart();
    }

    private void buildWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 560);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        JLabel title = new JLabel("ONLINE STORE CART & ORDER PIPELINE", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 21));
        title.setBorder(BorderFactory.createEmptyBorder(12, 8, 8, 8));
        add(title, BorderLayout.NORTH);

        JPanel catalogue = new JPanel(new BorderLayout(5, 5));
        catalogue.setBorder(BorderFactory.createTitledBorder("Sample Catalogue"));
        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));
        search.add(new JLabel("Search:"));
        search.add(searchField);
        JButton searchButton = new JButton("Search");
        JButton allButton = new JButton("Show All");
        search.add(searchButton);
        search.add(allButton);
        catalogue.add(search, BorderLayout.NORTH);
        catalogue.add(new JScrollPane(catalogueTable), BorderLayout.CENTER);
        JPanel add = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add.add(new JLabel("Quantity:"));
        add.add(quantityField);
        JButton addButton = new JButton("Add to Cart");
        add.add(addButton);
        catalogue.add(add, BorderLayout.SOUTH);

        JPanel cartPanel = new JPanel(new BorderLayout(5, 5));
        cartPanel.setBorder(BorderFactory.createTitledBorder("Cart Review"));
        cartPanel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton removeButton = new JButton("Remove Selected");
        JButton reviewButton = new JButton("Review Order");
        JButton exportButton = new JButton("Export CSV");
        actions.add(removeButton);
        actions.add(reviewButton);
        actions.add(exportButton);
        cartPanel.add(actions, BorderLayout.SOUTH);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, catalogue, cartPanel);
        split.setResizeWeight(0.48);
        add(split, BorderLayout.CENTER);
        JPanel footer = new JPanel(new GridLayout(2, 1));
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        footer.add(totalLabel);
        footer.add(statusLabel);
        footer.setBorder(BorderFactory.createEmptyBorder(0, 14, 12, 14));
        add(footer, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchCatalogue());
        allButton.addActionListener(e -> refreshCatalogue(store.getProducts()));
        addButton.addActionListener(e -> addSelectedProduct());
        removeButton.addActionListener(e -> removeSelectedLine());
        reviewButton.addActionListener(e -> reviewOrder());
        exportButton.addActionListener(e -> exportCsv());
    }

    private void searchCatalogue() {
        List<Product> matches = store.search(searchField.getText());
        refreshCatalogue(matches);
        if (matches.isEmpty()) showError("No matching item found.");
    }

    private void addSelectedProduct() {
        int row = catalogueTable.getSelectedRow();
        if (row < 0) { showError("Select an item first."); return; }
        try {
            int quantity = Integer.parseInt(quantityField.getText().trim());
            String name = String.valueOf(catalogueModel.getValueAt(row, 0));
            CartOperation operation = new AddProductOperation(store.findProduct(name), quantity);
            operation.execute(cart);
            refreshCart();
        } catch (NumberFormatException ex) {
            showError("Quantity must be a whole number.");
        } catch (InvalidCartInputException ex) {
            showError(ex.getMessage());
        }
    }

    private void removeSelectedLine() {
        int row = cartTable.getSelectedRow();
        if (row < 0) { showError("Select a cart line first."); return; }
        cart.removeLine(row);
        refreshCart();
    }

    private void reviewOrder() {
        try {
            CartOperation operation = new ReviewCartOperation();
            operation.execute(cart);
            Order order = new Order(cart.getCartId(), cart.getTotal());
            statusLabel.setText("Status: " + order.getStatus());
            JOptionPane.showMessageDialog(this, "Order review complete.\nOrder ID: " + order.getOrderId()
                    + "\nSample total: " + order.getSampleTotal() + "\nStatus: " + order.getStatus()
                    + "\n\nDemo only. No real payment is processed.");
        } catch (InvalidCartInputException ex) {
            showError(ex.getMessage());
        }
    }

    private void exportCsv() {
        try {
            Path path = Path.of("results", "cart-report.csv");
            ReportService.write(path, cart);
            JOptionPane.showMessageDialog(this, "CSV saved to:\n" + path.toAbsolutePath());
        } catch (IOException ex) {
            showError("Could not save CSV: " + ex.getMessage());
        }
    }

    private void refreshCatalogue(List<Product> products) {
        catalogueModel.setRowCount(0);
        for (Product p : products) catalogueModel.addRow(new Object[]{p.getName(), p.getSampleUnitPrice()});
    }

    private void refreshCart() {
        cartModel.setRowCount(0);
        for (CartLine line : cart.getLines()) {
            cartModel.addRow(new Object[]{line.getProduct().getName(), line.getQuantity(),
                    line.getProduct().getSampleUnitPrice(), line.getLineTotal()});
        }
        totalLabel.setText("Sample total: " + cart.getTotal());
        statusLabel.setText("Status: " + cart.getState());
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input problem", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StoreApp().setVisible(true));
    }

    static class Product {
        private final String name;
        private final int sampleUnitPrice;
        Product(String name, int sampleUnitPrice) {
            if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required.");
            if (sampleUnitPrice <= 0) throw new IllegalArgumentException("Price must be positive.");
            this.name = name; this.sampleUnitPrice = sampleUnitPrice;
        }
        String getName() { return name; }
        int getSampleUnitPrice() { return sampleUnitPrice; }
    }

    static class CartLine {
        private final Product product;
        private final int quantity;
        CartLine(Product product, int quantity) throws InvalidCartInputException {
            if (product == null) throw new InvalidCartInputException("Choose a valid product.");
            if (quantity <= 0) throw new InvalidCartInputException("Quantity must be greater than zero.");
            this.product = product; this.quantity = quantity;
        }
        Product getProduct() { return product; }
        int getQuantity() { return quantity; }
        int getLineTotal() { return quantity * product.getSampleUnitPrice(); }
    }

    static class Cart {
        private final String cartId;
        private final List<CartLine> lines = new ArrayList<>();
        private String state = "OPEN";
        Cart(String cartId) { this.cartId = cartId; }
        void add(Product product, int quantity) throws InvalidCartInputException {
            if ("READY_FOR_REVIEW".equals(state)) state = "OPEN";
            for (int i = 0; i < lines.size(); i++) {
                CartLine line = lines.get(i);
                if (line.getProduct().getName().equalsIgnoreCase(product.getName())) {
                    lines.set(i, new CartLine(product, line.getQuantity() + quantity));
                    return;
                }
            }
            lines.add(new CartLine(product, quantity));
        }
        void removeLine(int index) { if (index >= 0 && index < lines.size()) { lines.remove(index); state = "OPEN"; } }
        void markReadyForReview() throws InvalidCartInputException {
            if (lines.isEmpty()) throw new InvalidCartInputException("Cannot review an empty cart.");
            state = "READY_FOR_REVIEW";
        }
        int getTotal() { return lines.stream().mapToInt(CartLine::getLineTotal).sum(); }
        int recursiveTotal(int index) {
            if (index >= lines.size()) return 0;
            return lines.get(index).getLineTotal() + recursiveTotal(index + 1);
        }
        String[] sortedNames() {
            String[] names = lines.stream().map(x -> x.getProduct().getName()).toArray(String[]::new);
            Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
            return names;
        }
        String getCartId() { return cartId; }
        String getState() { return state; }
        List<CartLine> getLines() { return List.copyOf(lines); }
    }

    static class Store {
        private final List<Product> products = new ArrayList<>();
        void addProduct(Product p) { products.add(p); }
        List<Product> getProducts() { return List.copyOf(products); }
        Product findProduct(String name) throws InvalidCartInputException {
            return products.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst()
                    .orElseThrow(() -> new InvalidCartInputException("Unknown item: " + name));
        }
        List<Product> search(String query) {
            if (query == null || query.isBlank()) return getProducts();
            return products.stream().filter(p -> p.getName().toLowerCase().contains(query.trim().toLowerCase()))
                    .sorted(Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());
        }
    }

    interface CartOperation { void execute(Cart cart) throws InvalidCartInputException; }

    static class AddProductOperation implements CartOperation {
        private final Product product; private final int quantity;
        AddProductOperation(Product product, int quantity) { this.product = product; this.quantity = quantity; }
        public void execute(Cart cart) throws InvalidCartInputException { cart.add(product, quantity); }
    }

    static class ReviewCartOperation implements CartOperation {
        public void execute(Cart cart) throws InvalidCartInputException { cart.markReadyForReview(); }
    }

    static class Order {
        private final String orderId; private final int sampleTotal; private final String status;
        Order(String orderId, int sampleTotal) {
            this.orderId = orderId; this.sampleTotal = sampleTotal; this.status = "READY_FOR_REVIEW";
        }
        String getOrderId() { return orderId; }
        int getSampleTotal() { return sampleTotal; }
        String getStatus() { return status; }
    }

    static class InvalidCartInputException extends Exception {
        InvalidCartInputException(String message) { super(message); }
    }

    static class ReportService {
        static void write(Path path, Cart cart) throws IOException {
            Files.createDirectories(path.getParent());
            List<String> rows = new ArrayList<>();
            rows.add("cartId,state,item,quantity,unitPrice,lineTotal");
            List<CartLine> sorted = cart.getLines().stream()
                    .sorted(Comparator.comparing(x -> x.getProduct().getName()))
                    .collect(Collectors.toList());
            for (CartLine line : sorted) rows.add(cart.getCartId() + "," + cart.getState() + ","
                    + line.getProduct().getName() + "," + line.getQuantity() + ","
                    + line.getProduct().getSampleUnitPrice() + "," + line.getLineTotal());
            Files.write(path, rows, StandardCharsets.UTF_8);
        }
    }
}
