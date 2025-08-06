package TransporteDeAgua;

import Estructuras.Diccionario;

public class Prueba {
    public static void main(String[] args) {

         Diccionario arbol = new Diccionario();

        // 
        int[] claves = {
           50, 30, 70, 20, 40, 60, 80, 35, 45};

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