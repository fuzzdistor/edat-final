package Estructuras;

public class Cola {
    private Nodo frente = null;
    private Nodo fin = null;

    public Cola() {}

    public boolean poner(Object elemento) {
        if (frente == null) {
            frente = new Nodo(elemento, null);
            fin = frente;
        } else {
            Nodo nuevoNodo = new Nodo(elemento, null);
            fin.setEnlace(nuevoNodo);
            fin = nuevoNodo;
        }
        return true;
    }

    public boolean sacar() {
        boolean exito = frente != null;
        if (exito) {
            frente = frente.getEnlace();
            if (frente == null) {
                fin = null;
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object elem = null;
        if (frente != null) {
            elem = frente.getElem();
        }
        return elem;
    }

    public boolean esVacia() {
        return frente == null;
    }

    public void vaciar() {
        frente = null;
        fin = null;
    }

    // clonado iterativo de nodos
    public Cola clone() {
        Cola nuevaCola = new Cola();
        if (this.frente != null) {
            nuevaCola.frente = new Nodo(this.frente.getElem(), null);
            nuevaCola.fin = nuevaCola.frente;
            Nodo inodo = this.frente.getEnlace();
            while (inodo != null) {
                Nodo nuevoNodo = new Nodo(inodo.getElem(), null);
                nuevaCola.fin.setEnlace(nuevoNodo);
                nuevaCola.fin = nuevoNodo;
                inodo = inodo.getEnlace();
            }
        }
        return nuevaCola;
    }

    public String toString() {
        String s = "[";
        Nodo inodo = this.frente;
        while (inodo != null) {
            s += inodo.getElem().toString();
            if (inodo.getEnlace() != null) {
                s += ',';
            }
            inodo = inodo.getEnlace();
        }
        s += ']';
        return s;
    }
}