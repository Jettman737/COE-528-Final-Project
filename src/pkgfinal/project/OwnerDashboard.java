package pkgfinal.project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OwnerDashboard extends JPanel {

    private DefaultTableModel bookModel;
    private JTable bookTable;
    private Owner owner; // reference to owner

    public OwnerDashboard(Owner owner) {
        this.owner = owner;

        setLayout(new BorderLayout());

        JPanel bookPanel = new JPanel(new BorderLayout());
        JLabel bookLabel = new JLabel("Manage Books:");
        bookLabel.setFont(new Font("Arial", Font.BOLD, 14));

        bookModel = new DefaultTableModel(
            new String[]{"Title", "Price"}, 0
        );

        bookTable = new JTable(bookModel);
        bookTable.setRowHeight(20);
        bookTable.setFillsViewportHeight(true);

        JScrollPane bookScroll = new JScrollPane(bookTable);

        // Buttons
        JButton addBookBtn = new JButton("Add Book");
        JButton removeBookBtn = new JButton("Remove Selected");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBookBtn);
        buttonPanel.add(removeBookBtn);

        bookPanel.add(bookLabel, BorderLayout.NORTH);
        bookPanel.add(bookScroll, BorderLayout.CENTER);
        bookPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bookPanel, BorderLayout.CENTER);

        // Add Book
        addBookBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter book name:");
            String priceStr = JOptionPane.showInputDialog("Enter price:");

            try {
                double price = Double.parseDouble(priceStr);
                owner.addBooks(name, price);
                loadBooks();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        });

        // Remove Book
        removeBookBtn.addActionListener(e -> {
            int row = bookTable.getSelectedRow();
            if (row >= 0) {
                String name = (String) bookModel.getValueAt(row, 0);
                owner.removeBookByName(name);
                loadBooks();
            }
        });

        loadBooks(); // initial load
    }

    public void loadBooks() {
        bookModel.setRowCount(0);

        for (Book b : owner.getBookStoreSystem().getInventory()) {
            bookModel.addRow(new Object[]{
                b.getName(),
                b.getPrice()
            });
        }
    }
}