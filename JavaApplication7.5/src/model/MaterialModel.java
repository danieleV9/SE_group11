/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.MaterialDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jenni
 */
public class MaterialModel {

    private String materialName;
    private MaterialDAO dao;

    public MaterialModel(String materialName) {
        this.materialName = materialName;
        this.dao = new MaterialDAO();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public List<MaterialModel> listMaterials() {
        return dao.getAllMaterials();
    }

    public boolean deleteMaterial(String materialName) {
        return dao.deleteMaterial(materialName);
    }

    public boolean insertMaterial(String materialName,int idattivita) {
        return dao.insertMaterial(materialName, idattivita);
    }

    @Override
    public String toString() {
        return ""+ materialName ;
    }

}
