package pkgfinal.project;

/**
 * Represents an Owner in the BookStoreSystem.
 * AF(bookStoreSystem) = An administrator who manages the inventory and
 * user base of the store referenced by 'bookStoreSystem'.
 * RI: bookStoreSystem != null;
 */
public class Owner extends User {

    private BookStoreSystem bookStoreSystem;

    /**
     * Constructor for Owner.
     * Links the Owner to a specific BookStoreSystem and sets credentials.
     * @param username The owner's login name.
     * @param password The owner's password.
     * @param bss The BookStoreSystem this owner will manage.
     */
    public Owner(String username, String password, BookStoreSystem bss) {
        super(username, password); // Passes info to the User class
        this.bookStoreSystem = bss;
        checkRep(); // Validates that the store is not null
    }

    /**
     * Asserts that the Rep Invariant holds.
     */
    private void checkRep() {
        if (bookStoreSystem == null) {
            throw new RuntimeException("Rep Invariant Violated: Owner must be linked to a BookStoreSystem.");
        }
    }

    public void manageBooks() {
        checkRep();
        // Logic to switch to OwnerBooksScreen GUI
    }

    /**
     * Adds a new book to the system.
     */
    public void addBooks(String n, double p) {
        checkRep();
        Book newBook = new Book();
        newBook.setName(n);
        newBook.setPrice(p);
        bookStoreSystem.addBook(newBook);
        System.out.println(newBook.getName() + " has been added at a price of $" + newBook.getPrice());
    }

    /**
     * Removes a book from the system.
     */
    public void removeBooks(Book book) {
        checkRep();
        bookStoreSystem.removeBook(book);
        System.out.println(book.getName() + " has been removed");
    }

    public void manageCustomer() {
        checkRep();
        // Logic to switch to OwnerCustomerScreen GUI
    }

    /**
     * Adds a new customer to the system.
     */
    public void addCustomer(String username, String password) {
        checkRep();
        Customer newCustomer = new Customer(username, password);
        bookStoreSystem.addCustomer(newCustomer);
        System.out.println(newCustomer.getUsername() + " has been added");
    }

    /**
     * Removes a customer from the system.
     */
    public void removeCustomer(Customer c) {
        checkRep();
        bookStoreSystem.removeCustomer(c);
        System.out.println(c.getUsername() + " has been removed");
    }
}
