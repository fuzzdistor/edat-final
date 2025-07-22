package Estructuras;

public class Grafo {

    private NodoVert inicio;

    public Grafo() {
        inicio = null;
    };// constructor vacio

    public boolean insertarVertice(Object nuevoVertice) {
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoVertice, this.inicio);
            exito = true;
        }
        return exito;
    }

    private NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean existeVertice(NodoVert nodito) {
        boolean res = false;
        if (nodito != null) {
            res = ubicarVertice(nodito.getElem()) != null;
        }

        return res;
    }

    public boolean existeArco(Object elemOrigen, Object elemDestino) {
        // Busco los vértices
        NodoVert origen = ubicarVertice(elemOrigen);
        NodoVert destino = ubicarVertice(elemDestino);
        boolean exito = false;

        // Busco el arco si ambos vértices existen
        if (origen != null && destino != null) {
            // Recorro los nodos adyacentes del origen
            NodoAdy arco = origen.getPrimerAdy();
            while (arco != null && !exito) {
                if (arco.getVertice() == destino) {
                    exito = true;
                }
                arco = arco.getSigAdyacente();
            }
        }

        return exito;
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        NodoVert nodoActual = this.inicio;

        // Recorre todos los nodos del grafo
        while (nodoActual != null) {
            if (visitados.localizar(nodoActual.getElem()) == -1) {
                profundidadDesde(nodoActual, visitados);
            }
            nodoActual = nodoActual.getSigVertice();
        }
        return visitados;
    }

    private void profundidadDesde(NodoVert nodo, Lista visitados) {
        // inserta el nodo
        visitados.insertar(nodo.getElem(), visitados.longitud() + 1);

        // Recorrer todos los adyacentes del nodo
        NodoAdy arista = nodo.getPrimerAdy();
        while (arista != null) {
            NodoVert nodoAdyacente = arista.getVertice();
            if (visitados.localizar(nodoAdyacente.getElem()) == -1) {
                profundidadDesde(nodoAdyacente, visitados);
            }
            arista = arista.getSigAdyacente();
        }
    }

    public Lista listarEnAnchura() {
        Lista visitados = new Lista();
        NodoVert nodo = this.inicio;

        while (nodo != null) {
            if (visitados.localizar(nodo.getElem()) == -1) {
                anchuraDesde(nodo, visitados);
            }
            nodo = nodo.getSigVertice(); // Faltaba esta línea
        }
        return visitados;
    }

    private void anchuraDesde(NodoVert nodoInicial, Lista visitados) {
        Cola cola = new Cola();
        visitados.insertar(nodoInicial.getElem(), visitados.longitud() + 1);
        cola.poner(nodoInicial);

        while (!cola.esVacia()) {
            Object nodoActual = cola.obtenerFrente();
            cola.sacar(); // falta el metodo

            // Recorre los adyacentes del nodo actual
            NodoAdy adyacente = nodoInicial.getPrimerAdy();
            if (adyacente != null) {
                nodoInicial = adyacente.getVertice();
                while (adyacente != null) {
                    NodoVert nodoAdyacente = adyacente.getVertice();
                    if (visitados.localizar(nodoAdyacente.getElem()) == -1) {
                        visitados.insertar(nodoAdyacente.getElem(), visitados.longitud() + 1);
                        cola.poner(nodoAdyacente);
                    }
                    adyacente = adyacente.getSigAdyacente(); // Avanza al siguiente adyacente
                }
            }
        }
    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
        boolean exito = false;
        NodoVert nodoOrigen = this.ubicarVertice(origen);
        NodoVert nodoDestino = this.ubicarVertice(destino);

        if (nodoOrigen != null && nodoDestino != null && etiqueta != null) {
            // Verifico si el arco ya existe 
            NodoAdy arcoActual = nodoOrigen.getPrimerAdy();
            while (arcoActual != null && !exito) {
                if (arcoActual.getVertice().getElem().equals(destino) &&
                        arcoActual.getEtiqueta().equals(etiqueta)) {
                    exito = true;
                }
                arcoActual = arcoActual.getSigAdyacente();
            }

            if (!exito) {
                // Insertar nuevo arco etiquetado
                NodoAdy nuevoArco = new NodoAdy(nodoDestino, nodoOrigen.getPrimerAdy(), etiqueta);
                nodoOrigen.setPrimerAdy(nuevoArco);
                exito = true; 
            }
        }
        return exito;
    }

    public boolean eliminarVertice(NodoVert origen, NodoVert destino) {

        boolean exito = false;

        NodoVert elNodo = this.inicio;
        if (elNodo != null && (origen != null && destino != null)) {

            // caso especial cuando se elimina el primer vértice
            if (elNodo.getElem().equals(origen.getElem())) {

                // Si existe el nodo destino
                if (ubicarVertice(destino.getElem()) != null) {// Actualizar el inicio y eliminar los arcos del vertice

                    this.inicio = elNodo.getSigVertice();
                    // Eliminar todos los arcos del vertice
                    eliminarArco(origen, destino);

                    exito = true;
                }

            } else {// sino busca al vertice

                elNodo = elNodo.getSigVertice();
                NodoVert anterior = elNodo;

                while (elNodo != null && !elNodo.getElem().equals(origen)) {
                    anterior = elNodo;
                    elNodo = elNodo.getSigVertice();
                }

                // Si se encuentra el vertice a eliminar
                if (elNodo != null) {
                    anterior.setSigVertice(elNodo.getSigVertice()); // El nodo anterior se enlaza con el siguiente del
                                                                    // Nodo Actual

                    // Eliminar todas los arcos del vertice
                    eliminarArco(origen, destino);
                    exito = true;
                }
            }
        }
        return exito;
    }

    public boolean eliminarArco(NodoVert origen, NodoVert destino) {
        boolean exito = false;

        if (existeVertice(origen) && existeVertice(destino)) { // Si existen los vertices

            // Buscar el vertice destino en la lista de adyacencia
            NodoAdy adyacenteActual = origen.getPrimerAdy();
            NodoAdy adyacenteAnterior = null;

            while (adyacenteActual != null) {

                if (adyacenteActual.getVertice() == destino) {
                    // Se encontro el arco a eliminar

                    // Caso especial, es el primer adyacente
                    if (adyacenteAnterior == null) {
                        origen.setPrimerAdy(adyacenteActual.getSigAdyacente()); // Lo enlaza con el siguiente

                    } else {// Esta en el medio o final
                        adyacenteAnterior.setSigAdyacente(adyacenteActual.getSigAdyacente()); // El enlaza el anterior
                                                                                              // con el siguiente
                    }
                    exito = true;
                }

                adyacenteAnterior = adyacenteActual;
                adyacenteActual = adyacenteActual.getSigAdyacente();

            }
        }
        return exito;
    }

    public boolean existeCamino(Object origen, Object destino) {
        NodoVert nodoOrigen = ubicarVertice(origen);
        NodoVert nodoDestino = ubicarVertice(destino);
        boolean existen = nodoOrigen != null && nodoDestino != null;
        boolean sonIguales = nodoOrigen == nodoDestino;

        boolean hayCamino = existen && sonIguales;

        if (existen && !sonIguales) {
            Lista visitados = new Lista();
            hayCamino = existeCaminoAux(nodoOrigen, nodoDestino, visitados);
        }

        return hayCamino;
    }

    private boolean existeCaminoAux(NodoVert actual, NodoVert destino, Lista visitados) {
        boolean encontrado = false;
        visitados.insertar(actual.getElem(), visitados.longitud() + 1);

        NodoAdy adyacente = actual.getPrimerAdy();
        while (adyacente != null) {
            NodoVert vecino = adyacente.getVertice();

            encontrado = vecino == destino;

            if (!encontrado && visitados.localizar(vecino.getElem()) < 0) {
                if (existeCaminoAux(vecino, destino, visitados)) {
                    encontrado = true;
                }
            }

            if (!encontrado) {
                adyacente = adyacente.getSigAdyacente();
            }
        }
        return encontrado;
    }

    public boolean esVacio() {
        return this.inicio == null;
    }

    public Grafo clone() {
        Grafo grafoClone = new Grafo();

        // Inserta los vertices en el nuevov grafo
        NodoVert nodoActual = this.inicio;

        while (nodoActual != null) {
            grafoClone.insertarVertice(nodoActual.getElem());
            nodoActual = nodoActual.getSigVertice();
        }

        // Recorre denuevo para insertar todos los arcos
        nodoActual = this.inicio;
        while (nodoActual != null) {
            NodoAdy arco = nodoActual.getPrimerAdy();
            while (arco != null) {
                grafoClone.insertarArco(nodoActual.getElem(), nodoActual.getSigVertice().getElem(), arco.getEtiqueta());
                arco = arco.getSigAdyacente();
            }
            nodoActual = nodoActual.getSigVertice();
        }

        return grafoClone;
    }

}
