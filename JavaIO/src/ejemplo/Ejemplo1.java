package ejemplo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Ejemplo1 {

    public static void exec() {
        System.out.println("Creando un archivo de texto");
        Path path = Paths.get("outputfile.txt");
        try (
                BufferedWriter br_out = Files.newBufferedWriter(path,
                        Charset.defaultCharset(), StandardOpenOption.CREATE);
                
        ) {

            BufferedReader br_in = new BufferedReader(new InputStreamReader(System.in));
            String line = br_in.readLine();

            while (!line.equals("end")) {
                br_out.write(line);
                br_out.newLine();
                line = br_in.readLine();
            }
            //br_out.close(); //no necesario por el bloque try-catch-resource
            //br_in.close(); //no lo puedo cerrar porque si no cierra System.in y 
            //falla main
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
