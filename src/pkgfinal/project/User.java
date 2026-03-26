/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project;

public class User {
    private String Username;
    private String Password;

    public User(String username, String password) {
        Username = username;
        Password = password;
    }

    public User() {

    }

    public String getUsername(){
        return Username;
    }
    public void setUsername(String username){
        this.Username = Username;
    }
    public String getPassword(){
        return Password;
    }
    public void setPassword(String password){
        this.Password = password;
    }
}
