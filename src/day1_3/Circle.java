package day1_3;

// Step.2-2 クラス
public class Circle extends Figure {
	private double r;

	public Circle(double x, double y, double r) {
		super(x, y);
		this.r = r;
	}

	@Override
	public void draw() {
		System.out.println(this);
	}

	@Override
	public String toString() {
//		return "(x: " + position.getX() + ", y: " + position.getY() +")　AREA: " + Math.pow(r, 2) * Math.PI;
		// 最後に改造
		return super.toString() +"　AREA: " + Math.pow(r, 2) * Math.PI;
	}
}
