package day5_2;

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


	public Canvas(int width, int height) {
		this.setSize(width, height);


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
		g2.setFont(new Font("Consolas", Font.BOLD, 14));


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
