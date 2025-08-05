package TransporteDeAgua;

import Estructuras.Grafo;
import Estructuras.Lista;

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
        }
        return !existe;
    }

    public boolean crearTuberia(Ciudad fuente, Ciudad destino, double caudalMin, double caudalMax, double diametro, Tuberia.Estado estado) {
        return crearTuberia(fuente.getNomenclatura(), destino.getNomenclatura(), caudalMax, caudalMin, diametro, estado);
    }

    private boolean setCaudalMax(String fuente, String destino, double caudalMax) {
        Tuberia tuberia = getTuberia(fuente, destino);
        if (tuberia != null) {
            tuberia.setCaudalMax(caudalMax);
            grafo.eliminarArco(fuente, destino);
            grafo.insertarArco(fuente, destino, caudalMax);
        }
        return tuberia != null;
    }

    public boolean setCaudalMax(Ciudad fuente, Ciudad destino, double caudalMax) {
        return setCaudalMax(fuente.getNomenclatura(), destino.getNomenclatura(), caudalMax);
    }


    private boolean setCaudalMin(String fuente, String destino, double caudalMin) {
        Tuberia tuberia = getTuberia(fuente, destino);
        if (tuberia != null) {
            tuberia.setCaudalMin(caudalMin);
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
        }
        return tuberia != null;
    }

    public void eliminarTuberiasDeCiudad(Ciudad ciudad) {
        String nomenclatura = ciudad.getNomenclatura();
        // implementación usando métodos propios del Hash de java y el Set retornado por keySet()
        // para remover llaves que contengan la nomenclatura como fuente o destino
        tuberias.keySet().removeIf((LlaveTuberias llave) -> llave.contieneNomenclatura(nomenclatura));

        grafo.eliminarVertice(ciudad.getNomenclatura());
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