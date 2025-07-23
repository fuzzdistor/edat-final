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
        String consumoStr= String.format("%.2f", consumo);
        return nombre + " - " + consumoStr + " m3";
    }
}

