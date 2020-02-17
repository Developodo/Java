package singleton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Agenda {

    private static Agenda instance;
    ArrayList<Contacto> contactos;

    private Agenda() {
        contactos = new ArrayList<>();
    }

    private Agenda(String url) {
        contactos = new ArrayList<>();
        if (url != null) {
            fromXML(url);
        }
    }

    public static Agenda getInstance() {
        return Agenda.getInstance(null);
    }

    public static Agenda getInstance(String url) {
        if (instance == null) {
            instance = new Agenda(url);
        }
        return instance;
    }

    public boolean addContacto(Contacto c) {
        return this.contactos.add(c);
    }

    public boolean removeContacto(String email) {
        boolean result = false;
        /**
         * PRIMERA FORMA
         * for(int i=0;i<this.contactos.size();i++){
         * if(this.contactos.get(i).getEmail().equals(email)){
         * this.contactos.remove(i); i--; } }
         */
        Contacto removed = new Contacto("", email);
        /**
         * SEGUNDA FORMA
         * while (contactos.remove(removed));
         * */

        Collection<Contacto> c = new ArrayList<>();
        c.add(removed);
        contactos.removeAll(c);
        return result;
    }

    public String toString() {
        String result = "";
        for (Contacto c : this.contactos) {
            result += " > " + c + "\n";
        }
        return result;
    }

    public void toXML() {
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build;

            build = dFact.newDocumentBuilder();   //EXC

            org.w3c.dom.Document doc = build.newDocument();

            Element root = doc.createElement("Agenda");

            for (Contacto c : contactos) {

                Element con = doc.createElement("Contacto");
                //con.setAttribute("id", 1);

                Element name = doc.createElement("Nombre");
                name.appendChild(doc.createTextNode(c.getNombre()));
                con.appendChild(name);

                Element email = doc.createElement("Email");
                email.appendChild(doc.createTextNode(String.valueOf(c.getEmail())));
                con.appendChild(email);

                root.appendChild(con);

            }
            doc.appendChild(root);

            // Save the document to the disk file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            //OPTIONAL
            // format the XML nicely
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("agenda.xml"));

            transformer.transform(source, result);  //EXC
        } catch (TransformerException ex) {
            System.out.println(ex);
        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        }

    }

    public void fromXML(String url) {
        this.contactos.clear();
        try {
            File file = new File(url);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            // estos métodos podemos usarlos combinados para normalizar el archivo XML
            //getDocumentElement()	Accede al nodo raíz del documento
            //normalize()	Elimina nodos vacíos y combina adyacentes en caso de que los hubiera
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Contacto");   //nList.getLength() -> n_nodos
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //String id=eElement.getAttribute("id");
                    String nombre = eElement.getElementsByTagName("Nombre").item(0).getTextContent();
                    String email = eElement.getElementsByTagName("Email").item(0).getTextContent();
                    Contacto c = new Contacto(nombre, email);
                    contactos.add(c);
                }
            }

        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        } catch (SAXException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
}
