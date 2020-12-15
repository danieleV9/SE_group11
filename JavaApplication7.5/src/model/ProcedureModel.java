/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ProcedureDao;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author HP
 */
public class ProcedureModel {

    private String nomeProc;
    private String path;
    private ProcedureDao dao;

    public ProcedureModel(String nomeProc, String path) {
        this.path = path;
        this.nomeProc = nomeProc;
        dao = new ProcedureDao();
    }

    public List<ProcedureModel> getAllProcedure() {
        //ProcedureDao dao = new ProcedureDao();
        return dao.getAllProcedures();
    }

    public List<SkillModel> getProcedureSkill(String nomeprocedura) {
        //ProcedureDao dao = new ProcedureDao();
        return dao.getProcedureSkill(nomeprocedura);
    }

    public boolean addCompetence(String nomeprocedura, int id) {
        return dao.addCompetence(nomeprocedura, id);
    }

    public boolean removeCompetence(String nomeprocedura, int id) {
        return dao.removeCompetence(nomeprocedura, id);
    }

    public boolean createProcedure(String nomeprocedura, String path) {
        return dao.createProcedure(nomeprocedura, path);
    }

    public boolean deleteProcedure(String nomeprocedura) {
        return dao.deleteProcedure(nomeprocedura);
    }

    public String getNomeProc() {
        return nomeProc;
    }

    public String getPath() {
        return path;
    }

    public String getPath(String name) {
        return dao.getPath(name);
    }

    public boolean proceduraExists(String name) {
        return dao.proceduraExists(name);
    }
    
    public boolean hasCompetence(String nomeprocedura, int idcompetenza){
        return dao.hasCompetence(nomeprocedura, idcompetenza);
    }


}
