package vista;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PastoAnimacion extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;
    private BufferedImage pastoImage;
    private BufferedImage aguaImage;
    private BufferedImage arenaImage;
    private List<Integer> yPositions;
    private static final int ALTO = 1000; // altura de la ventana
    private ScheduledExecutorService executorService;
    private BufferedImage currentOverlayImage = null;
    private boolean drawingOverlay = false;

    public PastoAnimacion() {
        try {
            pastoImage = ImageIO.read(new File("src/multimedia/pasto.png"));
            aguaImage = ImageIO.read(new File("src/multimedia/agua.png"));
            arenaImage = ImageIO.read(new File("src/multimedia/arena.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        yPositions = new ArrayList<>();
        yPositions.add(-700); // Inicialmente dibujamos una línea de pasto fuera de la pantalla

        Thread animationThread = new Thread(this);
        animationThread.start();

        // Hilo para cambiar entre dibujar agua o arena cada 10 segundos
        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(this::toggleDrawWaterOrSand, 0, 10, TimeUnit.SECONDS);

        // Detener el cambio después de 15 segundos
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            executorService.shutdown();
        }, 15, TimeUnit.SECONDS);
    }

    private void toggleDrawWaterOrSand() {
        Random random = new Random();
        currentOverlayImage = random.nextBoolean() ? aguaImage : arenaImage;
        drawingOverlay = true;
        repaint();

        // Detener el dibujo después de 15 segundos
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            drawingOverlay = false;
            currentOverlayImage = null; // Limpiar la imagen
            repaint();
        }, 15, TimeUnit.SECONDS);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y : yPositions) {
            drawGrassPattern(g, y);
        }

        // Dibujar agua o arena sobre las líneas de pasto antes de y = 1000
        if (drawingOverlay && currentOverlayImage != null) {
            drawOverlayImage(g, currentOverlayImage);
        }
    }

    private void drawGrassPattern(Graphics g, int y) {
        // Dibujar pasto en el área de x = 0 a x = 300
        int tempY = y;
        while (tempY <= ALTO) {
            int x = 0;
            while (x <= 300) {
                g.drawImage(pastoImage, x, tempY, pastoImage.getWidth(), pastoImage.getHeight(), null);
                x += pastoImage.getWidth();
            }
            tempY += pastoImage.getHeight();
        }

        // Dibujar pasto en el área de x = 800 a x = 1100
        tempY = y;
        while (tempY <= ALTO) {
            int x = 800;
            while (x <= 1100) {
                g.drawImage(pastoImage, x, tempY, pastoImage.getWidth(), pastoImage.getHeight(), null);
                x += pastoImage.getWidth();
            }
            tempY += pastoImage.getHeight();
        }
    }

    private void drawOverlayImage(Graphics g, BufferedImage overlayImage) {
        // Dibujar sobre las líneas de pasto antes de y = 1000
        for (int y : yPositions) {
            if (y <= ALTO) { // Cambiado de 0 a ALTO
                int x = 0;
                while (x <= 300) {
                    g.drawImage(overlayImage, x, y, overlayImage.getWidth(), overlayImage.getHeight(), null);
                    x += overlayImage.getWidth();
                }
                x = 800;
                while (x <= 1100) {
                    g.drawImage(overlayImage, x, y, overlayImage.getWidth(), overlayImage.getHeight(), null);
                    x += overlayImage.getWidth();
                }
            }
        }
    }

    @Override
    public void run() {
        // Hilo para la animación de pasto
        while (true) {
            // Actualizar posiciones de y para mover hacia abajo más rápido
            for (int i = 0; i < yPositions.size(); i++) {
                yPositions.set(i, yPositions.get(i) + 10); // Incrementar más rápido
            }

            // Añadir una nueva línea de pasto si la última línea visible ha llegado a 0
            if (yPositions.get(yPositions.size() - 1) >= 0) {
                yPositions.add(-300);
            }

            // Eliminar las líneas de pasto que están fuera de la pantalla
            yPositions.removeIf(y -> y > ALTO);

            repaint(); // Volver a pintar para actualizar la posición de pasto.png

            try {
                Thread.sleep(50); // Pausa para la animación, menor pausa para más velocidad
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
