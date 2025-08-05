package org.example;

import TransporteDeAgua.InterfazDeUsuario;
import TransporteDeAgua.Sistema;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.cargarCiudades("data/Ciudades.csv");
        sistema.cargarHabitantes("data/Habitantes.csv");
        sistema.cargarTuberias("data/Tuberias.csv");
        InterfazDeUsuario ui = new InterfazDeUsuario(sistema);
        ui.iniciar();
    }
}