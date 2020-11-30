
import controller.PlannerHomeController;
import javax.swing.SwingUtilities;
import model.PlannerModel;
import view.PlannerHomeView;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              PlannerHomeView pv = new PlannerHomeView();
              PlannerModel pm = new PlannerModel("","");
              PlannerHomeController phc = new PlannerHomeController(pv,pm);
              phc.assegnaGestore();
              pv.setVisible(true);
            }
        });
    
 
    }
}
