package day5_1;

import java.awt.AWTEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	private final int PARTICLE_COUNT = 200;
	private final double R;
	private Figure[] particles;
	private boolean isDrawingInfo;
	private boolean isSeparated;
	private boolean isHit;
	private Position mousePosition;
	private StringBuilder searchWordBuilder;

	// こっちもモリモリ改造
	public Canvas(int width, int height) {
		this(width, height, new ArrayList<>());
	}

	public Canvas(int width, int height, List<String[]> dataset) {
		this.setSize(width, height);
		particles = new Figure[PARTICLE_COUNT];
		mousePosition = new Position();

		setBackground(new Color(24, 24, 24));

		int i = 0;
		// データ有のパーティクルのみ形を変える
		for (; i < dataset.size(); i++) {
			// データを持たせる
			particles[i] = new Circle(Math.random() * width, Math.random() * height, (Math.random() - 0.5) * 3,
					(Math.random() - 0.5) * 3, 8, new Color(240, 240, 240), dataset.get(i));
		}

		for (; i < PARTICLE_COUNT; i++) {
			particles[i] = new Rect(Math.random() * width, Math.random() * height, (Math.random() - 0.5) * 3,
					(Math.random() - 0.5) * 3, 8, 8, new Color(155, 155, 155));
		}

		addMouseListener(this);
		addMouseMotionListener(this);

		enableEvents(AWTEvent.KEY_EVENT_MASK);
		setFocusable(true);
		addKeyListener(this);

		R = 50;
		isDrawingInfo = false;
		isSeparated = false;
		isHit = false;
		mousePosition = new Position();
		searchWordBuilder = new StringBuilder();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Consolas", Font.BOLD, 14));
		int w = this.getWidth();
		int h = this.getHeight();

		if(isHit) {
			orbitalMove(g2, w, h);
		} else {
			freeMove(g2, w, h);
		}

	}

	private void freeMove(Graphics2D g2, int w, int h) {
		boolean checked = false;

		for (int i = 0; i < particles.length; i++) {
			if (isSeparated) {
				particles[i].update(mousePosition, R);
			} else {
				particles[i].update();
			}

			// 画面端まで行くと反対側から出てくる処理　X
			if (particles[i].position.getX() < 0)
				particles[i].position.setX(w);
			else if (w < particles[i].position.getX())
				particles[i].position.setX(0);
			// 画面端まで行くと反対側から出てくる処理　Y
			if (particles[i].position.getY() < 0)
				particles[i].position.setY(h);
			else if (h < particles[i].position.getY())
				particles[i].position.setY(0);

			g2.setPaint(particles[i].color);
			particles[i].draw(g2);

			// オブジェクト指向を活用しましょう instanceof で，指定したクラスのインスタンスかを判定できます
			if (isDrawingInfo && particles[i] instanceof Circle) {
				Circle circle = (Circle) particles[i];
				String[] data = circle.getData();
				// 文字を描く
				g2.setPaint(Color.WHITE);
				drawString(g2, data[1], circle.getPosition());

				// searchWordに当てはまる箇所は，上書き。こういう時にstartsWithメソッドは便利
				String searchWord = searchWordBuilder.toString().toLowerCase();
				String currentWord = data[1].toLowerCase();

				if(currentWord.startsWith(searchWord)) {
					g2.setPaint(Color.RED);
					drawString(g2, data[1].substring(0, searchWord.length()), circle.getPosition());
				}

				if(!checked) checked = wordCheck(currentWord, searchWord);
			}
		}
		isHit = checked;

		double distance = 0;
		double limit = 80;
		for (int i = 0; i < particles.length - 1; i++) {
			for (int j = i + 1; j < particles.length; j++) {
				distance = particles[i].getDistance(particles[j]);
				double percentage = 1 - distance / limit;
				if (distance < limit) {
					Color color = new Color(particles[i].color.getRed(), particles[i].color.getGreen(),
							particles[i].color.getBlue(), (int) (percentage * 255));
					// 太さも変えたい
					BasicStroke stroke = new BasicStroke(1 * (int) (percentage * 5));
					g2.setStroke(stroke);
					g2.setPaint(color);
					g2.drawLine((int) particles[i].position.getX(), (int) particles[i].position.getY(),
							(int) particles[j].position.getX(), (int) particles[j].position.getY());
				}
			}
		}
	}

	// データの構造は　0:id 1:name 2:grade 3:semester 4:prev
	private void orbitalMove(Graphics2D g2, int w, int h) {
		boolean checked = false;
		String searchWord = searchWordBuilder.toString();
		int index = 0;
		for (; index < particles.length; index++) {
			if(wordCheck(particles[index], searchWord)) {
				isHit = true;
				break;
			}
			isHit = false;
		}

		if(isHit) {
			// TODO: ここの処理，prevの周囲を回るように位置を変えたい

			int centerX = w / 2;
			int centerY = h / 2;
			for(int i = 0; i < particles.length; i++) {
				Figure particle = particles[i];
				boolean isCircle = (particles[i] instanceof Circle);
				if (i == index) {
					particle.update(centerX, centerY);
				} else {
					Position pos = particle.position;
					double diffX = centerX - pos.getX();
					double diffY = centerY - pos.getY();
					int distance = particle.getRandomR() + ((isCircle) ?  150 : 300);
//					double r = Math.sqrt(diffX * diffX + diffY * diffY) + distance;
					double radian = Math.atan2(diffY, diffX) + 0.01;

					double newX = Math.cos(radian) * -distance + centerX;
					double newY = Math.sin(radian) * -distance + centerY;

					particle.update(newX, newY);
				}
				g2.setPaint(particle.color);
				particle.draw(g2);

				if(isCircle && i != 0) {
					Color color = new Color(particle.color.getRed(), particle.color.getGreen(),
							particles[i].color.getBlue(), 155);
					BasicStroke stroke = new BasicStroke(2);
					g2.setStroke(stroke);
					g2.setPaint(color);

					int prev = Integer.valueOf(((Circle)particle).getData()[4]);
					g2.drawLine((int) particle.position.getX(), (int) particle.position.getY(),
							(int) particles[prev].position.getX(), (int) particles[prev].position.getY());
				}

				// 文字を表示
				if (isDrawingInfo && particles[i] instanceof Circle) {
					// TODO: ここ，関数にして切り出したいな。
					Circle circle = (Circle) particles[i];
					String[] data = circle.getData();
					// 文字を描く
					g2.setPaint(Color.WHITE);
					drawString(g2, data[1], circle.getPosition());


					String currentWord = data[1].toLowerCase();
					if(currentWord.startsWith(searchWord)) {
						g2.setPaint(Color.RED);
						drawString(g2, data[1].substring(0, searchWord.length()), circle.getPosition());
					}

					if(!checked) checked = wordCheck(currentWord, searchWord);
				}
			}
		}
	}

	// 検索ワードの完全一致を取得したい
	private boolean wordCheck(String word1, String word2) {
		return word1.equals(word2);
	}

	private boolean wordCheck(Figure obj1, String word2) {
		if(obj1 instanceof Circle) {
			return ((Circle)obj1).getData()[1].toLowerCase().equals(word2.toLowerCase());
		} else {
			return false;
		}
	}

	private void drawString(Graphics2D g2, String name, Position pos) {
		g2.drawString(name, (int)pos.getX() + 8, (int)pos.getY() + 5);
	}

	// ドラッグ状態を取得
	@Override
	public void mouseDragged(MouseEvent e) {
//		System.out.println("Dragged");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition.setX(e.getPoint().getX());
		mousePosition.setY(e.getPoint().getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			// クリックするたびに状態を変更
			switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				isDrawingInfo = !isDrawingInfo;
				break;
			case MouseEvent.BUTTON3:
				isSeparated = !isSeparated;
				break;
			}
		} else if (2 <= e.getClickCount()) {
			System.out.println("Double clicked!!");
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
//		System.out.println("mouse pressed");
	}

	// ドラッグ終了を取得
	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println("mouse released");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouse entered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouse exited");
	}

	// KeyEvent関連
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		// 入力が制御文字でなければ
		if(!Character.isISOControl(c)) {
			searchWordBuilder.append(c);
//			System.out.println(searchWordBuilder);
		}

	}

	// 特殊な操作を定義
	@Override
	public void keyPressed(KeyEvent e) {
		// 取得したキーコードで処理を変更
		switch (e.getKeyCode()) {
		// バックスペースが押された場合は入力済みの文字を削除
		case KeyEvent.VK_BACK_SPACE:
			int size = searchWordBuilder.length();
			if (0 < size) {
				searchWordBuilder.delete(size - 1, size);
			}
			break;
		// ESCが押されたときは入力内容をリセット
		case KeyEvent.VK_ESCAPE:
			searchWordBuilder.delete(0, searchWordBuilder.length());
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
