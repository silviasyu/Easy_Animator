package cs5004.animator.util;

/**
 * This interface represents the operations offered by all types of shapes.
 * @author silvia
 */
public interface Shape {
  /**
   * Gets and returns the name of this Shape.
   * @return the name of this Shape, as a string
   */
  String getName();
  
  /**
   * Gets and returns the type of this Shape.
   * @return the type of this Shape, as a string
   */
  String getType();

  /**
   * Gets and returns the reference point of this Shape. 
   * Note that for different shapes the reference point has a different meaning.
   * For example, for ovals and circles the reference point is the center, while
   * for rectangles it is a corner.
   * @return the reference point of this Shape, as a Point2D object
   */
  Point2D getReferencePoint();
  
  /**
   * Gets and returns the width of this Shape. 
   * Note that for different shapes the width might have a different name. 
   * For example, for ovals the width is the x radius. 
   * @return the width of this Shape, as an int
   */
  int getWidth();
  
  /**
   * Gets and returns the height of this Shape. 
   * Note that for different shapes the height might have a different name. 
   * For example, for ovals the height is the y radius. 
   * @return the height of this Shape, as an int
   */
  int getHeight();
  
  /**
   * Gets and returns the color of this Shape.
   * @return the color of this Shape, as a RGBValue object
   */
  RgbValue getColor();
  
  /**
   * Gets and returns the appear time of this Shape.
   * @return the appear time of this Shape, as an int
   */
  int getAppearTime();
  
  /**
   * Gets and returns the disappear time of this Shape.
   * @return the disappear time of this Shape, as an int
   */
  int getDisappearTime();
  
  /**
   * Sets the width of this Shape with the given new width.
   * @param newWidth the new width
   */
  void setWidth(int newWidth);
  
  /**
   * Sets the height of this Shape with the given new height.
   * @param newHeight the new height 
   */
  void setHeight(int newHeight);
  
  /**
   * Sets the reference point of this Shape with the given new reference point.
   * @param newReference the new reference point
   */
  void setReferencePoint(Point2D newReference);
   
  /**
   * Sets the color of this Shape with the given new color.
   * @param newColor the new color
   */
  void setColor(RgbValue newColor);
  
  /**
   * Updates the disappear time of this Shape with the given new time.
   * @param newT the new disappear time
   */
  void updateDisappearTime(int newT);
  
  /**
   * Creates a copy of this Shape.
   * @return the clone of this Shape
   */
  Shape duplicate();
}
