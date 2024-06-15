package modelo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Sprite {
	
	private BufferedImage image;
	private BufferedImage rotatedImage;
	private Dimension dimension;
	private Point position;
	private double dregees = 0;
	private boolean test = false;
	private String testString = "Test";
	
	public Sprite(BufferedImage image) {
		super();
		this.image = image;
		this.position = new Point(0,0);
		this.dimension = new Dimension(image.getWidth(), image.getHeight());
	}

	public Sprite(BufferedImage image, Dimension dimension) {
		super();
		this.image = image;
		this.setDimension(dimension);
		this.position = new Point(0,0);
	}

	public Sprite(BufferedImage image, Dimension dimension, Point position) {
		super();
		this.image = image;
		this.setDimension(dimension);
		this.position = position;
	}

	public BufferedImage getImage() {
		this.rotatedImage = rotateImageByDegrees(image, dregees);
		this.dimension = new Dimension(rotatedImage.getWidth(), rotatedImage.getHeight());
		return rotatedImage;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		this.dimension = new Dimension(image.getWidth(), image.getHeight());
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
		this.image = transformImage(image, dimension);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public double getDregees() {
		return dregees;
	}

	public void setDregees(double dregees) {
		this.dregees = dregees;
	}
	
	public void setTest(boolean test) {
		this.test = test;
	}
	
	public void setTestString(String testString) {
		this.testString = testString;
	}

	private BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
		double rads = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
		int w = img.getWidth();
		int h = img.getHeight();
		int newWidth = (int) Math.floor(w * cos + h * sin);
		int newHeight = (int) Math.floor(h * cos + w * sin);

		BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotated.createGraphics();
		AffineTransform at = new AffineTransform();
		at.translate((newWidth - w) / 2, (newHeight - h) / 2);

		int x = w / 2;
		int y = h / 2;

		at.rotate(rads, x, y);
		g2d.setTransform(at);
		g2d.drawImage(img, 0, 0, null);
		
		if (test) {
			g2d.setColor(Color.BLACK);
			g2d.drawString(testString, x, y);
		}
		
		g2d.dispose();

		return rotated;
	}

	private BufferedImage transformImage(BufferedImage img, Dimension dimension) {
		BufferedImage transformed = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = transformed.createGraphics();
		g2d.drawImage(img, 0, 0, dimension.width, dimension.height, null);
		g2d.dispose();
		return transformed;
	}

}
