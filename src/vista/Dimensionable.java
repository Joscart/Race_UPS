package vista;

import java.awt.Dimension;
import java.awt.Toolkit;

public interface Dimensionable {
	
	public final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	public final int ANCHO = 1100;
	public final int ALTO = 700;
	
	// Dimensiones de la imagen calle1.png
    public final Dimension IMAGEN_CALLE1_DIMENSION = new Dimension(100, 100); // Dimensiones deseadas de la imagen calle1.png
    
	
	public final int ANCHO_VENTANA = ANCHO + 25 > SCREEN_SIZE.width? SCREEN_SIZE.width : ANCHO + 25;
	public final int ALTO_VENTANA = ALTO + 50 > SCREEN_SIZE.height? SCREEN_SIZE.height : ALTO + 50;
	
	public final int POS_X = SCREEN_SIZE.width / 2 - ANCHO_VENTANA / 2;
	public final int POS_Y = SCREEN_SIZE.height / 2 - ALTO_VENTANA / 2;
	
}
