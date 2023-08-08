package cs5004.animator.util;

/**
 * This class represents a move change to a shape. It defines all the operations mandated by 
 * the ShapeAnimation interface. 
 * @author silvia
 */
public class Move extends AbstractShapeAnimation {
  private Point2D oldReference;
  private Point2D newReference;
  private Shape newShape;
  
  /**
   * Creates a Move object with the given shape, start time, end time, 
   * and new reference point. This constructor also creates a new Shape object
   * with the attributes of the modified shape.
   * @param shape the shape to be moved
   * @param t1 the start time of moving the shape
   * @param t2 the end time of moving the shape
   * @param newX the new X coordinate of the reference point of the shape
   * @param newY the new Y coordinate of the reference point of the shape
   */
  public Move(Shape shape, int t1, int t2, int newX, int newY) {
    super(shape, t1, t2);
    this.oldReference = this.shape.getReferencePoint();
    this.newReference = new Point2D(newX,newY);
    this.newShape = this.shape.duplicate();
    this.newShape.setReferencePoint(this.newReference);
  }
  
  /**
   * Returns a string that represents the move change to the shape. This string 
   * has the form: "shape name moves from (x,y) to (x,y) from time t=t1 to t=t2", 
   * where t1 is the start time and t2 is the end time of the changes to the shape.
   * @return the formatted string
   */
  @Override 
  public String toString() {
    return this.shape.getName() + " moves from " + this.oldReference.toString() 
        + " to " + this.newReference.toString() + " from time t=" + this.t1 + " to t=" + this.t2;
  }
  
  @Override
  public Shape getNewShape() {
    return this.newShape;
  }

  @Override
  public ShapeAnimation duplicate() {
    return new Move(this.shape, this.t1, this.t2, this.newReference.getX(), 
        this.newReference.getY());
  }
}
