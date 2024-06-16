package controlador;

import java.awt.CardLayout;
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//System.out.println("Key typed");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Key pressed");
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			CardLayout cl = (CardLayout) lb.getContentPane().getLayout();
			cl.next(lb.getContentPane());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
