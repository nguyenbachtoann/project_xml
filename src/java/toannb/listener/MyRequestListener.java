/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.listener;

import java.util.List;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import toannb.dao.LapTopDAO;
import toannb.dto.LaptopDTOList;
import toannb.utils.XMLUtilities;

/**
 * Web application lifecycle listener.
 *
 * @author bachtoan
 */
public class MyRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        try {
            LapTopDAO laptopDAO = new LapTopDAO();

            LaptopDTOList laptopList = laptopDAO.getTop2TrendingBrand();
            List<String> listBrand = laptopDAO.getLaptopBrand();
            String xml = XMLUtilities.marshallingToString(laptopList);

            sre.getServletRequest().setAttribute("LAPTOPLIST", xml);
            sre.getServletRequest().setAttribute("BRANDLIST", listBrand);
        } catch (Exception e) {
        }
    }
}
