package TransporteDeAgua;

import java.util.Scanner;

public class InterfazDeUsuario {
    Scanner sc = new Scanner(System.in);
    Sistema sistema;

    public enum Operaciones {
        AltaCiudad,
        BajaCiudad,
        ModificarCiudad,
        ConsultarCiudad,
        AltaTuberia,
        BajaTuberia,
        ModificarTuberia,
        ConsultarTuberia,
        ConsultaConsumoPorRango,
        CaminoConMenorCaudalPleno,
        CaminoConMenorCantidadCiudades,
        RankingPorAnio,
        MostrarSistema,
    }

    public InterfazDeUsuario(Sistema sistema) {// constructor
        this.sistema = sistema;
    };

    private void menuPrincipal() {
        System.out.println("-------< MENU >-------");
        System.out.println("| 1. Realizar tareas/consultas en ciudades");
        System.out.println("| 2. Realizar tareas en tuberias");
        System.out.println("| 3. Realizar consultas del transporte de Agua");
        System.out.println("| 4. Mostrar Sistema");
        System.out.println("| x. salir");

        String opcion;
        opcion = sc.nextLine();

        switch (opcion) {
            case "1":
                menuCiudades();
                break;
            case "2":
                menuTuberias();
                break;
            case "3":
                menuTransporteDeAgua();
                break;
            case "4":
                mostrarSistema();
                break;

            default:
                break;
        }

    }

    private void menuCiudades() {
        System.out.println("\t <Ciudad>");
        System.out.println("| 1. Dar de alta una ciudad");
        System.out.println("| 2. Dar de baja una ciudad");
        System.out.println("| 3. Modificar una ciudad");
        System.out.println("| 4. Consultar cantidad de habitantes y volumen de agua distribuido de una ciudad");
        System.out.println("| 5. Listar ciudades que han consumido un volumen de agua en un rango en un mes de un año");
        System.out.println("| 6. Listar ciudades por consumo de agua en un año de mayor a menor");
        System.out.println("| 7. Ir al menu principal");
        System.out.println("| x. Salir");

        String respuesta = sc.nextLine();

        switch (respuesta) {
            case "1":
                // aniadir ciudad
                break;
            case "2":
                // Pedir una ciudar y eliminarla
                break;
            case "3":
                // Pedir una ciudad y modificarla
                break;
            case "4":
                // Dar la cantidad de habitantes y el volumen de agua de una ciudad
                break;
            case "5":
                // pedir un nummin y maxmax y listar la listas que tienen un volumen de agua
                // entre esas valores en un año y mes especifico
                break;
            case "6":
                // Dado un anio, listar las ciudades de menor a mayor en base al consumo de agua
                // anual
                break;
            case "7":
                menuPrincipal();
                break;

            default:
                break;

        }
        String posibilidades = "123456";
        if (posibilidades.indexOf(respuesta) != -1) {
            System.out.println("| 1. Volver al menu principal");
            System.out.println("| 2. Volver al menu de ciudad");
            System.out.println("| x. salir");
            respuesta = sc.nextLine();

            switch (respuesta) {
                case "1":
                    menuPrincipal();
                    break;

                case "2":
                    menuCiudades();
                    break;

                default:
                    break;
            }
        }

    }

    private void menuTuberias() {
        System.out.println("\t <Tuberia>");
        System.out.println("| 1. Dar de alta una tuberia");
        System.out.println("| 2. Dar de baja una tuberia");
        System.out.println("| 3. Modificar una tuberia");
        System.out.println("| 4. Ir al menu principal");
        System.out.println("|x.");

        String respuesta = sc.nextLine();

        switch (respuesta) {
            case "1":
                // aniadir tuberia
                break;
            case "2":
                // Pedir una tuberia y eliminarla

                break;
            case "3":
                // Pedir una tuberia y modificarla

                break;
            case "4":
                menuPrincipal();

                break;

            default:
                break;

        }
        String posibilidades = "123";
        if (posibilidades.indexOf(respuesta) != -1) {
            System.out.println();
            System.out.println("| 1. Volver al menu principal");
            System.out.println("| 2. Volver al menu de tuberias");
            System.out.println("| x. salir");
            respuesta = sc.nextLine();

            switch (respuesta) {
                case "1":
                    menuPrincipal();
                    break;

                case "2":
                    menuTuberias();
                    ;
                    ;
                    break;

                default:
                    break;
            }
        }

    }

    private void menuTransporteDeAgua() {
        System.out.println("\t <Transporte de Agua>");
        System.out.println("| 1. Camino de A a B ciudades mediante el minimo caudal");
        System.out.println("| 2. Camino de A a B ciudades mediante la minima cantidad de ciudades");
        System.out.println("| 3. Ir al menu principal");
        System.out.println("|x.");

        String respuesta = sc.nextLine();

        switch (respuesta) {
            case "1":
                // dada la ciudad A y B dar el camino con el minimo caudal que haya
                break;
            case "2":
                // dada la ciudad A y B dar el camino con la minima cantidad de ciudades

                break;

            case "3":
                menuPrincipal();

                break;

            default:
                break;

        }
        String posibilidades = "12";
        if (posibilidades.indexOf(respuesta) != -1) {
            System.out.println("| 1. Volver al menu principal");
            System.out.println("| 2. Volver al menu de tuberias");
            System.out.println("| x. salir");
            respuesta = sc.nextLine();

            switch (respuesta) {
                case "1":
                    menuPrincipal();
                    break;

                case "2":
                    menuTransporteDeAgua();
                    break;

                default:
                    break;
            }
        }

    }

    private void mostrarSistema() {
        // Mostrar todas las estructuras utilizadas en ejecucucion, Grafo, AVL y Mapeo,
        // utilizar sus toString()
    }
}
