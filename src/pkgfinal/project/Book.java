/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project;

/**
 *
 * @author Jettman737
 */
public class Book {
    
    private String name;
    private double price;
    
    public Book(String n, double p){
        name = n;
        price = p;
    }
    
    public String getName(){
    return name;
    }
    
    public double getPrice(){
    return price;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public void setPrice(double p){
        price = p;
    }
}
