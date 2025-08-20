package day5_1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public abstract class Figure {
	protected double vx;
	protected double vy;
	protected Position position;
	protected Color color;
	// 追加
	protected int randomR;

	public Figure() {
		this(0, 0, 0, 0, Color.BLACK);
	}

	public Figure(double x, double y) {
		this(x, y, 0, 0, Color.BLACK);
	}


	public Figure(double x, double y, Color color) {
		this(x, y, 0, 0, color);
	}

	public Figure(double x, double y, double vx, double vy) {
		this(x, y, vx, vy, Color.BLACK);
	}

	public Figure(double x, double y, double vx, double vy, Color color) {
		this.position = new Position(x, y);
		this.vx = vx;
		this.vy = vy;
		this.color = color;
		this.randomR = (int) (Math.random() * 100);
	}

	public Figure(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public int getRandomR() {
		return randomR;
	}

	public void update() {
		position.add(vx, vy);
	}

	public void update(Position target, double r) {
		this.update();

		double distance = getDistance(target);
		if(distance < r)  {
			double radian = Math.atan2(target.getY() - this.position.getY(), target.getX() - this.position.getX());

			double newX = Math.cos(radian) * -r;
			double newY = Math.sin(radian) * -r;

			this.position.set(newX + target.getX(), newY + target.getY());
		}
	}

	// この場所に移動させたい。というときはこっち使う。
	public void update(double x, double y) {
		position.set(x, y);
	}

	public abstract void draw();
	public abstract void draw(Graphics2D g2);

	@Override
	public String toString() {
		return "(x: " + position.getX() + ", y: " + position.getY() +")";
	}

	public double getDistance(Figure target) {
		return this.getDistance(target.position);
	}

	public double getDistance(Position target) {
		return Math.sqrt(Math.pow(this.position.getX() - target.getX(), 2) + Math.pow(this.position.getY() - target.getY(), 2));
	}
}
