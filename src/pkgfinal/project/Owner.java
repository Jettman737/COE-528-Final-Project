package pkgfinal.project;

public class Owner extends User {

    private BookStoreSystem bookStoreSystem;

    public Owner(String username, String password, BookStoreSystem bss) {
        super(username, password);
        this.bookStoreSystem = bss;
    }

    public BookStoreSystem getBookStoreSystem() {
        return bookStoreSystem;
    }

    public void addBooks(String name, double price) {
        Book newBook = new Book();
        newBook.setName(name);
        newBook.setPrice(price);

        bookStoreSystem.addBook(newBook);

        System.out.println(name + " added.");
    }

    public void removeBookByName(String name) {
        for (Book b : bookStoreSystem.getInventory()) {
            if (b.getName().equals(name)) {
                bookStoreSystem.removeBook(b);
                System.out.println(name + " removed.");
                return;
            }
        }
    }
}