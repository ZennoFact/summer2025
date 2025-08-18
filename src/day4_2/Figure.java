package day4_2;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Figure {
	protected double vx;
	protected double vy;
	protected Position position;
	protected Color color;

	public Figure() {
		this(0, 0);
	}

	public Figure(double x, double y) {
		this(x, y, 0, 0, Color.BLACK);
	}


	public Figure(double x, double y, Color color) {
		this(x, y, 0, 0, color);
	}

	public Figure(double x, double y, double vx, double vy) {
		this(x, y, 0, 0, Color.BLACK);
	}

	public Figure(double x, double y, double vx, double vy, Color color) {
		this.position = new Position(x, y);
		this.vx = vx;
		this.vy = vy;
		this.color = color;
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

	// オーバーロードの実行
	public void update(Position target, double r) {
		this.update();

		double distance = getDistance(target);
		if(distance < r)  {
			// 出でよ，三角関数。atan2で角度を求める
			double radian = Math.atan2(target.getY() - this.position.getY(), target.getX() - this.position.getX());

			// 角度と距離から座標を求める
			double newX = Math.cos(radian) * -r;
			double newY = Math.sin(radian) * -r;
			// 結果，いける。
			this.position.set(newX + target.getX(), newY + target.getY());
		}
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

	// こっちも改造してやれ
	public double getDistance(Position target) {
		return Math.sqrt(Math.pow(this.position.getX() - target.getX(), 2) + Math.pow(this.position.getY() - target.getY(), 2));
	}
}
