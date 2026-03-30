package pkgfinal.project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CustomerDashboard extends JPanel {

    private JLabel infoLabel;
    private JTable bookTable;

    public CustomerDashboard() {
        setLayout(new BorderLayout());

        infoLabel = new JLabel("Welcome");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(infoLabel, BorderLayout.NORTH);

        bookTable = new JTable(new DefaultTableModel(
                new Object[]{"Book Name", "Price", "Select"}, 0) {

            @Override
            public Class<?> getColumnClass(int column) {
                return column == 2 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        });

        add(new JScrollPane(bookTable), BorderLayout.CENTER);

        JButton buyBtn = new JButton("Buy");
        JButton redeemBtn = new JButton("Redeem Points and Buy");
        JButton logoutBtn = new JButton("Logout");

        JPanel panel = new JPanel();
        panel.add(buyBtn);
        panel.add(redeemBtn);
        panel.add(logoutBtn);

        add(panel, BorderLayout.SOUTH);

        buyBtn.addActionListener(e -> handleBuy(false));
        redeemBtn.addActionListener(e -> handleBuy(true));
        logoutBtn.addActionListener(e -> handleLogout());
    }

    public void refresh() {
        BookStoreSystem system = BookStoreSystem.getInstance();
        Customer customer = (Customer) system.getCurrentUser();

        infoLabel.setText(
                "Welcome " + customer.getUsername() +
                ". You have " + customer.getPoints() +
                " points. Your status is " + customer.getStatusString()
        );

        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);

        Object[][] data = customer.viewBooks();
        for (Object[] row : data) {
            model.addRow(row);
        }
    }

    private ArrayList<Book> getSelectedBooks() {
        ArrayList<Book> cart = new ArrayList<>();
        BookStoreSystem system = BookStoreSystem.getInstance();

        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean selected = (Boolean) model.getValueAt(i, 2);

            if (selected != null && selected) {
                String name = model.getValueAt(i, 0).toString();

                for (Book b : system.getInventory()) {
                    if (b.getName().equals(name)) {
                        cart.add(b);
                        break;
                    }
                }
            }
        }
        return cart;
    }

    private void handleBuy(boolean redeem) {
        BookStoreSystem system = BookStoreSystem.getInstance();
        Customer customer = (Customer) system.getCurrentUser();

        ArrayList<Book> cart = getSelectedBooks();

        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No books selected.");
            return;
        }

        double cost = customer.viewCartCost(cart);

        if (redeem) {
            customer.redeemAndBuy(cart);
        } else {
            customer.buyBooks(cart);
        }

        refresh();

        JOptionPane.showMessageDialog(this, "Total Cost: $" + cost);
    }

    private void handleLogout() {
        BookStoreSystem.getInstance().logOut();

    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

    JPanel main = (JPanel) frame.getContentPane().getComponent(0);
    CardLayout cl = (CardLayout) main.getLayout();

    cl.show(main, "card2");
    }
}