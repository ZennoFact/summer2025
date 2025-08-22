package day5_3;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.crypto.Data;

public class Main extends JFrame implements Runnable {
	private JPanel mainPanel;

	public static void main(String[] args) {

		JFrame app = new Main();
	}

	// コンストラクタも修正
	public Main() {
		this(new ArrayList<>());
	}

	public Main(List<String[]> dataset) {
		super("KCG Summer 2025");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
		setLocationRelativeTo(null);

		mainPanel = new Canvas(getWidth(), getHeight(), dataset);

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.add(mainPanel);

		setVisible(true);
        new Timer(16, (ActionEvent e) -> {
            mainPanel.repaint();
        }).start();
	}

	@Override
	public void run() {
		this.repaint();
	}
}
