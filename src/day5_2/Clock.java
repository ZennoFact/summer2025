package day5_2;

import java.awt.AWTEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JPanel;

import day4_2.Position;

public class Clock extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	// 時針の設定
	private final int HOUR_LENGTH = 100;
	private final int HOUR_REVERSE_LENGTH = 10;
	private final int HOUR_WIDTH = 4;
	private final Color HOUR_COLOR = new Color(255, 160, 0);
	// 分針の設定
	private final int MINUTE_LENGTH = 200;
	private final int MINUTE_REVERSE_LENGTH = 10;
	private final int MINUTE_WIDTH = 2;
	private final Color MINUTE_COLOR = new Color(255, 160, 0);
	// 秒針の設定
	private final int SECOND_LENGTH = 175;
	private final int SECOND_REVERSE_LENGTH = 40;
	private final int SECOND_WIDTH = 2;
	private final Color SECOND_COLOR = new Color(150, 150, 150);
	private Point prevSecondPoint;

	// 時計の文字盤関係
	private final int BAR_LENGTH = 40;
	private final int BAR_WIDTH = 4;
	private final int BAR_COUNT = 12;
	private final Color BAR_COLOR = new Color(150, 150, 150);

	// 針の蓋
	private Ellipse2D.Double dot;

	// 通知画面関連
	private final Color DISPLAY_COLOR = new Color(250, 250, 250);
	private int displayAngle;
	private double displayBeginAngle;
	private int displaySpeed;

	private int width;
	private int height;
	private Point center;
	private boolean isClicked;
	private boolean isAnimated;
	private double[] startPos;
	private Arc2D.Double arc;

	private final String TITLE = "Notification:";
	private final Font TITLE_FONT = new Font("Consolas", Font.PLAIN, 32);
	private final String[] CONTENTS = {"サマーコース第2クールでJavaやる。", "成績の確認，秋学期の準備", "腹筋，背筋，腕立て，スクワット各100回"};
	private final Font CONTENT_FONT = new Font("Dialog", Font.PLAIN, 14);
	private String content;

	public Clock(int width, int height, List<String[]> dataset) {
		this.setSize(width, height);
		setBackground(new Color(24, 24, 24));

		this.width = width;
		this.height = height;
		this.center = new Point(width / 2, height / 2);
		this.isClicked = false;
		this.isAnimated = false;
		this.startPos = null;
		this.arc = new Arc2D.Double();
		this.dot = new Ellipse2D.Double(center.x - 4, center.y - 4, 8, 8);
		this.displayAngle = 0;
		this.displayBeginAngle = 0;
		this.displaySpeed = 1;

		// 今回はサンプルとして，インスタンス生成時に通知内容を決定
		this.content = CONTENTS[(int)(Math.random() * CONTENTS.length)];


		addMouseListener(this);
		addMouseMotionListener(this);

		enableEvents(AWTEvent.KEY_EVENT_MASK);
		setFocusable(true);
		addKeyListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		// アンチエイリアスの設定
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setFont(new Font("Consolas", Font.BOLD, 14));
		int w = this.getWidth();
		int h = this.getHeight();

		// 時計の文字盤の設定
		double barDegree = 360 / BAR_COUNT;
		for (int i = 0; i < BAR_COUNT; i++) {
			double[] beginPos = getPos(i * barDegree, MINUTE_LENGTH - 20);
			double[] endPos = getPos(i * barDegree, MINUTE_LENGTH + 20);

			BasicStroke stroke = new BasicStroke(BAR_WIDTH);
			g2.setStroke(stroke);
			g2.setPaint(BAR_COLOR);

			g2.drawLine((int)beginPos[0], (int)beginPos[1], (int)endPos[0], (int)endPos[1]);
		}

		clockMovement(g2, w, h);
	}

	private void clockMovement(Graphics2D g2, int w, int h) {
 		LocalDateTime now = LocalDateTime.now();

		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();


		// 時針，分針，秒針の準備描画（下から上）
		double hDeg = (hour % 12) * 30 + minute * 0.5;
		drawNeedle(g2, HOUR_LENGTH, HOUR_WIDTH, hDeg, HOUR_COLOR);
//		drawNeedle(g2, HOUR_REVERSE_LENGTH, HOUR_WIDTH, hDeg + 180, HOUR_COLOR);
		double mDeg = minute * 6;
		drawNeedle(g2, MINUTE_LENGTH, MINUTE_WIDTH, mDeg, MINUTE_COLOR);
//		drawNeedle(g2, MINUTE_REVERSE_LENGTH, MINUTE_WIDTH, mDeg + 180, MINUTE_COLOR);
		double sDeg = second * 6;
		drawNeedle(g2, SECOND_LENGTH, SECOND_WIDTH, sDeg, SECOND_COLOR);
		// 中央から反対に針を飛び出させるための描画
		drawNeedle(g2, SECOND_REVERSE_LENGTH, SECOND_WIDTH, sDeg + 180, SECOND_COLOR);

		// 針の蓋を描画
		g2.setColor(HOUR_COLOR);
		g2.fill(dot);

		if(isClicked) {
			// 弧の描画
			if(!isAnimated) {
				this.startPos = getPos(mDeg, MINUTE_LENGTH);
				isAnimated = !isAnimated;
				displayBeginAngle = -mDeg + 90;
				displayAngle = 0;
			}

			g2.setColor(DISPLAY_COLOR);
			// 弧の引数

			arc.setArc(0 - height, 0 - width, width + height + height, height + width + width, displayBeginAngle, -displayAngle, Arc2D.PIE);
			g2.fill(arc);
			if(displayAngle < 360) {
				// ｲｰｽﾞアウトにしたい
				displayAngle += 20;
			}

			// 通知の表示
			if(200 < displayAngle)  {
				drawNotification(g2);
			}
		} else {
			if(displayAngle == 360) {
				displayBeginAngle = -mDeg + 90;
			} else if(displayAngle == 0) {
				isAnimated = false;
			}

			g2.setColor(DISPLAY_COLOR);
			// 弧の引数
			arc.setArc(0 - height, 0 - width, width + height + height, height + width + width, displayBeginAngle, -displayAngle, Arc2D.PIE);
			g2.fill(arc);


			// 通知の表示
			if(280 < displayAngle)  {
				drawNotification(g2);
			}
			if(0 < displayAngle) {
				// イーズアウトにしたい
				displayAngle -= 20;
			}
		}
		// TODO: デバッグ用
//		drawNeedle(g2, MINUTE_LENGTH, MINUTE_WIDTH, mDeg, MINUTE_COLOR);
	}

	private void drawNotification(Graphics2D g2) {
		// センタリングをして，画面に表示
		FontRenderContext fontRenderContext = g2.getFontRenderContext();

		g2.setColor(HOUR_COLOR);
		g2.setFont(TITLE_FONT);
		Rectangle2D bounds = TITLE_FONT.getStringBounds(TITLE, fontRenderContext);
		g2.drawString(TITLE, center.x - (int)bounds.getWidth() / 2, center.y - 80);


		g2.setColor(Color.BLACK);
		g2.setFont(CONTENT_FONT);
		bounds = CONTENT_FONT.getStringBounds(content, fontRenderContext);
		g2.drawString(content, center.x - (int)bounds.getWidth() / 2, center.y);
	}

	private void drawNeedle(Graphics2D g2, int length, int width, double degree, Color color) {
		BasicStroke stroke = new BasicStroke(width);
		g2.setStroke(stroke);
		g2.setPaint(color);

		double[] pos = getPos(degree, length);
		g2.drawLine(center.x, center.y, (int)pos[0], (int)pos[1]);
	}

	private double[] getPos(double degree, int length) {
		double radian = Math.toRadians(degree - 90);
		double[] pos = {
			Math.cos(radian) * length + center.x,
			Math.sin(radian) * length + center.y
		};
		return pos;
	}


	// ドラッグ状態を取得
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			isClicked = !isClicked;
			if(!isAnimated) displayAngle = 0;
		} else if (2 <= e.getClickCount()) {

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	// 特殊な操作を定義
	@Override
	public void keyPressed(KeyEvent e) {
		// 取得したキーコードで処理を変更
		switch (e.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE:

			break;
		case KeyEvent.VK_ESCAPE:

			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
