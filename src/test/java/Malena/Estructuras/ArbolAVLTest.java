package Malena.Estructuras;

import TransporteDeAgua.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.Pattern;

import Estructuras.*;

public class ArbolAVLTest {

    private static boolean isSubstring(String s, String rx){
        Pattern pattern = Pattern.compile(rx);
        java.util.regex.Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    private static  ArbolAVL cargarArbolConCiudades() {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertar(new Ciudad("Rosario",  1200, 0.7,"Ro3000"));
        arbol.insertar(new Ciudad("Buenos Aires",  2000, 0.9,""));
        arbol.insertar(new Ciudad("Cordoba",  1500, 0.8,""));
        arbol.insertar(new Ciudad("Mendoza",  1000, 0.75,""));
        arbol.insertar(new Ciudad("Salta",  900, 0.6,""));
        return arbol;
    }

    @Test
    public void testArbolConCiudadesBalanceado() {
        ArbolAVL  arbol = cargarArbolConCiudades();
        String salida = arbol.toString();
        System.out.println(salida);

        boolean contieneRaiz = isSubstring(salida, "\\[CORDOBA, BUENOS AIRES, ROSARIO\\]");
        boolean contieneBA = isSubstring(salida, "\\[BUENOS AIRES, null, null\\]");
        boolean contieneRosario = isSubstring(salida, "\\[ROSARIO, MENDOZA, SALTA\\]");
        boolean contieneMendoza = isSubstring(salida, "\\[MENDOZA, null, null\\]");
        boolean contieneSalta = isSubstring(salida, "\\[SALTA, null, null\\]");

        assertTrue(contieneRaiz);
        assertTrue(contieneBA);
        assertTrue(contieneRosario);
        assertTrue(contieneMendoza);
        assertTrue(contieneSalta);
    }


        @Test
    public void testInsertarYBuscar() {
        ArbolAVL arbol = cargarArbolConCiudades();
        // verificar que se puede buscar cada ciudad insertada
        assertTrue(arbol.pertenece(new Ciudad("Rosario", 0, 0,"")));
        assertTrue(arbol.pertenece(new Ciudad("Buenos Aires",  0, 0,"")));
        assertTrue(arbol.pertenece(new Ciudad("Cordoba",  0, 0,"")));
        assertTrue(arbol.pertenece(new Ciudad("Mendoza",  0, 0,"")));
        assertTrue(arbol.pertenece(new Ciudad("Salta",  0, 0,"")));

        // pertenece ciudad que no existe
        assertFalse(arbol.pertenece(new Ciudad("La Plata",  0, 0,"")));
    }

  

    @Test
    public void testEliminarNodoHoja() {
        ArbolAVL arbol = cargarArbolConCiudades();
        // Eliminar un nodo hoja
        boolean eliminado = arbol.eliminar(new Ciudad("Salta", 0, 0,""));
        assertTrue(eliminado);
        // No debe encontrar la ciudad eliminada
        assertFalse(arbol.pertenece(new Ciudad("Salta",  0, 0,"")));
    }

    @Test
    public void testEliminarNodoConHijos() {
        ArbolAVL arbol = cargarArbolConCiudades();
        // Eliminar nodo con hijos
        boolean eliminado = arbol.eliminar(new Ciudad("Rosario",  0, 0,""));
        assertTrue(eliminado);
        // No debe encontrar la ciudad eliminada
        assertFalse(arbol.pertenece(new Ciudad("Rosario",  0, 0,"")));
        
    }
@Test
    public void testRecorridoInOrderOrdenado() {
        ArbolAVL arbol = cargarArbolConCiudades();
        Lista listado = arbol.listar();
        assertEquals(5, listado.longitud());

        for (int i = 2; i <= listado.longitud(); i++) {
            Ciudad anterior = (Ciudad) listado.recuperar(i - 1);
            Ciudad actual = (Ciudad) listado.recuperar(i);
            assertTrue(anterior.getNombre().compareTo(actual.getNombre()) < 0);
        }
    }


    
@Test
public void testListarEnRango() {
    ArbolAVL arbol = cargarArbolConCiudades();

    Lista todas = arbol.listar(); // devuelve lista de Comparable
    Lista enRango = new Lista();

    String desde = "BUENOS AIRES";
    String hasta = "MENDOZA";

    for (int i = 1; i <= todas.longitud(); i++) {
        Comparable elem = (Comparable) todas.recuperar(i);
        // Solo casteamos acá, fuera del árbol
        if (elem instanceof Ciudad) {
            Ciudad ciudad = (Ciudad) elem;
            String nombre = ciudad.getNombre();
            if (nombre.compareTo(desde) >= 0 && nombre.compareTo(hasta) <= 0) {
                enRango.insertar(ciudad, enRango.longitud() + 1);
            }
        }
    }
    System.out.println(todas.toString());
    System.out.println(enRango.toString());

    // Verificamos que el resultado contiene las ciudades correctas
    assertEquals(3, enRango.longitud()); // BsAs, Córdoba, Mendoza

    for (int i = 2; i <= enRango.longitud(); i++) {
        Ciudad anterior = (Ciudad) enRango.recuperar(i - 1);
        Ciudad actual = (Ciudad) enRango.recuperar(i);
        assertTrue(anterior.getNombre().compareTo(actual.getNombre()) < 0);
    }
}

  @Test
    public void testObtenerCiudadExistente() {
        ArbolAVL arbol = new ArbolAVL();
        Ciudad neuquen = new Ciudad("Neufuén", 1200000, 0.25, "NE3001");
        Ciudad rionegra = new Ciudad("Rionegra", 890000, 0.23, "RI3009");

        arbol.insertar(neuquen);
        arbol.insertar(rionegra);

        // Busco una ciudad existente con nombre "Neufuén"
        Ciudad dummy = new Ciudad("Neufuén", 0, 0,"");
        Ciudad resultado = (Ciudad) arbol.obtener(dummy);
        System.out.println(resultado.obtenerDetalles());
    }

    @Test
    public void testObtenerCiudadInexistente() {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertar(new Ciudad("Terraviva", 750000, 0.26, "TE3005"));

        // Busco una ciudad que no existe
        Ciudad dummy = new Ciudad("CiudadFicticia", 0, 0,"");
        Ciudad resultado = (Ciudad) arbol.obtener(dummy);

        assertNull("La ciudad no debe existir en el árbol", resultado);
    }


}



