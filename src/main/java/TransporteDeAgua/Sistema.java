package TransporteDeAgua;

import java.nio.file.Path;

import Archivos.CargaArchivos;
import Estructuras.HeapMax;
import Estructuras.Lista;

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

    public boolean agregarCiudad(String ciudad, double superficie, double consumo, String nomenclatura) {
        return gestorCiudades.agregarCiudad(ciudad, superficie, consumo, nomenclatura);
    }

    public boolean eliminarCiudad(String unaCiudad) {
        // como la ciudad es la que sabe su nomenclatura hay que pedirlo primero del arbol para poder mandarlo al gestor de tuberias
        Ciudad ciudad = gestorCiudades.getCiudad(unaCiudad);
        if (ciudad != null) {
            gestorCiudades.eliminarCiudad(unaCiudad);
            gestorTuberias.eliminarTuberiasDeCiudad(ciudad);
        }
        return ciudad != null;
    }

    public boolean existeCiudad(String nombre) {
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

    public boolean setCantidadHabitantes(String ciudad, int anio, int mes, int cantidad) {
        return gestorCiudades.setCantidadHabitantes(ciudad, anio, mes, cantidad);
    }

    public void mostrarCiudades() {
        Lista ciudades = gestorCiudades.listarCiudades();
        ciudades.toString();
    }

    public boolean setConsumoPromedio(String unaCiudad, double unConsumo) {

        return gestorCiudades.setConsumoPromedio(unaCiudad, unConsumo);
    }

    public boolean setCantidadHabitantesAnio(String unaCiudad, int anio, int[] datos) {

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
    public boolean crearTuberia(String fuente, String destino, double caudalMin, double caudalMax, double diametro, String estadoStr) {
        Tuberia.Estado estado = Tuberia.Estado.valueOf(estadoStr);
        boolean exito = false;
        Ciudad ciudadFuente = gestorCiudades.getCiudad(fuente);
        if (ciudadFuente != null) {
            Ciudad ciudadDestino = gestorCiudades.getCiudad(destino);
            if (ciudadDestino != null) {
                exito = gestorTuberias.crearTuberia(ciudadFuente, ciudadDestino, caudalMin, caudalMax, diametro, estado);
            }
        }
        return exito;
    }

    public boolean eliminarTuberia(String fuente, String destino) {
        boolean exito = false;
        Ciudad ciudadFuente = gestorCiudades.getCiudad(fuente);
        if (ciudadFuente != null) {
            Ciudad ciudadDestino = gestorCiudades.getCiudad(destino);
            if (ciudadDestino != null) {
                exito = gestorTuberias.eliminarTuberia(ciudadFuente, ciudadDestino);
            }
        }
        return exito;
    }

    public boolean cambiarEstadoTuberia(String fuente, String destino, String estadoStr) {
        Tuberia.Estado estado = Tuberia.Estado.valueOf(estadoStr);
        boolean exito = false;
        Ciudad ciudadFuente = gestorCiudades.getCiudad(fuente);
        if (ciudadFuente != null) {
            Ciudad ciudadDestino = gestorCiudades.getCiudad(destino);
            if (ciudadDestino != null) {
                exito = gestorTuberias.modificarEstadoTuberia(ciudadFuente, ciudadDestino, estado);
            }
        }
        return exito;
    }

    public Lista menorCaminoEntre(String fuente, String destino) {
        Lista camino= new Lista();

        Ciudad ciudadFuente = gestorCiudades.getCiudad(fuente);
        if (ciudadFuente != null) {
            Ciudad ciudadDestino = gestorCiudades.getCiudad(destino);
            if (ciudadDestino != null) {
                camino= gestorTuberias.menorCaminoEntre(ciudadFuente, ciudadDestino);
            }
        }
        return camino;
    }
     public Lista caminoConMenorPleno(String fuente, String destino) {
        Lista camino= new Lista();

        Ciudad ciudadFuente = gestorCiudades.getCiudad(fuente);
        if (ciudadFuente != null) {
            Ciudad ciudadDestino = gestorCiudades.getCiudad(destino);
            if (ciudadDestino != null) {
                camino= gestorTuberias.caminoConMenorPleno(ciudadFuente, ciudadDestino);
                System.out.println(camino);
            }
        }
        return camino;
    }

    public String obtenerEstadoCamino(Lista camino) {
        Tuberia.Estado estado;
        String estadoString = "INEXISTENTE";
        if(camino.longitud()>2){
            estado = gestorTuberias.obtenerEstadoDeCamino(camino);
            switch (estado) {
                case ACTIVO -> {
                    estadoString = "Activo";
                }
                case ENREPARACION -> {
                    estadoString = "En Reparacion";
                }
                case ENDISENIO -> {
                    estadoString = "En Diseño";
                }
                case INACTIVO -> {
                    estadoString = "Inactivo";
                }
            }
        }
        return estadoString;
    }

    public boolean setCaudalMax(String fuente, String destino, double caudal) {
        boolean exito = false;
        Ciudad fuenteC = gestorCiudades.getCiudad(fuente);
        Ciudad destinoC = gestorCiudades.getCiudad(destino);
        if (fuenteC != null && destinoC != null) {
            exito = gestorTuberias.setCaudalMax(fuenteC, destinoC, caudal);//falta este metodo en el gestor tuberias
        }
        return exito;
    }

    public boolean setCaudalMin(String fuente, String destino, double caudal) {
        boolean exito = false;
        Ciudad fuenteC = gestorCiudades.getCiudad(fuente);
        Ciudad destinoC = gestorCiudades.getCiudad(destino);
        if (fuenteC != null && destinoC != null) {
            exito = gestorTuberias.setCaudalMin(fuenteC, destinoC, caudal);//falta este metodo en el gestor tuberias
        }
        return exito;
    }

    public String mostrarSistema() {
        String ciudades = "Estructura Ciudades \n" + gestorCiudades.toStringCiudades();
        String tuberias = "Estructura Tuberias \n" + gestorTuberias.toString();
        return ciudades + "\n" + tuberias;
    }
}
