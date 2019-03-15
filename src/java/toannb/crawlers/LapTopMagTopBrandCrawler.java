/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.crawlers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import toannb.dto.TopBrandDTO;
import toannb.dto.TopBrandDTOList;
import toannb.utils.TextUtilities;
import toannb.utils.XMLUtilities;

/**
 *
 * @author bachtoan
 */
public class LapTopMagTopBrandCrawler {

    private static String welformedHTML;
    private static TextUtilities htmlUtils = new TextUtilities();

    //Get laptop brand page
    public TopBrandDTOList getBrandRank(String pageURL, String beginTag, String endTag) {
        String rankContent = "";
        XMLStreamReader reader;
        TopBrandDTOList brandList = new TopBrandDTOList();
        try {
            URL url = new URL(pageURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line;
            boolean begin = false, end = false;
            while ((line = br.readLine().trim()) != null) {
                if (line.contains(beginTag)) {
                    begin = true;
                    rankContent = rankContent + line;
                    continue;
                }
                if (begin && !end) {
                    if (line.contains(endTag)) {

                        break;
                    }
                    rankContent = rankContent + line;
                }
            }
            welformedHTML = htmlUtils.refineHtml(rankContent); //welform html truoc khi parse 
            reader = XMLUtilities.parseInputStreamToStAXCursor(welformedHTML);// parse to StAx Cursor
            brandList = getBrandRankStAX(reader);
          

            is.close();
            br.close();
        } catch (Exception e) {
            Logger.getLogger(LapTopProComCrawler.class.getName()).log(Level.SEVERE, null, e);
        }
        return brandList;
    }

    private static TopBrandDTOList getBrandRankStAX(XMLStreamReader reader) {

        TopBrandDTOList brandList = new TopBrandDTOList();

        try {
            if (reader != null) {
                while (reader.hasNext()) {
                    String laptopTrendBrand = "", laptopTrendReviews = "", laptopTrendDesign = "", laptopTrendSW = "", laptopTrendInnovation = "", laptopTrendIVS = "", laptopTrendOverall = "";
                    int currentCursor = reader.next();
                    if (currentCursor == XMLStreamConstants.START_ELEMENT) {

                        String tagName = reader.getLocalName();

                        if ("tr".equals(tagName)) {
                            String styleAttr = reader.getAttributeValue(null, "style");

                            if (styleAttr.contains("font-weight: bold; border-collapse: collapse; border-top: 1px solid #ccc")) {

                                reader.next();
                                String tdTag = reader.getLocalName();

                                //laptopTrendBrand
                                if ("td".equals(tdTag)) {
                                    reader.next();

                                    if (!reader.isCharacters()) {
                                        reader.next();
                                        //the a

                                        laptopTrendBrand = reader.getText();
                                        reader.nextTag();
                                        reader.next();
                                        reader.next();

                                    } else {
                                        laptopTrendBrand = reader.getText();
                                        reader.nextTag();
                                    }

                                }

                                //laptopTrendReviews
                                if ("td".equals(tdTag)) {
                                    reader.next();

                                    laptopTrendReviews = reader.getText();
                                    reader.nextTag();
                                    reader.next();

                                }

                                //laptopTrendDesign
                                if ("td".equals(tdTag)) {
                                    reader.next();


                                    laptopTrendDesign = reader.getText();
                                    reader.nextTag();
                                    reader.next();

                                }

                                //laptopTrendSW
                                if ("td".equals(tdTag)) {
                                    reader.next();

                                    laptopTrendSW = reader.getText();
                                    reader.nextTag();
                                    reader.next();

                                }

                                //laptopTrendInnovation
                                if ("td".equals(tdTag)) {
                                    reader.next();

                                    laptopTrendInnovation = reader.getText();
                                    reader.nextTag();
                                    reader.next();

                                }

                                //laptopTrendIVS
                                if ("td".equals(tdTag)) {
                                    reader.next();


                                    laptopTrendIVS = reader.getText();
                                    reader.nextTag();
                                    reader.next();

                                }

                                //laptopTrendOverall
                                if ("td".equals(tdTag)) {
                                    reader.next();


                                    laptopTrendOverall = reader.getText();
                                    reader.nextTag();
                                    reader.next();

                                }
                                TopBrandDTO brandDTO = new TopBrandDTO(laptopTrendBrand, laptopTrendReviews, laptopTrendDesign, laptopTrendSW, laptopTrendInnovation, laptopTrendIVS, laptopTrendOverall);
                                brandList.getListBrand().add(brandDTO);

                            }

                        }

                    }

                }
            }

        } catch (Exception e) {
            Logger.getLogger(LapTopMagTopBrandCrawler.class.getName()).log(Level.SEVERE, null, e);
        }

        return brandList;

    }
}
