import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

public class App extends JFrame {

    int width = 720;
    int height = 480;

    Point snake;

    int widthPoint = 20;
    int heightPoint = 20;

    int direccion = KeyEvent.VK_UP;

    int frecuencia = 30;
    ImageSnake imageSnake;

    public App() {
        setTitle("Snake");
        setSize(width, height);

        // Dependiendo del tamaño de la pantalla, calcula el centro para que el juego
        // esté centrado
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - width / 2, dim.height / 2 - height / 2);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Teclas teclas = new Teclas();
        this.addKeyListener(teclas);

        snake = new Point(width / 2 - widthPoint / 2, height / 2 - heightPoint / 2);

        imageSnake = new ImageSnake();
        this.getContentPane().add(imageSnake);

        setVisible(true);

        Moment moment = new Moment();
        Thread trid = new Thread(moment);
        trid.start();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Inició el juego!");
        App s = new App();
    }

    public void actualizar() {
        imageSnake.repaint();

    }

    public class ImageSnake extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0, 0, 255));
            g.fillRect(snake.x, snake.y, widthPoint, heightPoint);
        }
    }

    public class Teclas extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                direccion = KeyEvent.VK_UP;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                direccion = KeyEvent.VK_DOWN;
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                direccion = KeyEvent.VK_LEFT;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                direccion = KeyEvent.VK_RIGHT;
            }

        }
    }

    public class Moment extends Thread {
        long last = 0;

        public void run() {
            while (true) {
                if ((java.lang.System.currentTimeMillis() - last) > frecuencia) {

                    if (direccion == KeyEvent.VK_UP) {
                        snake.y = snake.y - widthPoint;
                        if (snake.y < 0) {
                            snake.y = height - heightPoint;
                        }
                    }
                    actualizar();
                    last = java.lang.System.currentTimeMillis();
                }
            }
        }
    }
}
