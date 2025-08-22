package day5_3;

import java.awt.AWTEvent;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class Canvas extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	static class Drop {
		double x;
		double y;
		double vx;
		double vy;
		double r;
		int w;
		int h;

		double viscosity; // 0.0 = 水みたいにサラサラ, 0.2 = ネバネバ


		public Drop(double x, double y, double r, int w, int h) {
			this.x = x;
			this.y = y;
			this.r = r;
			this.vx = (Math.random() - 0.5) * 2;
			this.vy = (Math.random() - 0.5) * 2;

			this.w = w;
			this.h = h;

			this.viscosity = Math.random() * 2 / 10;
		}

		public void update() {
			x += vx;
			y += vy;

			// 粘性による減速
            double vx2 = vx * (1.0 - viscosity);
            double vy2 = vx * (1.0 - viscosity);

			if (x < r || w - r < x) {
				vx = -vx;
				x = Math.max(r, Math.min(w-r, x));}
			if (y < r || h - r < y) {
				vy = -vy;
				y = Math.max(r, Math.min(h-r, y));}
		}
	}

	List<Drop> drops;
	BufferedImage buffer;
	double adjustment;

	public Canvas(int width, int height, List<String[]> dataset) {
		this.setSize(width, height);
		setBackground(new Color(24, 24, 24));

		drops = new ArrayList<>();
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		adjustment = 0.001;

		// 水滴をランダム生成
		for (int i = 0; i < 30; i++) {
			drops.add(new Drop(Math.random() * width, Math.random() * height, 5 + Math.random() * 25, width, height));
		}

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
		// アンチエイリアスの設定（
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (Drop drop : drops) {
			drop.update();
		}

		movement(g2);
	}

	private void movement(Graphics2D g2) {
		Graphics2D dg2 = buffer.createGraphics();
		// 毎回BufferedReaderを作り替える場合は処理が重くなるため避ける。
		dg2.setComposite(AlphaComposite.Clear);
		dg2.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());


		// メタボール（metaball）の処理
		// ピクセルごとに計算
		for (int y = 0; y < buffer.getHeight(); y += 1) { // 2ピクセル間引きで軽量化等も可能
			for (int x = 0; x < buffer.getWidth(); x += 1) {
				double val = 0;
				for (Drop drop : drops) {
					double dx = x - drop.x;
					double dy = y - drop.y;
					val += ((drop.r * drop.r) / (dx * dx + dy * dy + 1)  + adjustment); // +1 でゼロ除算回避
				}
				if (val > 1.0) {
					buffer.setRGB(x, y, new Color(150, 200, 255, 180).getRGB());
				}
			}
		}

		g2.drawImage(buffer, 0, 0, null);
	}


    private double distance(int x, int y, int cx, int cy) {
        double dx = x - cx, dy = y - cy;
        return Math.sqrt(dx * dx + dy * dy);
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
