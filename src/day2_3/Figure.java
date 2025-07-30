package day2_3;

import java.awt.Graphics2D;

public abstract class Figure {
	protected double vx;
	protected double vy;
	protected Position position;

	public Figure() {
		this(0, 0);
	}

	public Figure(double x, double y) {
		this(x, y, 0, 0);
	}

	public Figure(double x, double y, double vx, double vy) {
		this.position = new Position(x, y);
		this.vx = vx;
		this.vy = vy;
	}

	public Figure(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void update() {
		position.add(vx, vy);
	}

	public abstract void draw();
	public abstract void draw(Graphics2D g2);


	// 最後に改造
	@Override
	public String toString() {
		return "(x: " + position.getX() + ", y: " + position.getY() +")";
	}
}
