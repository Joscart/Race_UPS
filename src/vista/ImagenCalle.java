package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagenCalle {

    private BufferedImage imagen;
    private Dimension dimension;

    public ImagenCalle(String rutaImagen, Dimension dimension) {
        try {
            this.imagen = ImageIO.read(new File(rutaImagen));
            this.dimension = dimension;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dibujar(Graphics g, int x, int y, int width, int height, int anchoPantalla, int altoPantalla) {
        int xActual = x;
        int yActual = y;

        while (yActual <= altoPantalla) {
            while (xActual <= anchoPantalla && xActual <= 800) { // Limitar hasta x 
                g.drawImage(imagen, xActual, yActual, width, height, null);
                xActual += width;
            }
            xActual = x; // Reiniciar x para la siguiente fila
            yActual += height;
        }
    }

    public Dimension getDimension() {
        return dimension;
    }
}
