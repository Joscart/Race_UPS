package modelo;

import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import vista.Dimensionable;

public class Fondo extends Thread implements Dimensionable{
	
	private BufferedImage imagen;
	private Sprite imagenMovimiento;
	private Point posicion = new Point(0,0);
	private Dimension dimension = new Dimension(ANCHO,ALTO);
	private Dimension spriteDimension = new Dimension(100,100);
	private int velocidad = 0;
	private Sprite sprites[];
	private List<Rectangle> colisiones = new ArrayList<Rectangle>();
	private List<Sprite> autos = new ArrayList<Sprite>();
	private int contador = 0;
	
	private enum Sprite_tipo {
		GRAVA, PASTO, AGUA, ARENA, CALLE1, CALLE2, CALLE3, CARRO1, CARRO2, CARRO3
	}
	
	private void loadSprites() throws IOException {
        this.sprites = new Sprite[Sprite_tipo.values().length];
        this.sprites[Sprite_tipo.GRAVA.ordinal()] = new Sprite(ImageIO.read(new File("src/multimedia/grava.png")), spriteDimension);
        this.sprites[Sprite_tipo.PASTO.ordinal()] = new Sprite(ImageIO.read(new File("src/multimedia/pasto.png")), spriteDimension);
        this.sprites[Sprite_tipo.AGUA.ordinal()] = new Sprite(ImageIO.read(new File("src/multimedia/agua.png")), spriteDimension);
        this.sprites[Sprite_tipo.ARENA.ordinal()] = new Sprite(ImageIO.read(new File("src/multimedia/arena.png")), spriteDimension);
        this.sprites[Sprite_tipo.CALLE1.ordinal()] = new Sprite(ImageIO.read(new File("src/multimedia/calle1.png")), spriteDimension);
        this.sprites[Sprite_tipo.CALLE2.ordinal()] = new Sprite(ImageIO.read(new File("src/multimedia/calle2.png")), spriteDimension);
        this.sprites[Sprite_tipo.CALLE3.ordinal()] = new Sprite(ImageIO.read(new File("src/multimedia/calle3.png")), spriteDimension);
        this.sprites[Sprite_tipo.CARRO1.ordinal()] = new Sprite(ImageIO.read(new File("src/multimedia/carrito1.png")), new Dimension(70, 100));
        this.sprites[Sprite_tipo.CARRO2.ordinal()] = new Sprite(ImageIO.read(new File("src/multimedia/carrito2.png")), new Dimension(70, 100));
        this.sprites[Sprite_tipo.CARRO3.ordinal()] = new Sprite(ImageIO.read(new File("src/multimedia/carrito3.png")), new Dimension(70, 100));
    }

	public Fondo() {
		// TODO Auto-generated constructor stub
		try {
			loadSprites();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_ARGB);
		imagenMovimiento = new Sprite(dibujarImagenFondo(), new Dimension(ANCHO, ALTO + 200));
	}
	
	private BufferedImage dibujarImagenFondo() {
		int ancho = ANCHO;
		int alto = ALTO + 200;
		BufferedImage fondo = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = fondo.createGraphics();
		for (int i = 0; i < ancho; i += spriteDimension.width) {
			for (int j = 0; j < alto; j += spriteDimension.height) {
				g2d.drawImage(sprites[Sprite_tipo.PASTO.ordinal()].getImage(), i, j, null);
			}
		}
		for (int i = 250; i < ancho - 250; i += spriteDimension.width * 2) {
			for (int j = 0; j < alto; j += spriteDimension.height * 2) {
				if (i < 400) {
                    g2d.drawImage(sprites[Sprite_tipo.CALLE1.ordinal()].getImage(), i, j, spriteDimension.width * 2, spriteDimension.height * 2, null);
                } else if (i < 600){
                    g2d.drawImage(sprites[Sprite_tipo.CALLE3.ordinal()].getImage(), i, j, spriteDimension.width * 2, spriteDimension.height * 2, null);
                } else {
                    g2d.drawImage(sprites[Sprite_tipo.CALLE2.ordinal()].getImage(), i, j, spriteDimension.width * 2, spriteDimension.height * 2, null);
                }
			}
		}
		g2d.dispose();
		return fondo;
	}
	
	private BufferedImage dibujarFondo(Sprite fondo, int ancho, int alto) {
		BufferedImage aux = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = aux.createGraphics();
		
		if (fondo.getPosition().y > ALTO) {
			fondo.setPosition(new Point(fondo.getPosition().x, ALTO - alto));
		}
		
		g2d.drawImage(fondo.getImage(), fondo.getPosition().x, fondo.getPosition().y - alto, ancho, alto, null);
		
		g2d.drawImage(fondo.getImage(), fondo.getPosition().x, fondo.getPosition().y, ancho, alto, null);
		
		for (Rectangle rect : colisiones) {
			g2d.drawImage(autos.get(colisiones.indexOf(rect)).getImage(), rect.x - 10, rect.y - 10, null);
		}
		
		g2d.dispose();
		return aux;
	}
	
	public Rectangle generarAuto() {
		int x = (int) (Math.random() * 6);
		x = switch (x) {
		case 0 -> 250;
		case 1 -> 350;
		case 2 -> 450;
		case 3 -> 550;
		case 4 -> 650;
		case 5 -> 750;
		default -> 0;
		};
		x += 25;
		int y = (int) (Math.random() * 3);
		switch (y) {
		case 0:
			autos.add(new Sprite(sprites[Sprite_tipo.CARRO1.ordinal()].getImage()));
			break;
		case 1:
			autos.add(new Sprite(sprites[Sprite_tipo.CARRO2.ordinal()].getImage()));
			break;
		case 2:
			autos.add(new Sprite(sprites[Sprite_tipo.CARRO3.ordinal()].getImage()));
			break;
		}
		y = -100;
		return new Rectangle(x + 10, y + 10, 60, 90);
	}
	
	private void movimientoAuto() {
		List<Rectangle> eliminar = new ArrayList<Rectangle>();
		for (Rectangle rect : colisiones) {
			rect.y += velocidad;
			if (rect.y > ALTO + 10) {
				eliminar.add(rect);
				autos.remove(colisiones.indexOf(rect));
			}
		}
		colisiones.removeAll(eliminar);
		contador++;
	}
	
	public List<Rectangle> getColisiones() {
        return colisiones;
    }
	
	public BufferedImage getImagen() {
		return imagen;
	}
	
	public Point getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int ancho = ANCHO;
		int alto = ALTO + 200;
		while (true) {
			
			imagenMovimiento.setPosition(new Point(imagenMovimiento.getPosition().x, imagenMovimiento.getPosition().y + velocidad));
			
			movimientoAuto();
			
			if (contador % 15 == 0) {
				colisiones.add(generarAuto());
				contador = 0;
			}
			
			imagen = dibujarFondo(imagenMovimiento, ancho, alto);
			
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
