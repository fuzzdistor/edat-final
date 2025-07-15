package TransporteDeAgua;

import Estructuras.Diccionario;
import Estructuras.Lista;

public class GestorCiudades {
    private Diccionario ciudades;

    public GestorCiudades(){
        this.ciudades=new Diccionario();
    }

    public boolean agregarCiudad(String nombre, double superficie, double consumo, String nomenclatura) {
        String norm= nombre.toUpperCase();
        Ciudad nuevaCiudad = new Ciudad(norm, superficie, consumo, nomenclatura);
        return ciudades.insertar(norm, nuevaCiudad);
    }

    public boolean eliminarCiudad(String unaCiudad){
       
        return ciudades.eliminar(unaCiudad.toUpperCase());
    }

    public Ciudad getCiudad(String nombreCiudad){
        return (Ciudad)ciudades.recuperar(nombreCiudad.toUpperCase());
    }
    
    public int getCantidadHabitantes(String unaCiudad, int anio, int mes) {
        Ciudad tem = (Ciudad)ciudades.recuperar(unaCiudad.toUpperCase());
    return (tem != null) ? tem.getHabitantes(anio, mes) : -1;
    }

    public double getConsumoPromedio(String unaCiudad){
        double consumo=0;
        Ciudad ciudad = (Ciudad) ciudades.recuperar(unaCiudad.toUpperCase());
        if (ciudad != null) {
            consumo=ciudad.getConsumoPromedio();
        }
        return consumo;
    }

    public double getConsumoMes(String unaCiudad, int anio, int mes){
        double consumo=0;

        Ciudad ciudad = (Ciudad) ciudades.recuperar(unaCiudad.toUpperCase());
        if (ciudad != null) {
            consumo=ciudad.getConsumoMes(anio,mes);  
        }
        return consumo;
    }
    

    public Lista listarNomCiudades(){
        return ciudades.listarClaves();
    }

    public Lista listarCiudades(){
        return ciudades.listarDatos();
    }

    public void setConsumoPromedio(String unaCiudad, double unConsumo) {
        Ciudad ciudad = (Ciudad) ciudades.recuperar(unaCiudad.toUpperCase());
        if (ciudad != null) {
            ciudad.setConsumoPromedio(unConsumo);
        }
    }



    public Lista filtrarRango(String minNom, String maxNom){
        return ciudades.listarRangoDatos(minNom.toUpperCase(), maxNom.toUpperCase());
    }

    public boolean setCantidadHabitantes(String unaCiudad, int anio, int mes, int cantidad) {
        Ciudad tem = (Ciudad) ciudades.recuperar(unaCiudad.toUpperCase());
    return (tem != null) && tem.setHabitantes(anio, mes, cantidad);
    }    
}
