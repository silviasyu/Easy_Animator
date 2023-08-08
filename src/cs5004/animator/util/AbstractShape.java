package cs5004.animator.util;

/**
 * This class represents an AbstractShape. It offers all the operations mandated by the
 * Shape interface. 
 * @author silvia
 */
public abstract class AbstractShape implements Shape {
  protected String name;
  protected Point2D reference;
  protected int width;
  protected int height;
  protected RgbValue color;
  protected int appearTime;
  protected int disappearTime;
  
  /**
   * Constructs an AbstractShape object with the given name, reference point,
   * and rgb color values.
   * @param name the name of this AbstractShape
   * @param t1 the time this AbstractShape appears in the animation
   * @param reference the reference point of the AbstractShape; for an oval this is 
   *        the center, for a rectangle this is the corner
   * @param width the width of this AbstractShape; for an oval it is the xRadius, for
   *        a rectangle it is the width
   * @param height the height of this AbstactShape; for an oval it is the yRadius, for 
   *        a rectangle it is the height
   * @param color the color of this AbstractShape
   */
  public AbstractShape(String name, int t1, Point2D reference, 
      int width, int height, RgbValue color) {
    this.name = name;
    this.width = width; 
    this.height = height;
    this.reference = reference;
    this.color = color;
    this.appearTime = t1;
    this.disappearTime = 0;
  }
  
  @Override
  public String getName() {
    return this.name;
  }
  
  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public Point2D getReferencePoint() {
    return this.reference;
  }

  @Override
  public RgbValue getColor() {
    return this.color;
  }
  
  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }
  
  @Override
  public int getAppearTime() {
    return this.appearTime;
  }
  
  @Override
  public void setWidth(int newWidth) {
    this.width = newWidth;
  }

  @Override
  public void setHeight(int newHeight) {
    this.height = newHeight;
  }

  @Override
  public void setReferencePoint(Point2D newReference) {
    this.reference = newReference;
  }
  
  @Override
  public void setColor(RgbValue newColor) {
    this.color = newColor;
  }
  
  @Override
  public void updateDisappearTime(int newT) {
    this.disappearTime = newT;
  }
}
