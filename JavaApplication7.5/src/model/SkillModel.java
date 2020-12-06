/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.SkillDAO;
import java.util.ArrayList;
import java.util.List;

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

    public boolean deleteSkill(int idSkill) {
        SkillDAO dao = new SkillDAO();
        return dao.deleteSkill(idSkill);
    }

    public boolean modifySkill(int idSkill, String descrizione) {
        SkillDAO dao = new SkillDAO();
        return dao.modifySkill(idSkill, descrizione);
    }

    public void insertSkill(String description) {
        SkillDAO ad = new SkillDAO();
        ad.insertSkill(description);
    }

}
