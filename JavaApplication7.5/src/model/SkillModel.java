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

    public SkillModel(int idSkill, String description) {
        this.idSkill = idSkill;
        this.description = description;
    }

    public SkillModel(String description) {
        this.description = description;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public String getDescription() {
        return description;
    }

    public List<SkillModel> listSkills() {
        List<SkillModel> list = new ArrayList<>();
        list = SkillDAO.listSkills();
        return list;
    }

    public List<SkillModel> listSkillsMA(String username) {
        List<SkillModel> list = new ArrayList<>();
        return SkillDAO.listSkillsMA(username);
    }

    public boolean deleteSkill(int idSkill) {
        return SkillDAO.deleteSkill(idSkill);
    }

    public boolean modifySkill(int idSkill, String descrizione) {
        return SkillDAO.modifySkill(idSkill, descrizione);
    }

    public boolean insertSkill(String description) {
        return SkillDAO.insertSkill(description);
    }

    public SkillModel findSkill(int id) {
        return SkillDAO.findSkill(id);
    }

    public SkillModel findSkill(String description) {
        return SkillDAO.findSkill(description);
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

    public Connection getConnection() {
        return SkillDAO.getConnection();
    }

    public void closeConnection() {
        SkillDAO.closeConnection();
    }

}
