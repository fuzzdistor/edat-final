package Estructuras;

public class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Object etiqueta;

    public NodoAdy(NodoVert elVertice, NodoAdy elAdyacente, Object laEtiqueta) {
        vertice = elVertice;
        sigAdyacente = elAdyacente;
        etiqueta = laEtiqueta;
    }

    public Object getEtiqueta() {
        return etiqueta;
    }

    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }

    public NodoVert getVertice() {
        return vertice;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }

    public void setEtiqueta(Object etiqueta) {
        this.etiqueta = etiqueta;
    }
}

