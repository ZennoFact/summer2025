package day1_3;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* Step.1 start */
// JFrame(ウィンドウ)を継承して，オリジナルのアプリケーションを作る
public class Main extends JFrame {
	// フィールドを持たせる。特に外部に公開する必要がないものはprivateで
	private JPanel mainPanel;
	/* Step.3-5-1 start */
	private boolean isClicked;

	/* Step.3-5-1 end */
	public static void main(String[] args) {
		System.out.println("Hello World");

		/* Step.2-5 記述　Start */
		Circle c1 = new Circle(4, 10, 2);
		c1.draw();
		Rect r1 = new Rect(30, 64, 3, 5);
		System.out.println(r1);
		/* Step.2-5 記述 End */


		new Main();
	}

	public Main() {
		// タイトルを指定して親クラスのコンストラクタを起動
		super("KCG Summer 2025");
		// ウィンドウを閉じるときに一緒にアプリケーションも停止したい。
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// サイズの指定 横，縦
		setSize(new Dimension(800, 600));
		// 画面の中央に表示 サイズを決めてから実施しなければ，ズレる。
		setLocationRelativeTo(null);

		/* Step.3-5-2 start */
		isClicked = false;
		/* Step.3-5-2 end */
		mainPanel = new JPanel();

		/* Step.3-3-1 start */
		// 隙間をいい感じに埋めたい
		mainPanel.add(Box.createHorizontalGlue());
		/* Step.3-3-1 end */

		/* Step.3-4-1 start */
		// 等幅フォント，36pxの設定をする
		Font font = new Font("Monospaced", Font.PLAIN, 36);
		/* Step.3-4-1 end */
		JLabel label = new JLabel("いえーい");
		/* Step.3-4-2 start */
		label.setFont(font);
		/* Step.3-4-2 end */
		mainPanel.add(label);

		/* Step.3-1 start */
		// 並ぶ方向を設定して，レイアウトの設定。
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		JButton button = new JButton("Click me!");
		/* Step.3-4-3 start */
		button.setFont(font);
		/* Step.3-4-3 end */
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* Step.3-5-3 start */
				isClicked = !isClicked;
				if (isClicked) {
					/* Step.3-5-3 end */
					label.setText("Clicked!!!!!!!!!!!");
					/* Step.3-5-4 start */
				} else {
					label.setText("I love Java.");
				}
				/* Step.3-5-4 end */
			}
		});

		/* Step.3-2 start */
		// パーツ間の隙間
		mainPanel.add(Box.createHorizontalStrut(10));
		/* Step.3-2 end */

		mainPanel.add(button);
		/* Step.3-1 end */

		/* Step.3-3-2 start */
		// 隙間をいい感じに埋めたい
		mainPanel.add(Box.createHorizontalGlue());
		/* Step.3-3-2 end */

		// メインパネルをフレームに載せる。
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.add(mainPanel);

		setVisible(true);
	}
}
/* Step.1 end */
