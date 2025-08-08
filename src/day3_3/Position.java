package day3_3;

// Step.2-3 クラス
public class Position {
	private double x;
	private double y;

	public Position() {
		this(0, 0);
	}

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void set(double x, double y) {
		this.setX(x);
		this.setY(y);
	}

	public void addX(double vx) {
		this.x += vx;
	}

	public void addY(double vy) {
		this.y += vy;
	}

	public void add(double vx, double vy) {
		this.addX(vx);
		this.addY(vy);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
