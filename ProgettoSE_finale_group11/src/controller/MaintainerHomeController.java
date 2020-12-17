/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.MaintainerModel;
import model.MaintenanceActivityModel;
import view.MaintainerHomeView;

/**
 *
 * @author lyuba
 */
public class MaintainerHomeController {
    private MaintainerHomeView view;
    private MaintainerModel model;
    
    public MaintainerHomeController(MaintainerHomeView view , MaintainerModel model){
        this.view= view;
        this.model=model;
        populateTable(model.getUsername());
    }
    
    public void populateTable(String username){
        List<MaintenanceActivityModel> list= new ArrayList<>();
        list=model.getActivities(username);
        String data="";
        for (int i=0; i<list.size();i++){
            MaintenanceActivityModel activity = list.get(i);
            String descrizione = activity.getDescription();
            String fabbrica = activity.getFabbrica()+ activity.getArea();
            int estimatedTime = activity.getEstimatedTime();
            data=activity.getData();
            String[] row={"New assigned activity: "+ descrizione+ " in: "+ fabbrica + " estimated time : "+ estimatedTime+" in data: "+data};
            view.getModelTab().addRow(row);
        }
    }
}

