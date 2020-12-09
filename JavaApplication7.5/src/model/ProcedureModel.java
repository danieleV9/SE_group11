/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ProcedureDao;
import java.util.List;

/**
 *
 * @author HP
 */
public class ProcedureModel {
    String nomeProc;
    int id;

    public ProcedureModel(int id,String nomeProc) {
        this.id=id;
        this.nomeProc = nomeProc;
    }

    public ProcedureModel() {
    }
    
    
    public List<ProcedureModel> getAllProcedure(){
        ProcedureDao dao = new ProcedureDao();
        return dao.getAllProcedures();
    }
    public List<SkillModel> getProcedureSkill(String nomeprocedura) {     
        ProcedureDao dao = new ProcedureDao();
        return dao.getProcedureSkill(nomeprocedura);
    }
     public boolean addCompetence(String nomeprocedura, int id){
        ProcedureDao dao = new ProcedureDao();
        return dao.addCompetence(nomeprocedura,id);
    }
     
     
    public boolean removeCompetence(String nomeprocedura, int id){
       ProcedureDao dao = new ProcedureDao();
       return dao.removeCompetence(nomeprocedura,id);
    }
    
    public boolean createProcedure(String nomeprocedura){
      ProcedureDao dao = new ProcedureDao();
      return dao.createProcedure(nomeprocedura);  
    }
    
    public boolean deleteProcedure(String nomeprocedura){
      ProcedureDao dao = new ProcedureDao();
      return dao.deleteProcedure(nomeprocedura);    
    }
     
    public int getId() {
        return id;
    }

    public String getNomeProc() {
        return nomeProc;
    }
    
    
}
