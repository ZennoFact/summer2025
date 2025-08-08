package day3_3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circle extends Figure {
	private double r;

	public Circle(double x, double y, double r) {
		super(x, y);
		this.r = r;
	}

	public Circle(double x, double y, double r, Color color) {
		super(x, y, color);
		this.r = r;
	}

	public Circle(double x, double y, double vx, double vy, double r) {
		super(x, y, vx, vy);
		this.r = r;
	}

	public Circle(double x, double y, double vx, double vy, double r, Color color) {
		super(x, y, vx, vy, color);
		this.r = r;
	}

	@Override
	public void draw() {
		System.out.println(this);
	}


	// 位置がずれるので修正
	@Override
	public void draw(Graphics2D g2) {
		g2.fill(new Ellipse2D.Double(position.getX() - r / 2, position.getY() - r / 2, r, r));
	}

	@Override
	public String toString() {
		return super.toString() +"　AREA: " + Math.pow(r, 2) * Math.PI;
	}
}
