/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author bachtoan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "laptop", propOrder = {
    "laptopId",
    "laptopBrand",
    "laptopNameInfo",
    "laptopPrice",
    "laptopImageURL",
    "laptopDescription",
    "laptopDomain"
})
public class LaptopDTO implements Serializable {

    @XmlElement(required = true)
    private String laptopId;
    @XmlElement(required = true)
    private String laptopBrand;
    @XmlElement(required = true)
    private String laptopNameInfo;
    @XmlElement(required = true)
    private String laptopPrice;
    @XmlElement(required = true)
    private String laptopImageURL;
    @XmlElement(required = true)
    private String laptopDescription;
    @XmlElement(required = true)
    private String laptopDomain;

    public LaptopDTO() {
    }

    public LaptopDTO(String laptopId, String laptopBrand, String laptopNameInfo, String laptopPrice, String laptopImageURL, String laptopDescription, String laptopDomain) {
        this.laptopId = laptopId;
        this.laptopBrand = laptopBrand;
        this.laptopNameInfo = laptopNameInfo;
        this.laptopPrice = laptopPrice;
        this.laptopImageURL = laptopImageURL;
        this.laptopDescription = laptopDescription;
        this.laptopDomain = laptopDomain;
    }

    public LaptopDTO(String laptopBrand, String laptopNameInfo, String laptopPrice, String laptopImageURL, String laptopDescription, String laptopDomain) {
        this.laptopBrand = laptopBrand;
        this.laptopNameInfo = laptopNameInfo;
        this.laptopPrice = laptopPrice;
        this.laptopImageURL = laptopImageURL;
        this.laptopDescription = laptopDescription;
        this.laptopDomain = laptopDomain;
    }

    public LaptopDTO(String laptopId, String laptopImageURL) {
        this.laptopId = laptopId;
        this.laptopImageURL = laptopImageURL;
    }

    public String getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(String laptopId) {
        this.laptopId = laptopId;
    }

    public String getLaptopBrand() {
        return laptopBrand;
    }

    public void setLaptopBrand(String laptopBrand) {
        this.laptopBrand = laptopBrand;
    }

    public String getLaptopNameInfo() {
        return laptopNameInfo;
    }

    public void setLaptopNameInfo(String laptopNameInfo) {
        this.laptopNameInfo = laptopNameInfo;
    }

    public String getLaptopPrice() {
        return laptopPrice;
    }

    public void setLaptopPrice(String laptopPrice) {
        this.laptopPrice = laptopPrice;
    }

    public String getLaptopImageURL() {
        return laptopImageURL;
    }

    public void setLaptopImageURL(String laptopImageURL) {
        this.laptopImageURL = laptopImageURL;
    }

    public String getLaptopDescription() {
        return laptopDescription;
    }

    public void setLaptopDescription(String laptopDescription) {
        this.laptopDescription = laptopDescription;
    }

    public String getLaptopDomain() {
        return laptopDomain;
    }

    public void setLaptopDomain(String laptopDomain) {
        this.laptopDomain = laptopDomain;
    }

}
