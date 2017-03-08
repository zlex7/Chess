

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawRectExample extends JPanel {

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(new Color(212, 212, 212));
    //(x coordinate,y coordinate,width,height)
    g2d.drawRect(10, 15, 90, 60);


    g2d.setColor(new Color(31, 21, 1));
  //(x coordinate,y coordinate,width,height)
    g2d.fillRect(250, 195, 90, 60);

  }

  public static void main(String[] args) {
    DrawRectExample rects = new DrawRectExample();
    JFrame frame = new JFrame("Rectangles");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(rects);
    frame.setSize(new Dimension(360, 300));
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
