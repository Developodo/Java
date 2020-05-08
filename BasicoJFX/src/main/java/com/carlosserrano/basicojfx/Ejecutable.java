package com.carlosserrano.basicojfx;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class Ejecutable {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger("Basico");
        FileHandler fh = new FileHandler("MyLogFile.log");
        logger.addHandler(fh);
        App.main(args);
    }
}
