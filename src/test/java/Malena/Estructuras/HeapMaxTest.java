package Malena.Estructuras;
import Estructuras.*;
import TransporteDeAgua.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HeapMaxTest {


    @Test
    public void insertarYEliminarCima() {
        HeapMax heap= new HeapMax(5);
        heap.insertar(new ConsumoCiudad("Ciudad A", 250.5));
        heap.insertar(new ConsumoCiudad("Ciudad B", 480.2));
        heap.insertar(new ConsumoCiudad("Ciudad C", 120.0));
        heap.insertar(new ConsumoCiudad("Ciudad D", 700.0));
        heap.insertar(new ConsumoCiudad("Ciudad E", 300.3));

        assertFalse(heap.esVacio());
        ConsumoCiudad cima = (ConsumoCiudad) heap.recuperarCima();
        assertEquals("Ciudad D", cima.getNombre());
        assertEquals(700.0, cima.getConsumo());

        assertTrue(heap.eliminarCima());

        ConsumoCiudad nuevaCima = (ConsumoCiudad) heap.recuperarCima();
        assertEquals("Ciudad B", nuevaCima.getNombre());
        assertEquals(480.2, nuevaCima.getConsumo());

        while (!heap.esVacio()) {
            //System.out.println(heap.recuperarCima());
            heap.eliminarCima();
        }
        assertTrue(heap.esVacio());
    }

    @Test
    public void eliminarCimaEnHeapVacio() {
        HeapMax heap= new HeapMax(1);
        assertFalse(heap.eliminarCima());
    }
}

