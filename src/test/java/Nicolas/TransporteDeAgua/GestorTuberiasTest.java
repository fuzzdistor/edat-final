package Nicolas.TransporteDeAgua;

import static org.junit.Assert.*;

import TransporteDeAgua.*;
import org.junit.Assert;
import org.junit.Test;

public class GestorTuberiasTest {
    private GestorTuberias gestorCargado() {
        //Ciudad fuente = new Ciudad("Fuente", "FU3001", 12.345, 1.2345);
        //Ciudad destino = new Ciudad("Destino", "DE3002", 67.890, 6.7890);
        GestorTuberias gt = new GestorTuberias();
        //gt.crearTuberia(fuente, destino, 12.345, 67.890, 1.2345 ,Tuberia.Estado.Activo);
        return gt;
    }

    @Test
    public void creacionTest() {
        Ciudad fuente = new Ciudad("Fuente", 2929, 12.345, "FU3001");
        Ciudad destino = new Ciudad("Destino", 3434, 67.890, "DE3003");
        GestorTuberias gt = new GestorTuberias();
        gt.crearTuberia(fuente, destino, 12.345, 67.890, 1.2345 ,Tuberia.Estado.Activo);
        Assert.assertFalse(gt.eliminarTuberia(destino, fuente));
        Assert.assertTrue(gt.eliminarTuberia(fuente, destino));
    }

    @Test
    public void cargaMasivaTuberiasTest() {
    }

}