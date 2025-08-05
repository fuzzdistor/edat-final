package Flor;

import static org.junit.Assert.*;

import Estructuras.Grafo;
import Estructuras.Lista;
import Estructuras.Grafo.*;
import org.junit.Assert;
import org.junit.Test;

public class GrafoTest {

    @Test
    public void testIsEmptyGrafo() {
        Grafo grafo = new Grafo();
        boolean vacia = grafo.esVacio();
        assertEquals(vacia, true);
    }

    @Test
    public void testInsertarVerticeEnGrafoVacio() {
        Grafo grafo = new Grafo();
        boolean insertado = grafo.insertarVertice("A");
        boolean grafoVacio = grafo.esVacio();
        assertEquals(insertado, true);
        assertEquals(grafoVacio, false);

    }

    @Test
    public void testInsertarArco() {
        Grafo grafo = new Grafo();
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        boolean hayArco = grafo.insertarArco("A", "B", 10);
        boolean siHayArco = grafo.existeArco("A", "B");
        boolean noHyaArco = grafo.existeArco("B", "A");
        assertEquals(hayArco, true);
        assertEquals(siHayArco, true);
        assertEquals(noHyaArco, false);

        // Que pasa si lo Elimino

        grafo.eliminarArco("A", "B");
        siHayArco = grafo.existeArco("A", "B");
        assertEquals(siHayArco, false);
    }

    @Test
    public void eliminarVertice() {
        Grafo grafo = new Grafo();
        grafo.insertarVertice("A");
        boolean eliminado = grafo.eliminarVertice("A");
        assertEquals(eliminado, true);
    }

    @Test
    public void eliminarVerticeConArcos() {
        Grafo grafo = new Grafo();
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");

        grafo.insertarArco("A", "B", "AB");
        grafo.insertarArco("A", "C", "AC");
        grafo.insertarArco("D", "A", "DA");

        grafo.eliminarVertice("A");

        boolean noDeberiaExistir = grafo.existeArco("A", "B");
        assertEquals(noDeberiaExistir, false);

        noDeberiaExistir = grafo.existeArco("C", "A");
        assertEquals(noDeberiaExistir, false);

    }

    @Test
    public void testExisteCamino() {
        Grafo grafo = new Grafo();
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");

        grafo.insertarArco("A", "B", "AB");
        grafo.insertarArco("A", "C", "AC");
        grafo.insertarArco("C", "A", "CA");
        grafo.insertarArco("B", "D", "BD");

        assertTrue(grafo.existeCamino("A", "D"));
        assertFalse(grafo.existeCamino("D", "A"));
        assertTrue(grafo.existeCamino("A", "C"));
        assertTrue(grafo.existeCamino("C", "A"));
        assertFalse(grafo.existeCamino("B", "A"));
    }

}
