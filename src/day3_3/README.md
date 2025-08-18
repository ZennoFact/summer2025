# Day3-3

実際にサンプルを作りました。パーティクルの位置関係によって繋がるようになっています。

## サンプルで行ったこと一覧

1. 2つのパーティクルの距離を出す。（三平方の定理）
    - 別名：ピタゴラスの定理
    - a^{2} + b^{2} = c^{2}

    ```java:Figure.java
	public double getDistance(Figure target) {
		return Math.sqrt(Math.pow(this.position.getX() - target.position.getX(), 2) + Math.pow(this.position.getY() - target.position.getY(), 2));
	}
	```

2. 一定の距離の中にあるパーティクル同士を線でつなぐ（drawLine)
    - `g2.drawLine(x1, y1, x2, y2);`
3. 距離を割合に変換して，透明度に設定する
	- `(int)((1 -distance / limit) * 255)`
4. 線を引くことでずれに気付くので，その調整

    ```java:Figure.java
	@Override
	public void draw(Graphics2D g2) {
		g2.fill(new Ellipse2D.Double(position.getX() - r / 2, position.getY() - r / 2, r, r));
	}
	```

 色や大きさだけでなく，複数の図形を混在させるなど，色々なことにチャレンジできるはず。三角関数が使えると，起動にも工夫がしやすいよ。