package TransporteDeAgua;

public class Prueba {
    public static void main(String[] args) {
        Sistema sistema = new Sistema(); 

        sistema.cargarCiudades("src\\main\\java\\Archivos\\Ciudades.csv");
        sistema.cargarHabitantes("src\\main\\java\\Archivos\\Habitantes.csv");
        sistema.cargarTuberias("src\\main\\java\\Archivos\\Tuberias.csv");

        InterfazDeUsuario interfaz = new InterfazDeUsuario(sistema);
        interfaz.iniciar();
    }
}