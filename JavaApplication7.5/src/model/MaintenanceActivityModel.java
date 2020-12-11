package model;
import dao.ActivityDao;
import java.util.List;
import model.Materiali;
import model.Procedure;

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
    private String area;
    private String Fabbrica;

    public MaintenanceActivityModel(int WeekNum, boolean interuptible, int id_Activity, String description, 
                                String tipology, String type, int EstimatedTime, String workspaceNotes, 
                                Procedure procedura, List<Materiali> materiali, String area,String fabbrica) {
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
        this.Fabbrica=fabbrica;
    }


    public MaintenanceActivityModel(int WeekNum, int id_Activity,String tipology, String description,String workspaceNotes,String area,int estimatedTime,String fabbrica) {
        this.WeekNum = WeekNum;
        this.id_Activity = id_Activity;
        this.description = description;
        this.tipology = tipology;
        this.workspaceNotes=workspaceNotes;
        this.area=area;
        this.EstimatedTime=estimatedTime;
        this.Fabbrica=fabbrica;
        
    }

    public MaintenanceActivityModel() {
    }

    public String getFabbrica() {
        return Fabbrica;
    }

    public void setFabbrica(String Fabbrica) {
        this.Fabbrica = Fabbrica;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean deleteActivity(int id) {
      ActivityDao dao = new ActivityDao();
      return dao.deleteActivity(id);
    }

    public MaintenanceActivityModel viewActivity(int id) {
        ActivityDao dao = new ActivityDao();
        return  dao.viewActivity(id);
    }

    public void aggiornaNote(String note, int id) {
        ActivityDao dao = new ActivityDao();
        dao.aggiornaNote(note, id);
    }

    public List<MaintenanceActivityModel> getAllActivity() {
      ActivityDao dao = new ActivityDao();
      return dao.getAllActivity();
    }
    
    public List<MaintenanceActivityModel> getAllActivity(int numWeek) {
      ActivityDao dao = new ActivityDao();
      return dao.getAllActivity(numWeek);
    }

    public boolean insertActivity(int numberWeek, String workNotes, String type, String factory, String tipology, int time, String description, String area) {
        ActivityDao dao = new ActivityDao();
        boolean ad = dao.insertActivity(numberWeek, workNotes, type, factory, tipology, time, description, area);
        return ad;
    }

    @Override
    public String toString() {
        return "MaintenanceActivityModel{" + "WeekNum=" + WeekNum + ", interuptible=" + interuptible + ", id_Activity=" + id_Activity + ", description=" + description + ", tipology=" + tipology + ", type=" + type + ", EstimatedTime=" + EstimatedTime + ", workspaceNotes=" + workspaceNotes + ", procedura=" + procedura + ", materiali=" + materiali + ", area=" + area + ", Fabbrica=" + Fabbrica + '}';
    }

    public String findProcedura(int id){
        ActivityDao dao = new ActivityDao();
        return dao.findProcedura(id);
    }
    
    public boolean assignedActivity(int id){
        ActivityDao dao = new ActivityDao();
        return dao.assignedActivity(id);
    }
    
    public boolean assignNewActivity(int id, String username,String data){
        ActivityDao dao = new ActivityDao();
        return dao.assignNewActivity(id,username,data);
    }
}
