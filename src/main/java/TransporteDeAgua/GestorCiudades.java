package TransporteDeAgua;

import Estructuras.Diccionario;
import Estructuras.Lista;

public class GestorCiudades {
    private Diccionario ciudades;

    public GestorCiudades() {
        this.ciudades = new Diccionario();
    }

    public boolean agregarCiudad(String nombre, double superficie, double consumo, String nomenclatura) {
        String norm = nombre.toUpperCase();
        Ciudad nuevaCiudad = new Ciudad(norm, superficie, consumo, nomenclatura);
        boolean exito=ciudades.insertar(norm, nuevaCiudad);
        if(exito){
            Log.alta("Ciudad %s agregada (superficie: %.2f, consumo: %.2f, código: %s)".formatted(
            norm, superficie, consumo, nomenclatura));
        }else{
            Log.error("Error al agregar ciudad %s: ya existe".formatted(norm));
        }
        return exito;
    }

    public boolean eliminarCiudad(String unaCiudad) {
        String norm=unaCiudad.toUpperCase();
        boolean exito=ciudades.eliminar(norm);
        if(exito){
            Log.baja("Ciudad %s eliminada".formatted(norm));
        }else{
            Log.error("Error en eliminacion de ciudad: %s".formatted(norm));
        }
        return exito;
    }

    public Ciudad getCiudad(String nombreCiudad) {
        return (Ciudad) ciudades.recuperar(nombreCiudad.toUpperCase());
    }

    public boolean existeCiudad(String nombreCiudad) {
        return ((Ciudad) ciudades.recuperar(nombreCiudad.toUpperCase())) != null;
    }

    public int getCantidadHabitantes(String unaCiudad, int anio, int mes) {
        Ciudad tem = (Ciudad) ciudades.recuperar(unaCiudad.toUpperCase());
        return (tem != null) ? tem.getHabitantes(anio, mes) : -1;
    }

    public double getConsumoPromedio(String unaCiudad) {
        double consumo = 0;
        Ciudad ciudad = (Ciudad) ciudades.recuperar(unaCiudad.toUpperCase());
        if (ciudad != null) {
            consumo = ciudad.getConsumoPromedio();
        }
        return consumo;
    }

    public double getConsumoMes(String unaCiudad, int anio, int mes) {
        //-1 si la ciduda no existe
        //0 si no hay registro de habitantes para ese mes/anio
        double consumo = -1;

        Ciudad ciudad = (Ciudad) ciudades.recuperar(unaCiudad.toUpperCase());
        if (ciudad != null) {
            consumo = ciudad.getConsumoMes(anio, mes);
        }
        return consumo;
    }

    public double getConsumoAnual(String unaCiudad, int anio) {
        //-1 si si la ciduda no existe
        //0 si no hay registro de habitantes para ese mes/anio
        double consumo = -1;
        Ciudad ciudad = (Ciudad) ciudades.recuperar(unaCiudad.toUpperCase());
        if (ciudad != null) {
            consumo = ciudad.getConsumoAnual(anio);
        }
        return consumo;
    }


    public Lista listarNomCiudades() {
        return ciudades.listarClaves();
    }

    public Lista listarCiudades() {
        return ciudades.listarDatos();
    }

    public boolean setConsumoPromedio(String unaCiudad, double unConsumo) {
        boolean exito = false;
        String norm= unaCiudad.toUpperCase();
        Ciudad ciudad = (Ciudad) ciudades.recuperar(norm);
        if (ciudad != null) {
            ciudad.setConsumoPromedio(unConsumo);
            exito = true;
            Log.modificacion("Ciudad %s: consumo promedio actualizado a %.2f L".formatted(norm, unConsumo));
        } else {
            Log.error("Error al modificar consumo: ciudad %s no encontrada".formatted(norm));
        }
        return exito;
    }


    public Lista filtrarNombreVolumen(String minNom, String maxNom, double minVol, double maxVol, int anio, int mes) {

        Lista filtroNombre = ciudades.listarRangoDatos(minNom.toUpperCase(), maxNom.toUpperCase());
        Lista filtroTotal = new Lista();
        for (int i = 1; i <= filtroNombre.longitud(); i++) {
            String ciudad = ((Ciudad) filtroNombre.recuperar(i)).getNombre();
            double consumo = getConsumoMes(ciudad, anio, mes);
            if (consumo >= minVol && consumo <= maxVol) {
                filtroTotal.insertar(new ConsumoCiudad(ciudad, consumo), filtroTotal.longitud() + 1);
            }
        }

        return filtroTotal;
    }


    public boolean setCantidadHabitantes(String unaCiudad, int anio, int mes, int cantidad) {
        String norm= unaCiudad.toUpperCase();
        Ciudad tem = (Ciudad) ciudades.recuperar(norm);
        boolean exito= false;
        if (tem != null) {
            tem.setHabitantes(anio, mes, cantidad);
            exito = true;
            Log.modificacion("Ciudad %s: habitantes actualizados a %d para %02d/%d".formatted(norm, cantidad, mes, anio));
        } else {
            Log.error("Error al modificar cantidad de habitantes: ciudad %s no encontrada".formatted(norm));
        }
        return  exito;
    }

    public boolean setCantidadHabitantesAnio(String unaCiudad, int anio, int[] datos) {
        boolean exito = false;
        Ciudad tem = (Ciudad) ciudades.recuperar(unaCiudad.toUpperCase());
        if (tem != null && datos.length == 12) {
            tem.setHabitantesAnio(anio, datos);
            exito = true;    
        }
        return exito;
    }

    public String toStringCiudades() {
        return ciudades.toStringClaves();
    }
}