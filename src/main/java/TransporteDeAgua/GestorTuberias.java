package TransporteDeAgua;

import Estructuras.Grafo;
import Estructuras.Lista;

import TransporteDeAgua.Tuberia.Estado;

import java.util.HashMap;
import java.util.Objects;

public class GestorTuberias {
    private static class LlaveTuberias {
        private final String nomenclaturaFuente;
        private final String nomenclaturaDestino;

        public LlaveTuberias(String nomenclaturaFuente, String nomenclaturaDestino) {
            this.nomenclaturaFuente = nomenclaturaFuente;
            this.nomenclaturaDestino = nomenclaturaDestino;
        }

        public boolean contieneNomenclatura(String nomenclatura) {
            return nomenclaturaFuente.equals(nomenclatura) || nomenclaturaDestino.equals(nomenclatura);
        }

        @Override
        public boolean equals(Object otro) {
            boolean exito = otro != null && otro.getClass() == this.getClass();

            if (exito) {
                final LlaveTuberias otraLlave = (LlaveTuberias) otro;
                exito = otraLlave.nomenclaturaDestino.equals(this.nomenclaturaDestino)
                        && otraLlave.nomenclaturaFuente.equals(this.nomenclaturaFuente);
            }
            return exito;
        }

        @Override
        public int hashCode() {
            return Objects.hash(nomenclaturaFuente, nomenclaturaDestino);
        }

        @Override
        public String toString() {
            return nomenclaturaFuente + '-' + nomenclaturaDestino;
        }
    }

    private final HashMap<LlaveTuberias, Tuberia> tuberias;
    private final Grafo grafo;


    public GestorTuberias() {
        this.grafo = new Grafo();
        this.tuberias = new HashMap<>();
    }

    public boolean crearTuberia(String nomenclaturaFuente, String nomenclaturaDestino, double caudalMin, double caudalMax, double diametro, Tuberia.Estado estado) {
        LlaveTuberias llave = new LlaveTuberias(nomenclaturaFuente, nomenclaturaDestino);
        boolean existe = tuberias.containsKey(llave);
        if (!existe) {
            Tuberia tuberia = new Tuberia(nomenclaturaFuente, nomenclaturaDestino, caudalMin, caudalMax, diametro, estado);
            tuberias.put(llave, tuberia);
            grafo.insertarVertice(nomenclaturaFuente);
            grafo.insertarVertice(nomenclaturaDestino);
            grafo.insertarArco(nomenclaturaFuente, nomenclaturaDestino, caudalMax);
            Log.alta("Tuberia creada: %s -> %s (caudalMin: %.2f, caudalMax: %.2f, diametro: %.2f, estado: %s)"
            .formatted(nomenclaturaFuente, nomenclaturaDestino, caudalMin, caudalMax, diametro, estado));
        } else {
            Log.error("Error al crear tuberia: %s -> %s".formatted(nomenclaturaFuente, nomenclaturaDestino));
        }
        return !existe;
    }

    public boolean crearTuberia(Ciudad fuente, Ciudad destino, double caudalMin, double caudalMax, double diametro, Tuberia.Estado estado) {
        return crearTuberia(fuente.getNomenclatura(), destino.getNomenclatura(), caudalMin, caudalMax, diametro, estado);
    }

    private boolean setCaudalMax(String fuente, String destino, double caudalMax) {
        Tuberia tuberia = getTuberia(fuente, destino);
        boolean exito = false;
        if (tuberia != null) {
            tuberia.setCaudalMax(caudalMax);
            grafo.eliminarArco(fuente, destino);
            grafo.insertarArco(fuente, destino, caudalMax);
            exito=true;
            Log.modificacion("Tuberia %s -> %s: caudal maximo modificado a %.2f"
            .formatted(fuente, destino, caudalMax));
        }else{
            Log.error("Error al modificar caudal maximo: tuberia %s -> %s"
            .formatted(fuente, destino));
        }
        return exito;
    }

    public boolean setCaudalMax(Ciudad fuente, Ciudad destino, double caudalMax) {
        return setCaudalMax(fuente.getNomenclatura(), destino.getNomenclatura(), caudalMax);
    }


    private boolean setCaudalMin(String fuente, String destino, double caudalMin) {
        Tuberia tuberia = getTuberia(fuente, destino);
        if (tuberia != null) {
            tuberia.setCaudalMin(caudalMin);
            Log.modificacion("Tuberia %s -> %s: caudal minimo modificado a %.2f"
            .formatted(fuente, destino, caudalMin));

        }else{
            Log.error("Error al modificar caudal minimo: tuberia %s -> %s"
            .formatted(fuente, destino));
        }
        return tuberia != null;
    }

    public boolean setCaudalMin(Ciudad fuente, Ciudad destino, double caudalMin) {
        return setCaudalMin(fuente.getNomenclatura(), destino.getNomenclatura(), caudalMin);
    }

    public boolean eliminarTuberia(Ciudad fuente, Ciudad destino) {
        return eliminarTuberia(fuente.getNomenclatura(), destino.getNomenclatura());
    }

    private boolean eliminarTuberia(String nomenclaturaFuente, String nomenclaturaDestino) {
        return eliminarTuberia(new LlaveTuberias(nomenclaturaFuente, nomenclaturaDestino));
    }

    private boolean eliminarTuberia(LlaveTuberias llave) {
        boolean existe = tuberias.containsKey(llave);
        if (existe) {
            tuberias.remove(llave);
            grafo.eliminarArco(llave.nomenclaturaFuente, llave.nomenclaturaDestino);
            Log.baja("Tubería de %s a %s eliminada".formatted(llave.nomenclaturaFuente, llave.nomenclaturaDestino));
        }else{
            Log.error("Error en eliminacion de tuberia de %s a %s".formatted(llave.nomenclaturaFuente, llave.nomenclaturaDestino));
        }
        return existe;
    }

    public Tuberia getTuberia(Ciudad fuente, Ciudad destino) {
        return getTuberia(fuente.getNomenclatura(), destino.getNomenclatura());
    }

    private Tuberia getTuberia(String nomenclaturaFuente, String nomenclaturaDestino) {
        return tuberias.get(new LlaveTuberias(nomenclaturaFuente, nomenclaturaDestino));
    }

    public Tuberia.Estado getEstadoTuberia(Ciudad fuente, Ciudad destino) {
        return getTuberia(fuente.getNomenclatura(), destino.getNomenclatura()).getEstado();
    }

    public boolean modificarEstadoTuberia(Ciudad fuente, Ciudad destino, Tuberia.Estado estado) {
        Tuberia tuberia = getTuberia(fuente, destino);
        if (tuberia != null) {
            tuberia.setEstado(estado);
            Log.modificacion("Estado de la tuberia de %s -> %s modificado a %s"
            .formatted(fuente.getNomenclatura(), destino.getNomenclatura(), estado));
        }else{
            Log.info("No se pudo modificar el estado: tuberia de %s -> %s "
            .formatted(fuente.getNomenclatura(), destino.getNomenclatura()));
        }
        return tuberia != null;
    }

    public void eliminarTuberiasDeCiudad(Ciudad ciudad) {
        String nomenclatura = ciudad.getNomenclatura();
        // implementación usando métodos propios del Hash de java y el Set retornado por keySet()
        // para remover llaves que contengan la nomenclatura como fuente o destino
        tuberias.keySet().removeIf((LlaveTuberias llave) -> llave.contieneNomenclatura(nomenclatura));

        grafo.eliminarVertice(ciudad.getNomenclatura());
        Log.baja("Eliminadas todas las tuberias asociadas a la ciudad %s".formatted(nomenclatura));
    }

    public Lista menorCaminoEntre(Ciudad origen, Ciudad destino) {
        return grafo.caminoMasCorto(origen.getNomenclatura(), destino.getNomenclatura());
    }

    public Lista caminoConMenorPleno(Ciudad origen, Ciudad destino) {
        // TODO
        return new Lista();
    }

    /// Se asume que el camino es válido, ordenado y que contiene al menos dos ciudades
    public Estado obtenerEstadoDeCamino(Lista camino) {
        int longitud = camino.longitud();
        Estado estado = Estado.ACTIVO;
        int indiceActual = 2;
        while (estado != Estado.ENDISENIO && indiceActual <= longitud) {
            Ciudad anterior = (Ciudad) camino.recuperar(indiceActual - 1);
            Ciudad actual = (Ciudad) camino.recuperar(indiceActual);
            LlaveTuberias llave = new LlaveTuberias(anterior.getNomenclatura(), actual.getNomenclatura());
            Tuberia.Estado estadoTrecho = tuberias.get(llave).getEstado();

            if (estadoTrecho == Estado.ENDISENIO)
                estado = Estado.ENDISENIO;
            else if (estadoTrecho == Estado.INACTIVO)
                estado = Estado.INACTIVO;
            else if (estadoTrecho == Estado.ENREPARACION)
                if (estado == Estado.ACTIVO)
                        estado = Estado.ENREPARACION;
        }
        return estado;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("Elementos Hash (desordenados): ");
        // hash
        for (LlaveTuberias llave : tuberias.keySet()) {
            Tuberia tuberia = tuberias.get(llave);
            s.append(String.format("\n%s: %s", llave.toString(), tuberia.toString()));
        }

        // grafo
        s.append("\n").append(grafo.toString());
        return s.toString();
    }
}