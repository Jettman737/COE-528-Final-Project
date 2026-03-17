/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project;

import java.util.ArrayList;


/**
 *
 * @author Jettman737
 */
public class Customer extends User {
    private int points;
    private String status;
    
    public void viewBooks(){};
    
    public void buyBooks(ArrayList<Book> cart){};
    
    public double viewCartCost(ArrayList<Book> cart){
        double cost = 0;
        for (Book b : cart) {
            cost += b.getPrice();
        }
        return cost;
    };
    
    public void earnPoints(double moneySpent){};
    public void viewPointsAndStatus(){};
}
