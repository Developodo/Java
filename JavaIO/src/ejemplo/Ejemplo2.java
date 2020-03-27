package ejemplo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejemplo2 {

    public static void exec() {
        System.out.println("Leyendo un archivo de texto");

        Path path = Paths.get("outputfile.txt");

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(System.out::println);
            //stream.forEach(line->{System.out.println(line);});
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        System.out.println("No se vayan todavía, aún hay mas");
            List<String> unaLista=new ArrayList<>();
            unaLista.add("HOLA");
            unaLista.add("MUNDO");
            unaLista.add("hIELO");
            
            Stream<String> unStream=unaLista.stream();
            Object[] x1=unStream
                    .map(s->s.toLowerCase())  //hola mundo hielo
                    .filter(s->s.startsWith("h"))   //hola hielo
                    .sorted()  //hielo hola
                    .toArray();  //{"hielo", "hola"}
            
            
            for(Object x:x1){
                System.out.println(x);   //hielo hola
            }
            
            Stream<String> otroStream=unaLista.stream();
            Object x2=otroStream
                    .map(s->s+"<")   //HOLA<  MUNDO<  HIELO<
                    .collect(Collectors.joining(","));  //"HOLA<,MUNDO<,HIELO>"
            System.out.println(x2);
            //https://www.geeksforgeeks.org/stream-in-java/
                    
    }
}
