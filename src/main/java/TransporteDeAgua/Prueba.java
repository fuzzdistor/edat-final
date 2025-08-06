package TransporteDeAgua;

import Estructuras.Diccionario;

public class Prueba {
    public static void main(String[] args) {

         Diccionario arbol = new Diccionario();

        // 
        int[] claves = {
                30, 20, 15, 24, 12, 18, 19, 22, 23, 27, 50, 35, 33, 40, 37, 100, 90, 75, 95, 93, 105, 190 };

        for (int clave : claves) {
            arbol.insertar(clave, "Dato" + clave);
        }

        System.out.println("Arbol antes de eliminar 30:");
        System.out.println(arbol.toStringClaves());

        // Eliminar la raiz (30) que tiene dos hijos
        arbol.eliminar(30);

        System.out.println("Arbol después de eliminar 30:");
        System.out.println(arbol.toStringClaves());
    }
        
}