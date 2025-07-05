package org.example;

import java.util.Scanner;

public class menu {

    public static void main(String[] args) {
        Scanner sc= new Scanner (System.in);
        mostrarMenuCiudades(sc);

       
        
    }

    public static void mostrarMenuCiudades(Scanner sc){
        int opcion;
        do{
                System.out.println("--- MENU DE CIUDADES ---");
                System.out.println("1. Alta de ciudad");
                System.out.println("2. Baja de ciudad");
                System.out.println("3. Modificar ciudad");
                System.out.println("4. Buscar ciudad");
                System.out.println("5. Listar ciudades");
                System.out.println("0. Salir");
                System.out.print("Opcion: ");
                opcion= sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        altaCiudad(sc);
                        break;
                    case 2:
                        //bajaCiudad(sc);
                        break;
                    case 3:
                        //modificarCiudad(sc);
                        break;
                    case 4:
                        //buscarCiudad(sc);
                        break;
                    case 5:
                        //listarCiudad(sc);
                        break;
                    case 0:
                        System.out.println("Programa finalizado");
                        break;
                
                    default:
                        System.out.println("Opcion invalida");
                        break;
                }

            }while(opcion != 0);
    }
    public static void altaCiudad(Scanner sc){

        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese nomenclatura: ");
        String nomenclatura = sc.nextLine();

        System.out.print("Ingrese superficie (m2): ");
        double superficie = sc.nextDouble();

        System.out.print("Ingrese consumo promedio: ");
        double consumo = sc.nextDouble();
        sc.nextLine(); 

        Ciudad nueva = new Ciudad(nombre, nomenclatura, superficie, consumo);
        System.out.println(nueva.toString());
        //falta agregar al avl
    }


}
    

