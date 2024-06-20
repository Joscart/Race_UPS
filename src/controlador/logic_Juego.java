package controlador;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import vista.Juego;

public class logic_Juego extends Thread implements KeyListener{
	
	private Juego lb;
	boolean pause = false;

	public logic_Juego(Juego lb) {
		// TODO Auto-generated constructor stub
		this.lb = lb;
		listener();
		
		lb.jugador1.setVelocidad(lb.jugador1.getVelocidadMax());
		lb.jugador2.setVelocidad(lb.jugador2.getVelocidadMax());
		
		lb.fondo.start();
		lb.fondo.setVelocidad(15);
		
		lb.jugador1.start();
		lb.jugador2.start();
		
		start();
	}
	
	private void listener() {
		lb.addKeyListener(this);
	}

	public void pause() {
		if (!pause) {
			this.suspend();
			lb.fondo.suspend();
			lb.jugador1.suspend();
			lb.jugador2.suspend();
			pause = true;
			return;
		}
		
		if (pause) {
			this.resume();
			lb.fondo.resume();
			lb.jugador1.resume();
			lb.jugador2.resume();
			pause = false;
			return;
		}
	}
	
	private void colision() {
		if (lb.jugador1.getCajaColision().intersects(lb.jugador2.getCajaColision())) {
			
			lb.jugador1.setColisionando(true);
			lb.jugador2.setColisionando(true);
			
			if (lb.jugador1.getCajaColision().x < lb.jugador2.getCajaColision().x) {
				lb.jugador1.setGirandoIzq(true);
				lb.jugador2.setGirandoDer(true);
			} else if (lb.jugador1.getCajaColision().x > lb.jugador2.getCajaColision().x) {
				lb.jugador1.setGirandoDer(true);
				lb.jugador2.setGirandoIzq(true);
			} else {
				lb.jugador1.setColisionandoVertical(true);
				lb.jugador2.setColisionandoVertical(true);
			}
			
			
			Point aux = lb.jugador1.getPosicionAux();
			Point aux2 = lb.jugador2.getPosicionAux();
			
			if (aux.x < aux2.x) {
				aux.x = aux.x + (aux.x + lb.jugador1.getCajaColision().width - aux2.x) / 2;
				aux2.x = aux2.x - (aux.x + lb.jugador1.getCajaColision().width - aux2.x) / 2;
			} else if (aux.x > aux2.x) {
				aux2.x = aux2.x - (aux2.x + lb.jugador2.getCajaColision().width - aux.x) / 2;
				aux.x = aux.x + (aux2.x + lb.jugador2.getCajaColision().width - aux.x) / 2;
			}
			if (Math.abs(aux.y - aux2.y) < 30) {
				if (aux.y < aux2.y) {
					aux.y = aux.y + (aux.y + lb.jugador1.getCajaColision().height - aux2.y) / 2;
					aux2.y = aux2.y - (aux.y + lb.jugador1.getCajaColision().height - aux2.y) / 2;
				} else if (aux.y > aux2.y) {
					aux2.y = aux2.y - (aux2.y + lb.jugador2.getCajaColision().height - aux.y) / 2;
					aux.y = aux.y + (aux2.y + lb.jugador2.getCajaColision().height - aux.y) / 2;
				}
			}
			
			lb.jugador1.setPosicion(aux);
			lb.jugador2.setPosicion(aux2);
		}
		
		if (lb.jugador1.isColisionando() || lb.jugador2.isColisionando() || lb.jugador1.isColisionandoVertical() || lb.jugador2.isColisionandoVertical()) {
			
			if (!lb.jugador1.getCajaColision().intersects(lb.jugador2.getCajaColision())) {
				if (lb.jugador1.getPosicion().x < lb.jugador2.getPosicion().x) {
					lb.jugador1.setGirandoIzq(false);
					lb.jugador2.setGirandoDer(false);
				} else if (lb.jugador1.getPosicion().x > lb.jugador2.getPosicion().x) {
					lb.jugador1.setGirandoDer(false);
					lb.jugador2.setGirandoIzq(false);
				} else {
					lb.jugador1.setColisionandoVertical(false);
					lb.jugador2.setColisionandoVertical(false);
				}
			}
			
		}
		
		if (!lb.jugador1.getCajaColision().intersects(lb.jugador2.getCajaColision())) {
			lb.jugador1.setColisionando(false);
			lb.jugador2.setColisionando(false);
			lb.jugador1.setColisionandoVertical(false);
			lb.jugador2.setColisionandoVertical(false);
		}
	}
	
	@Override
	public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
            	colision();
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
			pause();
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
	}
}
