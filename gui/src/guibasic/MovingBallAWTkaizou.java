package guibasic;

import java.awt.*;
import java.awt.event.*;

public class MovingBallAWTkaizou {
    public static void main(Stri.]]
    ng[] args) {
        FFrame f = new FFrame();
        f.setSize(500, 500);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.show();
    }

    static class FFrame extends Frame implements Runnable {

        Thread th;
        Ball[] balls = new Ball[5];

        private boolean enable = true;
        private int counter = 0;

        FFrame() {
            th = new Thread(this);
            th.start();
        }

        public void run() {

            Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA};
            int[][] positions = {{200, 150}, {50, 250}, {300, 100}, {100, 300}, {250, 200}};
            int[] radii = {10, 20, 15, 25, 18};

            for (int i = 0; i < balls.length; i++) {
                balls[i] = new Ball();
                balls[i].setPosition(positions[i][0], positions[i][1]);
                balls[i].setR(radii[i]);
                balls[i].setColor(colors[i]);
            }

            while (enable) {

                try {
                    th.sleep(100);
                    counter++;
                    if (counter >= 200) enable = false;
                } catch (InterruptedException e) {
                }

                for (Ball ball : balls) {
                    ball.move();
                }

                repaint();
            }
        }

        public void paint(Graphics g) {
            for (Ball ball : balls) {
                ball.draw(g);
            }
        }

        // Ball というインナークラスを作る
        class Ball {
            int x;
            int y;
            int r; // 半径
            Color c = Color.RED;

            int xDir = 1;  // 1:+方向  -1: -方向
            int yDir = 1;

            void setColor(Color c) {
                this.c = c;
            }

            void move() {

                if ((xDir == 1) && (x >= 300)) {
                    xDir = -1;
                }
                if ((xDir == -1) && (x <= 100)) {
                    xDir = 1;
                }

                if (xDir == 1) {
                    x = x + 10;
                } else {
                    x = x - 10;
                }

                if ((yDir == 1) && (y >= 300)) {
                    yDir = -1;
                }
                if ((yDir == -1) && (y <= 100)) {
                    yDir = 1;
                }

                if (yDir == 1) {
                    y = y + 10;
                } else {
                    y = y - 10;
                }
            }

            void setPosition(int x, int y) {
                this.x = x;
                this.y = y;
            }

            void setR(int r) {
                this.r = r;
            }

            void draw(Graphics g) {
                g.setColor(c);
                g.fillOval(x, y, 2 * r, 2 * r);  // rは半径なので2倍にする
            }
        } // inner class Ball end

    }

}
