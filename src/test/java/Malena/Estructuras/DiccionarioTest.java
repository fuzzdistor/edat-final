package Malena.Estructuras;

import TransporteDeAgua.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.Pattern;

import Estructuras.*;

public class DiccionarioTest {

    private static boolean isSubstring(String s, String rx){
        Pattern pattern = Pattern.compile(rx);
        java.util.regex.Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    private static  Diccionario cargarArbolConCiudades() {
        Diccionario arbol = new Diccionario();
        arbol.insertar("ROSARIO",(new Ciudad("Rosario",  1200, 0.7,"Ro3000")));
        arbol.insertar("BUENOS AIRES",(new Ciudad("Buenos Aires",  2000, 0.9,"")));
        arbol.insertar("CORDOBA",(new Ciudad("Cordoba",  1500, 0.8,"")));
        arbol.insertar("MENDOZA",(new Ciudad("Mendoza",  1000, 0.75,"")));
        arbol.insertar("SALTA",(new Ciudad("Salta",  900, 0.6,"")));
        return arbol;
    }

    @Test
    public void testArbolConCiudadesBalanceado() {
        Diccionario arbol = cargarArbolConCiudades();
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
        Diccionario arbol = cargarArbolConCiudades();
        // verificar que se puede buscar cada ciudad insertada
        assertTrue(arbol.existeClave("ROSARIO"));
        assertTrue(arbol.existeClave("BUENOS AIRES"));
        assertTrue(arbol.existeClave("CORDOBA"));
        assertTrue(arbol.existeClave("MENDOZA"));
        assertTrue(arbol.existeClave("SALTA"));

        // existeClave ciudad que no existe
        assertFalse(arbol.existeClave("LA PLATA"));
    }

  

    @Test
    public void testEliminarNodoHoja() {
        Diccionario arbol = cargarArbolConCiudades();
        // Eliminar un nodo hoja
        boolean eliminado = arbol.eliminar("SALTA");
        assertTrue(eliminado);
        // No debe encontrar la ciudad eliminada
        assertFalse(arbol.existeClave("SALTA"));
    }

    @Test
    public void testEliminarNodoConHijos() {
        Diccionario arbol = cargarArbolConCiudades();
        // Eliminar nodo con hijos
        boolean eliminado = arbol.eliminar("ROSARIO");
        System.out.println(arbol.listarClaves().toString());
        assertTrue(eliminado);
        // No debe encontrar la ciudad eliminada
        assertFalse(arbol.existeClave("ROSARIO"));
        
    }
@Test
    public void testRecorridoInOrderOrdenado() {
        Diccionario arbol = cargarArbolConCiudades();
        Lista claves = arbol.listarClaves();
        for (int i = 1; i < claves.longitud(); i++) {
            String anterior = (String) claves.recuperar(i);
            String actual = (String) claves.recuperar(i + 1);
            assertTrue(anterior.compareTo(actual) < 0);
        }
    }


    

@Test
public void testListarRangoClavesOrdenadas() {
    Diccionario arbol = cargarArbolConCiudades();

    String desde = "BUENOS AIRES";
    String hasta = "MENDOZA";
    Lista todas= arbol.listarClaves();
    System.out.println("todas: " +todas.toString());

    Lista enRango = arbol.listarRangoDatos(desde, hasta);
    System.out.println("rango: " +enRango.toString());

    // Verificamos longitud
    assertEquals(3, enRango.longitud()); // Debería incluir BA, CORDOBA, MENDOZA

    // Verificamos contenido exacto (en orden alfabético)
    assertEquals("BUENOS AIRES", enRango.recuperar(1));
    assertEquals("CORDOBA", enRango.recuperar(2));
    assertEquals("MENDOZA", enRango.recuperar(3));

    // Verificamos orden alfabético
    for (int i = 1; i < enRango.longitud(); i++) {
        String a = (String) enRango.recuperar(i);
        String b = (String) enRango.recuperar(i + 1);
        assertTrue("Claves fuera de orden: " + a + " y " + b, a.compareTo(b) < 0);
    }
}


  @Test
    public void testObtenerCiudadExistente() {
        Diccionario arbol = cargarArbolConCiudades();


        Ciudad resultado = (Ciudad) arbol.recuperar("ROSARIO");
        System.out.println(resultado.obtenerDetalles());
    }

    @Test
    public void testObtenerCiudadInexistente() {
        Diccionario arbol = new Diccionario();
        arbol.insertar("TERRAVIVA", new Ciudad("terraviva", null));

        Ciudad resultado= (Ciudad) arbol.recuperar("CINCO SALTOS");
        assertNull("La ciudad no debe existir en el árbol", resultado);
    }


}



