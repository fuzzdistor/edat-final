package TransporteDeAgua;


import java.util.HashMap;

public class Ciudad  implements Comparable {
    private String nombre;
    private HashMap<String, Integer> habitantesPorFecha;//k. "AAAA-MM" v. "habitantes"
    private double superficie;//en m2
    private double consumoPromedio; // m3/persona/dia
    private String nomeclatura;

    public Ciudad(String nombre, String nomeclatura, double superficie, double consumo){
        this.nombre= nombre.toUpperCase();
        this.nomeclatura=nomeclatura;
        this.superficie=superficie;
        this.consumoPromedio=consumo;
        this.habitantesPorFecha= new HashMap<>();
    }
    
    public String getNombre(){
        return nombre;
    }
    public double getConsumoPromedio() {
        return consumoPromedio;
    }

    public String getNomeclatura() {
        return nomeclatura;
    }
    public double getSuperficie() {
        return superficie;
    }

    public int compareTo(Object otra) {
        Ciudad otraCiudad = (Ciudad)otra;
        return this.nombre.compareTo(otraCiudad.nombre);
    }
    //formato fecha "AAAA-MM"
    public void agregarHabitantes(String fecha, int cantidad){
        habitantesPorFecha.put(fecha, cantidad);
    }
    public int obtenerHabitantes(int anio, int mes){
        String clave= anio +"-" +(mes < 10 ? "0" + mes : mes);;
        return habitantesPorFecha.get(clave);
    }
    
    public String toString() {
        return "Ciudad{" +
            "nombre='" + nombre + '\'' +
            ", nomenclatura='" + nomeclatura + '\'' +
            ", superficie=" + superficie + " m²" +
            ", consumoPromedio=" + consumoPromedio + " m³/persona/día" +
            '}';
    }


    
}