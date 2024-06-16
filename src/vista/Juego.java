package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import controlador.logic_Juego;
import modelo.Carrito;
import modelo.ImagenCalle;

public class Juego extends JPanel implements Dimensionable{

    private static final long serialVersionUID = 1L;
    public Carrito jugador1;
    public Carrito jugador2;
    private ImagenCalle imagenCalle1;
    private ImagenCalle imagenCalle3;
    public logic_Juego logic;

    /**
     * Create the panel.
     */
    public Juego() {
        this.setSize(ANCHO, ALTO); // Tamaño original de la ventana
        this.setFocusable(true);
        
        jugador1 = new Carrito();
        jugador2 = new Carrito();
        jugador2.setPosicion(new Point(jugador2.getPosicion().x + 100, jugador2.getPosicion().y));
        
        // Cargar las imágenes de las calles
        imagenCalle1 = new ImagenCalle("src/multimedia/calle1.png", IMAGEN_CALLE1_DIMENSION);
        imagenCalle3 = new ImagenCalle("src/multimedia/calle3.png", new Dimension(100, 100)); // Dimensiones deseadas para calle3.png
        
        logic = new logic_Juego(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Dibujar calle1.png
        imagenCalle1.dibujar(g, 250, 0, imagenCalle1.getDimension().width, imagenCalle1.getDimension().height, ANCHO, ALTO);
        
        // Dibujar calle3.png hacia la derecha y abajo hasta x = 500
        int xCalle3 = 250 + imagenCalle1.getDimension().width; // Posición x inicial de calle3.png
        imagenCalle3.dibujar(g, xCalle3, 0, imagenCalle3.getDimension().width, imagenCalle3.getDimension().height, 500, ALTO);
        
        // Dibujar a los jugadores
        g.drawImage(jugador1.getImagen(), jugador1.getPosicion().x, jugador1.getPosicion().y, jugador1.getDimension().width, jugador1.getDimension().height, null);
        g.drawImage(jugador2.getImagen(), jugador2.getPosicion().x, jugador2.getPosicion().y, jugador2.getDimension().width, jugador2.getDimension().height, null);
    }

}
