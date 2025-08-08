package day3_3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

// Step.2-5
public class Rect extends Figure {
	private double width;
	private double height;

	public Rect(double x, double y, double width, double height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	public Rect(double x, double y, double width, double height, Color color) {
		super(x, y, color);
		this.width = width;
		this.height = height;
	}

	public Rect(double x, double y, double vx, double vy, double width, double height) {
		super(x, y, vx, vy);
		this.width = width;
		this.height = height;
	}

	public Rect(double x, double y, double vx, double vy, double width, double height, Color color) {
		super(x, y, vx, vy, color);
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw() {
		System.out.println(this);
	}

	// 位置がずれるので修正
	@Override
	public void draw(Graphics2D g2) {
		g2.fill(new Rectangle2D.Double(position.getX() - width / 2, position.getY() - height / 2, width, height));
	}

	@Override
	public String toString() {
		return super.toString() + "　AREA: " + this.width * this.height;
	}
}
