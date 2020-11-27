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
    
    public AdminModel findAdmin(String username, String password, String role) throws Exception{
        AdminDAO dao = new AdminDAO();
        AdminModel ad = dao.findAdmin(username, password, role);
        return ad;
    }
    

    
    /*
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    //Metodi che accedono a risorse nel database
    
    public void createUser(String username, String password, String role){
        
        if(username.equals("") || password.equals("")){
            String error1 = "Username and password fields cannot be empty";
        }
        
        if(role.equals("Planner")){
            try{
                con = ConnectionDatabase.getConnection();
                String query = "insert into pianificatore ('usernamepl','passwordpl') values ('?','?')";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.execute();
                    
            }catch(Exception ex){
                System.out.println(""+ex);
            }
        }
        else if(role.equals("Maintainer")){
            try{
                con = ConnectionDatabase.getConnection();
                String query = "insert into manutentore ('usernamema','passwordma') values ('?','?')";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.execute();
                    
            }catch(Exception ex){
                System.out.println(""+ex);
            }
        }
        else{
            String error2 = ("Role not defined");
        }
        
    }*/
}
