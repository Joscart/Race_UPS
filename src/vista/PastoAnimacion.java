package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PastoAnimacion extends JPanel implements Dimensionable, Runnable {

    private static final long serialVersionUID = 1L;
    private BufferedImage pastoImage;
    private BufferedImage aguaImage;
    private BufferedImage arenaImage;
    private BufferedImage currentImage;
    private Timer timer;
    private int randomNumber;

    public PastoAnimacion() {
        try {
            pastoImage = ImageIO.read(new File("src/multimedia/pasto.png"));
            aguaImage = ImageIO.read(new File("src/multimedia/agua.png"));
            arenaImage = ImageIO.read(new File("src/multimedia/arena.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        randomNumber = generateRandomNumber();
        currentImage = pastoImage;

        // Inicializar el temporizador para cambiar las imágenes cada 15 segundos
        timer = new Timer(15000, e -> {
            randomNumber = generateRandomNumber();
            currentImage = (randomNumber % 2 == 0) ? aguaImage : arenaImage;
            repaint(); // Volver a pintar para reflejar el cambio de imagen
        });
        timer.start();

        // Iniciar el hilo para la animación de pasto
        Thread thread = new Thread(this);
        thread.start();
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 10) + 1;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar pasto en su posición actual y redibujar en las posiciones anteriores
        int y = 0;
        while (y <= ALTO) {
            int x = 0;
            while (x <= 300) { // Dibuja solo hasta x = 300
                g.drawImage(currentImage, x, y, pastoImage.getWidth(), pastoImage.getHeight(), null);
                x += pastoImage.getWidth();
            }
            y += pastoImage.getHeight();
        }

        // Dibujar pasto nuevamente desde x = 700 hasta x = 1100
        y = 0;
        while (y <= ALTO) {
            int x = 800;
            while (x <= 1100) { // Dibuja desde x = 700 hasta x = 1100
                g.drawImage(currentImage, x, y, pastoImage.getWidth(), pastoImage.getHeight(), null);
                x += pastoImage.getWidth();
            }
            y += pastoImage.getHeight();
        }
    }
    
    

    @Override
    public void run() {
        // Hilo para la animación de pasto
        while (true) {
            repaint(); // Volver a pintar para actualizar la posición de pasto.png
            try {
                Thread.sleep(500); // Pausa para la animación
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
