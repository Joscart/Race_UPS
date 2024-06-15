package vista;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import controlador.logic_Juego;
import modelo.Carrito;

public class Juego extends JPanel implements Dimensionable{

	private static final long serialVersionUID = 1L;
	public Carrito jugador1;
	public Carrito jugador2;

	/**
	 * Create the panel.
	 */
	public Juego() {
		this.setSize(ANCHO, ALTO);
		this.setFocusable(true);
		
		jugador1 = new Carrito();
		jugador2 = new Carrito();
		jugador2.setPosicion(new Point(jugador2.getPosicion().x + 100, jugador2.getPosicion().y));
		
		logic_Juego logic = new logic_Juego(this);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getBackground());
		g.clearRect(0, 0, ANCHO, ALTO);
		
		g.drawImage(jugador1.getImagen(), jugador1.getPosicion().x, jugador1.getPosicion().y, jugador1.getDimension().width, jugador1.getDimension().height, null);
		
		g.drawImage(jugador2.getImagen(), jugador2.getPosicion().x, jugador2.getPosicion().y, jugador2.getDimension().width, jugador2.getDimension().height, null);
	}

}
