/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.crawlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import toannb.constant.AppConstants;
import toannb.dao.LapTopDAO;
import toannb.dto.LaptopDTO;
import toannb.dto.LaptopDTOList;
import toannb.dto.TopBrandDTOList;
import toannb.utils.XMLUtilities;

/**
 *
 * @author bachtoan
 */
public class CrawlerMaster extends TimerTask{

    private String realPath;

    public CrawlerMaster(String realPath) {
        this.realPath = realPath;
    }

    public CrawlerMaster() {
    }
    
    
    public void crawlData(String realPath) {
        LapTopXachTayCrawler ltxtCrawler = new LapTopXachTayCrawler();
        int lastPage = ltxtCrawler.getPaginationStAX(AppConstants.LTXT_HOME_URL, AppConstants.LTXT_PAGE_BEGIN_TAG, AppConstants.LTXT_PAGE_END_TAG);

        LapTopDAO dao = new LapTopDAO();

        try {
            //truncate table before insert
            dao.truncateTable(AppConstants.DB_TABLE);
            dao.truncateTable(AppConstants.LTM_DB_TABLE);
        } catch (Exception e) {
            Logger.getLogger(CrawlerMaster.class.getName()).log(Level.SEVERE, null, e);
        }

        for (int i = 0; i < lastPage; i++) {

            List<LaptopDTO> laptopList = ltxtCrawler.getListProductEachPage(AppConstants.LTXT_HOME_URL, i + 1, AppConstants.LTXT_BEGIN_TAG, AppConstants.LTXT_END_TAG);
            LaptopDTOList laptopDTOList = new LaptopDTOList();
            laptopDTOList.setLaptop(laptopList);

            String xmlString = XMLUtilities.marshallingToString(laptopDTOList);

            boolean validated = validateXMLToInsertDB(xmlString, realPath + "schemas\\laptopSchema.xsd");
            try {

                if (validated == true) {
                    dao.insert(laptopList);
                }

            } catch (Exception e) {
                Logger.getLogger(CrawlerMaster.class.getName()).log(Level.SEVERE, null, e);
            }

        }

        //Laptop Pro Com
        LapTopProComCrawler ltpcCrawler = new LapTopProComCrawler();
        List<String> urlList = new ArrayList<>();
        urlList = ltpcCrawler.getLaptopPageBrandStAX(AppConstants.LTPC_HOME_URL, AppConstants.LTPC_PAGE_BEGIN_TAG, AppConstants.LTPC_PAGE_END_TAG);

        for (int i = 0; i < urlList.size(); i++) {
            List<LaptopDTO> laptopList = ltpcCrawler.getListProductEachBrand(AppConstants.LTPC_HOME_BASE_URL, urlList.get(i), AppConstants.LTPC_BEGIN_TAG, AppConstants.LTPC_END_TAG);

            LaptopDTOList laptopDTOList = new LaptopDTOList();
            laptopDTOList.setLaptop(laptopList);

            String xmlString = XMLUtilities.marshallingToString(laptopDTOList);
            boolean validated = validateXMLToInsertDB(xmlString, realPath + "schemas\\laptopSchema.xsd");

            try {
                if (validated == true) {
                    dao.insert(laptopList);
                }

            } catch (Exception e) {
                Logger.getLogger(CrawlerMaster.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        try {
            dao.updateImageURL();
        } catch (Exception e) {
            Logger.getLogger(CrawlerMaster.class.getName()).log(Level.SEVERE, null, e);
        }

        LapTopMagTopBrandCrawler brandCrawler = new LapTopMagTopBrandCrawler();
        TopBrandDTOList topBrandList = new TopBrandDTOList();
        topBrandList = brandCrawler.getBrandRank(AppConstants.LTM_HOME_URL, AppConstants.LTM_BEGIN_TAG, AppConstants.LTM_END_TAG);
        try {
            dao.insertTopBrand(topBrandList);
        } catch (Exception e) {
            Logger.getLogger(CrawlerMaster.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private  boolean validateXMLToInsertDB(String xmlString, String schemaFilePath) {
        try {

            SchemaFactory sFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sFactory.newSchema(new File(schemaFilePath));
            Validator validator = schema.newValidator();
            InputSource inputSource = new InputSource(new StringReader(xmlString));
            validator.validate(new SAXSource(inputSource));

            return true;
        } catch (SAXException e) {
            Logger.getLogger(CrawlerMaster.class.getName()).log(Level.SEVERE, null, e);
        } catch (FileNotFoundException e) {
            Logger.getLogger(CrawlerMaster.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(CrawlerMaster.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    @Override
    public void run() {
        crawlData(realPath);
    }
}
