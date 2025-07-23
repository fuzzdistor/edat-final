package Archivos;

import java.nio.file.Path;

import Estructuras.Lista;
import TransporteDeAgua.Ciudad;
import TransporteDeAgua.GestorCiudades;
import TransporteDeAgua.GestorTuberias;
import TransporteDeAgua.Tuberia.Estado;


public class CargaArchivos {

    public static void cargarCiudades(Path archivo, GestorCiudades gestor) {
        Lista lineas = AdminArchivos.leerArchivo(archivo);

        for (int i = 1; i <= lineas.longitud(); i++) {
            String linea = (String) lineas.recuperar(i);
            String[] partes = linea.split(",");

            if (partes.length == 4) {
            
                String nombre = partes[0].trim();
                double superficie = Double.parseDouble(partes[1].trim());
                double consumo = Double.parseDouble(partes[2].trim());
                String nomenclatura = partes[3].trim();

                gestor.agregarCiudad(nombre, superficie, consumo, nomenclatura);
            }
        }
    }

/* //para formato ciudad,año,mes,cantidad
    public static void cargarHabitantes(Path archivo, GestorCiudades gestor) {
        Lista lineas = AdminArchivos.leerArchivo(archivo);

        String ciudadActual = null;
        Ciudad referenciaActual = null;

        for (int i = 1; i <= lineas.longitud(); i++) {
            String linea = (String) lineas.recuperar(i);
            String[] partes = linea.split(",");

            if (partes.length == 4) {
                String nombreCiudad = partes[0].trim();
                int anio = Integer.parseInt(partes[1].trim());
                int mes = Integer.parseInt(partes[2].trim());
                int cantidad = Integer.parseInt(partes[3].trim());

                if (!nombreCiudad.equalsIgnoreCase(ciudadActual)) {
                    referenciaActual = gestor.getCiudad(nombreCiudad);
                    ciudadActual = nombreCiudad;
                }

                if (referenciaActual != null) {
                    referenciaActual.setHabitantes(anio, mes, cantidad);
                
                
                }
            }
        }
            lineas.vaciar();
    }*/
    //formato ciudad,año,12meses
  public static void cargarHabitantes(Path archivo, GestorCiudades gestor) {
    Lista lineas = AdminArchivos.leerArchivo(archivo);

    for (int i = 1; i <= lineas.longitud(); i++) {
        String linea = (String) lineas.recuperar(i);
        String[] partes = linea.split(",");

        if (partes.length == 14) {
            String nombreCiudad = partes[0].trim().toUpperCase();
            int anio = Integer.parseInt(partes[1].trim());

            Ciudad ciudad = gestor.getCiudad(nombreCiudad);
            if (ciudad != null) {
                int[] datosMensuales = new int[12];
                for (int j = 0; j < 12; j++) {
                    datosMensuales[j] = Integer.parseInt(partes[j + 2].trim());
                }

                ciudad.setHabitantesAnio(anio, datosMensuales);
            }
        }
    }

    lineas.vaciar();
}


//formato carga nomfuente,nomdestino,caudalmin,caudalmax,diametro,estado
public static void cargarTuberias(Path archivo, GestorTuberias gestor) {
        Lista lineas = AdminArchivos.leerArchivo(archivo);

        for (int i = 1; i <= lineas.longitud(); i++) {
            String linea = (String) lineas.recuperar(i);
            String[] partes = linea.split(",");

            if (partes.length == 6) {
            
                String fuente = partes[0].trim();
                String destino = partes[1].trim();
                double caudalMin= Double.parseDouble(partes[2]);
                double caudalMax= Double.parseDouble(partes[3]);
                double diametro =  Double.parseDouble(partes[4]);
                String estadoStr = partes[5].trim().toUpperCase();
                Estado estado = Estado.valueOf(estadoStr);
                gestor.crearTuberia(fuente, destino, caudalMin, caudalMax, diametro, estado);
            }
        }
        lineas.vaciar();
    }


}