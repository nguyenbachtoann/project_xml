/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.crawlers;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import toannb.constant.AppConstants;
import toannb.dao.LapTopDAO;
import toannb.dto.LaptopDTO;
import toannb.listener.MyContextListener;

/**
 *
 * @author bachtoan
 */
public class CrawlerMaster {

    public static void main(String[] args) {
        crawlData();

    }

    public static void crawlData() {
        LapTopXachTayCrawler ltxtCrawler = new LapTopXachTayCrawler();
        int lastPage = ltxtCrawler.getPaginationStAX(AppConstants.LTXT_HOME_URL, AppConstants.LTXT_PAGE_BEGIN_TAG, AppConstants.LTXT_PAGE_END_TAG);

        LapTopDAO dao = new LapTopDAO();

        try {
            //truncate table before insert
            dao.truncateTable(AppConstants.DB_TABLE);
        } catch (Exception e) {
            Logger.getLogger(LapTopXachTayCrawler.class.getName()).log(Level.SEVERE, null, e);
        }

        for (int i = 0; i < lastPage; i++) {

            List<LaptopDTO> laptopList = ltxtCrawler.getListProductEachPage(AppConstants.LTXT_HOME_URL, i + 1, AppConstants.LTXT_BEGIN_TAG, AppConstants.LTXT_END_TAG);

            try {

                dao.insert(laptopList);
            } catch (Exception e) {
                Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, e);
            }

        }

        //Laptop Pro Com
        LapTopProComCrawler ltpcCrawler = new LapTopProComCrawler();
        List<String> urlList = new ArrayList<>();
        urlList = ltpcCrawler.getLaptopPageBrandStAX(AppConstants.LTPC_HOME_URL, AppConstants.LTPC_PAGE_BEGIN_TAG, AppConstants.LTPC_PAGE_END_TAG);

        for (int i = 0; i < urlList.size(); i++) {
            List<LaptopDTO> laptopList = ltpcCrawler.getListProductEachBrand(AppConstants.LTPC_HOME_BASE_URL, urlList.get(i), AppConstants.LTPC_BEGIN_TAG, AppConstants.LTPC_END_TAG);
            try {

                dao.insert(laptopList);
            } catch (Exception e) {
                Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        try {
            dao.updateImageURL();
        } catch (Exception e) {
            Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
