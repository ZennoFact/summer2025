package day1_2;

import java.awt.Button;
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

// JFrame(ウィンドウ)を継承して，オリジナルのアプリケーションを作る
// Step.2-6-1 extends
public class Main extends JFrame {
	// フィールドを持たせる。特に外部に公開する必要がないものはprivateで
	private JPanel mainPanel;

	public static void main(String[] args) {
		/* Step.2-4 記述　Start */
		Circle c1 = new Circle(4, 10, 2);
		c1.draw();
		Rect r1 = new Rect(30, 64, 3, 5);
		System.out.println(r1);
		/* Step.2-4 記述 End */

		// Step.2-6-3

	}

	// Step.2-6-2
	public Main() {
		// タイトルを指定して親クラスのコンストラクタを起動
		super("KCG Summer 2025");
		// ウィンドウを閉じるときに一緒にアプリケーションも停止したい。
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// サイズの指定 横，縦
		setSize(new Dimension(800, 600));
		// 画面の中央に表示 サイズを決めてから実施しなければ，ズレる。
		setLocationRelativeTo(null);

		mainPanel = new JPanel();


		JLabel label = new JLabel("いえーい");

		mainPanel.add(label);

		// メインパネルをフレームに載せる。
		JPanel contentPane = (JPanel) getContentPane();
		contentPane.add(mainPanel);

		setVisible(true);
	}
}
