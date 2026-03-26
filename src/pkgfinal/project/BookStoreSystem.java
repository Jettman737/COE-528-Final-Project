package pkgfinal.project;

import java.util.ArrayList; // Essential import!

/**
 * Represents the core system of the BookStore.
 * AF(inventory, customerList) = A retail system containing a collection of
 * books available for sale and a registry of authorized customers.
 * RI: inventory != null && customerList != null;
 */
public class BookStoreSystem {
    public ArrayList<Book> inventory = new ArrayList<>();
    public ArrayList<Customer> customerList = new ArrayList<>();
    public User currentUser;

    /**
     * Checks if the credentials match the current user.
     */
    public boolean login(String username, String password) {
        if (currentUser == null) return false;
        return currentUser.getUsername().equalsIgnoreCase(username) &&
                currentUser.getPassword().equals(password);
    }

    /**
     * Adds a Book object to the inventory.
     * @param book The book to add.
     */
    public void addBook(Book book) {
        inventory.add(book);
    }

    /**
     * Removes a Book object from the inventory.
     * @param book The book to remove.
     */
    public void removeBook(Book book) {
        inventory.remove(book);
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customerList.remove(customer);
    }

    public void logOut() {
        this.currentUser = null;
    }

    public void startApp() {
        // Logic to launch the LoginScreen
    }
}
