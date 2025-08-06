package Estructuras;

public class Grafo {

    private NodoVert inicio;

    public Grafo() {
        inicio = null;
    }// constructor vacio

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

    public boolean existeVertice(Object elem) {
        boolean res = false;
        if (elem != null) {
            res = ubicarVertice(elem) != null;
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
                if (arco.getVertice().equals(destino)) {
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
            boolean yaExiste = false;
            NodoAdy arcoActual = nodoOrigen.getPrimerAdy();
            while (!yaExiste && arcoActual != null) {
                // al no tratarse de un multigrafo no admitimos arcos paralelos por más que la etiqueta sea diferente
                if (arcoActual.getVertice().equals(nodoDestino)) {
                    yaExiste = true;
                }
                arcoActual = arcoActual.getSigAdyacente();
            }
            if (!yaExiste) {
                // Insertar nuevo arco etiquetado
                NodoAdy nuevoArco = new NodoAdy(nodoDestino, nodoOrigen.getPrimerAdy(), etiqueta);
                nodoOrigen.setPrimerAdy(nuevoArco);
                exito = true;
            }
        }
        return exito;
    }

    public boolean eliminarVertice(Object elem) {
        boolean exito = false;

        NodoVert anterior = this.inicio;
        NodoVert vertice = this.inicio;

        // busco el vértice y su anterior
        while (vertice != null && !vertice.getElem().equals(elem)) {
            anterior = vertice;
            vertice = vertice.getSigVertice();
        }

        // si existe quito el vértice del gráfo
        if (vertice != null) {
            // caso especial cuando se elimina el primer vértice
            if (vertice.equals(this.inicio)) {
                this.inicio = vertice.getSigVertice();
            } else {
                anterior.setSigVertice(vertice.getSigVertice());
            }
            // recorro todos los vértices limpiando arcos que tengan a vertice como destino
            for (NodoVert inodo = this.inicio; inodo != null; inodo = inodo.getSigVertice()) {
                eliminarArcoVert(inodo, vertice);
            }

            exito = true;
        }
        return exito;
    }

    private boolean eliminarArcoVert(NodoVert origen, NodoVert destino) {
        boolean exito = false;
        NodoAdy anterior = origen.getPrimerAdy();
        NodoAdy adyacente = anterior;

        // busco el arco que termine en destino
        while (adyacente != null && adyacente.getVertice() != destino) {
            anterior = adyacente;
            adyacente = adyacente.getSigAdyacente();
        }

        // si existe elimino el arco
        if (adyacente != null) {
            // caso especial cuando se elimina el primer adyacente
            if (adyacente == anterior) {
                origen.setPrimerAdy(adyacente.getSigAdyacente());
            } else {
                anterior.setSigAdyacente(adyacente.getSigAdyacente());
            }
            exito = true;
        }

        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean exito = false;
        NodoVert vertOrigen = ubicarVertice(origen);
        if (vertOrigen != null) {
            NodoVert vertDestino = ubicarVertice(destino);
            if (vertDestino != null) {
                exito = eliminarArcoVert(vertOrigen, vertDestino);
            }
        }

        return exito;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean hayCamino = false;
        NodoVert nodoOrigen = ubicarVertice(origen);
        if (nodoOrigen != null) {
            NodoVert nodoDestino = ubicarVertice(destino);
            if (nodoDestino != null) {
                if (nodoOrigen.equals(nodoDestino))
                    hayCamino = true;
                else {
                    Lista visitados = new Lista();
                    hayCamino = existeCaminoAux(nodoOrigen, nodoDestino, visitados);
                }
            }
        }
        return hayCamino;
    }

    private boolean existeCaminoAux(NodoVert actual, NodoVert destino, Lista visitados) {
        // implementación de busqueda de camino usando dfs
        boolean encontrado = false;

        if (visitados.localizar(actual) == -1) {

            visitados.insertar(actual, 1);

            NodoAdy adyacente = actual.getPrimerAdy();
            while (!encontrado && adyacente != null) {
                NodoVert vecino = adyacente.getVertice();

                encontrado = vecino.equals(destino);

                if (!encontrado && visitados.localizar(vecino.getElem()) < 0) {
                    encontrado = existeCaminoAux(vecino, destino, visitados);
                }

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

    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista camino = new Lista();
        NodoVert vertOrigen = ubicarVertice(origen);
        if (vertOrigen != null) {
            NodoVert vertDestino = ubicarVertice(destino);
            if (vertDestino != null && vertOrigen != vertDestino) {
                int[] menorLongitud = {Integer.MAX_VALUE};
                Lista caminoActual = new Lista();
                int longActual = 0;
                caminoMasCorto(vertOrigen, vertDestino, caminoActual, longActual, camino, menorLongitud);
            }
        }
        return camino;
    }

    private void caminoMasCorto(NodoVert actual, NodoVert destino, Lista caminoActual, int longActual, Lista camino, int[] menorLongitud) {
        longActual += 1;
        if (longActual < menorLongitud[0]) {
            caminoActual.insertar(actual.getElem(), 1);
            if (actual.getElem().equals(destino.getElem())) {
                camino.vaciar();
                for (int i = 1; i <= longActual; i++)
                    // el camino actual está invertido así que esta forma de copiar el camino es la que se requiere
                    camino.insertar(caminoActual.recuperar(i), 1);
                menorLongitud[0] = longActual;
            } else {
                NodoAdy ady = actual.getPrimerAdy();
                while (ady != null) {
                    if (caminoActual.localizar(ady.getVertice().getElem()) == -1) {
                        caminoMasCorto(ady.getVertice(), destino, caminoActual, longActual, camino, menorLongitud);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            caminoActual.eliminar(1);
        }
    }

    public Lista caminoConMenorEtiqueta(Object origen, Object destino) {
        Lista camino = new Lista();
        NodoVert vertOrigen = ubicarVertice(origen);
        if (vertOrigen != null) {
            NodoVert vertDestino = ubicarVertice(destino);
            if (vertDestino != null && vertOrigen != vertDestino) {
                double[] menorEtiqueta = {Double.MAX_VALUE};
                double menorEtiquetaActual = Double.MAX_VALUE;
                caminoConMenorEtiqueta(vertOrigen, vertDestino, camino, 0, new Lista(), menorEtiqueta, menorEtiquetaActual);
            }
        }
        return camino;
    }

    private void caminoConMenorEtiqueta(NodoVert actual, NodoVert destino, Lista camino, int longActual, Lista caminoActual, double[] menorEtiqueta, double menorEtiquetaActual) {
        longActual += 1;
        caminoActual.insertar(actual.getElem(), 1);
        if (actual.getElem().equals(destino.getElem())) {
            if (menorEtiquetaActual < menorEtiqueta[0]) {
                camino.vaciar();
                for (int i = 1; i <= longActual; i++)
                    // el camino actual está invertido así que esta forma de copiar el camino es la que se requiere
                    camino.insertar(caminoActual.recuperar(i), 1);
                menorEtiqueta[0] = menorEtiquetaActual;
            }
        } else {
            NodoAdy ady = actual.getPrimerAdy();
            while (ady != null) {
                if (caminoActual.localizar(ady.getVertice().getElem()) == -1) {
                    double etiqueta = (double) ady.getEtiqueta();
                    caminoConMenorEtiqueta(ady.getVertice(), destino, caminoActual, longActual, camino, menorEtiqueta, Math.min(etiqueta, menorEtiquetaActual));
                }
                ady = ady.getSigAdyacente();
            }
        }
        caminoActual.eliminar(1);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        NodoVert nodo = inicio;
        s.append("Grafo:");
        while (nodo != null) {
            s.append("\n").append(nodo.getElem().toString()).append(" ->");
            NodoAdy ady = nodo.getPrimerAdy();
            while (ady != null) {
                NodoVert adyVert = ady.getVertice();
                s.append(" ").append(adyVert.getElem().toString()).append("(").append(ady.getEtiqueta().toString()).append(")");
                ady = ady.getSigAdyacente();
                if (ady != null) {
                    s.append(',');
                }
            }
            nodo = nodo.getSigVertice();
        }
        return s.toString();
    }

}
