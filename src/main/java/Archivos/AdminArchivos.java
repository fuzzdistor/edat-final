package Archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Estructuras.*;
import TransporteDeAgua.Log;


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

        Log.info("Archivo leido exitosamente: %s. Lineas validas: %d"
        .formatted(archivoPath.getFileName(), longitud));

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nEl archivo del "
                    + "que queriamos leer no existe.");
            Log.error("Archivo no encontrado: " + archivoPath.getFileName());
        } catch (IOException ex) {
            System.err.println("Error leyendo en " + archivoPath.getFileName());
            Log.error("Error leyendo archivo: " + archivoPath.getFileName());
        }

        return lineas;
    }
}

