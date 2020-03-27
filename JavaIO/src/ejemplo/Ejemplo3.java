package ejemplo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class Ejemplo3 {

    public static void exec() {
        System.out.println("Leyendo un archivo y creando objetos");

        Path path = Paths.get("outputfile.txt");
        //formato:
        //nombre, precio
        //nombre, precio...

        try (Stream<String> stream = Files.lines(path)) {
            OptionalDouble total=stream.map(linea->linea.split(","))
                  .filter(array->array.length==2)
                  .map(e->others.Items.fromArray(e))   //.map(others.Items::fromArray); 
                  .mapToDouble(o -> o.getPrize())
                  .reduce(Double::sum);  //.ifPresent(System.out::print);
             System.out.println(total);
             
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
                    
    }
}
