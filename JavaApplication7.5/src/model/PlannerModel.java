/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.PlannerDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lyuba
 */
public class PlannerModel extends EmployeeModel {

    public PlannerModel(String username, String password) {
        super(username, password);
    }

    @Override
    public UserModel findUser(String username, String password, String role) throws Exception {
        PlannerDAO dao = new PlannerDAO();
        PlannerModel ad = (PlannerModel) dao.findUser(username, password, role);
        return ad;
    }

    @Override
    public EmployeeModel findUsername(String username) {
        PlannerDAO dao = new PlannerDAO();
        PlannerModel ad = (PlannerModel) dao.findUsername(username);
        return ad;
    }

    public List<PlannerModel> listPlanners() { //getsate
        PlannerDAO dao = new PlannerDAO();
        List<PlannerModel> list = new ArrayList<>();
        list = dao.listPlanners();
        return list;
    }

    @Override
    public boolean createUser(String username, String password) {
        PlannerDAO dao = new PlannerDAO();
        boolean ad = dao.createUser(username, password);
        return ad;
    }

    @Override
    public boolean deleteUser(String username) {
        PlannerDAO dao = new PlannerDAO();
        return dao.deleteUser(username);
    }

    @Override
    public boolean updateUserPassword(String username, String newpass) {
        PlannerDAO dao = new PlannerDAO();
        return dao.updateUserPassword(username, newpass);
    }

}
