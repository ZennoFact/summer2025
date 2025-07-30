package day1_2;

// Step.2-3 クラス
public class Position {
	private int x;
	private int y;

	public Position() {
		this(0, 0);
	}

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void set(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public void addX(int vx) {
		this.x += vx;
	}

	public void addY(int vy) {
		this.y += vy;
	}

	public void add(int vx, int vy) {
		this.addX(vx);
		this.addY(vy);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
