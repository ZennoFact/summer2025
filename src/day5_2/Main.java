package day5_2;

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
		System.out.println("Hello World");

		// データ読み込みをしてみる。 nioが便利でおすすめ。
		Path path = Paths.get("./assets/data.csv");

		List<String[]> dataset = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

			for(int i = 1; i < lines.size(); i++) {
				String[] row = lines.get(i).split(",");

				if(row.length == 5) {
					System.out.print(row[0] + ", ");
					System.out.print(row[1] + ", ");
					System.out.print(row[2] + ", ");
					System.out.print(row[3] + ", ");
					System.out.println(row[4]);
					dataset.add(row);
				}
			}
		} catch (IOException e) {
			System.err.println("ファイル読み込みに失敗");
		}

		JFrame app = new Main(dataset);
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
