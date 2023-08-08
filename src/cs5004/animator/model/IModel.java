package cs5004.animator.model;

import cs5004.animator.util.Point2D;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;

import java.util.ArrayList;

/**
 * This interface represents the operations offered by the Model part of 
 * this MVC animation program. 
 * @author silvia
 */
public interface IModel {
  
  /**
   * Adds the given Shape to this animation. The collection of shapes in 
   * this animation is represented in a Java ArrayList of type Shape. 
   * @param shape the Shape to be added to the end of 
   */
  void addShape(Shape shape);
  
  /**
   * Adds the given ShapeAnimation to this animation. The collection of 
   * shape animations in this animation is represented in a Java ArrayList
   * of type ShapeAnimation. This method should also update the disappear
   * time of according to the end time of the changes made to that shape.
   * It also sorts the ShapeAnimation objects based on the start time of 
   * the animation. If the start time is the same, then it will be in the 
   * order that the animation is added. 
   * @param animation the ShapeAnimation to be added to  
   */
  void addShapeAnimation(ShapeAnimation animation);
  
  /**
   * Creates a time summary of each shape in this animation with its appear 
   * and disappear time. This method returns a formatted string representing 
   * the time summary of this animation. Note that after each time summary of 
   * a shape, there is an empty line character "\n".
   * @return the time summary, as a string 
   */
  String timeSummary();
  
  /**
   * Gets and returns the list of shapes in this animation.
   * @return the shape list, as a Java Array List
   */
  ArrayList<Shape> getShapeList();
  
  /**
   * Gets and returns the list of shape animations or changes in this animation.
   * @return the list of shape animations, as a Java Array List
   */
  ArrayList<ShapeAnimation> getAnimationList();
  
  /**
   * Gets and returns top corner of the canvas.
   * @return the top corner of the canvas, as a Point2D object
   */
  Point2D getCanvasTopCorner();
  
  /**
   * Gets and returns the width of the canvas.
   * @return the width of the canvas, as an int
   */
  int getCanvasWidth();
  
  /**
   * Gets and returns the height of the canvas.
   * @return the height of the canvas, as an int
   */
  int getCanvasHeight();
  
  /**
   * Sets the top corner of the canvas with the given top corner point.
   * @param topCorner the new top corner of the canvas
   */
  void setCanvasTopCorner(Point2D topCorner);
  
  /**
   * Sets the width of the canvas with the given width.
   * @param width the new width of the canvas
   */
  void setCanvasWidth(int width);
  
  /**
   * Sets the height of the canvas with the given height.
   * @param height the new height of the canvas
   */
  void setCanvasHeight(int height);

  /**
   * Gets and returns the end time of the whole animation.
   * @return end time of the animation, as an int
   */
  int getEndTime(); 
  
  /**
   * Gets and returns the start time of the whole animation.
   * @return start time of the animation, as an int
   */
  int getStartTime();
  
  /**
   * Sets the speed of the animation with the given speed.
   * @param speed the speed of the animation
   */
  void setSpeed(int speed);

  /**
   * Gets and returns the speed of the animation.
   * @return the speed of the animation, an an int
   */
  int getSpeed();

  
}
