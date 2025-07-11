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
    
    public int getCantidadHabitantes(String unaCiudad, int anio, int mes) {
        Ciudad tem = getCiudad(unaCiudad);
    return (tem != null) ? tem.getHabitantes(anio, mes) : -1;
    }

    public double getConsumoPromedio(String unaCiudad){
        double consumo=0;
        Ciudad ciudad = (Ciudad) ciudades.obtener(new Ciudad(unaCiudad, ""));
        if (ciudad != null) {
            consumo=ciudad.getConsumoPromedio();
        }
        return consumo;
    }

    public double getConsumoMes(String unaCiudad, int anio, int mes){
        double consumo=0;

        Ciudad ciudad = (Ciudad) ciudades.obtener(new Ciudad(unaCiudad, ""));
        if (ciudad != null) {
            consumo=ciudad.getConsumoMes(anio,mes);  
        }
        return consumo;
    }
    

    public Lista listarCiudades(){
        return ciudades.listar();
    }

    public void setConsumoPromedio(String unaCiudad, double unConsumo) {
    Ciudad ciudad = (Ciudad) ciudades.obtener(new Ciudad(unaCiudad, ""));
    if (ciudad != null) {
        ciudad.setConsumoPromedio(unConsumo);
    }
}



    public Lista filtrarRango(String minNom, String maxNom){
        Ciudad min = new Ciudad(minNom, "");
        Ciudad max = new Ciudad(maxNom, "");
        return ciudades.listarRango(min, max);
    }

    public boolean setCantidadHabitantes(String unaCiudad, int anio, int mes, int cantidad) {
        Ciudad tem = (Ciudad) ciudades.obtener(new Ciudad(unaCiudad, ""));
    return (tem != null) && tem.setHabitantes(anio, mes, cantidad);
    }


    
    
}
