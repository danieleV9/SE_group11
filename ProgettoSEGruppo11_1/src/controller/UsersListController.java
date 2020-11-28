/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.AdminModel;
import view.UsersListView;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 *
 * @author dava9
 */
public class UsersListController {
    private UsersListView view;
    private AdminModel model;

    public UsersListController(UsersListView view, AdminModel model) {
        this.view = view;
        this.model = model;

    }    
    
    
    
}
