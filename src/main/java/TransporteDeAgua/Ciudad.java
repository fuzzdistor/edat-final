package TransporteDeAgua;

import Estructuras.ArbolAVL;

public class Ciudad {
    private String nombre;
    private ArbolAVL habitantesPorFecha;
    private double superficie;//en m2
    private double consumoPromedio; // m3/persona/dia
    private String nomenclatura;

    public Ciudad(String nombre, double superficie, double consumo, String nomenclatura) {
        this.nombre = nombre.toUpperCase();
        this.nomenclatura = nomenclatura.trim().toUpperCase();
        this.superficie = superficie;
        this.consumoPromedio = consumo;
        this.habitantesPorFecha = new ArbolAVL();
    }

    public Ciudad(String nombre, String nomenclatura) {
        this.nombre = nombre.trim().toUpperCase();
        this.superficie = 0;
        this.consumoPromedio = 0;
        this.habitantesPorFecha = new ArbolAVL();
        this.nomenclatura = nomenclatura;
    }

    public String getNombre() {
        return nombre;
    }

    public double getConsumoPromedio() {
        return consumoPromedio;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public double getSuperficie() {
        return superficie;
    }

    public int getHabitantes(int anio, int mes) {
        int resultado = -1;

        if (mes >= 1 && mes <= 12) {
            RegistroAnio registro = (RegistroAnio) habitantesPorFecha.obtener(new RegistroAnio(anio));
            if (registro != null) {
                resultado = registro.getDatoMes(mes);
            }
        }

        return resultado;
    }

    public double getConsumoMes(int anio, int mes) {
        double consumo = 0;
        int cantidadHabitantes = getHabitantes(anio, mes);
        if (cantidadHabitantes > 0) {
            consumo = cantidadHabitantes * consumoPromedio * 30;
        }
        return consumo;
    }

    public boolean setConsumoPromedio(double unConsumo) {
        boolean exito = false;
        if (unConsumo > 0) {
            this.consumoPromedio = unConsumo;
            exito = true;
        }
        return exito;

    }

    public boolean setHabitantes(int anio, int mes, int cantidad) {
        boolean exito = false;

        if (cantidad > 0 && mes >= 1 && mes <= 12) {
            RegistroAnio registro = (RegistroAnio) habitantesPorFecha.obtener(new RegistroAnio(anio));

            if (registro == null) {
                registro = new RegistroAnio(anio);
                habitantesPorFecha.insertar(registro);
            }

            registro.setDatoMensual(mes, cantidad);
            exito = true;
        }

        return exito;
    }

    public void setHabitantesAnio(int anio, int[] datos) {
        RegistroAnio registro = (RegistroAnio) habitantesPorFecha.obtener(new RegistroAnio(anio));
        if (registro == null) {
            registro = new RegistroAnio(anio);
            habitantesPorFecha.insertar(registro);
        }
        registro.setDatoAnual(datos);

    }

    public double getConsumoAnual(int anio) {
        RegistroAnio registro = (RegistroAnio) habitantesPorFecha.obtener(new RegistroAnio(anio));
        double suma = 0;
        if (registro != null) {
            suma = 0;
            for (int i = 1; i <= 12; i++) {
                double valor = getConsumoMes(anio, i); // meses del 1 al 12
                if (valor != -1) {
                    suma += valor;
                }
            }
        }
        return suma;
    }


    public String toString() {
        return "Ciudad{" +
                "nombre='" + nombre + '\'' +
                ", nomenclatura='" + nomenclatura + '\'' +
                ", superficie=" + superficie + " m²" +
                ", consumoPromedio=" + consumoPromedio + " m³/persona/día" +
                '}';

    }


}