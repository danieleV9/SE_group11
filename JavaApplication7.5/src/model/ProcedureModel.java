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
    String path;

    public ProcedureModel(String nomeProc,String path) {
        this.path=path;
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
    
    public boolean createProcedure(String nomeprocedura,String path){
      ProcedureDao dao = new ProcedureDao();
      return dao.createProcedure(nomeprocedura,path);  
    }
    
    public boolean deleteProcedure(String nomeprocedura){
      ProcedureDao dao = new ProcedureDao();
      return dao.deleteProcedure(nomeprocedura);    
    }
     



    public String getNomeProc() {
        return nomeProc;
    }
    

    public String getPath() {
        return path;
    }


    public String getPath(String name){
      ProcedureDao dao = new ProcedureDao();
      return dao.getPath(name);     
    }
    
    public boolean proceduraExists(String name){
       ProcedureDao dao = new ProcedureDao();
       return dao.proceduraExists(name);
   }
}
