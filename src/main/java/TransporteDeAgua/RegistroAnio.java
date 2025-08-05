package TransporteDeAgua;

public class RegistroAnio implements Comparable {
    private int anio;
    private int[] datosMensuales;

    public RegistroAnio(int anio) {
        this.anio = anio;
        this.datosMensuales = new int[12];
        for (int i = 0; i < 12; i++) {
            datosMensuales[i] = -1;  // valor por defecto: sin datos
        }
    }

    public int getAnio() {
        return anio;
    }

    public int[] getDatosMensuales() {
        return datosMensuales;
    }

    public int getDatoMes(int mes) {
        int indice = mes - 1;
        int res = 0;
        if (indice < 12 && indice >= 0) {
            res = datosMensuales[indice];
        }
        return res;
    }

    public void setDatoMensual(int mes, int cant) {
        int indice = mes - 1;

        if (indice < 12 && indice >= 0) {
            datosMensuales[indice] = cant;
        }
    }

    public void setDatoAnual(int[] datos) {
        datosMensuales = datos;
    }

    public int compareTo(Object otroRegistro) {
        RegistroAnio otro = (RegistroAnio) otroRegistro;
        return anio - otro.anio;
    }


    public String toString() {
        String texto = "Año " + anio + ": [";
        for (int i = 0; i < datosMensuales.length; i++) {
            texto += "Mes " + (i + 1) + ": ";
            if (datosMensuales[i] == -1) {
                texto += "sin registro";
            } else {
                texto += datosMensuales[i];
            }
            if (i < datosMensuales.length - 1) {
                texto += ", ";
            }
        }
        texto += "]";
        return texto;
    }

}
