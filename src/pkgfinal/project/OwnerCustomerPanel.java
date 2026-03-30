package pkgfinal.project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OwnerCustomerPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;

    public OwnerCustomerPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Username", "Password", "Points"}, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton addBtn = new JButton("Add Customer");
        JButton deleteBtn = new JButton("Delete");
        JButton backBtn = new JButton("Back");

        JPanel panel = new JPanel();
        panel.add(addBtn);
        panel.add(deleteBtn);
        panel.add(backBtn);

        add(panel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addCustomer());
        deleteBtn.addActionListener(e -> deleteCustomer());
        backBtn.addActionListener(e -> goBack());

        loadCustomers();
    }

    private void addCustomer() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");

        if (username != null && password != null) {
            username = username.trim();
            password = password.trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty.");
                return;
            }

            // Check if username already exists
            for (Customer c : BookStoreSystem.getInstance().getCustomerList()) {
                if (c.getUsername().equalsIgnoreCase(username)) {
                    JOptionPane.showMessageDialog(this, "Username already exists!");
                    return;
                }
            }

            Customer c = new Customer(username, password, BookStoreSystem.getInstance());
            BookStoreSystem.getInstance().addCustomer(c);

            System.out.println("Added customer: " + c.getUsername() + " / " + c.getPassword());
            loadCustomers();
        }
    }

    private void deleteCustomer() {
        int row = table.getSelectedRow();

        if (row >= 0) {
            String username = (String) model.getValueAt(row, 0);

            Customer toRemove = null;
            for (Customer c : BookStoreSystem.getInstance().getCustomerList()) {
                if (c.getUsername().equals(username)) {
                    toRemove = c;
                    break;
                }
            }

            if (toRemove != null) {
                BookStoreSystem.getInstance().removeCustomer(toRemove);
                System.out.println("Deleted customer: " + toRemove.getUsername());
            }

            loadCustomers();
        }
    }

    public void loadCustomers() {
        model.setRowCount(0);
        for (Customer c : BookStoreSystem.getInstance().getCustomerList()) {
            model.addRow(new Object[]{
                    c.getUsername(),
                    c.getPassword(),
                    c.getPoints()
            });
        }
        System.out.println("Customers now: " + BookStoreSystem.getInstance().getCustomerList().size());
    }

    private void goBack() {
        Container parent = this.getParent();
        while (!(parent instanceof JPanel)) parent = parent.getParent();

        ((CardLayout) parent.getLayout()).show(parent, "ownerStart");
    }
}