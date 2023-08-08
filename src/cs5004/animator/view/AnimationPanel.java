package cs5004.animator.view;

import cs5004.animator.util.Shape;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class represents a AnimationPanel object. This is the panel that 
 * the animation will appear on. 
 * @author silvia 
 */
public class AnimationPanel extends JPanel {
  ArrayList<Shape> shapeList;
  
  /**
   * Creates an AnimationPanel object which is a JPanel where 
   * the animation will appear. 
   */
  public AnimationPanel() {
    super(true);
    this.setBackground(Color.WHITE);
    this.setLocation(0,0);
    this.setLayout(new FlowLayout());
    this.shapeList = null;
  }
  
  /**
   * Sets the list of shapes to the list of shapes that need to be shown 
   * in the animation at the current time.
   * @param shapeList the list of shapes, as an ArrayList
   */
  public void setShapeList(ArrayList<Shape> shapeList) {
    this.shapeList = shapeList;
  }
  
  @Override 
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    if (this.shapeList.size() > 0) {
      for (int i = 0; i < this.shapeList.size(); i++) {
        Shape shape = this.shapeList.get(i);
        g2.setColor(new Color(shape.getColor().getR(),
            shape.getColor().getB(),shape.getColor().getB()));
        int x = shape.getReferencePoint().getX();
        int y = shape.getReferencePoint().getY();
        int width = shape.getWidth();
        int height = shape.getHeight();
        if (shape.getType().equals("oval")) {
          g2.fillOval(x, y, width, height);
        }
        else if (shape.getType().equals("rectangle")) {
          g2.fillRect(x, y, width, height);
        }
  
      }
    }
  }
}
