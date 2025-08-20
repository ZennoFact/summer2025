package day5_1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

// モリモリ改造
public class Circle extends Figure {
	private double r;
	private String[] data;

	// TODO:コンストラクタの整理
	public Circle(double x, double y, double r) {
		super(x, y);
		this.r = r;
		this.data = null;
	}

	public Circle(double x, double y, double r, Color color) {
		super(x, y, color);
		this.r = r;
		this.data = null;
	}

	public Circle(double x, double y, double vx, double vy, double r) {
		super(x, y, vx, vy);
		this.r = r;
		this.data = null;
	}

	public Circle(double x, double y, double vx, double vy, double r, Color color) {
		super(x, y, vx, vy, color);
		this.r = r;
		this.data = null;
	}

	public Circle(double x, double y, double vx, double vy, double r, Color color, String[] data) {
		super(x, y, vx, vy, color);
		this.r = r;
		this.data = data;
	}

	public String[] getData() {
		return this.data;
	}

	@Override
	public void draw() {
		System.out.println(this);
	}


	@Override
	public void draw(Graphics2D g2) {
		g2.fill(new Ellipse2D.Double(position.getX() - r / 2, position.getY() - r / 2, r, r));
	}

	@Override
	public String toString() {
		return super.toString() +"　AREA: " + Math.pow(r, 2) * Math.PI;
	}
}
