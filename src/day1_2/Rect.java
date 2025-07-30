package day1_2;


// Step.2-5
public class Rect extends Figure {
	private double width;
	private double height;

	public Rect(double x, double y, double width, double height) {
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
