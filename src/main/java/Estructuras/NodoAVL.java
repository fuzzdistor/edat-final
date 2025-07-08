package Estructuras;


public class NodoAVL {
    private Comparable elemento;
    private NodoAVL hijoIzquierdo;
    private NodoAVL hijoDerecho;
    private int altura;

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void recalcularAltura() {
        altura = Math.max(hijoDerecho == null? -1 : hijoDerecho.altura,
                hijoIzquierdo == null? -1 : hijoIzquierdo.altura) + 1;
    }

    public Comparable getElem() {
        return elemento;
    }

    public void setElem(Comparable elemento) {
        this.elemento = elemento;
    }

    public NodoAVL getIzquierdo() {
        return hijoIzquierdo;
    }

    public void setIzquierdo(NodoAVL hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoAVL getDerecho() {
        return hijoDerecho;
    }

    public void setDerecho(NodoAVL hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public NodoAVL(Comparable elemento) {
        this.elemento = elemento;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    public NodoAVL(Comparable elemento, NodoAVL hijoIzquierdo, NodoAVL hijoDerecho) {
        this.elemento = elemento;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }
}
