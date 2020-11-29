/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.MaintainerDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lyuba
 */
public class MaintainerModel {
    private String username;
    private String password;

    public MaintainerModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public MaintainerModel findMaintainer(String username, String password, String role) throws Exception{
        MaintainerDAO dao = new MaintainerDAO();
        MaintainerModel ad = dao.findMaintainer(username, password, role);
        return ad;
    }
    
    public List<MaintainerModel> listMaintainers(){ //getsate
        MaintainerDAO dao = new MaintainerDAO();
        List<MaintainerModel> list = new ArrayList<>();
        list = dao.listMaintainers();
        return list;
    }
    
}
