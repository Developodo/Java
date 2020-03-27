package ejemplo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import others.Items;

public class Ejemplo4 {

    public static void exec() {
        System.out.println("Serializando y Desearializando");

        Path path = Paths.get("outputfile.txt");
        //formato:
        //nombre, precio
        //nombre, precio...

        try (Stream<String> stream = Files.lines(path)) {
            List<Items> lista=stream.map(linea->linea.split(","))
                  .filter(array->array.length==2)
                  .map(e->others.Items.fromArray(e))   //.map(others.Items::fromArray); 
                  .collect(Collectors.toList());
          
             try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("outputObject.data"))){
                 out.writeObject(lista);
                 System.out.println("Serializado, pero no se vayan todavía");
                 try(ObjectInputStream in=new ObjectInputStream(new FileInputStream("outputObject.data"))){
                     try {
                         List<Items> lista2=(List)in.readObject();
                         System.out.println(lista2);
                     } catch (ClassNotFoundException ex) {
                         ex.printStackTrace();
                     }
                 }
             }catch(IOException ex){
                 ex.printStackTrace();
             }
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
                    
    }
}
