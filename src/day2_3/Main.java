package day2_3;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

// JFrame(ウィンドウ)を継承して，オリジナルのアプリケーションを作る
public class Main extends JFrame implements Runnable {
	// フィールドを持たせる。特に外部に公開する必要がないものはprivateで
	private JPanel mainPanel;

	public static void main(String[] args) {
		System.out.println("Hello World");

		JFrame app = new Main();
	}

	public Main() {
		super("KCG Summer 2025");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
		setLocationRelativeTo(null);

		// Step.2 mainPanelを独自のCanvasに変える
		mainPanel = new Canvas(getWidth(), getHeight());

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.add(mainPanel);

		setVisible(true);

		 // 16msごとに更新（60fps）
        new Timer(16, (ActionEvent e) -> {
            mainPanel.repaint();
        }).start();
	}

	@Override
	public void run() {
		this.repaint();
	}


}
