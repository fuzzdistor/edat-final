package TransporteDeAgua;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;

public class Log {
    static final Path logfilePath = Path.of("log.txt");

    enum Tipo {
        Info,
        Error,
        Alta,
        Baja,
        Modificacion,
    }

    public static void info(String mensaje) {
        escribirEnArchivo(mensaje, Tipo.Info);
    }

    public static void error(String mensaje) {
        escribirEnArchivo(mensaje, Tipo.Error);
    }

    public static void alta(String mensaje) {
        escribirEnArchivo(mensaje, Tipo.Alta);
    }

    public static void baja(String mensaje) {
        escribirEnArchivo(mensaje, Tipo.Baja);
    }

    public static void modificacion(String mensaje) {
        escribirEnArchivo(mensaje, Tipo.Modificacion);
    }

    private static void escribirEnArchivo(String mensaje, Tipo tipo) {
        try (BufferedWriter writer = Files.newBufferedWriter(logfilePath,StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.newLine();
            String timestamp = new Timestamp(System.currentTimeMillis()).toString();
            writer.write("[%s][%s]: %s".formatted(timestamp, tipo.toString(), mensaje));
        } catch (IOException e) {
            System.err.println("Error escribiendo en " + logfilePath.getFileName());
        }
    }
}