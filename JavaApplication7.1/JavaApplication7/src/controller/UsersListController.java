/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.AdminModel;
import view.UsersListView;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.List;
import model.MaintainerModel;
import model.PlannerModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dava9
 */
public class UsersListController {
    private UsersListView view;
    private AdminModel model;
    private PlannerModel plmodel = new PlannerModel("","");
    private MaintainerModel mamodel = new MaintainerModel("","");

    public UsersListController(UsersListView view, AdminModel model) {
        this.view = view;
        this.model = model;
        this.view.addSelectedRowListener(new ClickOnTableListener());
        this.view.addSelectedRowListener1(new ClickOnTableListener1());
        this.view.addNewUserListener(new NewUserListener());
        this.view.addModifyListener(new NewUserListener());
        this.view.addDeleteListener(new NewUserListener());
    }    
    
    public void populateTables(){
        List<PlannerModel> listpl = plmodel.listPlanners();
        List<MaintainerModel> listma = mamodel.listMaintainers();
        for(int i=0;i<listpl.size();i++){
            plmodel = listpl.get(i);
            String array[] = {plmodel.getUsername(),plmodel.getPassword()};
            view.getTable().addRow(array);
        }
        
        for(int j=0;j<listma.size();j++){
            mamodel = listma.get(j);
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("SHA-256"); //non mostro la pass in chiaro    
                String pass= mamodel.getPassword();
                md.update(pass.getBytes(StandardCharsets.UTF_8));
                byte[] digest=md.digest();
                String hex=String.format("%064x", new BigInteger(1,digest));
                System.out.println(hex);
                String array[] = {mamodel.getUsername(),hex};
                view.getTable1().addRow(array);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsersListController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    
    public class ClickOnTableListener implements MouseListener {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("Ho cliccato sulla tabella planner");
            int r = view.getSelectedRow();
            System.out.println(r);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public class ClickOnTableListener1 implements MouseListener {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("Ho cliccato sulla tabella maintainer");
            int r = view.getSelectedRow1();
            System.out.println(r);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

 public class NewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("ciao");
        }
    }
 
  public class  ModifyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("ciao");
        }
    }
  
   public class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("ciao");
        }
    }
 

}
