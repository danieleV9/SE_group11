/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.SkillDAO;
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

    public SkillModel(int idSkill, String description) {
        this.idSkill = idSkill;
        this.description = description;
    }
        
    public SkillModel() {
    }

    public SkillModel(String description) {
        this.description=description;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public String getDescription() {
        return description;
    }

    public List<SkillModel> listSkills() {
        SkillDAO dao = new SkillDAO();
        List<SkillModel> list = new ArrayList<>();
        list = dao.listSkills();
        return list;
    }
    
    public List<SkillModel> listSkillsMA(String username) {
        SkillDAO dao = new SkillDAO();
        List<SkillModel> list = new ArrayList<>();
        return dao.listSkillsMA(username); 
    }

    public boolean deleteSkill(int idSkill) {
        SkillDAO dao = new SkillDAO();
        return dao.deleteSkill(idSkill);
    }
    

    public boolean modifySkill(int idSkill, String descrizione) {
        SkillDAO dao = new SkillDAO();
        return dao.modifySkill(idSkill, descrizione);
    }

    public void insertSkill(String description) {
        SkillDAO dao = new SkillDAO();
        dao.insertSkill(description);
    }
    
    public SkillModel findSkill(int id){ 
         SkillDAO dao = new SkillDAO();
         return dao.findSkill(id);
    }
    public SkillModel findSkill(String description){ 
        SkillDAO dao = new SkillDAO();
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
    
    

}
