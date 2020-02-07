package estructurasdinamicas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream;


public class Ejecutable {

    public static void main(String[] args) {
        IEstructura<Person> lista=new Estructura<>();
        lista.push(new Person(1,"Carlos",40));
        lista.push(new Person(2,"Marta",9));
        lista.push(new Person(3,"Gloria",6));
        lista.push(new Person(2,"Marta",9));
        
  
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(new File("myObjects.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(lista);
            o.close();
            f.close();
            System.out.println("---------   SAVED --------");
            
 

            FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            // Read objects
            Estructura nueva = (Estructura) oi.readObject();
            oi.close();
            fi.close();
            System.out.println(nueva);
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) { 
      
        }
 

       
       
       
        for(Person elemento: lista){
            System.out.println(elemento);
        }
        
    }
    
}
