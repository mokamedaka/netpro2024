// FacesAWTMain.java
package guibasic;

import java.awt.*;
import java.awt.event.*;

public class FacesAWTKo {

    public static void main(String[] args) {
        new FacesAWTMain();
    }

    FacesAWTMain() {
        FaceFrame f = new FaceFrame();
        f.setSize(800, 800);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    // インナークラスを定義
    class FaceFrame extends Frame {

        private FaceObj[][] faces = new FaceObj[3][3];

        FaceFrame() {
            // 3x3の顔を生成
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    faces[i][j] = new FaceObj(50 + j * 250, 50 + i * 250, 200, 200, i, j);
                }
            }
        }

        public void paint(Graphics g) {
            // 3x3の顔を描画
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    faces[i][j].drawFace(g);
                }
            }
        }
    }

    // Faceクラスを作成
    private class FaceObj {
        private int xStart;
        private int yStart;
        private int w;
        private int h;
        private int expressionType;

        FaceObj(int x, int y, int w, int h, int row, int col) {
            this.xStart = x;
            this.yStart = y;
            this.w = w;
            this.h = h;
            this.expressionType = (row + col) % 3; // 表情タイプを設定
        }

        public void drawFace(Graphics g) {
            drawRim(g);
            drawBrow(g);
            drawEye(g);
            drawNose(g);
            drawMouth(g);
        }

        public void drawRim(Graphics g) { // 顔の輪郭を描く
            g.setColor(Color.BLACK);
            g.drawOval(xStart, yStart, w, h);
        }

        public void drawBrow(Graphics g) { // 眉を描く
            g.setColor(Color.BLACK);
            int browY = yStart + 40;
            g.drawLine(xStart + 50, browY, xStart + 90, browY - (expressionType == 0 ? 10 : 0));
            g.drawLine(xStart + 110, browY - (expressionType == 0 ? 10 : 0), xStart + 150, browY);
        }

        public void drawEye(Graphics g) { // 目を描く
            g.setColor(Color.BLUE);
            g.fillOval(xStart + 60, yStart + 70, 30, 30);
            g.fillOval(xStart + 110, yStart + 70, 30, 30);
        }

        public void drawNose(Graphics g) { // 鼻を描く
            g.setColor(Color.BLACK);
            g.drawLine(xStart + 100, yStart + 100, xStart + 100, yStart + 140);
        }

        public void drawMouth(Graphics g) { // 口を描く
            g.setColor(Color.RED);
            int mouthY = yStart + 180;
            switch (expressionType) {
                case 0:
                    g.drawArc(xStart + 70, mouthY - 10, 60, 20, 0, -180); // 笑顔
                    break;
                case 1:
                    g.drawLine(xStart + 80, mouthY, xStart + 120, mouthY); // 無表情
                    break;
                case 2:
                    g.drawArc(xStart + 70, mouthY - 10, 60, 20, 0, 180); // 悲しい顔
                    break;
            }
        }
    }
}
