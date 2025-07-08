package Estructuras;


public class ArbolAVL {
    private NodoAVL raiz = null;

    public ArbolAVL(){};

    
    private NodoAVL balancear(NodoAVL nodo) {
        if (nodo != null) {
            nodo.recalcularAltura();
            int balance = calcularBalance(nodo);
            if (balance < -1) {
                int balanceHijoDer = calcularBalance(nodo.getDerecho());
                if (balanceHijoDer <= 0) {
                    // rotacion simple izquierda
                    nodo = rotacionIzquierda(nodo);
                } else {
                    // rotacion doble derecha-izquierda
                    nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
                    nodo = rotacionIzquierda(nodo);
                }
            } else if (balance > 1) {
                int balanceHijoIzq = calcularBalance(nodo.getIzquierdo());
                if (balanceHijoIzq >= 0) {
                    // rotacion simple a derecha
                    nodo = rotacionDerecha(nodo);
                } else {
                    // rotacion doble izquierda-derecha
                    nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
                    nodo = rotacionDerecha(nodo);
                }
            }
        }
        return nodo;
    }

    private NodoAVL rotacionIzquierda(NodoAVL padre) {
        NodoAVL hd = padre.getDerecho();
        NodoAVL temp = hd.getIzquierdo();
        hd.setIzquierdo(padre);
        padre.setDerecho(temp);
        padre.recalcularAltura();
        hd.recalcularAltura();
        return hd;
    }

    private NodoAVL rotacionDerecha(NodoAVL padre) {
        NodoAVL hi = padre.getIzquierdo();
        NodoAVL temp = hi.getDerecho();
        hi.setDerecho(padre);
        padre.setIzquierdo(temp);
        padre.recalcularAltura();
        hi.recalcularAltura();
        return hi;
    }
    
    private int calcularBalance(NodoAVL nodo) {
        int alturaIzq = nodo.getIzquierdo() == null? -1: nodo.getIzquierdo().getAltura();
        int alturaDer = nodo.getDerecho() == null? -1: nodo.getDerecho().getAltura();
        return alturaIzq - alturaDer;
    }
    
    public void vaciar() {
        raiz = null;
    }

    public boolean vacio() {
        return raiz == null;
    }
    
    public boolean insertar(Comparable elemento) {
        boolean[] b = { false };
        raiz = insertar(elemento, raiz, b);
        return b[0];
    }
    
    private NodoAVL insertar(Comparable elemento, NodoAVL nodo, boolean[] b) {
        if (nodo == null) {
            nodo = new NodoAVL(elemento);
            b[0] = true;
        } else {
            int res = nodo.getElem().compareTo(elemento);
            if (res < 0) {
                nodo.setDerecho(insertar(elemento, nodo.getDerecho(), b));
            } else if (res > 0) {
                nodo.setIzquierdo(insertar(elemento, nodo.getIzquierdo(), b));
            }
        }

        return b[0]? balancear(nodo) : nodo;
    }

    private NodoAVL eliminar(Comparable elemento, NodoAVL nodo, boolean[] b) {
        if (nodo != null) {
            int comp = nodo.getElem().compareTo(elemento);
            if (comp > 0)
            nodo.setIzquierdo(eliminar(elemento, nodo.getIzquierdo(), b));
            else if (comp < 0)
            nodo.setDerecho(eliminar(elemento, nodo.getDerecho(), b));
            else if (nodo.getDerecho() != null && nodo.getIzquierdo() != null) {
                // caso dos hijos
                nodo.setElem(minimoElemento(nodo.getDerecho()));
                nodo.setDerecho(eliminar(nodo.getElem(), nodo.getDerecho(), b));
            } else {
                // caso uno o cero hijos
                nodo = nodo.getIzquierdo() != null ? nodo.getIzquierdo(): nodo.getDerecho();
                b[0] = true;
            }
        }
        return b[0]? balancear(nodo) : nodo;
    }
    
    public boolean eliminar(Comparable elemento) {
        boolean[] b = { false };
        raiz = eliminar(elemento, raiz, b);
        return b[0];
    }
    
    private Comparable maximoElemento(NodoAVL nodo) {
        Comparable c = null;
        if (nodo != null) {
            if (nodo.getDerecho() == null) {
                c = nodo.getElem();
            } else {
                c = maximoElemento(nodo.getDerecho());
            }
        }
        return c;
    }
    
    public Comparable maximoElemento() {
        return maximoElemento(raiz);
    }
    
    private Comparable minimoElemento(NodoAVL nodo) {
        Comparable c = null;
        if (nodo != null) {
            if (nodo.getIzquierdo() == null) {
                c = nodo.getElem();
            } else {
                c = minimoElemento(nodo.getIzquierdo());
            }
        }
        return c;
    }

    public Comparable minimoElemento() { return minimoElemento(raiz); }
    
    public boolean pertenece(Comparable elem) {
        return perteneceAux(raiz, elem);
    }

    private boolean perteneceAux(NodoAVL nodo, Comparable elem) {
        boolean resultado = false;
        if (nodo != null) {
            int comp = elem.compareTo(nodo.getElem());
            if (comp == 0) {
                resultado = true;
            } else if (comp < 0) {
                resultado = perteneceAux(nodo.getIzquierdo(), elem);
            } else {
                resultado = perteneceAux(nodo.getDerecho(), elem);
            }
        }
        return resultado;
    }


    private String toString(NodoAVL nodo, String s) {
        if (nodo != null) {
            s += '[' + nodo.getElem().toString() + ", ";
            s += nodo.getIzquierdo() != null? nodo.getIzquierdo().getElem().toString() + ", " : "null, ";
            s += nodo.getDerecho() != null? nodo.getDerecho().getElem().toString() + "], " : "null],";
            s+= "\n" ;
            s = toString(nodo.getIzquierdo(), s);
            s = toString(nodo.getDerecho(), s);
        }
        return s;
    }
    
    public String toString() {

        String s = "[";
        s = toString(raiz, s);
        if (s.length() > 1)
        s = s.substring(0, s.length() - 1);
        return s + ']';
    }
    
    public Lista listarRango(Comparable min, Comparable max){
        Lista ls = new Lista();
        if (this.raiz != null && min.compareTo(max) <= 0) {
            listarRangoAux(raiz, min, max, ls);
        }
        return ls;
    }

    private void listarRangoAux(NodoAVL n, Comparable min, Comparable max, Lista ls){
    if (n != null) {
        Comparable elem = n.getElem();

        if (elem.compareTo(min) > 0) {
            listarRangoAux(n.getIzquierdo(), min, max, ls);
        }

        if (elem.compareTo(min) >= 0 && elem.compareTo(max) <= 0) {
            ls.insertar(elem, ls.longitud() + 1);
        }

        if (elem.compareTo(max) < 0) {
            listarRangoAux(n.getDerecho(), min, max, ls);
        }
    }
}

     public Lista listar(){
        Lista ls = new Lista();
        if (raiz != null) {
            listarInOrden(raiz, ls);
        }
        return ls;
    }
    private void listarInOrden(NodoAVL n, Lista ls){
        if (n != null) {
            listarInOrden(n.getIzquierdo(), ls);
            ls.insertar(n.getElem(), ls.longitud()+1);
            listarInOrden(n.getDerecho(), ls);
        }
    }

    public Comparable obtener(Comparable buscado) {
        return obtenerAux(raiz, buscado);
    }

    private Comparable obtenerAux(NodoAVL nodo, Comparable buscado) {
        Comparable resultado = null;
        if (nodo != null) {
            int cmp = buscado.compareTo(nodo.getElem());
            if (cmp == 0) {
                resultado = nodo.getElem();
            } else if (cmp < 0) {
                resultado = obtenerAux(nodo.getIzquierdo(), buscado);
            } else {
                resultado = obtenerAux(nodo.getDerecho(), buscado);
            }
        }
        return resultado;
    }
}
