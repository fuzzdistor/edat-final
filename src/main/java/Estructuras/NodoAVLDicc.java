package Estructuras;

public class NodoAVLDicc {
    private Comparable clave;
    private Object dato;
    private int altura;
    private NodoAVLDicc hijoIzquierdo;
    private NodoAVLDicc hijoDerecho;

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void recalcularAltura() {
        altura = Math.max(hijoDerecho == null ? -1 : hijoDerecho.altura,
                hijoIzquierdo == null ? -1 : hijoIzquierdo.altura) + 1;
    }

    public Object getDato() {
        return dato;
    }

    public Comparable getClave() {
        return clave;
    }

    public void setClave(Comparable clave) {
        this.clave = clave;
    }
    public void setDato(Object dato) {
        this.dato = dato;
    }


    public NodoAVLDicc getIzquierdo() {
        return hijoIzquierdo;
    }

    public void setIzquierdo(NodoAVLDicc hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoAVLDicc getDerecho() {
        return hijoDerecho;
    }

    public void setDerecho(NodoAVLDicc hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public NodoAVLDicc(Comparable clave, Object dato) {
        this.clave = clave;
        this.dato = dato;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    public NodoAVLDicc(Comparable clave, Object dato, NodoAVLDicc hijoIzquierdo, NodoAVLDicc hijoDerecho) {
        this.clave = clave;
        this.dato = dato;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }
}
    

