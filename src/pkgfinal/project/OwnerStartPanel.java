package pkgfinal.project;

import javax.swing.*;
import java.awt.*;

public class OwnerStartPanel extends JPanel {

    public OwnerStartPanel() {
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton booksBtn = new JButton("Books");
        JButton customersBtn = new JButton("Customers");
        JButton logoutBtn = new JButton("Logout");

        add(booksBtn);
        add(customersBtn);
        add(logoutBtn);

        booksBtn.addActionListener(e -> switchPanel("books"));
        customersBtn.addActionListener(e -> switchPanel("customers"));
        logoutBtn.addActionListener(e -> logout());
    }

    private void switchPanel(String name) {
        Container parent = this.getParent();
        while (!(parent instanceof JPanel)) parent = parent.getParent();

        CardLayout cl = (CardLayout) parent.getLayout();
        cl.show(parent, name);
    }

    private void logout() {
    BookStoreSystem.getInstance().logOut();

    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

    JPanel main = (JPanel) frame.getContentPane().getComponent(0);
    CardLayout cl = (CardLayout) main.getLayout();

    cl.show(main, "card2");
    }
}