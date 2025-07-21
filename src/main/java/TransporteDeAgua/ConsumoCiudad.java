package TransporteDeAgua;

public class ConsumoCiudad implements Comparable {
    private String nombre;
    private double consumo;

    public ConsumoCiudad(String nombre, double consumo) {
        this.nombre = nombre;
        this.consumo = consumo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getConsumo() {
        return consumo;
    }

    public int compareTo(Object otra) {
        ConsumoCiudad otraCiudad = (ConsumoCiudad) otra;

        return Double.compare(this.consumo, otraCiudad.consumo);
    }

    
    public String toString() {
        return nombre + " - " + consumo + " m3";
    }
}

