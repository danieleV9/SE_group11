/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AdminDAO;



/**
 *
 * @author dava9
 */
public class AdminModel {
    private String username;
    private String password;

    public AdminModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public AdminModel findAdmin(String username, String password, String role){
        AdminDAO dao= new AdminDAO();
        AdminModel ad= dao.findAdmin(username, password, role);
        return ad;
    }
    
  

    

}
