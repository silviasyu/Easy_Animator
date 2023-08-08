package cs5004.animator.util;

import cs5004.animator.model.IModel;

import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * This class represents a animation builder implementation. It offers all the operations 
 * mandated by the AnimationBuilder interface.
 * @author silvia
 */
public class AnimationBuilderImpl implements AnimationBuilder<IModel> {
  private IModel model;
  private HashMap<String,String> shapeTypeMap;
  private HashMap<String,Shape> nameMap;
  
  /**
   * Creates a Builder object with the given model. 
   * @param model the model to connect this Builder with
   */
  public AnimationBuilderImpl(IModel model) {
    this.model = model;
    this.shapeTypeMap = new HashMap<>();
    this.nameMap = new HashMap<>();
  }
  
  @Override
  public IModel build() {
    return this.model;
  }

  @Override
  public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
    this.model.setCanvasTopCorner(new Point2D(x,y));
    this.model.setCanvasWidth(width);
    this.model.setCanvasHeight(height);
    return this;
  }

  @Override
  public AnimationBuilder<IModel> declareShape(String name, String type) {
    if (type.equals("ellipse")) {
      type = "oval";
    }
    this.shapeTypeMap.put(name, type);
    return this;
  }

  @Override
  public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
      int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) { 
    if (t1 > t2) {
      JOptionPane.showMessageDialog(null,"The start time of a tranformation cannot be "
          + "greater than the end time.");
      System.exit(0);
    }
    if (t1 < 0 || t2 < 0) {
      throw new IllegalArgumentException("The start and end time of a tranformation "
          + "cannot be negative.");
    }
    if (r1 < 0 || g1 < 0 || b1 < 0 || r1 > 255 || g1 > 255 || b1 > 255 
        || r2 < 0 || g2 < 0 || b2 < 0 || r2 > 255 || g2 > 255 || b2 > 255) {
      JOptionPane.showMessageDialog(null,"The RGB values have to be between 0 and 255 "
          + "inclusively.");
      System.exit(0);
    }
    if (w1 < 0 || h1 < 0 || w2 < 0 || h2 < 0) {
      JOptionPane.showMessageDialog(null,"The width or height cannot be negative.");
      System.exit(0);
    }
    Shape shape = null;
    String type = this.shapeTypeMap.get(name);
    if (type.equals("oval")) {
      shape = new Oval(name,t1,x1,y1,w1,h1,r1,g1,b1);
    }
    if (type.equals("rectangle")) {
      shape = new Rectangle(name,t1,x1,y1,w1,h1,r1,g1,b1);
    }
    if (x1 == x2 && y1 == y2 && w1 == w2 && h1 == h2 && r1 == r2 && g1 == g2 && b1 == b2) {
      if (!this.nameMap.containsKey(name)) {
        this.model.addShape(shape);
        this.nameMap.put(name, shape);
      }
    }
    else {
      ShapeAnimation animation = null;
      if (x1 != x2 || y1 != y2 && (w1 == w2 && h1 == h2 && r1 == r2 || g1 == g2 || b1 == b2)) {
        animation = new Move(shape,t1,t2,x2,y2);
        this.model.addShapeAnimation(animation);
      }
      if (w1 != w2 || h1 != h2 && (x1 == x2 && y1 == y2 && r1 == r2 || g1 == g2 || b1 == b2)) {
        animation = new Scale(shape,t1,t2,w2,h2);
        this.model.addShapeAnimation(animation);
      }
      if (r1 != r2 || g1 != g2 || b1 != b2 && (x1 == x2 || y1 == y2 && w1 == w2 && h1 == h2)) {
        animation = new ChangeColor(shape,t1,t2,r2,g2,b2);
        this.model.addShapeAnimation(animation);
      }
    }
    return this;
  }
}
