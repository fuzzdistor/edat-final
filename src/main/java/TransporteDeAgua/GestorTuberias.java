package TransporteDeAgua;

import java.util.HashMap;

public class GestorTuberias {
    private HashMap<String, Tuberia> tuberias;

    public GestorTuberias() {
        this.tuberias = new HashMap<>();
    }

    private String generarNomenclatura(Ciudad fuente, Ciudad destino) {
        return fuente.getNomenclatura() + '-' + destino.getNomenclatura();
    }

    public boolean crearTuberia(Ciudad fuente, Ciudad destino, double caudalMin, double caudalMax, double diametro, Tuberia.Estado estado) {
        String nomenclatura = generarNomenclatura(fuente, destino);
        boolean existe = tuberias.containsKey(nomenclatura);
        if (!existe) {
            Tuberia tuberia = new Tuberia(fuente, destino, caudalMin, caudalMax, diametro, estado);
            tuberias.put(nomenclatura, tuberia);
        }
        return !existe;
    }

    public Tuberia getTuberia(Ciudad fuente, Ciudad destino) {
        return getTuberia(generarNomenclatura(fuente, destino));
    }

    public Tuberia getTuberia(String nomenclatura) {
        return tuberias.get(nomenclatura);
    }
}