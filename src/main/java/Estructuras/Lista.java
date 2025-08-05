package Estructuras;

public class Lista {
    private Nodo cabecera;
    private int longitud;

    public Lista() {
        cabecera = null;
        longitud = 0;
    }

    public boolean insertar(Object nuevoElem, int pos) {

        boolean exito = true;

        if (pos < 1 || pos > this.longitud + 1) {
            exito = false;
        } else {

            if (pos == 1) {
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            longitud++;
        }
        return exito;
    }


    public boolean eliminar(int pos) {
        boolean exito = false;

        if (pos >= 1 && pos <= this.longitud) {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            exito = true;
            this.longitud--;
        }
        return exito;
    }


    public Object recuperar(int pos) {
        Object elemento = null;
        Nodo aux = cabecera;
        if (aux != null) {
            if (1 <= pos && pos <= longitud) {
                int i = 1;
                while (i != pos) {
                    aux = aux.getEnlace();
                    i++;
                }
                elemento = aux.getElem();

            }
        }
        return elemento;
    }


    public int localizar(Object elem) {

        Nodo aux = cabecera;
        int i = 1;
        int pos = -1;
        boolean encontrado = false;
        while (aux != null && !encontrado) {
            if (!(aux.getElem().equals(elem))) {
                i++;
                aux = aux.getEnlace();
            } else {
                pos = i;
                encontrado = true;
            }
        }
        return pos;
    }

    public void vaciar() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean esVacia() {

        return cabecera == null;
    }

    public int longitud() {
        return longitud;
    }


    public Lista clone() {

        Lista clon = new Lista();
        if (this.cabecera != null) {

            clon.longitud = this.longitud;

            Nodo auxOr = this.cabecera;

            clon.cabecera = new Nodo(auxOr.getElem(), null);
            auxOr = auxOr.getEnlace();
            Nodo auxClon = clon.cabecera;

            while (auxOr != null) {
                auxClon.setEnlace(new Nodo(auxOr.getElem(), null));
                auxClon = auxClon.getEnlace();
                auxOr = auxOr.getEnlace();
            }
        }
        return clon;
    }

    public String toString() {
        String s = "[";
        if (this.cabecera != null) {

            Nodo aux = this.cabecera;
            while (aux != null) {
                s += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s += ",";
                }
            }
        }
        s += "]";
        return s;
    }


}

