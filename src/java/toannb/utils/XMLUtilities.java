/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import toannb.dto.LaptopDTOList;

/**
 *
 * @author bachtoan
 */
public class XMLUtilities implements Serializable {
    
    public static XMLStreamReader parseInputStreamToStAXCursor(String i) throws XMLStreamException {
        InputStream is = new ByteArrayInputStream(i.getBytes(StandardCharsets.UTF_8));
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(is);

        return reader;
    }

    public static String getTextContent(String elementName, XMLStreamReader reader) throws XMLStreamException {
        if (reader == null) {
            return null;
        }

        if (elementName == null) {
            return null;
        }

        if (elementName.trim().isEmpty()) {
            return null;
        }

        while (reader.hasNext()) {
            int currentCursor = reader.getEventType();
            if (currentCursor == XMLStreamConstants.START_ELEMENT) {
                String tagName = reader.getLocalName();
                if (tagName.equals(elementName)) {
                    reader.next();//value
                    String result = reader.getText();
                    reader.nextTag();//end element
                    return result;

                }//end if tagname.equals()
            }//end if START_ELEMENT
//            reader.next();
        }// end while

        return null;
    }
    
    public static String marshallingToString(LaptopDTOList laptops){
        try {
            JAXBContext jaxb = JAXBContext.newInstance(LaptopDTOList.class);
            Marshaller mar = jaxb.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            
            StringWriter sw = new StringWriter();
            mar.marshal(laptops, sw);
            
            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
