package model;

import dao.ActivityDAO1;
import java.util.List;

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
    private ProcedureModel procedura;
    private List<MaterialModel> materiali;
    private String area;
    private String Fabbrica;
    private ActivityDAO1 dao;

    public MaintenanceActivityModel(int WeekNum, boolean interuptible, int id_Activity, String description,
            String tipology, String type, int EstimatedTime, String workspaceNotes,
            ProcedureModel procedura, List<MaterialModel> materiali, String area, String fabbrica) {
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
        this.area = area;
        this.Fabbrica = fabbrica;
        this.dao = new ActivityDAO1();
    }

    public MaintenanceActivityModel(int WeekNum, int id_Activity, String tipology, String description, String workspaceNotes, String area, int estimatedTime, String fabbrica, ProcedureModel proc) {
        this.WeekNum = WeekNum;
        this.id_Activity = id_Activity;
        this.description = description;
        this.tipology = tipology;
        this.workspaceNotes = workspaceNotes;
        this.area = area;
        this.EstimatedTime = estimatedTime;
        this.Fabbrica = fabbrica;
        this.procedura = proc;
        this.dao = new ActivityDAO1();
    }

    public MaintenanceActivityModel() {
        this.dao = new ActivityDAO1();
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

    public ProcedureModel getProcedura() {
        return procedura;
    }

    public void setProcedura(ProcedureModel procedura) {
        this.procedura = procedura;
    }

    /*public List<Materiali> getMateriali() {
        return materiali;
    }

    public void setMateriali(List<Materiali> materiali) {
        this.materiali = materiali;
    }*/
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean deleteActivity(int id) {
        return dao.deleteActivity(id);
    }

    public MaintenanceActivityModel viewActivity(int id) {
        return dao.viewActivity(id);
    }

    public boolean aggiornaNote(String note, int id) {
        return dao.aggiornaNote(note, id);
    }

    public List<MaintenanceActivityModel> getAllActivity() {
        return dao.getAllActivity();
    }

    public List<MaintenanceActivityModel> getAllActivity(int numWeek) {
        return dao.getAllActivity(numWeek);
    }

    public int insertActivity(int numberWeek, String workNotes, String type, String factory, String tipology, int time, String description, String area, boolean interruptible, ProcedureModel proc) {
        int ad = dao.insertActivity(numberWeek, workNotes, type, factory, tipology, time, description, area, interruptible, proc);
        return ad;
    }

    public boolean insertActivity1(int numberWeek, String workNotes, String type, String factory, String tipology, int time, String description, String area, boolean interruptible, ProcedureModel proc) {
        boolean ad = dao.insertActivity1(numberWeek, workNotes, type, factory, tipology, time, description, area, interruptible, proc);
        return ad;
    }

    @Override
    public String toString() {
        return "MaintenanceActivityModel{" + "WeekNum=" + WeekNum + ", interuptible=" + interuptible + ", id_Activity=" + id_Activity + ", description=" + description + ", tipology=" + tipology + ", type=" + type + ", EstimatedTime=" + EstimatedTime + ", workspaceNotes=" + workspaceNotes + ", procedura=" + procedura + ", materiali=" + materiali + ", area=" + area + ", Fabbrica=" + Fabbrica + '}';
    }

    public String findProcedura(int id) {
        return dao.findProcedura(id);
    }

    public boolean assignedActivity(int id) {
        return dao.assignedActivity(id);
    }

    public boolean assignNewActivity(int id, String username, String data) {
        return dao.assignNewActivity(id, username, data);
    }

}
