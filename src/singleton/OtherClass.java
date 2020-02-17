package singleton;


public class OtherClass {

    public OtherClass() {
    }
    
    public void run(){
        Agenda a=Agenda.getInstance();
        a.addContacto(new Contacto("Joker","j@j.com"));
               
    }
    
}
