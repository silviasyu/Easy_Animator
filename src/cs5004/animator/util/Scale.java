package cs5004.animator.util;

/**
 * This class represents a dimension change to a shape. It defines all the operations mandated by 
 * the ShapeAnimation interface. 
 * @author silvia
 */
public class Scale extends AbstractShapeAnimation {
  private int oldWidth;
  private int oldHeight;
  private int newWidth;
  private int newHeight;
  private Shape newShape;
  
  /**
   * Creates a Scale object with the given shape, start time, end time, 
   * and the changed dimension. This constructor also creates a new Shape object
   * with the attributes of the modified shape.
   * @param shape the shape to be scaled
   * @param t1 the start time of scaling the shape
   * @param t2 the end time of scaling the shape
   * @param newWidth the new width of the shape 
   * @param newHeight the new height of the shape
   */
  public Scale(Shape shape, int t1, int t2, int newWidth, int newHeight) {
    super(shape, t1, t2);
    this.oldWidth = this.shape.getWidth();
    this.oldHeight = this.shape.getHeight();
    this.newWidth = newWidth;
    this.newHeight = newHeight;
    this.newShape = this.shape.duplicate();
    this.newShape.setWidth(newWidth);
    this.newShape.setHeight(newHeight);
  }
  
  /**
   * Returns a string that represents the dimension change to the shape. This string 
   * has the form: "shape name changes width/height/width and height respectively from 
   * width/height/width and height to width/height/width and height from time t=t1 to t=t2", 
   * where t1 is the start time and t2 is the end time of the changes to the shape.
   * @return the formatted string
   */
  @Override 
  public String toString() {
    String str = this.shape.getName();
    if (this.oldWidth != this.newWidth && this.oldHeight != this.newHeight) {
      str += " changes width and height respectively from " + this.oldWidth + " and " 
          + this.oldHeight + " to " + this.newWidth + " and " + this.newHeight;
    }
    if (this.oldWidth != this.newWidth && this.oldHeight == this.newHeight) {
      str += " changes width from " + this.oldWidth + " to " + this.newWidth;
    }
    if (this.oldHeight != this.newHeight && this.oldWidth == this.newWidth) {
      str += " changes height from " + this.oldHeight + " to " + this.newHeight;
    }
    return str + " from time t=" + this.t1 + " to t=" + this.t2;
  }
  
  @Override
  public Shape getNewShape() {
    return this.newShape;
  }

  @Override
  public ShapeAnimation duplicate() {
    return new Scale(this.shape, this.t1, this.t2, this.newWidth, this.newHeight);
  }
}
