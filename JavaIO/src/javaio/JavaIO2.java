package javaio;

import java.util.Scanner;

public class JavaIO2 {

    public static void main(String[] args) {
        //Autocloseable
        try (Scanner in=new Scanner(System.in)) {
            String option = "";
            do {
                System.out.println("Elija una opción:");
                System.out.println("1. Cree un archivo de texto");
                System.out.println("2. Para leer archivo de texto");
                System.out.println("10. Para salir");

                if(in.hasNext())
                    option = in.nextLine();

                if(option.equals("1")){
                    ejemplo.Ejemplo1.exec();
                } else if(option.equals("2")){
                    ejemplo.Ejemplo2.exec();
                }
               

            } while (!option.equals("10"));
        } catch(Exception ex){
            ex.printStackTrace();
        }   
    }

}
