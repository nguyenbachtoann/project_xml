/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.crawlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import toannb.constant.AppConstants;

import toannb.dto.LaptopDTO;
import toannb.utils.TextUtilities;
import toannb.utils.XMLUtilities;

/**
 *
 * @author bachtoan
 */
public class LapTopProComCrawler {

    private static String welformedHTML;
    private static TextUtilities htmlUtils = new TextUtilities();

    public List<LaptopDTO> getListProductEachBrand(String baseURL, String subURL, String beginTag, String endTag) {
        String htmlContent = "";
        String completedURL = baseURL + subURL;
        List<LaptopDTO> listProduct = new ArrayList<>();
        try {
            URL url = new URL(completedURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line;
            boolean begin = false, end = false;
            while ((line = br.readLine()) != null) {
                if (line.contains(beginTag)) {
                    begin = true;
                    htmlContent = htmlContent + line;
                    continue;
                }
                if (begin && !end) {
                    if (line.contains(endTag)) {

                        break;
                    }
                    htmlContent = htmlContent + line;
                }

            }
            XMLStreamReader reader = null;
            welformedHTML = htmlUtils.refineHtml(htmlContent); //welform html truoc khi parse
            reader = XMLUtilities.parseInputStreamToStAXCursor(welformedHTML);// parse to StAx Cursor
            listProduct = getProductStAX(reader);

            is.close();
            br.close();
        } catch (XMLStreamException ex) {
            Logger.getLogger(LapTopProComCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LapTopProComCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listProduct;
    }

    public List<LaptopDTO> getProductStAX(XMLStreamReader reader) {
        List<LaptopDTO> listProduct = new ArrayList<>();

        try {
            String laptopId = "", laptopNameInfo = "", laptopPrice = "", laptopImg = "", laptopBrand = "", laptopDescription = "";

            if (reader != null) {

                while (reader.hasNext()) {
                    int currentCursor = reader.next();
                    if (currentCursor == XMLStreamConstants.START_ELEMENT) {

                        String tagName = reader.getLocalName();

                        if ("img".equals(tagName)) {
                            if (reader.getAttributeValue(null, "class") != null) {
                                String classImg = reader.getAttributeValue(null, "class");
                                if (classImg.equals("hinhchinh")) {
                                    String img = reader.getAttributeValue(null, "src");
                                    laptopImg = AppConstants.LTPC_HOME_BASE_URL + img; //http://laptopprocom.vn/ + link of image
                                }
                            }

                        }
                        if ("p".equals(tagName)) {
                            reader.next();
                            String pContent = reader.getText();

                            laptopNameInfo = pContent;
                            //get brand
                            StringTokenizer st = new StringTokenizer(pContent, " ");

                            laptopBrand = st.nextToken();

                            laptopDescription = laptopNameInfo;

                            reader.nextTag();

                        }

                        if ("div".equals(tagName)) {
                            String strongClass = reader.getAttributeValue(null, "class");
                            if (strongClass != null) {
                                if ("gia".equals(strongClass)) {
                                    reader.next();
                                    laptopPrice = reader.getText();
                                    if (laptopPrice.contains("VNĐ")) {
                                        laptopPrice = laptopPrice.replaceAll("VNĐ", "");
                                    }
                                    LaptopDTO dto = new LaptopDTO(laptopId, laptopBrand.toUpperCase(), laptopNameInfo, laptopPrice, laptopImg, laptopDescription, AppConstants.LTPC_HOME_BASE_URL);
                                    listProduct.add(dto);
                                }

                            }

                        }// end if div 

                    }

                }

            }

        } catch (XMLStreamException ex) {
            Logger.getLogger(LapTopProComCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;

    }

    //Get laptop brand page
    public List<String> getLaptopPageBrandStAX(String pageURL, String beginTag, String endTag) {
        String pageBrandContent = "";
        XMLStreamReader reader;
        List<String> urlList = new ArrayList<>();
        try {
            URL url = new URL(pageURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line;
            boolean begin = false, end = false;
            while ((line = br.readLine().trim()) != null) {
                if (line.contains(beginTag)) {
                    begin = true;
                    pageBrandContent = pageBrandContent + line;
                    continue;
                }
                if (begin && !end) {
                    if (line.contains(endTag)) {

                        break;
                    }
                    pageBrandContent = pageBrandContent + line;
                }
            }
            welformedHTML = htmlUtils.refineHtml(pageBrandContent); //welform html truoc khi parse 
            reader = XMLUtilities.parseInputStreamToStAXCursor(welformedHTML);// parse to StAx Cursor
            urlList = getLaptopPageBrand(reader);

            is.close();
            br.close();
        } catch (Exception e) {
            Logger.getLogger(LapTopProComCrawler.class.getName()).log(Level.SEVERE, null, e);
        }
        return urlList;
    }

    private static List<String> getLaptopPageBrand(XMLStreamReader reader) {

        List<String> urlList = new ArrayList<String>();
        try {
            if (reader != null) {
                while (reader.hasNext()) {
                    int currentCursor = reader.next();
                    if (currentCursor == XMLStreamConstants.START_ELEMENT) {

                        String className = reader.getAttributeValue(null, "class");

                        if ("muahang".equals(className)) {
                            reader.next();
                            String tagName = reader.getLocalName();
                            if ("a".equals(tagName)) {

                                String url = reader.getAttributeValue(null, "href");
                                if (url != null) {
                                    urlList.add(url);
                                }

                            }
                        }

                    }
                }
            }

        } catch (Exception e) {
            Logger.getLogger(LapTopProComCrawler.class.getName()).log(Level.SEVERE, null, e);
        }

        return urlList;

    }
}
