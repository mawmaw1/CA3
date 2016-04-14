/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.IOException;
import java.net.URL;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Magnus
 */
public class Tester {

    public static void main(String[] argv) {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
