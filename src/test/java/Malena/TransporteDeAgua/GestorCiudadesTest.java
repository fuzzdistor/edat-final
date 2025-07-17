package Malena.TransporteDeAgua;

import TransporteDeAgua.*;
import org.junit.Test;
import static org.junit.Assert.*;
import Estructuras.*;

public class GestorCiudadesTest {

    @Test
    public void testAgregarCiudades() {
        GestorCiudades gestor = new GestorCiudades();

        assertTrue(gestor.agregarCiudad("neuquen", 3000, 0.9, "ne3000"));
        assertTrue(gestor.agregarCiudad("rio negro", 2500, 0.7, "ri3001"));
        assertTrue(gestor.agregarCiudad("cordoba", 4000, 1.1, "co3002"));
        assertFalse(gestor.agregarCiudad("rio negro", 9999, 9.9, "ri3001")); // duplicada
        assertTrue(gestor.agregarCiudad("salta", 99599, 9, "sa3004"));

        String ciudades = gestor.listarCiudades().toString().toUpperCase();
        assertTrue(ciudades.contains("NEUQUEN"));
        assertTrue(ciudades.contains("SALTA"));
    }

    @Test
    public void testObtenerCiudadPorNombre() {
        GestorCiudades gestor = new GestorCiudades();
        gestor.agregarCiudad("neuquen", 3000, 0.9, "ne3000");
        gestor.agregarCiudad("cordoba", 4000, 1.1, "co3002");

        assertNotNull(gestor.getCiudad("neuquen"));
        assertNotNull(gestor.getCiudad("cordoba"));
        assertNull(gestor.getCiudad("jujuy")); // no cargada
    }

    @Test
    public void testModificarConsumoPromedio() {
        GestorCiudades gestor = new GestorCiudades();
        gestor.agregarCiudad("rio negro", 2500, 0.7, "ri3001");

        gestor.setConsumoPromedio("Rio negro", 0.1);
        assertEquals(0.1, gestor.getCiudad("Rio negro").getConsumoPromedio(), 0.001);
    }

    @Test
    public void testAgregarYObtenerHabitantes() {
        GestorCiudades gestor = new GestorCiudades();
        gestor.agregarCiudad("salta", 99599, 9, "sa3004");

        Ciudad salta = gestor.getCiudad("Salta");
        assertNotNull(salta);

        // cargar habitantes
        assertTrue(salta.setHabitantes(2025, 7, 15000)); // julio
        assertTrue(salta.setHabitantes(2025, 8, 100));   // agosto

        // verificar datos
        assertEquals(15000, gestor.getCantidadHabitantes("Salta", 2025, 7));
        assertEquals(100, gestor.getCantidadHabitantes("Salta", 2025, 8));
        assertEquals(-1, gestor.getCantidadHabitantes("Salta", 2025, 6)); // junio no cargado
    }


    //tengo que cambiar por fiktrar por nombre y vol en fecha 
    @Test
    public void testFiltrarCiudadesPorRango() {
        GestorCiudades gestor = new GestorCiudades();
        gestor.agregarCiudad("neuquen", 3000, 0.9, "ne3000");
        gestor.agregarCiudad("rio negro", 2500, 0.7, "ri3001");
        gestor.agregarCiudad("cordoba", 4000, 1.1, "co3002");
        gestor.agregarCiudad("salta", 99599, 0.9, "sa3004");

        gestor.setCantidadHabitantes("neuquen", 2025, 12, 1000 );
        gestor.setCantidadHabitantes("rio negro", 2025, 12, 18000 );
        gestor.setCantidadHabitantes("cordoba", 2025, 12, 10000 );
        gestor.setCantidadHabitantes("salta", 2025, 12, 1000 );


        Lista filtradas = gestor.filtrarNombreVolumen("D", "sz",10000, 50000, 2025, 12);
        String texto = filtradas.toString().toUpperCase();

        assertFalse(texto.contains("CORDOBA"));//cumple
        assertTrue(texto.contains("RIO NEGRO"));//cumple
        assertFalse(texto.contains("SALTA"));//fuera
        assertFalse(texto.contains("NEUQUEN"));//fuera
    }

    @Test
    public void testEliminarCiudad() {
        GestorCiudades gestor = new GestorCiudades();
        gestor.agregarCiudad("salta", 99599, 9, "sa3004");

        assertNotNull(gestor.getCiudad("salta"));
        assertTrue(gestor.eliminarCiudad("salta"));

        String ciudades = gestor.listarCiudades().toString().toUpperCase();
        assertFalse(ciudades.contains("SALTA"));
        assertNull(gestor.getCiudad("SALTA"));
    }

    @Test
    public void testSetCantidadHabitantes() {
        GestorCiudades gestor = new GestorCiudades();
        gestor.agregarCiudad("salta", 1000, 0.8, "sa0001");

        // cargar habitantes por primera vez
        assertTrue(gestor.setCantidadHabitantes("salta", 2025, 8, 5000));
        assertEquals(5000, gestor.getCantidadHabitantes("salta", 2025, 8));

        // sobrescribir cantidad
        assertTrue(gestor.setCantidadHabitantes("salta", 2025, 8, 12000));
        assertEquals(12000, gestor.getCantidadHabitantes("salta", 2025, 8));

        // mes inválido
        assertFalse(gestor.setCantidadHabitantes("salta", 2025, 13, 1000));
        assertEquals(-1, gestor.getCantidadHabitantes("salta", 2025, 13));

        // ciudad no existe
        assertFalse(gestor.setCantidadHabitantes("jujuy", 2025, 7, 9999));
        assertEquals(-1, gestor.getCantidadHabitantes("jujuy", 2025, 7));

        // cantidad negativa
        assertFalse(gestor.setCantidadHabitantes("salta", 2025, 7, -100));
    }
}
