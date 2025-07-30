package day1_2;

// Step.2-1 抽象クラス
public abstract class Figure {
//	private int x;
//	private int y;
	protected Position position;

	public Figure() {
		this(0, 0);
	}

	public Figure(int x, int y) {
		this.position = new Position(x, y);
//		this(new Position(x, y));
	}

	public Figure(Position position) {
		this.position = position;
	}

//	どうする？配列で返す？
//	public　??? getPosition() {
//		return {};
//	}

	// 現実世界のどこにあるか，はどの形状に対しても設定することができるはず
	public Position getPosition() {
		return position;
	}

	// 描き方や面積の出し方などは形状とサイズによって異なるため，まだ具体的に定義はしない。
	public abstract void draw();


	// 最後に改造
	@Override
		public String toString() {
			return "(x: " + position.getX() + ", y: " + position.getY() +")";
		}
}
