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

    private PlannerDAO dao;

    public PlannerModel(String username, String password) {
        super(username, password);
        dao = new PlannerDAO();
    }

    @Override
    public UserModel findUser(String username, String password) throws Exception {
        PlannerModel ad = (PlannerModel) dao.findUser(username, password);
        return ad;
    }

    @Override
    public EmployeeModel findUsername(String username) {
        PlannerModel ad = (PlannerModel) dao.findUsername(username);
        return ad;
    }

    @Override
    public boolean createUser(String username, String password) {
        boolean ad = dao.createUser(username, password);
        return ad;
    }

    @Override
    public boolean deleteUser(String username) {
        return dao.deleteUser(username);
    }

    @Override
    public boolean updateUserPassword(String username, String newpass) {
        return dao.updateUserPassword(username, newpass);
    }

    @Override
    public <PlannerModel extends EmployeeModel> List<PlannerModel> listUsers() {
        List<PlannerModel> list = new ArrayList<>();
        list = (List<PlannerModel>) dao.listPlanners();
        return list;
    }

    @Override
    public String toString() {
        return "PlannerModel{" + super.toString() + '}';
    }
}
