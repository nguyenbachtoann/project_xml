/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.dto;

import java.io.Serializable;

/**
 *
 * @author bachtoan
 */
public class TopBrandDTO implements Serializable{
    private String laptopBrand, laptopPoint;

    public TopBrandDTO() {
    }

    public TopBrandDTO(String laptopBrand, String laptopPoint) {
        this.laptopBrand = laptopBrand;
        this.laptopPoint = laptopPoint;
    }

    public String getLaptopBrand() {
        return laptopBrand;
    }

    public void setLaptopBrand(String laptopBrand) {
        this.laptopBrand = laptopBrand;
    }

    public String getLaptopPoint() {
        return laptopPoint;
    }

    public void setLaptopPoint(String laptopPoint) {
        this.laptopPoint = laptopPoint;
    }
    
    
}
