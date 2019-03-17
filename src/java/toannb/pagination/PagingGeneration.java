/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.pagination;

import java.util.logging.Level;
import java.util.logging.Logger;
import toannb.dao.LapTopDAO;
import toannb.dto.LaptopDTOList;

/**
 *
 * @author bachtoan
 */
public class PagingGeneration {

    private static final int DISPLAY_NUM = 5;

    public LaptopDTOList paginEachPage(String pageNum) {

        LaptopDTOList standardList = new LaptopDTOList();
        LaptopDTOList displayList = new LaptopDTOList();
        LapTopDAO laptopDAO = new LapTopDAO();

        int startPoint = 0;
        int endPoint = 0;
        try {

            standardList = laptopDAO.getAllLaptop();

            startPoint = (Integer.parseInt(pageNum) - 1) * DISPLAY_NUM;
            endPoint = DISPLAY_NUM * Integer.parseInt(pageNum);

            displayList.setLaptop(standardList.getLaptoppagination().subList(startPoint, endPoint));

        } catch (Exception e) {
            Logger.getLogger(PagingGeneration.class.getName()).log(Level.SEVERE, null, e);
        }

        return standardList;

    }

    public int PaginationCount() {
        LaptopDTOList standardList = new LaptopDTOList();
        LapTopDAO laptopDAO = new LapTopDAO();
        int count = 0;
        int numOfPagingTag = 0;
        try {
            standardList = laptopDAO.getAllLaptop();
            count = standardList.getLaptoppagination().size();

            numOfPagingTag = count / DISPLAY_NUM;

        } catch (Exception e) {
            Logger.getLogger(PagingGeneration.class.getName()).log(Level.SEVERE, null, e);
        }

        return numOfPagingTag;
    }
}
