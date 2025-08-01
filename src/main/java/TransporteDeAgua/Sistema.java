package TransporteDeAgua;

import java.nio.file.Path;

import Archivos.CargaArchivos;
import Estructuras.HeapMax;
import Estructuras.Lista;
import TransporteDeAgua.Tuberia.Estado;

public class Sistema {
    
    private GestorCiudades gestorCiudades;
    private GestorTuberias gestorTuberias;

    public Sistema() {
        this.gestorCiudades = new GestorCiudades();
        this.gestorTuberias = new GestorTuberias();
    }


    //CARGAS
    
    public void cargarCiudades(String ruta) {
        CargaArchivos.cargarCiudades(Path.of(ruta), gestorCiudades);
    }

    public void cargarTuberias(String ruta) {
        CargaArchivos.cargarTuberias(Path.of(ruta), gestorTuberias);
    }

    public void cargarHabitantes(String ruta) {
        CargaArchivos.cargarHabitantes(Path.of(ruta), gestorCiudades);
    }

    //CONSULTAS SOBRE CIUDADES

    public boolean agregarCiudad(String ciudad, double superficie, double consumo, String nomenclatura){
        return gestorCiudades.agregarCiudad(ciudad, superficie, consumo, nomenclatura);
    }
    public boolean eliminarCiudad(String unaCiudad){
        return gestorCiudades.eliminarCiudad(unaCiudad);
    }
    public boolean existeCiudad(String nombre){
        return gestorCiudades.existeCiudad(nombre);
    }
    
    public double consumoMes(String ciudad, int anio, int mes) {
        return gestorCiudades.getConsumoMes(ciudad, anio, mes);
    }
    
    public double consumoPromedio(String ciudad) {
        return gestorCiudades.getConsumoPromedio(ciudad);
    }
    
    public int habitantes(String ciudad, int anio, int mes) {
        return gestorCiudades.getCantidadHabitantes(ciudad, anio, mes);
    }
    
    public Lista filtrarCiudades(String minNom, String maxNom, double minVol, double maxVol, int anio, int mes) {
        return gestorCiudades.filtrarNombreVolumen(minNom, maxNom, minVol, maxVol, anio, mes);
    }
    
    public boolean setCantidadHabitantes(String ciudad, int anio, int mes, int cantidad){
        return gestorCiudades.setCantidadHabitantes(ciudad, anio, mes, cantidad);
    }
    
    public void mostrarCiudades() {
        Lista ciudades = gestorCiudades.listarCiudades();
        ciudades.toString();
    }

    public boolean setConsumoPromedio(String unaCiudad, double unConsumo){
        
        return gestorCiudades.setConsumoPromedio(unaCiudad, unConsumo);
    }

    public boolean setCantidadHabitantesAnio(String unaCiudad, int anio, int[]datos){

        return gestorCiudades.setCantidadHabitantesAnio(unaCiudad, anio, datos);
    }

    public HeapMax ciudadesOrdenadasPorConsumo(int anio) {
    Lista listaCiudades = gestorCiudades.listarCiudades(); 
    HeapMax heap = new HeapMax(listaCiudades.longitud());

    for (int i = 1; i <= listaCiudades.longitud(); i++) {
        Ciudad ciudad = (Ciudad) listaCiudades.recuperar(i);
        double consumo = ciudad.getConsumoAnual(anio);
        heap.insertar(new ConsumoCiudad(ciudad.getNombre(), consumo));
    }

    return heap;
}

    
    //CONSULTAS SOBRE TUBERIAS
    public boolean crearTuberia(String fuente, String destino, double caudalMin, double caudalMax, double diametro, String estadoStr){
        Estado estado = Estado.valueOf(estadoStr);
        return gestorTuberias.crearTuberia(fuente, destino, caudalMin, caudalMax, diametro, estado);
    }

    public boolean eliminarTuberia(String fuente, String destino){
        return gestorTuberias.eliminarTuberia(fuente, destino);
    }

    public boolean cambiarEstadoTuberia(String fuente, String destino, String estado){
        return true;

    }

    public String getEstadoCamino(String fuente, String destido){
        return "";
    }

    public String getCaminoMinimo(String fuente, String Destino){
        return "";
    }

    public boolean setCaudalMax(String fuente, String destino, double caudal){
        boolean exito= false;
        Ciudad fuenteC= gestorCiudades.getCiudad(fuente);
        Ciudad destinoC= gestorCiudades.getCiudad(destino);
        if(fuenteC!=null && destinoC!=null){
            String nom1= fuenteC.getNomenclatura();
            String nom2= fuenteC.getNomenclatura();
            exito= gestorTuberias.setCaudalMax(nom1,nom2,caudal);//falta este metodo en el gestor tuberias
        }
        return exito;    
    }

     public boolean setCaudalMin(String fuente, String destino, double caudal){
        boolean exito= false;
        Ciudad fuenteC= gestorCiudades.getCiudad(fuente);
        Ciudad destinoC= gestorCiudades.getCiudad(destino);
        if(fuenteC!=null && destinoC!=null){
            String nom1= fuenteC.getNomenclatura();
            String nom2= fuenteC.getNomenclatura();
            exito= gestorTuberias.setCaudalMin(nom1,nom2,caudal);//falta este metodo en el gestor tuberias
        }
        return exito;    
    }

    public String mostrarSistema(){

        String ciudades= "Estructura Ciudades \n"+gestorCiudades.toStringCiudades();
        //falta el resto de las estructuras
        return ciudades;
    }



}
