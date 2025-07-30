package day1_2;

// Step.2-2 クラス
public class Circle extends Figure {
	private int r;

	public Circle(int x, int y, int r) {
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
