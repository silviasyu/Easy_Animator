package cs5004.animator.model;

import cs5004.animator.util.Point2D;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class represents a Model object. It offers all the operations
 * mandated by the IModel interface.  
 * @author silvia
 */
public final class Model implements IModel {
  private ArrayList<Shape> shapeList;
  private ArrayList<ShapeAnimation> animationList;
  private int canvasX;
  private int canvasY;
  private int canvasWidth;
  private int canvasHeight;
  private int startTimeAnimation;
  private int endTimeAnimation;
  private int speed;
  
  /**
   * Creates a Model object with a collection of shapes, as a Java ArrayList 
   * and a collection of changes to those shapes, as a Java ArrayList. This Model 
   * object also has information about the canvas, such as the top left corner, the
   * width, and the height. All the attributes of the canvas are initialized to 0.
   */
  public Model() {
    this.shapeList = new ArrayList<>();
    this.animationList = new ArrayList<>();
    this.canvasX = 0;
    this.canvasY = 0;
    this.canvasWidth = 0;
    this.canvasHeight = 0;
    this.endTimeAnimation = 0;
    this.startTimeAnimation = -1;
    this.speed = 1;
  }
  
  /**
   * Returns a string that represents the model. This string has the form: 
   * 1) the list of shapes in this model
   * 2) an empty line
   * 3) the time summary of this model 
   * 4) an empty line,
   * 5) the list of changes to the shapes in the model in the order they were added 
   *    to the animation
   * Note that all shape and change entries have their own lines.
   * @return the formatted string
   */
  @Override 
  public String toString() {
    String part1 = "";
    String part2 = this.timeSummary();
    String part3 = "";
    String emptyLine = "\n";
    for (int i = 0; i < this.shapeList.size(); i++) {
      part1 += this.shapeList.get(i).toString() + "\n";
    }
    for (int i = 0; i < this.animationList.size(); i++) {
      part3 += this.animationList.get(i).toString();
      if (i < this.animationList.size() - 1) {
        part3 += "\n";
      }
    }
    return part1 + emptyLine + part2 + emptyLine + part3;
  }

  @Override
  public void addShape(Shape shape) {
    this.shapeList.add(shape);
    if (this.startTimeAnimation == -1) {
      this.startTimeAnimation = shape.getAppearTime();
    }
    else {
      if (this.startTimeAnimation > shape.getAppearTime()) {
        this.startTimeAnimation = shape.getAppearTime();
      }
    }
  }
  
  @Override
  public void addShapeAnimation(ShapeAnimation animation) {
    this.animationList.add(animation);
    if (this.endTimeAnimation < animation.getT2()) {
      this.endTimeAnimation = animation.getT2();
    }
    for (int i = 0; i < this.shapeList.size(); i++) {
      this.shapeList.get(i).updateDisappearTime(this.endTimeAnimation);
    }

    Collections.sort(this.animationList, new Comparator<ShapeAnimation>() {
      @Override
      public int compare(ShapeAnimation a, ShapeAnimation b) {
        int ta = a.getT1();
        int tb = b.getT1();
          if (ta == tb) {
            return 0;
          }
          else if (ta < tb) {
            return -1;
          }
          else {
            return 1;
          }
      }
    });
  }
  
  @Override 
  public String timeSummary() {
    String str = "";    
    for (int i = 0; i < this.shapeList.size(); i++) {
      Shape shape = this.shapeList.get(i);
      str += shape.getName() + " appears at time t=" + shape.getAppearTime() + " and "
        + "disappears at time t=" + shape.getDisappearTime() + "\n";
    }
    return str;
  }

  @Override
  public ArrayList<Shape> getShapeList() {
    ArrayList<Shape> clone = new ArrayList<Shape>();
    for (int i = 0; i < this.shapeList.size(); i++) {
      clone.add(this.shapeList.get(i).duplicate());
    }
    return clone;
  }

  @Override
  public ArrayList<ShapeAnimation> getAnimationList() {
    ArrayList<ShapeAnimation> clone = new ArrayList<ShapeAnimation>();
    for (int i = 0; i < this.animationList.size(); i++) {
      clone.add(this.animationList.get(i).duplicate());
    }
    return clone;
  }

  @Override
  public Point2D getCanvasTopCorner() {
    return new Point2D(this.canvasX, this.canvasY);
  }

  @Override
  public int getCanvasWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override
  public void setCanvasTopCorner(Point2D topCorner) {
    this.canvasX = topCorner.getX();
    this.canvasY = topCorner.getY();
  }

  @Override
  public void setCanvasWidth(int width) {
    this.canvasWidth = width;
  }

  @Override
  public void setCanvasHeight(int height) {
    this.canvasHeight = height;
  }
  
  @Override 
  public int getEndTime() {
    return this.endTimeAnimation;
  }
  
  @Override
  public int getStartTime() {
    return this.startTimeAnimation;
  }
  
  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }
  
  @Override 
  public int getSpeed() {
    return this.speed;
  }
}
