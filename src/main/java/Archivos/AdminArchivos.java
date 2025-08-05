package Archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Estructuras.*;


public class AdminArchivos {

    public static Lista leerArchivo(Path archivoPath) {
        Lista lineas = new Lista();
        int longitud = 0;
        try (BufferedReader buff = Files.newBufferedReader(archivoPath)) {
            String linea;
            while ((linea = buff.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lineas.insertar(linea, longitud + 1);
                    longitud++;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nEl archivo del "
                    + "que queriamos leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en " + archivoPath.getFileName());
        }

        return lineas;
    }
}

