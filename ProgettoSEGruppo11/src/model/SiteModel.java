/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HP
 */
public class SiteModel {
    private String fabbrica;
    private String area;

    public SiteModel(String fabbrica, String area) {
        this.fabbrica = fabbrica;
        this.area = area;
    }

    @Override
    public String toString() {
        return   fabbrica+" - "+area;
    }
    
    
}
