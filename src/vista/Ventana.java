package vista;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.logic_Ventana;

public class Ventana extends JFrame implements Dimensionable, KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public SeleccionPersonaje pn_seleccion;
	public Juego pn_juego;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(POS_X, POS_Y, ANCHO_VENTANA, ALTO_VENTANA);
		setFocusable(true);
		addKeyListener(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		pn_seleccion = new SeleccionPersonaje();
		contentPane.add(pn_seleccion);
		
		pn_juego = new Juego();
		contentPane.add(pn_juego);

		logic_Ventana logic = new logic_Ventana(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		pn_juego.dispatchEvent(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		pn_juego.dispatchEvent(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		pn_juego.dispatchEvent(e);
	}

}
