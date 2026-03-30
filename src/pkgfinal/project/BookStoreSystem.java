package pkgfinal.project;

import java.util.ArrayList;

public class BookStoreSystem {

    private static BookStoreSystem instance; // 🔥 singleton

    private Owner owner;
    protected ArrayList<Book> inventory;
    protected ArrayList<Customer> customerList;
    protected User currentUser;

    // PRIVATE constructor
    private BookStoreSystem() {
        this.inventory = FileManager.loadBooks();
        this.customerList = FileManager.loadCustomers(this);
        this.owner = new Owner("admin", "admin", this);
    }

    // GLOBAL ACCESS POINT
    public static BookStoreSystem getInstance() {
        if (instance == null) {
            instance = new BookStoreSystem();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            this.currentUser = owner;
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

    public void addBook(Book book) {
        if (book != null) inventory.add(book);
        FileManager.saveAllData(inventory, customerList);
    }

    public void removeBook(Book book) {
        inventory.remove(book);
        FileManager.saveAllData(inventory, customerList);
    }

    public void addCustomer(Customer customer) {
        if (customer != null) customerList.add(customer);
        FileManager.saveAllData(inventory, customerList);
    }

    public void removeCustomer(Customer customer) {
        customerList.remove(customer);
        FileManager.saveAllData(inventory, customerList);
    }

    public void logOut() {
        this.currentUser = null;
    }

    public ArrayList<Book> getInventory() {
        return inventory;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}