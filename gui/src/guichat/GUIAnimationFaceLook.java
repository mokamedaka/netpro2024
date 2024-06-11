package guichat;

import java.awt.Color;
import java.awt.Graphics;

class GUIAnimatinFaceLook { // 顔のオブジェクト

	int h = 100;
	int w = 100;

	int xStart = 0;
	int yStart = 0;

	public void setXY(int x, int y) {
		this.xStart = x;
		this.yStart = y;
	}

	public void setSize(int h, int w) {
		this.h = h;
		this.w = w;
	}

	public GUIAnimatinFaceLook() {

	}

	void makeFace(Graphics g) {
		makeFace(g, "normal");
	}

	public void makeFace(Graphics g, String emotion) {
		makeEyes(g, w / 5, emotion);
		makeNose(g, h / 5);
		makeMouth(g, w / 2, emotion);
	}

	void makeEyes(Graphics g, int eyeSize, String emotion) {
		g.setColor(Color.blue);
		g.fillArc(xStart + (h * 2 / 7), yStart + (w * 1 / 3), eyeSize, eyeSize, 0, 300);
		g.setColor(Color.black);
		g.drawOval(xStart + (h * 2 / 7), yStart + (w * 1 / 3), eyeSize, eyeSize);
		g.drawOval(xStart + (h * 4 / 7), yStart + (w * 1 / 3), eyeSize, eyeSize);

		// 表情によって眉毛の角度を変える
		int eyebrowY = yStart + (w * 1 / 3) - 10;
		if (emotion.equals("angry")) {
			g.drawLine(xStart + (h * 2 / 7), eyebrowY, xStart + (h * 2 / 7) + eyeSize, eyebrowY + 10);
			g.drawLine(xStart + (h * 4 / 7), eyebrowY + 10, xStart + (h * 4 / 7) + eyeSize, eyebrowY);
		} else if (emotion.equals("happy")) {
			g.drawLine(xStart + (h * 2 / 7), eyebrowY + 10, xStart + (h * 2 / 7) + eyeSize, eyebrowY);
			g.drawLine(xStart + (h * 4 / 7), eyebrowY, xStart + (h * 4 / 7) + eyeSize, eyebrowY + 10);
		} else { // normal or any other
			g.drawLine(xStart + (h * 2 / 7), eyebrowY, xStart + (h * 2 / 7) + eyeSize, eyebrowY);
			g.drawLine(xStart + (h * 4 / 7), eyebrowY, xStart + (h * 4 / 7) + eyeSize, eyebrowY);
		}
	}

	public void makeNose(Graphics g, int noseSize) {
		g.drawLine(xStart + (h * 1 / 2), yStart + (w * 2 / 4), xStart + (h * 1 / 2), yStart + (w * 2 / 4) + noseSize);
	}

	public void makeMouth(Graphics g, int mouthSize, String emotion) {
		int xMiddle = xStart + h / 2;
		int yMiddle = yStart + 3 * w / 4;
		if (emotion.equals("angry")) {
			g.drawLine(xMiddle - mouthSize / 2, yMiddle, xMiddle + mouthSize / 2, yMiddle);
		} else if (emotion.equals("happy")) {
			g.drawArc(xMiddle - mouthSize / 2, yMiddle - 10, mouthSize, 20, 0, -180);
		} else { // normal or any other
			g.drawLine(xMiddle - mouthSize / 2, yMiddle, xMiddle + mouthSize / 2, yMiddle);
		}
	}
}
