/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlproba0ler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import static javax.xml.stream.XMLStreamConstants.*;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author oracle
 */
public class XMLProba0ler {

    static XMLStreamReader xmlsr;
    static XMLInputFactory xmlif = XMLInputFactory.newInstance();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        conectar("/home/oracle/Desktop/compartido/NetBeansProjects/XMLProba0/proba0.xml");
        ler();
    }

    public static void conectar(String ruta) {
        try {
            xmlsr = xmlif.createXMLStreamReader(new FileReader(ruta));
        } catch (XMLStreamException ex) {
            Logger.getLogger(XMLProba0ler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLProba0ler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ler() {
        int lastElement;
        try {
            if (xmlsr.hasNext()) {
                xmlsr.next();
                System.out.print("<" + xmlsr.getLocalName() + "");
            }
            while (xmlsr.hasNext()) {
                lastElement = xmlsr.getEventType();
                xmlsr.next();
                //Comprobamos si es el elemento inicial
                if (xmlsr.getEventType() == START_ELEMENT && lastElement == START_ELEMENT) {
                    System.out.print(">\n<" + xmlsr.getLocalName() + "");
                    //Si el elemento contiene atributos, contamos cuantos hay y añadimos cada uno, cerrando el tag al final
                    if (xmlsr.getAttributeCount() > 0) {
                        for (int i = 0; i < xmlsr.getAttributeCount(); i++) {
                            System.out.print(" " + xmlsr.getAttributeName(i) + "=" + xmlsr.getAttributeValue(i));
                        }
                    }
                } else if (xmlsr.getEventType() == START_ELEMENT && lastElement != START_ELEMENT) {
                    System.out.print("<" + xmlsr.getLocalName() + "");
                    //Si el elemento contiene atributos, contamos cuantos hay y añadimos cada uno, cerrando el tag al final
                    if (xmlsr.getAttributeCount() > 0) {
                        for (int i = 0; i < xmlsr.getAttributeCount(); i++) {
                            System.out.print(" " + xmlsr.getAttributeName(i) + "=" + xmlsr.getAttributeValue(i));
                        }
                    }
                    //Si el elemento es el contenido
                } else if (xmlsr.getEventType() == CHARACTERS) {
                    System.out.print(">" + xmlsr.getText());
                    //Si el elemento es una etiqueta de cierre
                } else if (xmlsr.getEventType() == END_ELEMENT) {
                    System.out.print("</" + xmlsr.getLocalName() + ">\n");
                }
            }

        } catch (XMLStreamException ex) {
            Logger.getLogger(XMLProba0ler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
