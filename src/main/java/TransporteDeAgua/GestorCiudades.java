package TransporteDeAgua;

import Estructuras.ArbolAVL;
import Estructuras.Lista;

public class GestorCiudades {
    private ArbolAVL ciudades;

    public GestorCiudades(){
        this.ciudades=new ArbolAVL();
    }

    public boolean agregarCiudad(String nombre, double superficie, double consumo, String nomenclatura) {
        boolean exito = false;
        Ciudad nuevaCiudad = new Ciudad(nombre, superficie, consumo, nomenclatura);

        if (!ciudades.pertenece(nuevaCiudad)) {
            exito = ciudades.insertar(nuevaCiudad);
        }  
        return exito;
    }
    

    public boolean eliminarCiudad(String unaCiudad){
        Ciudad tem = new Ciudad(unaCiudad, "");
        return ciudades.eliminar(tem);
    }

    public Ciudad getCiudad(String nombreCiudad){
        Ciudad tem = new Ciudad(nombreCiudad,"");
        return (Ciudad)ciudades.obtener(tem);
    }

    public Lista listarCiudades(){
        return ciudades.listar();
    }

    public void setConsumoPromedio(String unaCiudad, double unConsumo){
        Ciudad tem = new Ciudad(unaCiudad,"");
        Ciudad ciudad=(Ciudad) ciudades.obtener(tem);
        ciudad.setConsumoPromedio(unConsumo);
    }

    public int cantidadHabitantes(String unaCiudad, int anio, int mes){
        Ciudad tem = getCiudad(unaCiudad);
        return tem.obtenerHabitantes(anio,mes );
    }

    public Lista filtrarRango(String minNom, String maxNom){
        Ciudad min = new Ciudad(minNom, "");
        Ciudad max = new Ciudad(maxNom, "");
        return ciudades.listarRango(min, max);
    }

    public static void main(String[] args) {
        //esto es solo de prueba
        GestorCiudades gestor = new GestorCiudades();

        System.out.println("Agregando ciudades ");
        gestor.agregarCiudad("neuquen", 3000, 0.9, "ne3000");
        gestor.agregarCiudad("rio negro", 2500, 0.7,"ri3001");
        gestor.agregarCiudad("cordoba", 4000, 1.1,"co3002");
        gestor.agregarCiudad("rio negro", 9999, 9.9,"ri3001"); 
        gestor.agregarCiudad("salta", 99599, 9,"sa3004");
        System.out.println("ciudades cargadas:\n" + gestor.listarCiudades());

        System.out.println("\n-----obteniendo ciudad por nombre-----");
        Ciudad c1 = gestor.getCiudad("rio negro");
        if (c1 != null) {
            System.out.println(c1.obtenerDetalles());
        }
        Ciudad c2 = gestor.getCiudad("neuquen");
        if (c2 != null) {
            System.out.println(c2.obtenerDetalles());
        }
        Ciudad c3 = gestor.getCiudad("cordoba");
        if (c3 != null) {
            System.out.println(c3.obtenerDetalles());
        }
        Ciudad c4 = gestor.getCiudad("jujuy");
        if (c4 != null) {
            System.out.println(c4.obtenerDetalles());
        }
        

        System.out.println("\n------cambiando consumo promedio -----");
        gestor.setConsumoPromedio("Rio negro", 0.1);
        System.out.println(gestor.getCiudad("Rio negro").obtenerDetalles());

        System.out.println("-----probando habitantes por fecha----- ");
        Ciudad salta = gestor.getCiudad("Salta");
        if (salta != null) {
            salta.agregarHabitantes("2025-07", 15000);
            salta.agregarHabitantes("2025-08", 100);
            System.out.println("datos cargados julio: 15000 agosto 100");
            System.out.println("Habitantes en Salta (julio 2025): " + gestor.cantidadHabitantes("Salta", 2025, 7));
            System.out.println("Habitantes en Salta (agosto 2025): " + gestor.cantidadHabitantes("Salta", 2025, 8));
            System.out.println("para un mes no cargado deberia devolver 0");
            System.out.println("Habitantes en Salta (junio 2025): " + gestor.cantidadHabitantes("Salta", 2025, 6));
        }
        System.out.println("------filtrar ciudades entre d y sz------");
        Lista filtradas = gestor.filtrarRango("d", "sz");
        System.out.println("Ciudades en el rango:");
        System.out.println(filtradas.toString());

        System.out.println("------ eliminando salta ------");
        gestor.eliminarCiudad("salta");
        System.out.println("ciudades dsp de eliminar salta:\n" + gestor.listarCiudades());

        System.out.println("------obtener ciudad eliminada------ ");
        Ciudad saltaEliminada = gestor.getCiudad("salta");
        if (saltaEliminada == null) {
            System.out.println("salta no se encuentra");
        } else {
            System.out.println(saltaEliminada.obtenerDetalles());
        }



}
    
}
