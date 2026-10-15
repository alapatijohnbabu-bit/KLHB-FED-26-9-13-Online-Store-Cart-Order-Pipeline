package com.store.ui;

import com.store.data.DataStore;
import com.store.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class StoreFrame extends JFrame {
    private final DataStore data = new DataStore();
    private final DefaultTableModel catalogModel = model("ID", "Product", "Category", "Price (INR)", "Stock");
    private final DefaultTableModel cartModel = model("ID", "Product", "Qty", "Unit price", "Line total");
    private final DefaultTableModel orderModel = model("Order ID", "Customer", "Created", "Total (INR)", "Status");
    private final DefaultTableModel reservationModel = model("Reservation ID", "Product", "Qty", "Delivery date", "Status");
    private final JTable catalogTable = new JTable(catalogModel);
    private final JTable cartTable = new JTable(cartModel);
    private final JTable ordersTable = new JTable(orderModel);
    private final JTable reservationsTable = new JTable(reservationModel);
    private final JTextField searchField = new JTextField(18);
    private final JLabel totalLabel = new JLabel("Cart total: ₹0.00");

    public StoreFrame() {
        super("CartFlow | Online Store & Order Pipeline");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(940, 600));
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));
        getContentPane().setBackground(new Color(245, 247, 251));
        add(header(), BorderLayout.NORTH);
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Product Catalog", catalogPanel());
        tabs.addTab("Shopping Cart", cartPanel());
        tabs.addTab("Orders & Tracking", ordersPanel());
        tabs.addTab("Future Reservations", reservationsPanel());
        add(tabs, BorderLayout.CENTER);
        refreshAll();
    }
    private JPanel header() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createEmptyBorder(14, 18, 10, 18));
        p.setBackground(new Color(26, 39, 68));
        JLabel title = new JLabel("CartFlow");
        title.setForeground(Color.WHITE); title.setFont(new Font("SansSerif", Font.BOLD, 25));
        JLabel sub = new JLabel("Shop today. Plan ahead. Track every order.");
        sub.setForeground(new Color(210, 220, 240));
        JPanel labels = new JPanel(new GridLayout(2, 1)); labels.setOpaque(false); labels.add(title); labels.add(sub);
        p.add(labels, BorderLayout.WEST);
        JLabel badge = new JLabel("JAVA SWING  •  JDK 17+  ");
        badge.setForeground(Color.WHITE); p.add(badge, BorderLayout.EAST);
        return p;
    }
    private JPanel catalogPanel() {
        JPanel p = panel();
        JPanel tools = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tools.add(new JLabel("Search:")); tools.add(searchField);
        JButton search = new JButton("Search"); JButton reset = new JButton("Show all"); JButton add = new JButton("Add selected to cart");
        tools.add(search); tools.add(reset); tools.add(add);
        search.addActionListener(e -> fillCatalog(data.getCatalog().search(searchField.getText())));
        reset.addActionListener(e -> { searchField.setText(""); fillCatalog(data.getCatalog().getAll()); });
        add.addActionListener(e -> guarded(() -> {
            int row = catalogTable.getSelectedRow();
            if (row < 0) throw new IllegalArgumentException("Select a product first.");
            String id = String.valueOf(catalogModel.getValueAt(catalogTable.convertRowIndexToModel(row), 0));
            Product product = data.getCatalog().findById(id).orElseThrow();
            String q = JOptionPane.showInputDialog(this, "Quantity to add:", "1");
            if (q == null) return;
            data.getCartService().add(product, Integer.parseInt(q.trim()));
            refreshCart();
            JOptionPane.showMessageDialog(this, "Added to cart.");
        }));
        p.add(tools, BorderLayout.NORTH); p.add(new JScrollPane(catalogTable), BorderLayout.CENTER);
        catalogTable.setAutoCreateRowSorter(true);
        return p;
    }
    private JPanel cartPanel() {
        JPanel p = panel();
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton remove = new JButton("Remove selected"); JButton checkout = new JButton("Checkout");
        buttons.add(remove); buttons.add(checkout); buttons.add(totalLabel);
        remove.addActionListener(e -> guarded(() -> {
            int row = cartTable.getSelectedRow(); if (row < 0) throw new IllegalArgumentException("Select a cart item.");
            data.getCartService().remove(String.valueOf(cartModel.getValueAt(cartTable.convertRowIndexToModel(row), 0)));
            refreshCart();
        }));
        checkout.addActionListener(e -> guarded(() -> {
            if (data.getCartService().getCart().isEmpty()) throw new IllegalStateException("Your cart is empty.");
            String[] methods = {"Demo Card", "Cash on Delivery", "UPI (simulated)"};
            String method = (String) JOptionPane.showInputDialog(this, "Choose demo payment method:", "Checkout", JOptionPane.QUESTION_MESSAGE, null, methods, methods[0]);
            if (method == null) return;
            Order order = data.getOrderService().checkout(data.getCustomer(), data.getCartService().getCart(), method);
            refreshAll();
            JOptionPane.showMessageDialog(this, "Order placed!\nOrder ID: " + order.getId() + "\nTotal: ₹" + order.getTotal() + "\nPayment is simulated for this project.");
        }));
        p.add(new JScrollPane(cartTable), BorderLayout.CENTER); p.add(buttons, BorderLayout.SOUTH);
        return p;
    }
    private JPanel ordersPanel() {
        JPanel p = panel();
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton advance = new JButton("Advance pipeline"); JButton cancel = new JButton("Cancel eligible order");
        buttons.add(advance); buttons.add(cancel);
        advance.addActionListener(e -> guarded(() -> {
            Order o = selectedOrder(); data.getOrderService().advance(o.getId()); refreshOrders();
        }));
        cancel.addActionListener(e -> guarded(() -> {
            Order o = selectedOrder(); data.getOrderService().cancel(o.getId()); refreshAll();
        }));
        p.add(new JScrollPane(ordersTable), BorderLayout.CENTER); p.add(buttons, BorderLayout.SOUTH);
        return p;
    }
    private JPanel reservationsPanel() {
        JPanel p = panel();
        JPanel tools = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reserve = new JButton("Reserve product"); JButton check = new JButton("Check availability"); JButton cancel = new JButton("Cancel reservation");
        tools.add(reserve); tools.add(check); tools.add(cancel);
        reserve.addActionListener(e -> guarded(() -> {
            List<Product> products = data.getCatalog().getAll();
            Product product = (Product) JOptionPane.showInputDialog(this, "Product:", "Future reservation", JOptionPane.QUESTION_MESSAGE, null, products.toArray(), products.get(0));
            if (product == null) return;
            String qtyText = JOptionPane.showInputDialog(this, "Quantity:", "1"); if (qtyText == null) return;
            String dateText = JOptionPane.showInputDialog(this, "Delivery date (YYYY-MM-DD):", LocalDate.now().plusDays(7).toString());
            if (dateText == null) return;
            Reservation r = data.getReservationService().reserve(product, Integer.parseInt(qtyText.trim()), LocalDate.parse(dateText.trim()));
            refreshReservations();
            JOptionPane.showMessageDialog(this, "Reservation created: " + r.getId() + "\nAvailability is checked against current demo stock.");
        }));
        check.addActionListener(e -> guarded(() -> {
            data.getReservationService().checkAvailability(); refreshReservations();
            List<Reservation> needsAction = data.getReservationService().getReservations().stream().filter(r -> r.getStatus().equals("ACTION REQUIRED")).toList();
            if (needsAction.isEmpty()) JOptionPane.showMessageDialog(this, "No scheduled reservation currently needs action.");
            else {
                Reservation r = needsAction.get(0);
                List<Product> alternatives = data.getReservationService().alternatives(r.getProduct());
                String names = alternatives.isEmpty() ? "No in-stock alternatives in this category." : alternatives.stream().map(Product::getName).reduce((a,b) -> a + ", " + b).orElse("");
                JOptionPane.showMessageDialog(this, r.getProduct().getName() + " needs attention. Alternatives: " + names + "\nChoose to reschedule or cancel with the customer.");
            }
        }));
        cancel.addActionListener(e -> guarded(() -> {
            int row = reservationsTable.getSelectedRow(); if (row < 0) throw new IllegalArgumentException("Select a reservation.");
            String id = String.valueOf(reservationModel.getValueAt(reservationsTable.convertRowIndexToModel(row), 0));
            data.getReservationService().cancel(id); refreshReservations();
        }));
        p.add(tools, BorderLayout.NORTH); p.add(new JScrollPane(reservationsTable), BorderLayout.CENTER);
        return p;
    }
    private Order selectedOrder() {
        int row = ordersTable.getSelectedRow(); if (row < 0) throw new IllegalArgumentException("Select an order.");
        String id = String.valueOf(orderModel.getValueAt(ordersTable.convertRowIndexToModel(row), 0));
        return data.getOrderService().getOrders().stream().filter(o -> o.getId().equals(id)).findFirst().orElseThrow();
    }
    private void fillCatalog(List<Product> products) {
        catalogModel.setRowCount(0);
        for (Product p : products) catalogModel.addRow(new Object[]{p.getId(), p.getName(), p.getCategory(), p.getPrice(), p.getStock()});
    }
    private void refreshCart() {
        cartModel.setRowCount(0);
        for (CartItem i : data.getCartService().getCart().getItems())
            cartModel.addRow(new Object[]{i.getProduct().getId(), i.getProduct().getName(), i.getQuantity(), i.getProduct().getPrice(), i.getLineTotal()});
        totalLabel.setText("Cart total: ₹" + data.getCartService().total());
    }
    private void refreshOrders() {
        orderModel.setRowCount(0);
        for (Order o : data.getOrderService().getOrders())
            orderModel.addRow(new Object[]{o.getId(), o.getCustomer().getName(), o.getCreatedAt().toString().replace('T', ' '), o.getTotal(), o.getStatus()});
    }
    private void refreshReservations() {
        reservationModel.setRowCount(0);
        for (Reservation r : data.getReservationService().getReservations())
            reservationModel.addRow(new Object[]{r.getId(), r.getProduct().getName(), r.getQuantity(), r.getDeliveryDate(), r.getStatus()});
    }
    private void refreshAll() { fillCatalog(data.getCatalog().getAll()); refreshCart(); refreshOrders(); refreshReservations(); }
    private void guarded(Runnable action) {
        try { action.run(); }
        catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Enter a valid whole number.", "Invalid input", JOptionPane.WARNING_MESSAGE); }
        catch (Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage() == null ? "Operation failed." : ex.getMessage(), "Cannot complete action", JOptionPane.WARNING_MESSAGE); }
    }
    private static DefaultTableModel model(String... columns) {
        return new DefaultTableModel(columns, 0) { @Override public boolean isCellEditable(int row, int col) { return false; } };
    }
    private static JPanel panel() { JPanel p = new JPanel(new BorderLayout(10, 10)); p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12)); return p; }
}