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

    private Ciudad fuente;
    private Ciudad destino;
    private double caudalMin;
    private double caudalMax;
    private double diametro;
    private Estado estado;

    public Tuberia(Ciudad fuente, Ciudad destino, double caudalMin, double caudalMax, double diametro, Estado estado) {
        this.fuente = fuente;
        this.destino = destino;
        this.caudalMin = caudalMin;
        this.caudalMax = caudalMax;
        this.diametro = diametro;
        this.estado = estado;
    }

    public String getNomenclatura() {
        return fuente.getNomenclatura() + '-' + destino.getNomenclatura();
    }

    public Ciudad getFuente() {
        return fuente;
    }

    public void setFuente(Ciudad fuente) {
        this.fuente = fuente;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
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
                "fuente=" + fuente.getNomenclatura() +
                ", destino=" + destino.getNomenclatura() +
                ", caudalMin=" + caudalMin +
                ", caudalMax=" + caudalMax +
                ", diametro=" + diametro +
                ", estado=" + estado +
                '}';
    }
}
