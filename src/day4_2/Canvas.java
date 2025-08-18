package day4_2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
	private Figure[] particles;

	public Canvas(int width, int height) {
		this.setSize(width, height);
		particles = new Figure[200];

		int i = 0;
		for (; i < particles.length / 2; i++) {
			particles[i] = new Circle(Math.random() * width , Math.random() * height, (Math.random() - 0.5) * 3, (Math.random() - 0.5) * 3, 8, Color.PINK);
		}

		for (; i < particles.length; i++) {
			particles[i] = new Rect(Math.random() * width, Math.random() * height, (Math.random() - 0.5) * 3, (Math.random() - 0.5) * 3, 8, 8, new Color(255, 150, 255));
		}

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		int w = this.getWidth();
		int h = this.getHeight();

		for (int i = 0; i < particles.length; i++) {
			particles[i].update();

			if(particles[i].position.getX() < 0) particles[i].position.setX(w);
			else if(w < particles[i].position.getX()) particles[i].position.setX(0);

			if(particles[i].position.getY() < 0) particles[i].position.setY(h);
			else if(h < particles[i].position.getY()) particles[i].position.setY(0);

			g2.setPaint(particles[i].color);
			particles[i].draw(g2);
		}

		double distance = 0;
		double limit = 50;
		for (int i = 0; i < particles.length - 1; i++) {
			for (int j = i + 1; j < particles.length; j++) {
				distance = particles[i].getDistance(particles[j]);
				if(distance < limit) {
					// Colorクラスのインスタンスはイミュータブルなので作り直し
					Color color = new Color(
						particles[i].color.getRed(),
						particles[i].color.getGreen(),
						particles[i].color.getBlue(),
						(int)((1 -distance / limit) * 255) // 透明度を頑張る。
					);
					g2.setPaint(color);
					g2.drawLine(
						(int)particles[i].position.getX(),
						(int)particles[i].position.getY(),
						(int)particles[j].position.getX(),
						(int)particles[j].position.getY()
					);
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.print("mouse dragged: ");
		System.out.println(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.print("mouse moved: ");
		System.out.println(e.getPoint());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 1) {
			System.out.print("mouse clicked: ");
			System.out.println(e.getPoint());
		} else if(2 <= e.getClickCount()) {
			System.out.println("Double click!!");
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mouse pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouse released");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouse entered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouse exited");
	}
}
