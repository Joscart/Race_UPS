package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import vista.Juego;

public class logic_Juego extends Thread implements KeyListener{
	
	Juego lb;

	public logic_Juego(Juego lb) {
		// TODO Auto-generated constructor stub
		this.lb = lb;
		listener();
		
		lb.jugador1.start();
		lb.jugador2.start();
		
		start();
	}
	
	private void listener() {
		lb.addKeyListener(this);
	}

	@Override
	public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
            	lb.repaint();
                Thread.sleep(10);
            } catch (InterruptedException e) {
            	
            }
        }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			lb.jugador1.setAcelerando(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			lb.jugador1.setAcelerando(false);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			lb.jugador2.setAcelerando(true);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			lb.jugador2.setAcelerando(false);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			lb.jugador1.setGirandoIzq(true);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			lb.jugador1.setGirandoDer(true);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			lb.jugador2.setGirandoIzq(true);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			lb.jugador2.setGirandoDer(true);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			lb.jugador1.suspend();
			lb.jugador2.suspend();
			this.suspend();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			lb.jugador1.setAcelerando(false);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			lb.jugador1.setAcelerando(false);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			lb.jugador2.setAcelerando(false);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			lb.jugador2.setAcelerando(false);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			lb.jugador1.setGirandoIzq(false);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			lb.jugador1.setGirandoDer(false);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			lb.jugador2.setGirandoIzq(false);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			lb.jugador2.setGirandoDer(false);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			lb.jugador1.resume();
			lb.jugador2.resume();
			this.resume();
		}
	}
}
