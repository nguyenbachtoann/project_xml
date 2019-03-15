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
    private String laptopTrendBrand, laptopTrendReviews,laptopTrendDesign, laptopTrendSW, laptopTrendInnovation, laptopTrendIVS, laptopTrendOverall;

    public TopBrandDTO() {
    }

    public TopBrandDTO(String laptopTrendBrand, String laptopTrendReviews, String laptopTrendDesign, String laptopTrendSW, String laptopTrendInnovation, String laptopTrendIVS, String laptopTrendOverall) {
        this.laptopTrendBrand = laptopTrendBrand;
        this.laptopTrendReviews = laptopTrendReviews;
        this.laptopTrendDesign = laptopTrendDesign;
        this.laptopTrendSW = laptopTrendSW;
        this.laptopTrendInnovation = laptopTrendInnovation;
        this.laptopTrendIVS = laptopTrendIVS;
        this.laptopTrendOverall = laptopTrendOverall;
    }

    public String getLaptopTrendBrand() {
        return laptopTrendBrand;
    }

    public void setLaptopTrendBrand(String laptopTrendBrand) {
        this.laptopTrendBrand = laptopTrendBrand;
    }

    public String getLaptopTrendReviews() {
        return laptopTrendReviews;
    }

    public void setLaptopTrendReviews(String laptopTrendReviews) {
        this.laptopTrendReviews = laptopTrendReviews;
    }

    public String getLaptopTrendDesign() {
        return laptopTrendDesign;
    }

    public void setLaptopTrendDesign(String laptopTrendDesign) {
        this.laptopTrendDesign = laptopTrendDesign;
    }

    public String getLaptopTrendSW() {
        return laptopTrendSW;
    }

    public void setLaptopTrendSW(String laptopTrendSW) {
        this.laptopTrendSW = laptopTrendSW;
    }

    public String getLaptopTrendInnovation() {
        return laptopTrendInnovation;
    }

    public void setLaptopTrendInnovation(String laptopTrendInnovation) {
        this.laptopTrendInnovation = laptopTrendInnovation;
    }

    public String getLaptopTrendIVS() {
        return laptopTrendIVS;
    }

    public void setLaptopTrendIVS(String laptopTrendIVS) {
        this.laptopTrendIVS = laptopTrendIVS;
    }

    public String getLaptopTrendOverall() {
        return laptopTrendOverall;
    }

    public void setLaptopTrendOverall(String laptopTrendOverall) {
        this.laptopTrendOverall = laptopTrendOverall;
    }

    
    
    
}
