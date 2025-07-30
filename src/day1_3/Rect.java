package day1_3;

public class Rect extends Figure {
	private int width;
	private int height;

	public Rect(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw() {
		System.out.println(this);
	}

	@Override
	public String toString() {
//		return "(x: " + position.getX() + ", y: " + position.getY() +")　AREA: " + this.width * this.height;

		// 最後に改造
		return super.toString() + "　AREA: " + this.width * this.height;
	}
}
