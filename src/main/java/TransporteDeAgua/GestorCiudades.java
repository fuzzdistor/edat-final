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
        return ciudades.insertar(norm, nuevaCiudad);
    }

    public boolean eliminarCiudad(String unaCiudad) {

        return ciudades.eliminar(unaCiudad.toUpperCase());
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
        Ciudad ciudad = (Ciudad) ciudades.recuperar(unaCiudad.toUpperCase());
        if (ciudad != null) {
            ciudad.setConsumoPromedio(unConsumo);
            exito = true;
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
        Ciudad tem = (Ciudad) ciudades.recuperar(unaCiudad.toUpperCase());
        return (tem != null) && tem.setHabitantes(anio, mes, cantidad);
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