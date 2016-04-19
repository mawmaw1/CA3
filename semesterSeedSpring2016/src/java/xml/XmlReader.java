/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

/**
 *
 * @author Magnus
 */
import entity.CurrencyRates;
import entity.Role;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import openshift_deploy.DeploymentConfiguration;

public class XmlReader extends DefaultHandler implements Runnable {

    private boolean weekend = true;
    public static String date = "";

    @Override
    public void startDocument() throws SAXException {
        //System.out.println("Start Document (Sax-event)");
    }

    @Override
    public void endDocument() throws SAXException {
        weekend = true;

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//     System.out.print("Element: " + localName + ": ");

        List<CurrencyRates> cr = new ArrayList();
        System.out.println(cr.size());
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.getQName(i).equals("id")) {
                if (!attributes.getValue(0).equals(date)) {
                    date = attributes.getValue(0);
                    weekend = false;

                }
            }
        }
        if (!weekend) {

            for (int i = 0; i < attributes.getLength(); i++) {

                if (attributes.getLocalName(i).equals("id")) {
                    date = attributes.getValue(0);
                }

                if (attributes.getLocalName(i).equals("code")) {
                    String countryCode = attributes.getValue(0);
                    Double rate = 0.0;
                    try {
                        rate = Double.parseDouble(attributes.getValue(2));
                    } catch (Exception e) {

                    }
                    String desc = attributes.getValue(1);

                    CurrencyRates cura = new CurrencyRates();
                    cura.setCountryCode(countryCode);
                    cura.setDescription(desc);
                    cura.setRate(rate);
                    cura.setDailyDate(date);
                    cr.add(cura);
                }
            }
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

        EntityManager em = emf.createEntityManager();
        for (CurrencyRates cura : cr) {
            try {
                em.getTransaction().begin();
                em.persist(cura);
                em.getTransaction().commit();

            } catch (Exception e) {
            } finally {
                em.close();
            }
        }

    }

    @Override
    public void run() {

        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");

            xr.parse(new InputSource(url.openStream()));

        } catch (IOException ex) {
            Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
