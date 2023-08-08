package cs5004.animator.util;

/**
 * This class represents a color change to a shape. It defines all the operations mandated by 
 * the ShapeAnimation interface. 
 * @author silvia
 */
public class ChangeColor extends AbstractShapeAnimation {
  private RgbValue oldColor;
  private RgbValue newColor;
  private Shape newShape;
  
  /**
   * Creates a Move object with the given shape, start time, end time, 
   * and new reference point. This constructor also creates a new Shape object with the  
   * attributes of the modified shape.
   * @param shape the shape to be moved
   * @param t1 the start time of moving the shape
   * @param t2 the end time of moving the shape
   * @param newRed the new red intensity value of the shape
   * @param newGreen the new green intensity value of the shape
   * @param newBlue the new blue intensity value of the shape
   */
  public ChangeColor(Shape shape, int t1, int t2, int newRed, int newGreen, int newBlue) {
    super(shape, t1, t2);
    this.oldColor = this.shape.getColor();
    this.newColor = new RgbValue(newRed, newGreen, newBlue);
    this.newShape = this.shape.duplicate();
    this.newShape.setColor(newColor);
  }
  
  /**
   * Returns a string that represents the color change to the shape. This string  
   * has the form: "shape name changes color from (r,g,b) to (r,g,b) from time t=t1 to 
   * t=t2", where t1 is the start time and t2 is the end time of the changes to the shape.
   * @return the formatted string
   */
  @Override 
  public String toString() {
    return this.shape.getName() + " changes color from " + this.oldColor.toString() + " to " 
        + this.newColor.toString() + " from time t=" + this.t1 + " to t=" + this.t2;
  }
  
  @Override
  public Shape getNewShape() {
    return this.newShape;
  }

  @Override
  public ShapeAnimation duplicate() {
    return new ChangeColor(this.shape, this.t1, this.t2, this.newColor.getR(), 
        this.newColor.getG(), this.newColor.getB());
  }
}
