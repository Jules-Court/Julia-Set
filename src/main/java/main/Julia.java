package main;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class Julia {
    private int MAX_ITER;
    private double zoom; // Zoom/Dézoom dans l'ensemble de Julia
    private Complexe c;
    private double xDirect = 0; // Déplacement sur l'axe X
    private double yDirect = 0; // Déplacement sur l'axe Y
    private File f;
    private int heightImg;
    private BufferedImage image;

    private Julia(Complexe c, double zoom, int MAX_ITER, int heightImg) {
        this.c = c;
        this.zoom = zoom;
        this.MAX_ITER = MAX_ITER;
        this.heightImg = heightImg;
    }

    public static Julia newJulia(Complexe c, double zoom, int MAX_ITER, int heightImg, int widthImg) {
        return new Julia(c, zoom, MAX_ITER, heightImg);
    }

    public void generateJuliasurunezone(CountDownLatch l, int x_init, int y_init, int x_fin, int y_fin) {
        int height = image.getHeight();
        int width = image.getWidth();
        IntStream.range(x_init, x_fin).parallel().forEach(x -> {
            IntStream.range(y_init, y_fin).parallel().forEach(y -> {
                double reel = 1.5 * (x - width / 2) / (0.5 * zoom * width) + xDirect;
                double imag = (y - height / 2) / (0.5 * zoom * height) + yDirect;
                Complexe z = new Complexe(reel, imag);
                float i = MAX_ITER;
                while (z.abs() < 4 && i > 0) {
                    Complexe.juliaCalcul(z, c);
                    i--;
                }
                int r = 64;
                int g = 224;
                int b = 208; // turquoise
                int c = (r << 16) | (g << 8) | b;
                c = Color.HSBtoRGB((MAX_ITER / i), 0.7f, 0.7f); // Couleur pour la fractale
                image.setRGB(x, y, c);
            });
        });
        l.countDown();

    }

    void generateJulia() {

        image = new BufferedImage(heightImg, heightImg, BufferedImage.TYPE_INT_RGB);
        int height = image.getHeight();
        int width = image.getWidth();

        try {
            CountDownLatch l = new CountDownLatch(4);
            Thread t1 = new Thread(() -> generateJuliasurunezone(l, 0, 0, height / 2, width / 2));
            Thread t2 = new Thread(() -> generateJuliasurunezone(l, 0, width / 2, height / 2, width));
            Thread t3 = new Thread(() -> generateJuliasurunezone(l, height / 2, 0, height, width / 2));
            Thread t4 = new Thread(() -> generateJuliasurunezone(l, height / 2, width / 2, height, width));
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            l.await();
        } catch (Exception e) {
            System.out.println(e);
        }

        f = new File("src/main/resources/" + "f(c)=" + c.x + "+" + c.y + "i,_" + zoom + "_" + MAX_ITER + ".png"); // On créé un fichier pour l'ensemble

    }

    public void addZoom() { // Ajouter 1 de Zoom
        zoom += 1;
        generateJulia();
    }

    public void lessZoom() { // Retirer 1 de Zoom
        zoom -= 1;
        generateJulia();
    }

    public void toLeft() { // Aller a gauche
        xDirect -= 0.1 / zoom;
        generateJulia();
    }

    public void toRight() { // Aller a droite
        xDirect += 0.1 / zoom;
        generateJulia();
    }

    public void toTop() { // Aller en haut
        yDirect -= 0.1 / zoom;
        generateJulia();
    }

    public void toBottom() { // Aller en bas
        yDirect += 0.1 / zoom;
        generateJulia();
    }

    public void writeIMG() { // Sauvegarde de l'image de l'ensemble de Julia (utiliser que pour la version
                             // terminal)
        try {
            ImageIO.write(image, "PNG", f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

}
