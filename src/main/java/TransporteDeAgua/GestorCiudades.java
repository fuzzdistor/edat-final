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
        return tem.getHabitantes(anio,mes );
    }

    public Lista filtrarRango(String minNom, String maxNom){
        Ciudad min = new Ciudad(minNom, "");
        Ciudad max = new Ciudad(maxNom, "");
        return ciudades.listarRango(min, max);
    }

    
    
}
