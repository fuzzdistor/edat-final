package Nicolas.Estructuras;

import Estructuras.Grafo;
import Estructuras.Lista;
import org.junit.Test;

import static org.junit.Assert.*;

public class GrafoTest {
    @Test
    public void crearGrafo() {
        Grafo grafo = new Grafo();
        assertTrue(grafo.esVacio());
        assertFalse(grafo.existeVertice(1));
        assertFalse(grafo.existeVertice(null));
        assertFalse(grafo.existeArco(1, 1));
        assertFalse(grafo.existeArco(null, null));
    }

    @Test
    public void insertarVertices() {
        Grafo grafo = new Grafo();
        assertTrue(grafo.esVacio());
        assertTrue(grafo.insertarVertice(1));
        assertTrue(grafo.existeVertice(1));
        assertFalse(grafo.existeVertice(null));
        assertFalse(grafo.existeVertice(2));
        assertFalse(grafo.existeArco(1, 1));
        assertFalse(grafo.existeArco(1, null));
    }

    @Test
    public void insertarArcos() {
        Grafo grafo = new Grafo();
        for (int i = 1; i < 10; i++) {
            assertTrue(grafo.insertarVertice(i));
        }
        assertTrue(grafo.insertarArco(1, 1, 0.1));
        assertFalse(grafo.insertarArco(1, 1, 0.1));
        assertFalse(grafo.insertarArco(1, 1, 2.0));
        assertTrue(grafo.insertarArco(1, 2, 0.1));
        assertTrue(grafo.insertarArco(2, 1, 0.1));
        assertFalse(grafo.insertarArco(2, 1, 0.1));
    }

    @Test
    public void eliminarArcos() {
        Grafo grafo = new Grafo();
        for (int i = 1; i < 10; i++) {
            assertTrue(grafo.insertarVertice(i));
        }
        assertFalse(grafo.existeArco(1, 1));
        assertFalse(grafo.eliminarArco(1, 1));
        assertTrue(grafo.insertarArco(1, 1, 0.1));
        assertTrue(grafo.existeArco(1, 1));
        assertTrue(grafo.eliminarArco(1, 1));
        assertFalse(grafo.existeArco(1, 1));

        assertFalse(grafo.existeArco(1, 2));
        assertFalse(grafo.eliminarArco(1, 2));
        assertTrue(grafo.insertarArco(1, 2, 0.1));
        assertTrue(grafo.existeArco(1, 2));
        assertTrue(grafo.eliminarArco(1, 2));
        assertFalse(grafo.existeArco(1, 2));

        assertTrue(grafo.insertarArco(1, 2, 0.1));
        assertTrue(grafo.existeArco(1, 2));
        assertTrue(grafo.insertarArco(2, 1, 0.2));
        assertTrue(grafo.existeArco(2, 1));

        assertTrue(grafo.eliminarArco(1, 2));
        assertFalse(grafo.existeArco(1, 2));
        assertTrue(grafo.existeArco(2, 1));
    }

    @Test
    public void eliminarVertices() {
        Grafo grafo = new Grafo();
        for (int i = 1; i < 10; i++) {
            assertTrue(grafo.insertarVertice(i));
        }

        assertFalse(grafo.eliminarVertice(10));

        assertFalse(grafo.eliminarVertice(0));
        assertTrue(grafo.eliminarVertice(9));

        // revisar que todos los arcos relacionados al vértice se eliminan también
        assertTrue(grafo.insertarArco(1, 1, 0.1));
        assertTrue(grafo.insertarArco(2, 1, 0.2));

        assertTrue(grafo.eliminarArco(1, 2));
        assertFalse(grafo.existeArco(1, 2));
        assertTrue(grafo.existeArco(2, 1));
    }

    @Test
    public void verificarCamino() {
        Grafo grafo = new Grafo();
        for (int i = 1; i < 10; i++) {
            assertTrue(grafo.insertarVertice(i));
        }

        assertTrue(grafo.existeCamino(1, 1));

        assertFalse(grafo.existeCamino(1, 3));
        grafo.insertarArco(1, 2, 12);
        assertTrue(grafo.existeCamino(1, 2));
        assertFalse(grafo.existeCamino(1, 3));
        grafo.insertarArco(2, 3, 23);
        assertTrue(grafo.existeCamino(2, 3));
        assertTrue(grafo.existeCamino(1, 3));
        assertFalse(grafo.existeCamino(2, 1));
        assertFalse(grafo.existeCamino(3, 2));
        assertFalse(grafo.existeCamino(3, 1));
        grafo.eliminarVertice(2);
        assertFalse(grafo.existeCamino(1, 3));
        grafo.eliminarVertice(1);
        grafo.eliminarVertice(3);
        grafo.insertarVertice(1);
        grafo.insertarVertice(2);
        grafo.insertarVertice(3);
        grafo.insertarArco(1, 2, 12);
        grafo.insertarArco(2, 1, 21);
        assertTrue(grafo.existeCamino(2, 1));
    }

    @Test
    public void listados() {
        Grafo grafo = new Grafo();
        for (int i = 1; i < 10; i++) {
            assertTrue(grafo.insertarVertice(i));
        }

        grafo.insertarArco(1, 2, 0.1);
        grafo.insertarArco(2, 3, 0.1);
        grafo.eliminarVertice(2);
        Lista l = grafo.listarEnProfundidad();
        int length = l.longitud();
        for (int i = 1; i <= length; i++) {
            System.out.println(l.recuperar(i));
        }
    }

    @Test
    public void caminosMasCortos() {
        Grafo grafo = new Grafo();
        for (int i = 1; i < 10; i++) {
            assertTrue(grafo.insertarVertice(i));
        }

        grafo.insertarArco(1, 2, 12);
        grafo.insertarArco(2, 3, 23);
        grafo.insertarArco(3, 4, 34);
        grafo.insertarArco(4, 5, 45);
        grafo.insertarArco(1, 5, 15);

        assertTrue(grafo.existeCamino(1, 5));
        Lista camino = grafo.caminoMasCorto(1, 5);

        assertEquals(2, camino.longitud());
        assertEquals(1, camino.recuperar(1));
        assertEquals(5, camino.recuperar(2));

        grafo.eliminarArco(1, 5);
        grafo.insertarArco(2, 5, 25);

        camino = grafo.caminoMasCorto(1, 5);

        assertEquals(3, camino.longitud());
        assertEquals(1, camino.recuperar(1));
        assertEquals(2, camino.recuperar(2));
        assertEquals(5, camino.recuperar(3));

        grafo.eliminarArco(2, 5);
        camino = grafo.caminoMasCorto(1, 5);
        assertEquals(5, camino.longitud());
        assertEquals(1, camino.recuperar(1));
        assertEquals(2, camino.recuperar(2));
        assertEquals(3, camino.recuperar(3));
        assertEquals(4, camino.recuperar(4));
        assertEquals(5, camino.recuperar(5));

        grafo.insertarArco(2, 4, 24);
        camino = grafo.caminoMasCorto(1, 5);
        assertEquals(4, camino.longitud());
        assertEquals(1, camino.recuperar(1));
        assertEquals(2, camino.recuperar(2));
        assertEquals(4, camino.recuperar(3));
        assertEquals(5, camino.recuperar(4));

        grafo.insertarArco(4, 9, 49);
        grafo.insertarArco(9, 8, 98);
        camino = grafo.caminoMasCorto(2, 8);
        assertEquals(4, camino.longitud());
        assertEquals(2, camino.recuperar(1));
        assertEquals(4, camino.recuperar(2));
        assertEquals(9, camino.recuperar(3));
        assertEquals(8, camino.recuperar(4));
    }
}