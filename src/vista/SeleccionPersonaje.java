package vista;

import java.awt.Color;

import javax.swing.JPanel;

public class SeleccionPersonaje extends JPanel implements Dimensionable{

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SeleccionPersonaje() {
		setLayout(null);
		setBackground(Color.GRAY);
		setSize(ANCHO, ALTO);
	}

}
