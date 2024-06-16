package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controlador.logic_Juego;
import modelo.Carrito;

public class Juego extends JPanel implements Dimensionable{

	private static final long serialVersionUID = 1L;
	public Carrito jugador1;
	public Carrito jugador2;
	private BufferedImage imagenCalle1; // Nueva imagen de la calle
    private Dimension dimensionImagenCalle1;

	/**
	 * Create the panel.
	 */
	public Juego() {
		this.setSize(ANCHO, ALTO);
		this.setFocusable(true);
		
		jugador1 = new Carrito();
		jugador2 = new Carrito();
		jugador2.setPosicion(new Point(jugador2.getPosicion().x + 100, jugador2.getPosicion().y));
		
		try {
            imagenCalle1 = ImageIO.read(new File("src/multimedia/calle1.png"));
            dimensionImagenCalle1 = IMAGEN_CALLE1_DIMENSION;
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		logic_Juego logic = new logic_Juego(this);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
super.paintComponent(g);
        
        // Dibujar la imagen de la calle1.png repetidamente hasta el final de la pantalla
        int x1 = 250;
        int y1 = 0;
        int width = dimensionImagenCalle1.width; // Usar la anchura definida
        int height = dimensionImagenCalle1.height; // Usar la altura definida
        
        while (y1 <= ALTO) { // Iterar hasta el final de la pantalla
            g.drawImage(imagenCalle1, x1, y1, width, height, null);
            y1 += height; // Incrementar la coordenada y por la altura de la imagen
        }
		
		g.drawImage(jugador1.getImagen(), jugador1.getPosicion().x, jugador1.getPosicion().y, jugador1.getDimension().width, jugador1.getDimension().height, null);
		
		g.drawImage(jugador2.getImagen(), jugador2.getPosicion().x, jugador2.getPosicion().y, jugador2.getDimension().width, jugador2.getDimension().height, null);
	}

}
