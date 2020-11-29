
import controller.ActivityInfoViewController;
import controller.PlannerActivityViewController;
import dao.ActivityDao;
import javax.swing.SwingUtilities;
import view.ActivityInfoView;
import view.PlannerActivityView;


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
            ActivityDao dao = new ActivityDao();
            PlannerActivityView listView = new PlannerActivityView();       
            listView.setVisible(true);
            PlannerActivityViewController controller1 = new PlannerActivityViewController(dao,listView);
            controller1.AssegnaGestori();
           
            }
        });
    
    
    }
}
