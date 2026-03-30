package pkgfinal.project;

import javax.swing.*;
import java.awt.*;

public class CustomerDashboard extends JPanel {

    public CustomerDashboard() {
        setLayout(new BorderLayout());

        JPanel bookPanel = new JPanel(new BorderLayout());
        JLabel bookLabel = new JLabel("Available Books:");
        bookLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTable bookTable = new JTable(
            new Object[][]{},
            new String[]{"Title", "Author", "Price"}
        );
        bookTable.setRowHeight(20);
        bookTable.setFillsViewportHeight(true);

        JScrollPane bookScroll = new JScrollPane(bookTable);
        bookScroll.setPreferredSize(new Dimension(200, 200));

        bookPanel.add(bookLabel, BorderLayout.NORTH);
        bookPanel.add(bookScroll, BorderLayout.CENTER);
        bookPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));

        JPanel cartPanel = new JPanel(new BorderLayout());
        JLabel cartLabel = new JLabel("Your Cart:");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTable cartTable = new JTable(
            new Object[][]{},
            new String[]{"Title", "Qty", "Price"}
        );
        cartTable.setRowHeight(20);
        cartTable.setFillsViewportHeight(true);

        JScrollPane cartScroll = new JScrollPane(cartTable);
        cartScroll.setPreferredSize(new Dimension(200, 200));

        cartPanel.add(cartLabel, BorderLayout.NORTH);
        cartPanel.add(cartScroll, BorderLayout.CENTER);
        cartPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));

        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            bookPanel,
            cartPanel
        );

        splitPane.setResizeWeight(0.5); // equal resize
        splitPane.setDividerLocation(0.5); // start centered

        JButton addToCartBtn = new JButton("Add to Cart");
        JButton viewCartBtn = new JButton("View Cart");
        JButton checkoutBtn = new JButton("Checkout");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addToCartBtn);
        buttonPanel.add(viewCartBtn);
        buttonPanel.add(checkoutBtn);

        add(splitPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}