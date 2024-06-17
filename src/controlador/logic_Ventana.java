package controlador;

import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import vista.Ventana;

public class logic_Ventana implements ActionListener, KeyListener{
	
	private Ventana lb;

	public logic_Ventana(Ventana lb) {
		// TODO Auto-generated constructor stub
		this.lb = lb;
		listener();
		lb.pn_juego.logic.pause();
	}
	
	private void listener() {
		lb.addKeyListener(this);
		lb.pn_seleccion.btn_Empezar.addActionListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == lb.pn_seleccion.btn_Empezar) {
			CardLayout cl = (CardLayout) lb.getContentPane().getLayout();
			cl.next(lb.getContentPane());
			lb.pn_juego.jugador1.setJugador(lb.pn_seleccion.seleccionado);
			lb.pn_juego.jugador2.setJugador(lb.pn_seleccion.seleccionado2);
			lb.pn_juego.jugador1.setPosicion(new Point(600,600));
			lb.pn_juego.jugador2.setPosicion(new Point(300,600));
			lb.pn_juego.logic.pause();
		}
	}

}
