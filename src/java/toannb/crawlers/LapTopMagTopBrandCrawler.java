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
//            brandList = getLaptopPageBrand(reader);
            System.out.println("");

            is.close();
            br.close();
        } catch (Exception e) {
            Logger.getLogger(LapTopProComCrawler.class.getName()).log(Level.SEVERE, null, e);
        }
        return brandList;
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
