package day2_3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

// Graphics2DはShapeを描きます。fillとdrawを使い分けていきましょう。
public class Canvas extends JPanel {
	private Figure[] dots;

	public Canvas(int width, int height) {
		this.setSize(width, height);
		dots = new Figure[200];

		int i = 0;
		for (; i < dots.length / 2; i++) {
			dots[i] = new Circle(Math.random() * width , Math.random() * height, (Math.random() - 0.5) * 3, (Math.random() - 0.5) * 3, 8, Color.PINK);
		}

		// 追加してみる
		for (; i < dots.length; i++) {
			dots[i] = new Rect(Math.random() * width, Math.random() * height, (Math.random() - 0.5) * 3, (Math.random() - 0.5) * 3, 8, 8, new Color(255, 150, 255, 255));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		int w = this.getWidth();
		int h = this.getHeight();

		for (int i = 0; i < dots.length; i++) {
			dots[i].update();

			if(dots[i].position.getX() < 0) dots[i].position.setX(w);
			else if(w < dots[i].position.getX()) dots[i].position.setX(0);

			if(dots[i].position.getY() < 0) dots[i].position.setY(h);
			else if(h < dots[i].position.getY()) dots[i].position.setY(0);

			g2.setPaint(dots[i].color);
			dots[i].draw(g2);
		}
	}
}
