package day2_2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

// Graphics2DはShapeを描きます。fillとdrawを使い分けていきましょう。
public class Canvas extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D)g;
	    int w = this.getWidth();
	    int h = this.getHeight();

	    Ellipse2D fillShap = new Ellipse2D.Double(0,0,w,h);
	    // グラデーションもできる。
	    GradientPaint gradation = new GradientPaint(10f,(float)h,Color.GREEN, (float)w,10,Color.BLUE);
	    g2.setPaint(gradation);
	    g2.fill(fillShap);

	    for(int i = 0;i < 10;i++){
	      Ellipse2D shape = new Ellipse2D.Double(i * (w / 20), i * (h/ 20), w - i * (w / 10),h - i * (w / 10));
	      g2.setPaint(new Color(255,255,0,255));
	      g2.setStroke(new BasicStroke(i));
	      g2.draw(shape);
	    };


	    // 線も引ける
	    BasicStroke buttStroke = new BasicStroke(24, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	    BasicStroke roundStroke = new BasicStroke(24, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
	    BasicStroke squareStroke = new BasicStroke(24, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
	    BasicStroke[] strokes = {buttStroke, roundStroke, squareStroke};

	    BasicStroke normalStroke = new BasicStroke(2);

	    for (int i = 0; i < strokes.length; i++) {
	    	g2.setPaint(Color.BLACK);
	    	g2.setStroke(strokes[i]);
	    	g2.draw(new Line2D.Double(50, 50 + 50 * i, w - 50, 50 + 50 * i));
	    	g2.setPaint(Color.WHITE);
	    	g2.setStroke(normalStroke);
	    	g2.draw(new Line2D.Double(50, 50 + 50 * i, w - 50, 50 + 50 * i));
		}


	    // 線は折ることもできる（path）
	    g2.setColor(Color.BLACK);
	    for (int i = 0; i < strokes.length; i++) {
	    	g2.setStroke(strokes[i]);
	    	GeneralPath polygon = new GeneralPath();
	    	polygon.moveTo(20 + 200 * i, 400);
	    	polygon.lineTo(50 + 200 * i, 300);
	    	polygon.lineTo(80 + 200 * i, 400);
	    	g2.draw(polygon);
		}


	    // 直接四角を描くとかもあるんやけど，形を作って置いた方が制御しやすいかもね。
	    g2.setColor(Color.RED);
	    g2.fillRect(10, 20, 30, 30);

	    g2.setStroke(new BasicStroke(5));
	    Rectangle2D rect = new Rectangle2D.Double(600, 400, 30, 50);
	    g2.draw(rect);
	}
}
