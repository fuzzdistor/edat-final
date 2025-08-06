package TransporteDeAgua;

import java.util.Scanner;

import Estructuras.HeapMax;
import Estructuras.Lista;

public class InterfazDeUsuario {
    Scanner sc = new Scanner(System.in);
    Sistema sistema;

    public InterfazDeUsuario(Sistema sistema) {// constructor
        this.sistema = sistema;
    };


    //MENUS
    private void menuPrincipal() {
        boolean volver = false;
        while (!volver) {
            System.out.println("-------< MENU >-------");
            System.out.println("| 1. Realizar tareas/consultas en ciudades");
            System.out.println("| 2. Realizar tareas en tuberias");
            System.out.println("| 3. Realizar consultas del transporte de Agua");
            System.out.println("| 4. Mostrar Sistema");
            System.out.println("| x. salir");

            String opcion = sc.nextLine();

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
                case "x":
                    volver = true;
                default:
                    break;
            }
        }
    }

    private void menuCiudades() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\t <Ciudad>");
            System.out.println("| 1. Dar de alta una ciudad");
            System.out.println("| 2. Dar de baja una ciudad");
            System.out.println("| 3. Modificar una ciudad");
            System.out.println("| 4. Consultar cantidad de habitantes y volumen de agua distribuido de una ciudad");
            System.out.println("| 5. Listar ciudades que han consumido un volumen de agua en un rango en un mes de un año");
            System.out.println("| 6. Listar ciudades por consumo de agua en un año de mayor a menor");
            System.out.println("| 7. Ir al menu principal");

            String respuesta = sc.nextLine();

            switch (respuesta) {
                case "1":
                    altaCiudad();
                    break;
                case "2":
                    bajaCiudad();
                    break;
                case "3":
                    modificarCiudad();
                    break;
                case "4":
                    // Dar la cantidad de habitantes y el volumen de agua de una ciudad
                    consultarCiudad();
                    break;
                case "5":
                    // pedir un nummin y maxmax y listar la listas que tienen un volumen de agua
                    // entre esas valores en un año y mes especifico
                    consultarConsumoRango();
                    break;
                case "6":
                    // Dado un anio, listar las ciudades de menor a mayor en base al consumo de agua
                    // anual
                    rankingConsumo();
                    break;
                case "7":
                    volver = true;
                    break;

                default:
                    volver=true;
                    break;

            }
            String posibilidades = "123456";
            if (posibilidades.indexOf(respuesta) != -1) {
                System.out.println("| 1. Volver al menu principal");
                System.out.println("| 2. Volver al menu de ciudad");
                System.out.println("| x. salir");
                respuesta = sc.nextLine();

                if (respuesta.equals("1")) {
                    volver = true;
                }
            }
        }
    }

    private void menuTuberias() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\t <Tuberia>");
            System.out.println("| 1. Dar de alta una tuberia");
            System.out.println("| 2. Dar de baja una tuberia");
            System.out.println("| 3. Modificar una tuberia");
            System.out.println("| 4. Ir al menu principal");
            System.out.println("|x.");

            String respuesta = sc.nextLine();

            switch (respuesta) {
                case "1":
                    altaTuberia();
                    break;
                case "2":
                    bajaTuberia();

                    break;
                case "3":
                    modificarTuberia();

                    break;
                case "4":
                    volver = true;

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

                if (respuesta.equals("1")) {
                    volver = true;
                }
            }
        }

    }

    private void menuTransporteDeAgua() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\t <Transporte de Agua>");
            System.out.println("| 1. Camino de A a B ciudades mediante el minimo caudal");
            System.out.println("| 2. Camino de A a B ciudades mediante la minima cantidad de ciudades");
            System.out.println("| 3. Ir al menu principal");
            System.out.println("|x.");

            String respuesta = sc.nextLine();

            switch (respuesta) {
                case "1":
                    // dada la ciudad A y B dar el camino con el minimo caudal que haya
                   caminoConMenorPleno();
                    break;
                case "2":
                    // dada la ciudad A y B dar el camino con la minima cantidad de ciudades
                    menorCamino();

                    break;

                case "3":
                    volver = true;

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

                if (respuesta.equals("1")) {
                    volver = true;
                }
            }
        }

    }


    //CONSULTAS SOBRE CIUDAD


    private void rankingConsumo() {
        System.out.print("Ingrese el año sobre el cual quiere el listado: ");
        int anio = Integer.parseInt(sc.nextLine());
        HeapMax ranking = sistema.ciudadesOrdenadasPorConsumo(anio);
        System.out.println("----RANKING CONSUMO " + anio + "----");

        while (!ranking.esVacio()) {
            ConsumoCiudad consumo = (ConsumoCiudad) ranking.recuperarCima();
            if (consumo.getConsumo() > 0) {
                System.out.println(consumo.toString());
            }
            ranking.eliminarCima();
        }

    }

    private void consultarConsumoRango() {
        System.out.print("Ingrese el nombre minimo: ");
        String minNom = sc.nextLine();
        System.out.print("Ingrese el nombre maximo: ");
        String maxNom = sc.nextLine();
        System.out.print("Ingrese el volumen minimo: ");
        Double minVol = Double.parseDouble(sc.nextLine());
        System.out.print("Ingrese el volumen maximo: ");
        Double maxVol = Double.parseDouble(sc.nextLine());
        System.out.print("Año: ");
        int anio = Integer.parseInt(sc.nextLine());
        System.out.print("Mes: ");
        int mes = Integer.parseInt(sc.nextLine());

        Lista ciudadesFiltradas = sistema.filtrarCiudades(minNom, maxNom, minVol, maxVol, anio, mes);
        if (!ciudadesFiltradas.esVacia()) {
            System.out.println("-----Ciudades en rango-----");
            while (!ciudadesFiltradas.esVacia()) {
                ConsumoCiudad ciudad = (ConsumoCiudad) ciudadesFiltradas.recuperar(1);
                System.out.println(ciudad.toString());
                ciudadesFiltradas.eliminar(1);
            }
        } else {
            System.out.println("No hay informacion para los datos indicados.");
        }
    }

    private void consultarCiudad() {
        System.out.print("Nombre de la ciudad: ");
        String nombre = sc.nextLine().toUpperCase();

        if (!sistema.existeCiudad(nombre)) {
            System.out.println("Ciudad no encontrada.");
        } else {

            System.out.print("Ingrese el año: ");
            int anio = Integer.parseInt(sc.nextLine());

            System.out.print("Ingrese el mes (1-12): ");
            int mes = Integer.parseInt(sc.nextLine());

            int habitantes = sistema.habitantes(nombre, anio, mes);
            System.out.println("\nCiudad: " + nombre);
            System.out.println("Año: " + anio + " - Mes: " + mes);
            if (habitantes > 0) {
                double volumenEstimado = sistema.consumoMes(nombre, anio, mes);
                String volString = String.format("%.2f", volumenEstimado);

                System.out.println("Cantidad de habitantes: " + habitantes);
                System.out.println("Volumen distribuido estimado (habitantes por consumo promedio): " + volString + " litros");

            } else {
                System.out.println("No hay informacion para mostrar en la fecha indicada.");
            }

        }

    }

    private void modificarCiudad() {
        System.out.print("Nombre de la ciudad a modificar: ");
        String nombre = sc.nextLine().toUpperCase();

        if (!sistema.existeCiudad(nombre)) {
            System.out.println("Ciudad no encontrada.");
        } else {
            boolean salir = false;
            while (!salir) {
                System.out.println("\n¿Que desea modificar?");
                System.out.println("1. Consumo promedio");
                System.out.println("2. Cantidad de habitantes en un año y mes");
                System.out.println("3. Volver al menú de ciudades");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");
                String opcion = sc.nextLine();

                switch (opcion) {
                    case "1":
                        modificarConsumo(nombre);
                        break;
                    case "2":
                        modificarHabitantes(nombre);
                        break;
                    case "3":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            }
        }
    }


    private void modificarHabitantes(String nombre) {
        System.out.print("Año: ");
        int anio = Integer.parseInt(sc.nextLine());
        System.out.print("Mes (1-12): ");
        int mes = Integer.parseInt(sc.nextLine());
        System.out.print("Cantidad de habitantes: ");
        int cantidad = Integer.parseInt(sc.nextLine());
        boolean exito = sistema.setCantidadHabitantes(nombre, anio, mes, cantidad);
        if (exito) {
            System.out.println("Cantidad de habitantes actualizada.");
        } else {
            System.out.println("Error realizando la modificacion");
        }


    }


    private void modificarConsumo(String ciudad) {

        System.out.print("Nuevo consumo promedio: ");
        double nuevoConsumo = sc.nextDouble();
        sc.nextLine();
        boolean exito = sistema.setConsumoPromedio(ciudad, nuevoConsumo);
        if (exito) {
            System.out.println("Consumo promedio actualizado.");
        } else {
            System.out.println("Error modificando el consumo promedio, verifique la existencia de la ciudad");
        }

    }


    private void bajaCiudad() {
        System.out.print("Ingrese el nombre de la ciudad a eliminar: ");
        String nombre = sc.nextLine().toUpperCase();
        boolean exito = sistema.eliminarCiudad(nombre);

        if (exito) {
            System.out.println("Ciudad eliminada con exito.");
        } else {
            System.out.println("Error. Ciudad no encontrada.");
        }
    }

    private void altaCiudad() {
        System.out.print("Ingrese nombre de la ciudad: ");
        String nombre = sc.nextLine().toUpperCase();
        System.out.print("Ingrese superficie (en m2): ");
        double superficie = Double.parseDouble(sc.nextLine());
        System.out.print("Ingrese consumo promedio mensual (en m3): ");
        double consumoPromedio = Double.parseDouble(sc.nextLine());
        System.out.print("Ingrese nomenclatura de ciudad: ");
        String nomenclatura = sc.nextLine().toUpperCase();

        boolean exito = sistema.agregarCiudad(nombre, superficie, consumoPromedio, nomenclatura);
        if (exito) {
            System.out.println("Ciudad agregada con exito.");
        } else {
            System.out.println("No se pudo agregar la ciudad. Verifique si ya existe.");
        }
    }


    //CONSULTAS SOBRE TUBERIAS
    private void modificarTuberia() {
        System.out.print("Ingrese nombre de la ciudad fuente: ");
        String fuente = sc.nextLine().toUpperCase();
        System.out.print("Ingrese nombre de la ciudad destino: ");
        String destino = sc.nextLine().toUpperCase();
        boolean salir = false;
        while (!salir) {
            System.out.println("\n¿Que desea modificar?");
            System.out.println("1. Estado Tuberia ");
            System.out.println("2. Caudal Minimo ");
            System.out.println("3. Caudal Maximo");
            System.out.println("4. Volver al menu de tuberias");
            System.out.println("x. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    modificarEstadoTuberia(fuente, destino);
                    break;
                case "2":
                    modificarCaudarMin(fuente, destino);
                    break;
                case "3":
                    modificarCaudarMax(fuente, destino);
                    break;

                case "4":
                    salir = true;
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }


    private void modificarCaudarMax(String fuente, String destino) {
        System.out.print("Ingrese el nuevo caudal maximo: ");
        double caudalMax = Double.parseDouble(sc.nextLine());

        boolean exito = sistema.setCaudalMax(fuente, destino, caudalMax);
        if (exito) {
            System.out.println("Modificacion exitosa");
        } else {
            System.out.println("La modificacion no pudo ser completada.");
        }
    }


    private void modificarCaudarMin(String fuente, String destino) {
        System.out.print("Ingrese caudal Minimo: ");
        double caudalMin = Double.parseDouble(sc.nextLine());

        boolean exito = sistema.setCaudalMin(fuente, destino, caudalMin);
        if (exito) {
            System.out.println("Modificacion exitosa");
        } else {
            System.out.println("La modificacion no pudo ser completada.");
        }
    }


    private void modificarEstadoTuberia(String fuente, String destino) {
        boolean exito = false;
        String estado = estadoTuberia();
        exito = sistema.cambiarEstadoTuberia(fuente, destino, estado);
        if (exito) {
            System.out.println("Modificacion de estado finalizada con exito");
        } else {
            System.out.println("Error modificando el estado verifque la existencia de la tuberia.");
        }
    }


    private void bajaTuberia() {
        System.out.print("Ingrese nombre de la ciudad fuente: ");
        String fuente = sc.nextLine().toUpperCase();
        System.out.print("Ingrese nombre de la ciudad destino: ");
        String destino = sc.nextLine().toUpperCase();
        boolean exito = sistema.eliminarTuberia(fuente, destino);
        if (exito) {
            System.out.println("Tuberia eliminada con exito");
        } else {
            System.out.println("La tuberia no pudo ser eliminada");
        }

    }

    private String estadoTuberia() {
        String estado = "";
        boolean valido = false;

        while (!valido) {
            System.out.print("Seleccione el estado de la tubería (1-Activo, 2-Inactivo, 3-En reparacion, 4-En Diseño): ");
            String opcionEstado = sc.nextLine().trim();

            switch (opcionEstado) {
                case "1":
                    estado = "ACTIVO";
                    valido = true;
                    break;
                case "2":
                    estado = "INACTIVO";
                    valido = true;
                    break;
                case "3":
                    estado = "ENREPARACION";
                    valido = true;
                    break;
                case "4":
                    estado = "ENDISENIO";
                    valido = true;
                    break;
                default:
                    System.out.println("Estado invalido. Intente nuevamente.");
            }
        }

        return estado;
    }

    private void altaTuberia() {

        System.out.print("Ingrese nombre de la ciudad fuente: ");
        String fuente = sc.nextLine().toUpperCase();
        System.out.print("Ingrese nombre de la ciudad destino: ");
        String destino = sc.nextLine().toUpperCase();
        System.out.print("Ingrese superficie el caudal minimo: ");
        double caudalMin = Double.parseDouble(sc.nextLine());
        System.out.print("Ingrese el caudal maximo: ");
        double caudalMax = Double.parseDouble(sc.nextLine());
        System.out.print("Ingrese el diametro: ");
        double diametro = Double.parseDouble(sc.nextLine());
        String opcionEstado = estadoTuberia();
        if (!opcionEstado.equals("")) {
            boolean exito = sistema.crearTuberia(fuente, destino, caudalMin, caudalMax, diametro, opcionEstado);
            if (exito) {
                System.out.println("Alta tuberia finalizada con exito");
            } else {
                System.out.println("Alta tuberia no completada.");
            }
        }


    }

    //CONSULTAS SOBRE TRANSPORTE DE AGUA

    private void caminoConMenorPleno() {
        System.out.print("Ingrese nombre de la ciudad fuente: ");
        String fuente = sc.nextLine().toUpperCase();
        System.out.print("Ingrese nombre de la ciudad destino: ");
        String destino = sc.nextLine().toUpperCase();
        Lista resultado = sistema.caminoConMenorPleno(fuente, destino);
        String estado= sistema.obtenerEstadoCamino(resultado);
        if(estado.equals("INEXISTENTE")){
            System.out.println("No hay un camino entre las ciudades dadas");
        }else{
            System.out.println("Camino: ");
            System.out.println(resultado.toString());
            System.out.println("Estado: "+estado);
        }
    }


    private void menorCamino() {
        System.out.print("Ingrese nombre de la ciudad fuente: ");
        String fuente = sc.nextLine().toUpperCase();
        System.out.print("Ingrese nombre de la ciudad destino: ");
        String destino = sc.nextLine().toUpperCase();
        Lista camino= sistema.menorCaminoEntre(fuente, destino);
        String estado= sistema.obtenerEstadoCamino(camino);
        if(estado.equals("INEXISTENTE")){
            System.out.println("No hay un camino entre las ciudades dadas");
        }else{
            System.out.println("Camino: ");
            System.out.println(camino.toString());
            System.out.println("Estado: "+estado);
        }
    }

    //CONSULTAS SOBRE SISTEMA

    private void mostrarSistema() {
        // Mostrar todas las estructuras utilizadas en ejecucucion, Grafo, AVL y Mapeo,
        // utilizar sus toString()
        System.out.println("----Estado del sistema----");
        System.out.println(sistema.mostrarSistema());
    }

    public void iniciar() {
        menuPrincipal();
    }
}
