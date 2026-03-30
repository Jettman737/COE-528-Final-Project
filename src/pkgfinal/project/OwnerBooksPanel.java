package pkgfinal.project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OwnerBooksPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;

    public OwnerBooksPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Title", "Price"}, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton addBtn = new JButton("Add Book");
        JButton removeBtn = new JButton("Delete");
        JButton backBtn = new JButton("Back");

        JPanel panel = new JPanel();
        panel.add(addBtn);
        panel.add(removeBtn);
        panel.add(backBtn);

        add(panel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addBook());
        removeBtn.addActionListener(e -> removeBook());
        backBtn.addActionListener(e -> goBack());

        loadBooks();
    }

    private void addBook() {
        String name = JOptionPane.showInputDialog("Enter book name:");
        String priceStr = JOptionPane.showInputDialog("Enter price:");

        try {
            double price = Double.parseDouble(priceStr);
            BookStoreSystem.getInstance().addBook(new Book(name, price));
            loadBooks();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

    private void removeBook() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String name = (String) model.getValueAt(row, 0);

            for (Book b : BookStoreSystem.getInstance().getInventory()) {
                if (b.getName().equals(name)) {
                    BookStoreSystem.getInstance().removeBook(b);
                    break;
                }
            }

            loadBooks();
        }
    }

    private void loadBooks() {
        model.setRowCount(0);

        for (Book b : BookStoreSystem.getInstance().getInventory()) {
            model.addRow(new Object[]{b.getName(), b.getPrice()});
        }
    }

    private void goBack() {
        Container parent = this.getParent();
        while (!(parent instanceof JPanel)) parent = parent.getParent();

        ((CardLayout) parent.getLayout()).show(parent, "ownerStart");
    }
}