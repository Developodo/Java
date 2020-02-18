package singleton;

/**
 *
 * @author carlosserrano
 */
public class Ejecutable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /* Agenda a=Agenda.getInstance();
        a.addContacto(new Contacto("Carlos","c@c.com"));
        a.addContacto(new Contacto("Eva","e@e.com"));
        a.addContacto(new Contacto("Sánchez","e@e.com"));
        OtherClass o=new OtherClass();
        o.run();
        a.toXML("Agenda.xml");
        a.toJSON("Agenda.json");
        */
        
       Agenda a2=Agenda.getInstance("Agenda.xml");
       System.out.println(a2);
       
       
       a2.fromJSON("Agenda.json");
       System.out.println(a2);

    }
    
}
