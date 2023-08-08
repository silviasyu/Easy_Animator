package cs5004.animator.view;

import cs5004.animator.controller.Controller;
import cs5004.animator.util.Point2D;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * This class represents a GuiView object, a visual GUI view of the animation.
 * It offers all the operations mandates by the IView interface.  
 * @author silvia
 */
public class GuiView extends JFrame implements IView {
  private AnimationPanel panel;
  
  /**
   * Creates a GuiView object with the given canvas attributes: the top corner, 
   * width, and height.
   * @param topCorner the top corner of the canvas
   * @param width the width of the canvas
   * @param height the height of the canvas
   */
  public GuiView(Point2D topCorner, int width, int height) {
    super("animation");
    this.setSize(width,height);
    this.setLocation(0,0);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.panel = new AnimationPanel();
    this.panel.setSize(width,height);
    
    this.panel.setLayout(new FlowLayout());
    JScrollPane scrollPane = new JScrollPane(this.panel);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(scrollPane);
    this.add(this.panel);
    
    
    this.setVisible(true);
    this.panel.repaint();
  }

  @Override
  public void display(ArrayList<Shape> shapeList, ArrayList<ShapeAnimation> animationList) {
    this.panel.setShapeList(shapeList);
    this.panel.repaint();
  }

  @Override
  public void updateCurrentShapes(ArrayList<Shape> shapeList) throws UnsupportedOperationException {
    this.panel.setShapeList(shapeList);
    this.panel.repaint();
  }

  @Override
  public void setController(Controller controller) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getInput() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
