/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.SkillDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jenni
 */
public class SkillModel {

    private int idSkill;
    private String description;
    private SkillDAO dao;

    public SkillModel(int idSkill, String description) {
        this.idSkill = idSkill;
        this.description = description;
        dao = new SkillDAO();
    }

    /*public SkillModel(String description) {
        this.description = description;
        dao = new SkillDAO();
    }*/

    public int getIdSkill() {
        return idSkill;
    }

    public String getDescription() {
        return description;
    }

    public List<SkillModel> listSkills() {
        List<SkillModel> list = new ArrayList<>();
        list = dao.listSkills();
        return list;
    }

    public List<SkillModel> listSkillsMA(String username) {
        
        List<SkillModel> list = new ArrayList<>();
        list=dao.listSkillsMA(username);
        return list;
    }

    public boolean deleteSkill(int idSkill) {
        
        return dao.deleteSkill(idSkill);
    }

    public boolean modifySkill(int idSkill, String descrizione) {
        
        return dao.modifySkill(idSkill, descrizione);
    }

    public boolean insertSkill(String description) {
        
        return dao.insertSkill(description);
    }

    public SkillModel findSkill(int id) {
        
        return dao.findSkill(id);
    }

    public SkillModel findSkill(String description) {
        
        return dao.findSkill(description);
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SkillModel other = (SkillModel) obj;
        if (this.idSkill != other.idSkill) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    public Connection getDaoConnection() {
        return dao.getConn();
    }

}
