/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author bachtoan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "laptop"
})
@XmlRootElement(name = "laptops")
public class LaptopDTOList implements Serializable {
    @XmlElement(required = true)
    private List<LaptopDTO> laptop;

    public List<LaptopDTO> getLaptop() {
        if(laptop == null){
            laptop = new ArrayList<LaptopDTO>();
        }
        return laptop;
    }

    public void setLaptop(List<LaptopDTO> laptop) {
        this.laptop = laptop;
    }
}
