package TransporteDeAgua;

import Estructuras.Grafo;

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

    }

    private final HashMap<LlaveTuberias, Tuberia> tuberias;
    private Grafo grafo;


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
            // TODO meter en el grafo.
        }
        return !existe;
    }

    public boolean crearTuberia(Ciudad fuente, Ciudad destino, double caudalMin, double caudalMax, double diametro, Tuberia.Estado estado) {
        return crearTuberia(fuente.getNomenclatura(), destino.getNomenclatura(), caudalMin, caudalMin, diametro, estado);
    }

    public boolean setCaudalMax(String fuente, String destino, double caudalMax) {
        // TODO checkear si la tuberia existe, modificarla y modificar la etiqueta en el grafo
        return false;
    }

    public boolean setCaudalMin(String fuente, String destino, double caudalMin) {
        // TODO checkear si la tuberia existe y modificarla
        return false;
    }

    public boolean eliminarTuberia(Ciudad fuente, Ciudad destino) {
        return eliminarTuberia(fuente.getNomenclatura(), destino.getNomenclatura());
    }

    public boolean eliminarTuberia(String nomenclaturaFuente, String nomenclaturaDestino) {
        return eliminarTuberia(new LlaveTuberias(nomenclaturaFuente, nomenclaturaDestino));
    }

    private boolean eliminarTuberia(LlaveTuberias llave) {
        boolean existe = tuberias.containsKey(llave);
        if (existe) {
            tuberias.remove(llave);
            // TODO eliminar del grafo.
        }
        return existe;
    }

    public Tuberia getTuberia(Ciudad fuente, Ciudad destino) {
        return getTuberia(fuente.getNomenclatura(), destino.getNomenclatura());
    }

    public Tuberia getTuberia(String nomenclaturaFuente, String nomenclaturaDestino) {
        return tuberias.get(new LlaveTuberias(nomenclaturaFuente, nomenclaturaDestino));
    }
}