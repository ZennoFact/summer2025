package day2_1;

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
public class Main extends JFrame {
	// フィールドを持たせる。特に外部に公開する必要がないものはprivateで
	private JPanel mainPanel;

	public static void main(String[] args) {
		System.out.println("Hello World");

		new Main();
	}

	public Main() {
		super("KCG Summer 2025");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
		setLocationRelativeTo(null);

		// Step.2 mainPanelを独自のCanvasに変える
		mainPanel = new Canvas();

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.add(mainPanel);

		setVisible(true);
	}
}
