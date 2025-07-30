package day2_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

// Step.2-1
// Graphics2DはShapeを描きます。fillとdrawを使い分けていきましょう。
public class Canvas extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D)g;
	    int w = this.getWidth();
	    int h = this.getHeight();

//	    Ellipse2D shape = new Ellipse2D.Double(0,0,w,h);
////	g2.setPaint(new Color(0,0,255,25));
//	    g2.fill(shape);
//	    g2.stroke(Shape);

	    // Step.2-3
	    for(int i = 0;i < 10;i++){
	      Ellipse2D shape = new Ellipse2D.Double(0,0,w,h - i * (w / 10));
	      g2.setPaint(new Color(0,0,255,25));
	      g2.fill(shape);
	    };
	}
}