package Estructuras;

public class HeapMax {
    private static int TAMANIO;
    private Comparable[] heap;
    private int ultimo;

    public HeapMax(int capacidad) {
        this.TAMANIO = capacidad + 1;
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = true;
        if (this.ultimo >= heap.length - 1) {
            exito = false; // heap lleno
        } else {
            this.ultimo++;
            heap[this.ultimo] = elemento;
            hacerSubir(this.ultimo);
        }
        return exito;
    }

    private void hacerSubir(int posHijo) {
        int posPadre = posHijo / 2;
        Comparable temp = heap[posHijo];

        while (posHijo > 1 && temp.compareTo(heap[posPadre]) > 0) {
            heap[posHijo] = heap[posPadre];
            posHijo = posPadre;
            posPadre = posHijo / 2;
        }

        heap[posHijo] = temp;
    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0) {
            exito = false;
        } else {
            heap[1] = heap[ultimo];
            ultimo--;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posH = posPadre * 2;

            if (posH <= this.ultimo) {
                // tiene al menos hijo izquierdo
                if (posH < this.ultimo) {
                    // tiene tambien hijo derecho
                    if (heap[posH + 1].compareTo(heap[posH]) > 0) {
                        posH++; // hijo derecho es mayor
                    }
                }

                if (heap[posH].compareTo(temp) > 0) {
                    // el hijo es mayor que el padre
                    heap[posPadre] = heap[posH];
                    posPadre = posH;
                } else {
                    salir = true;
                }
            } else {
                salir = true;
            }
        }

        heap[posPadre] = temp;
    }

    public Comparable recuperarCima() {
        Comparable resultado = null;
        if (this.ultimo != 0) {
            resultado = heap[1];
        }
        return resultado;
    }

    public boolean esVacio() {
        return this.ultimo == 0;
    }
}

