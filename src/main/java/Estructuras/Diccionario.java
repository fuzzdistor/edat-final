package Estructuras;


public class Diccionario {
    private NodoAVLDicc raiz = null;

    public Diccionario(){};

    
    private NodoAVLDicc balancear(NodoAVLDicc nodo) {
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

    private NodoAVLDicc rotacionIzquierda(NodoAVLDicc padre) {
        NodoAVLDicc hd = padre.getDerecho();
        NodoAVLDicc temp = hd.getIzquierdo();
        hd.setIzquierdo(padre);
        padre.setDerecho(temp);
        padre.recalcularAltura();
        hd.recalcularAltura();
        return hd;
    }

    private NodoAVLDicc rotacionDerecha(NodoAVLDicc padre) {
        NodoAVLDicc hi = padre.getIzquierdo();
        NodoAVLDicc temp = hi.getDerecho();
        hi.setDerecho(padre);
        padre.setIzquierdo(temp);
        padre.recalcularAltura();
        hi.recalcularAltura();
        return hi;
    }
    
    private int calcularBalance(NodoAVLDicc nodo) {
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
    
    public boolean insertar(Comparable elemento, Object dato) {
        boolean[] b = { false };
        raiz = insertar(elemento, dato, raiz, b);
        return b[0];
    }
    
    private NodoAVLDicc insertar(Comparable elemento, Object dato, NodoAVLDicc nodo, boolean[] b) {
        if (nodo == null) {
            nodo = new NodoAVLDicc(elemento, dato);
            b[0] = true;
        } else {
            int res = nodo.getClave().compareTo(elemento);
            if (res < 0) {
                nodo.setDerecho(insertar(elemento, dato, nodo.getDerecho(), b));
            } else if (res > 0) {
                nodo.setIzquierdo(insertar(elemento, dato, nodo.getIzquierdo(), b));
            }else{
                b[0]=false;
            }
        }

        return b[0]? balancear(nodo) : nodo;
    }

    private NodoAVLDicc eliminar(Comparable elemento, NodoAVLDicc nodo, boolean[] b) {
        if (nodo != null) {
            int comp = nodo.getClave().compareTo(elemento);
            if (comp > 0)
            nodo.setIzquierdo(eliminar(elemento, nodo.getIzquierdo(), b));
            else if (comp < 0)
            nodo.setDerecho(eliminar(elemento, nodo.getDerecho(), b));
            else{ 
                b[0] = true;
            if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                //  buscar sucesor
                NodoAVLDicc sucesor = obtenerMinimoNodo(nodo.getDerecho());
                NodoAVLDicc nuevoNodo = new NodoAVLDicc(sucesor.getClave(), sucesor.getDato());
                nuevoNodo.setIzquierdo(nodo.getIzquierdo());
                nuevoNodo.setDerecho(eliminar(sucesor.getClave(), nodo.getDerecho(), new boolean[]{false}));
                nuevoNodo.recalcularAltura(); 
                nodo = nuevoNodo;
            } else {
                // uno o cero hijos
                nodo = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();
            }
        }
    }
    return b[0] ? balancear(nodo) : nodo;
}
private NodoAVLDicc obtenerMinimoNodo(NodoAVLDicc nodo) {
    while (nodo.getIzquierdo() != null) {
        nodo = nodo.getIzquierdo();
    }
    return nodo;
}
    
    public boolean eliminar(Comparable elemento) {
        boolean[] b = { false };
        raiz = eliminar(elemento, raiz, b);
        return b[0];
    }
    
    private Object maximoElemento(NodoAVLDicc nodo) {
        Object c = null;
        if (nodo != null) {
            if (nodo.getDerecho() == null) {
                c = nodo.getDato();
            } else {
                c = maximoElemento(nodo.getDerecho());
            }
        }
        return c;
    }
    
    public Object maximoElemento() {
        return maximoElemento(raiz);
    }
    
    private Object minimoElemento(NodoAVLDicc nodo) {
        Object c = null;
        if (nodo != null) {
            if (nodo.getIzquierdo() == null) {
                c = nodo.getDato();
            } else {
                c = minimoElemento(nodo.getIzquierdo());
            }
        }
        return c;
    }

    public Object minimoElemento() { return minimoElemento(raiz); }
    
    public boolean existeClave(Comparable elem) {
        return existeClaveAux(raiz, elem);
    }

    private boolean existeClaveAux(NodoAVLDicc nodo, Comparable elem) {
        boolean resultado = false;
        if (nodo != null) {
            int comp = elem.compareTo(nodo.getClave());
            if (comp == 0) {
                resultado = true;
            } else if (comp < 0) {
                resultado = existeClaveAux(nodo.getIzquierdo(), elem);
            } else {
                resultado = existeClaveAux(nodo.getDerecho(), elem);
            }
        }
        return resultado;
    }


    private String toStringDatos(NodoAVLDicc nodo, String s) {
        if (nodo != null) {
            s += '[' + nodo.getDato().toString() + ", ";
            s += nodo.getIzquierdo() != null? nodo.getIzquierdo().getDato().toString() + ", " : "null, ";
            s += nodo.getDerecho() != null? nodo.getDerecho().getDato().toString() + "], " : "null],";
            s+= "\n" ;
            s = toStringDatos(nodo.getIzquierdo(), s);
            s = toStringDatos(nodo.getDerecho(), s);
        }
        return s;
    }
    
    public String toStringDatos() {

        String s = "[";
        s = toStringDatos(raiz, s);
        if (s.length() > 1)
        s = s.substring(0, s.length() - 1);
        return s + ']';
    }

    public String toStringClaves() {
        String resultado = "";
        resultado = toStringAux(raiz, resultado);
        return resultado;
    }

    private String toStringAux(NodoAVLDicc nodo, String s) {
        if (nodo != null) {
            // nodo actual
            s += nodo.getClave() + " (alt: " + nodo.getAltura() + ") ";

            // hijo izquierdo
            s += "HI: ";
            s += (nodo.getIzquierdo() != null) ? nodo.getIzquierdo().getClave() : "-";

            // hijo derecho
            s += " HD: ";
            s += (nodo.getDerecho() != null) ? nodo.getDerecho().getClave() : "-";

            s += "\n";
            s = toStringAux(nodo.getIzquierdo(), s);
            s = toStringAux(nodo.getDerecho(), s);
        }
        return s;
    }


    
    public Lista listarRangoDatos(Comparable min, Comparable max){
        Lista ls = new Lista();
        if (this.raiz != null && min.compareTo(max) <= 0) {
            listarRangoDatosAux(raiz, min, max, ls);
        }
        return ls;
    }

    private void listarRangoDatosAux(NodoAVLDicc n, Comparable min, Comparable max, Lista ls) {
        if (n != null) {
            Comparable clave = n.getClave();
           

            if (clave.compareTo(min) > 0) {
                listarRangoDatosAux(n.getIzquierdo(), min, max, ls);
            }

            if (clave.compareTo(min) >= 0 && clave.compareTo(max) <= 0) {
                ls.insertar(  n.getDato(), ls.longitud() + 1); 
            }

            if (clave.compareTo(max) < 0) {
                listarRangoDatosAux(n.getDerecho(), min, max, ls);
            }
        }
    }



     public Lista listarRangoClaves(Comparable min, Comparable max){
        Lista ls = new Lista();
        if (this.raiz != null && min.compareTo(max) <= 0) {
            listarRangoClavesAux(raiz, min, max, ls);
        }
        return ls;
    }

    private void listarRangoClavesAux(NodoAVLDicc n, Comparable min, Comparable max, Lista ls) {
        if (n != null) {
            Comparable clave = n.getClave();
           

            if (clave.compareTo(min) > 0) {
                listarRangoClavesAux(n.getIzquierdo(), min, max, ls);
            }

            if (clave.compareTo(min) >= 0 && clave.compareTo(max) <= 0) {
                ls.insertar( clave, ls.longitud() + 1); 
            }

            if (clave.compareTo(max) < 0) {
                listarRangoClavesAux(n.getDerecho(), min, max, ls);
            }
        }
    }



     public Lista listarClaves(){
        Lista ls = new Lista();
        if (raiz != null) {
            listarClavesAux(raiz, ls);
        }
        return ls;
    }
    private void listarClavesAux(NodoAVLDicc n, Lista ls) {
        if (n != null) {
            listarClavesAux(n.getIzquierdo(), ls);
            ls.insertar(n.getClave(), ls.longitud() + 1);
            listarClavesAux(n.getDerecho(), ls);
        }
    }



    public Lista listarDatos(){
        Lista ls = new Lista();
        if (raiz != null) {
            listarDatosAux(raiz, ls);
        }
        return ls;
    }
    private void listarDatosAux(NodoAVLDicc n, Lista ls) {
        if (n != null) {
            listarDatosAux(n.getIzquierdo(), ls);
            ls.insertar(n.getDato(), ls.longitud() + 1);
            listarDatosAux(n.getDerecho(), ls);
        }
    }


    public Object recuperar(Comparable buscado) {
        return recuperarAux(raiz, buscado);
    }

    private Object recuperarAux(NodoAVLDicc nodo, Comparable buscado) {
        Object resultado = null;
        if (nodo != null) {
            int cmp = buscado.compareTo(nodo.getClave());
            if (cmp == 0) {
                resultado = nodo.getDato();
            } else if (cmp < 0) {
                resultado = recuperarAux(nodo.getIzquierdo(), buscado);
            } else {
                resultado = recuperarAux(nodo.getDerecho(), buscado);
            }
        }
        return resultado;
    }

    
}
