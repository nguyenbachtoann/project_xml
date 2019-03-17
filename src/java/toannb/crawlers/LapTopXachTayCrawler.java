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
import java.util.regex.Pattern;
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
public class LapTopXachTayCrawler {

    private static String welformedHTML;
    private static TextUtilities htmlUtils = new TextUtilities();

    public List<LaptopDTO> getListProductEachPage(String pageURL, int page, String beginTag, String endTag) {
        pageURL = pageURL + "&p=" + page;
        String htmlContent = "";
        List<LaptopDTO> listProduct = new ArrayList<>();
        try {
            URL url = new URL(pageURL);
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
            welformedHTML = welformedHTML.replaceAll("<br/>", "-");
            reader = XMLUtilities.parseInputStreamToStAXCursor(welformedHTML);// parse to StAx Cursor
            listProduct = getProductStAX(reader);

            is.close();
            br.close();
        } catch (XMLStreamException ex) {
            Logger.getLogger(LapTopXachTayCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LapTopXachTayCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<LaptopDTO> getProductStAX(XMLStreamReader reader) {
        String htmlContent = "";
        int i = 1;
        List<LaptopDTO> listProduct = new ArrayList<>();
        try {
            String laptopId = "", laptopNameInfo = "", laptopPrice = "", laptopImg = "", laptopBrand = "", laptopDescription = "";
            if (reader != null) {

                while (reader.hasNext()) {
                    int currentCursor = reader.next();
                    if (currentCursor == XMLStreamConstants.START_ELEMENT) {

                        String tagName = reader.getLocalName();
                        if ("img".equals(tagName)) {
                            laptopImg = reader.getAttributeValue(null, "data-original");

                        }
                        if ("h3".equals(tagName)) {
                            //get small description
                            laptopNameInfo = XMLUtilities.getTextContent("h3", reader);
                            //get brand
                            StringTokenizer st = new StringTokenizer(laptopNameInfo, " ");
                            laptopBrand = st.nextToken();
                        }

                        if ("span".equals(tagName)) {
                            String spanClass = reader.getAttributeValue(null, "class");
                            if (spanClass != null) {
                                if ("priceinfo".equals(spanClass)) {
                                    if (!reader.isCharacters()) {
                                        reader.next();
                                        reader.next();
                                        reader.next();
                                        reader.next();
                                        if (reader.isCharacters()) {
                                            //get price
                                            laptopPrice = reader.getText();
                                        }
                                        reader.next();
                                    }
                                }
                            }
                        }// end if span

                        if ("p".equals(tagName)) {
                            if (reader.getAttributeValue(null, "class") != null) {
                                //get big description

                            } else {
                                reader.next();
                                if (reader.isCharacters()) {
                                    if (!reader.getText().contains("Trả góp")) {
                                        laptopDescription = reader.getText();
                                        String result = laptopDescription.replaceFirst(Pattern.quote("-"), " ");
                                        laptopDescription = result;
                                        LaptopDTO dto = new LaptopDTO(laptopId, laptopBrand.toUpperCase(), laptopNameInfo, laptopPrice, laptopImg, laptopDescription, AppConstants.LTXT_DOMAIN);
                                        listProduct.add(dto);
                                    }

                                }

                            }

                        }//end if p

                    }

                }

            }

        } catch (XMLStreamException ex) {
            Logger.getLogger(LapTopXachTayCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;

    }

    //Get last page
    public int getPaginationStAX(String pageURL, String beginTag, String endTag) {
        int lastPage = 0;
        String pagination = "";
        XMLStreamReader reader;

        try {
            URL url = new URL(pageURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line;
            boolean begin = false, end = false;
            while ((line = br.readLine()) != null) {
                if (line.contains(beginTag)) {
                    begin = true;
                    pagination = pagination + line;
                    continue;
                }
                if (begin && !end) {
                    if (line.contains(endTag)) {

                        break;
                    }
                    pagination = pagination + line;
                }

            }
            welformedHTML = htmlUtils.refineHtml(pagination); //welform html truoc khi parse
            reader = XMLUtilities.parseInputStreamToStAXCursor(welformedHTML);// parse to StAx Cursor
            lastPage = getLastPageNumber(reader);

            is.close();
            br.close();
        } catch (Exception e) {
            Logger.getLogger(LapTopXachTayCrawler.class.getName()).log(Level.SEVERE, null, e);
        }
        return lastPage;
    }

    private static int getLastPageNumber(XMLStreamReader reader) {

        int lastPageNumber = 0;
        String lastPageHref = "";

        try {
            if (reader != null) {
                while (reader.hasNext()) {
                    int currentCursor = reader.next();
                    if (currentCursor == XMLStreamConstants.START_ELEMENT) {

                        String tagName = reader.getLocalName();
                        if ("a".equals(tagName)) {
                            String href = reader.getAttributeValue(null, "href");
                            String aText = XMLUtilities.getTextContent("a", reader);
                            if (aText.equals("End")) {
                                lastPageHref = href;

                                if (lastPageHref.contains("https://laptopxachtayshop.com/Laptop-xach-tay-00112.html&p=")) {
                                    lastPageHref = lastPageHref.replaceAll("https://laptopxachtayshop.com/Laptop-xach-tay-00112.html&p=", "");
                                    lastPageNumber = Integer.parseInt(lastPageHref);
                                }
                            }

                        }

                    }
                }
            }

        } catch (Exception e) {
            Logger.getLogger(LapTopXachTayCrawler.class.getName()).log(Level.SEVERE, null, e);
        }

        return lastPageNumber;

    }

}
