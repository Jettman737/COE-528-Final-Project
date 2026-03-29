package pkgfinal.project;

import java.util.ArrayList;

/**
 * Represents the core system of the BookStore.
 * AF(inventory, customerList) = A retail system containing a collection of
 * books available for sale and a registry of authorized customers.
 * RI: inventory != null && customerList != null;
 */
public class BookStoreSystem {
    protected ArrayList<Book> inventory;
    protected ArrayList<Customer> customerList;
    protected User currentUser;

    /**
     * Constructor for BookStoreSystem.
     * Initializes the inventory and customer list from the FileManager.
     */
    public BookStoreSystem() {
        this.inventory = FileManager.loadBooks();
        this.customerList = FileManager.loadCustomers(this);
    }

    /**
     * Searches for a user with matching credentials.
     * @param username The entered username.
     * @param password The entered password.
     * @return true if a match is found, false otherwise.
     */
    public boolean login(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            this.currentUser = new Owner("admin", "admin", this);
            return true;
        }

        for (Customer c : customerList) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
                this.currentUser = c;
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a Book object to the inventory.
     * @param book The book to add.
     */
    public void addBook(Book book) {
        if (book != null) {
            inventory.add(book);
        }
    }

    /**
     * Removes a Book object from the inventory.
     * @param book The book to remove.
     */
    public void removeBook(Book book) {
        inventory.remove(book);
    }

    /**
     * Adds a Customer object to the registry.
     * @param customer The customer to add.
     */
    public void addCustomer(Customer customer) {
        if (customer != null) {
            customerList.add(customer);
        }
    }

    /**
     * Removes a Customer object from the registry.
     * @param customer The customer to remove.
     */
    public void removeCustomer(Customer customer) {
        customerList.remove(customer);
    }

    /**
     * Clears the current user session.
     */
    public void logOut() {
        this.currentUser = null;
    }

    /**
     * Launches the LoginScreen GUI.
     */
    public void startApp() {
        LoginScreen login = new LoginScreen(this);
        login.setVisible(true);
    }

    /**
     * Returns the list of books in the store.
     * @return ArrayList of Book objects.
     */
    public ArrayList<Book> getInventory() {
        return inventory;
    }

    /**
     * Returns the list of registered customers.
     * @return ArrayList of Customer objects.
     */
    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * Returns the currently logged-in user.
     * @return User object.
     */
    public User getCurrentUser() {
        return currentUser;
    }
}
