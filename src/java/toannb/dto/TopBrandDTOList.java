/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bachtoan
 */
public class TopBrandDTOList implements Serializable {
    private List<TopBrandDTO> listBrand;

    public List<TopBrandDTO> getListBrand() {
        if(listBrand == null){
            listBrand = new ArrayList<>();
        }
        return listBrand;
    }

    public void setListBrand(List<TopBrandDTO> listBrand) {
        this.listBrand = listBrand;
    }
    
    
}
