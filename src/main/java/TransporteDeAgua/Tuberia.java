package TransporteDeAgua;

public class Tuberia {
    /**
     * De las tuberías se guarda la siguiente información:
     * Nomenclatura
     * Formato: Nomenclatura ciudad desde-Nomenclaturaciudad hasta, por ejemplo: si las ciudades son Neufuén y Cupral-Có sería NE3001-CC3002
     * Caudal mínimo en metros cúbicos por hora
     * Caudal máximo en metros cúbicos por hora
     * Diámetro de la tubería en milímetros
     * Estado: ACTIVO, EN REPARACIÓN, EN DISEÑO, INACTIVO.
     **/
    public enum Estado {
        Activo,
        EnReparacion,
        EnDisenio,
        Inactivo
    }

    private final String nomenclaturaFuente;
    private final String nomenclaturaDestino;
    private double caudalMin;
    private double caudalMax;
    private double diametro;
    private Estado estado;

    public Tuberia(String nomenclaturaFuente, String nomenclaturaDestino, double caudalMin, double caudalMax, double diametro, Estado estado) {
        this.nomenclaturaFuente = nomenclaturaFuente;
        this.nomenclaturaDestino = nomenclaturaDestino;
        this.caudalMin = caudalMin;
        this.caudalMax = caudalMax;
        this.diametro = diametro;
        this.estado = estado;
    }

    public String getNomenclatura() {
        return nomenclaturaFuente + '-' + nomenclaturaDestino;
    }

    public String getNomenclaturaFuente() {
        return nomenclaturaFuente;
    }

    public String getNomenclaturaDestino() {
        return nomenclaturaDestino;
    }

    public double getCaudalMin() {
        return caudalMin;
    }

    public void setCaudalMin(double caudalMin) {
        this.caudalMin = caudalMin;
    }

    public double getCaudalMax() {
        return caudalMax;
    }

    public void setCaudalMax(double caudalMax) {
        this.caudalMax = caudalMax;
    }

    public double getDiametro() {
        return diametro;
    }

    public void setDiametro(double diametro) {
        this.diametro = diametro;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String toString() {
        return "Tuberia{" +
                "fuente=" + nomenclaturaFuente +
                ", destino=" + nomenclaturaDestino +
                ", caudalMin=" + caudalMin +
                ", caudalMax=" + caudalMax +
                ", diametro=" + diametro +
                ", estado=" + estado +
                '}';
    }
}
