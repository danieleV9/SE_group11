package model;
import java.util.List;
import model.Materiali;
import model.Procedure;
import model.Site;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HP
 */
public class MaintenanceActivityModel {
    private int WeekNum;
    private boolean interuptible;
    private int id_Activity;
    private String description;
    private String tipology;
    private String type;
    private int EstimatedTime;
    private String workspaceNotes;
    private Procedure procedura;
    private List<Materiali> materiali;
    private Site area;

    public MaintenanceActivityModel(int WeekNum, boolean interuptible, int id_Activity, String description, 
                                String tipology, String type, int EstimatedTime, String workspaceNotes, 
                                Procedure procedura, List<Materiali> materiali, Site area) {
        this.WeekNum = WeekNum;
        this.interuptible = interuptible;
        this.id_Activity = id_Activity;
        this.description = description;
        this.tipology = tipology;
        this.type = type;
        this.EstimatedTime = EstimatedTime;
        this.workspaceNotes = workspaceNotes;
        this.procedura = procedura;
        this.materiali = materiali;
        this.area=area;
    }


    public MaintenanceActivityModel(int WeekNum, int id_Activity, String description, String tipology, String wokspaceNotes) {
        this.WeekNum = WeekNum;
        this.id_Activity = id_Activity;
        this.description = description;
        this.tipology = tipology;
        this.workspaceNotes=workspaceNotes;
    }





    public int getWeekNum() {
        return WeekNum;
    }

    public void setWeekNum(int WeekNum) {
        this.WeekNum = WeekNum;
    }

    public boolean isInteruptible() {
        return interuptible;
    }

    public void setInteruptible(boolean interuptible) {
        this.interuptible = interuptible;
    }

    public int getId_Activity() {
        return id_Activity;
    }

    public void setId_Activity(int id_Activity) {
        this.id_Activity = id_Activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTipology() {
        return tipology;
    }

    public void setTipology(String tipology) {
        this.tipology = tipology;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEstimatedTime() {
        return EstimatedTime;
    }

    public void setEstimatedTime(int EstimatedTime) {
        this.EstimatedTime = EstimatedTime;
    }

    public String getWorkspaceNotes() {
        return workspaceNotes;
    }

    public void setWorkspaceNotes(String workspaceNotes) {
        this.workspaceNotes = workspaceNotes;
    }

    public Procedure getProcedura() {
        return procedura;
    }

    public void setProcedura(Procedure procedura) {
        this.procedura = procedura;
    }

    public List<Materiali> getMateriali() {
        return materiali;
    }

    public void setMateriali(List<Materiali> materiali) {
        this.materiali = materiali;
    }

    public Site getArea() {
        return area;
    }

    public void setArea(Site area) {
        this.area = area;
    }
    
    
}
