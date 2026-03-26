/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project;
/**
 *
 * @author Jettman737
 */
import java.util.ArrayList;

public class BookStoreSystem {
    public ArrayList<Book> inventory;
    public ArrayList<Customer> customerList;
    public User currentUser;
    
    public boolean login(String username, String password){
        return currentUser.getUsername().equalsIgnoreCase(username) && currentUser.getPassword().equals(password);
    }
    public void addBook(Book book) {
        inventory.add(book);
    }
    public void removeBook(Book book) {
        inventory.remove(book);
    }
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }
    public void removeCustomer(Customer customer) {
        customerList.remove(customer);
    }
    public void logOut(){};
    
    public void startApp(){};
}
