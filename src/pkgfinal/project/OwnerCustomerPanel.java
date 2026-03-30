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
        System.out.println("Customers: " + BookStoreSystem.getInstance().getCustomerList().size());
    }

    private void addCustomer() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");

        if (username != null && password != null) {
            Customer c = new Customer(username, password, BookStoreSystem.getInstance());
            BookStoreSystem.getInstance().addCustomer(c);
            loadCustomers();
        }
    }

    private void deleteCustomer() {
        int row = table.getSelectedRow();

        if (row >= 0) {
            String username = (String) model.getValueAt(row, 0);

            for (Customer c : BookStoreSystem.getInstance().getCustomerList()) {
                if (c.getUsername().equals(username)) {
                    BookStoreSystem.getInstance().removeCustomer(c);
                    break;
                }
            }

            loadCustomers();
        }
    }

    private void loadCustomers() {
        model.setRowCount(0);

        for (Customer c : BookStoreSystem.getInstance().getCustomerList()) {
            model.addRow(new Object[]{
                    c.getUsername(),
                    c.getPassword(),
                    c.getPoints()
            });
        }
    }

    private void goBack() {
        Container parent = this.getParent();
        while (!(parent instanceof JPanel)) parent = parent.getParent();

        ((CardLayout) parent.getLayout()).show(parent, "ownerStart");
    }
}