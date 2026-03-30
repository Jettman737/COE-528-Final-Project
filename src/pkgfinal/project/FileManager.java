package pkgfinal.project;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the persistence of Book and Customer data to text files.
 * AF() = A utility that maps the application's internal data structures
 * to physical storage in books.txt and customers.txt.
 * RI: None (Utility class with static methods).
 */
public class FileManager {
    private static final String BOOK_FILE = "books.txt";
    private static final String CUSTOMER_FILE = "customers.txt";

    /**
     * Overwrites books.txt and customers.txt with the current data.
     * This should be called when the [x] button is clicked.
     */
    public static void saveAllData(ArrayList<Book> books, ArrayList<Customer> customers) {
        // 1. Save Books (Overwrites existing file)
        try (PrintWriter out = new PrintWriter(new FileWriter(BOOK_FILE, false))) {
            for (Book b : books) {
                out.println(b.getName() + "," + b.getPrice());
            }
        } catch (IOException e) {
            System.err.println("Error: Could not save to " + BOOK_FILE);
        }

        // 2. Save Customers (Overwrites existing file)
        try (PrintWriter out = new PrintWriter(new FileWriter(CUSTOMER_FILE, false))) {
            for (Customer c : customers) {
                // Format: Username,Password,Points
                out.println(c.getUsername() + "," + c.getPassword() + "," + c.getPoints());
            }
        } catch (IOException e) {
            System.err.println("Error: Could not save to " + CUSTOMER_FILE);
        }
    }

    /**
     * Loads books from books.txt and returns them as an ArrayList.
     */
    public static ArrayList<Book> loadBooks() {
        ArrayList<Book> list = new ArrayList<>();
        File file = new File(BOOK_FILE);

        if (!file.exists()) return list;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    list.add(new Book(name,price));
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading books from file.");
        }
        return list;
    }

    /**
     * Loads customers from customers.txt and returns them as an ArrayList.
     * Note: It sets the State (Silver/Gold) based on the loaded points.
     */
    public static ArrayList<Customer> loadCustomers(BookStoreSystem store) {
        ArrayList<Customer> list = new ArrayList<>();
        File file = new File(CUSTOMER_FILE);

        if (!file.exists()) return list;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String user = parts[0];
                    String pass = parts[1];
                    int points = Integer.parseInt(parts[2]);

                    Customer c = new Customer(user, pass, store);
                    c.addPoints(points);

                    // Ensure the State Pattern reflects the loaded points
                    if (points >= 1000) {
                        c.setStatus(new GoldState());
                    } else {
                        c.setStatus(new SilverState());
                    }

                    list.add(c);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading customers from file.");
        }
        return list;
    }
}
