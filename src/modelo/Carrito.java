package modelo;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javax.imageio.ImageIO;

import vista.Dimensionable;

public class Carrito extends Thread implements Dimensionable{

	private Sprite carro;
	private Jugador jugador;
	private Point posicionAnterior;
	private double velocidad = 0.0;
	private double aceleracion = 0.3;
	private double desaceleracion = 0.2;
	private double velocidadMax = 30;
	private int anguloGiroMax = 30;
	private boolean acelerando = false;
	private boolean girandoIzq = false;
	private boolean girandoDer = false;
	private boolean colisionando = false;
	private boolean activo = true;
	private Instant tiempoInicio;
	private Instant tiempoFinal;

	public Carrito() {
		// TODO Auto-generated constructor stub
		try {
			this.carro = new Sprite(ImageIO.read(new File("src/multimedia/auto_negro.png")), new Dimension(70, 100), new Point(200, 200));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.carro.setTest(true);
	}
	
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
		
		switch(jugador.getPersonaje()) {
		case Andre:
			try {
				this.carro = new Sprite(ImageIO.read(new File("src/multimedia/auto_naranja.png")), new Dimension(70, 100), new Point(200, 200));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case Dylan:
			try {
				this.carro = new Sprite(ImageIO.read(new File("src/multimedia/auto_celeste.png")), new Dimension(70, 100), new Point(200, 200));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case Jose:
			try {
				this.carro = new Sprite(ImageIO.read(new File("src/multimedia/auto_negro.png")), new Dimension(70, 100), new Point(200, 200));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				this.carro = new Sprite(ImageIO.read(new File("src/multimedia/auto_negro.png")), new Dimension(70, 100), new Point(200, 200));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	public BufferedImage getImagen() {
		return carro.getImage();
	}

	public void setImagen(BufferedImage image) {
		carro.setImage(image);
	}

	public Dimension getDimension() {
		return carro.getDimension();
	}

	public void setDimension(Dimension dimension) {
		carro.setDimension(dimension);
	}

	public Point getPosicion() {
		return carro.getPosition();
	}

	public void setPosicion(Point position) {
		carro.setPosition(position);
	}

	public double getRotacion() {
		return carro.getDregees();
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public double getAceleracion() {
		return aceleracion;
	}

	public void setAceleracion(double aceleracion) {
		this.aceleracion = aceleracion;
	}

	public double getDesacelerancion() {
		return desaceleracion;
	}

	public void setDesacelerancion(double desacelerancion) {
		this.desaceleracion = desacelerancion;
	}

	public double getVelocidadMax() {
		return velocidadMax;
	}

	public void setVelocidadMax(double velocidadMax) {
		this.velocidadMax = velocidadMax;
	}

	public int getAnguloGiroMax() {
		return anguloGiroMax;
	}

	public void setAnguloGiroMax(int anguloGiroMax) {
		this.anguloGiroMax = anguloGiroMax;
	}

	public void setRotacion(double angulo) {
		if (angulo >= 0) {
			if (angulo <= anguloGiroMax) {
				carro.setDregees(angulo);
			} else {
				carro.setDregees(anguloGiroMax);
			}
		} else {
			if (angulo >= -anguloGiroMax) {
				carro.setDregees(angulo);
			} else {
				carro.setDregees(-anguloGiroMax);
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(carro.getPosition().x, carro.getPosition().y, carro.getDimension().width,
				carro.getDimension().height);
	}

	public boolean isAcelerando() {
		return acelerando;
	}

	public void setAcelerando(boolean estaAcelerando) {
		this.acelerando = estaAcelerando;
	}

	public boolean isGirandoIzq() {
		return girandoIzq;
	}

	public void setGirandoIzq(boolean estaGirandoIzq) {
		this.girandoIzq = estaGirandoIzq;
	}

	public boolean isGirandoDer() {
		return girandoDer;
	}

	public void setGirandoDer(boolean estaGirandoDer) {
		this.girandoDer = estaGirandoDer;
	}

	public boolean isColisionando() {
		return colisionando;
	}

	public void setColisionando(boolean estaColisionando) {
		this.colisionando = estaColisionando;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean estaActivo) {
		this.activo = estaActivo;
	}
	
	public Rectangle getCajaColision() {
		return new Rectangle(carro.getPosition().x + 10, carro.getPosition().y + 10, carro.getDimension().width - 20,
				carro.getDimension().height - 20);
	}
	
	public Duration getDuracion() {
		if (this.isAlive())
			return Duration.between(tiempoInicio, tiempoFinal);
		else
			return Duration.ZERO;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		tiempoInicio = Instant.now();
		while (activo) {
			if (acelerando) {
				if ((girandoIzq && girandoDer) || (!girandoIzq && !girandoDer)) {
					if (velocidad < velocidadMax) {
						velocidad += aceleracion;
					} else {
						velocidad = velocidadMax;
					}
				} else {
					if (velocidad < velocidadMax) {
						velocidad += desaceleracion;
					} else {
						velocidad = velocidadMax;
					}
				}
			} else {
				velocidad -= desaceleracion;
			}


			if ((girandoIzq && girandoDer) || (!girandoIzq && !girandoDer)) {
				if (getRotacion() > 0) {
					setRotacion(getRotacion() - 1);
				} else if (getRotacion() < 0) {
					setRotacion(getRotacion() + 1);
				}
			} else if (girandoIzq) {
				setRotacion(getRotacion() - 1);
			} else if (girandoDer) {
				setRotacion(getRotacion() + 1);
			}



			double rads = Math.toRadians(getRotacion());
			double sin = Math.sin(rads), cos = Math.cos(rads);
			int x = (int) Math.round(sin * (velocidad < 0? -velocidad:velocidad) + getPosicion().x);
			int y = (int) Math.round(cos * -velocidad + getPosicion().y);
			
			if (velocidad > 0) {
				if (girandoIzq || girandoDer) {
					y = getPosicion().y + 1;
				} else {
					y = (int) Math.round(cos * -1 + getPosicion().y);
				}
			}

			if (x < 0) {
				x = 0;
			} else if (x > ANCHO - getDimension().width) {
				x = ANCHO - getDimension().width;
			}

			if (y < 0) {
				y = 0;
			} else if (y > ALTO + 100) {
				activo = false;
			}
			
			if (posicionAnterior == null) {
				posicionAnterior = new Point(x, y);
			}
			
			if (colisionando) {
				setPosicion(posicionAnterior);
			}

			if (!colisionando) {
				setPosicion(new Point(x, y));
			}
			
			if (posicionAnterior.x != x && posicionAnterior.y != y) {
				posicionAnterior.x = x;
				posicionAnterior.y = y;
			}

			tiempoFinal = Instant.now();
			//carro.setTestString(velocidad + "");
			
			try {
				Thread.sleep(10);
				//carro.setPosition(new Point(carro.getPosition().x + 1, carro.getPosition().y));
				//carro.setDregees(carro.getDregees() + 1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
