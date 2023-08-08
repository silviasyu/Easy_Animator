package cs5004.animator.util;

/** 
 * This class represents an AbstractShapeAnimation. It offers all the operations mandated by the
 * ShapeAnimation interface. 
 * @author Silvia Yu
 */
public abstract class AbstractShapeAnimation implements ShapeAnimation {
  protected Shape shape;
  protected int t1;
  protected int t2;
  
  /**
   * Constructs an AbstractShapeAnimation object with the given shape and times.
   * @param shape the shape to be changed
   * @param t1 the start time of the change
   * @param t2 the end time of the change
   */
  public AbstractShapeAnimation(Shape shape, int t1, int t2) {
    this.shape = shape;
    this.t1 = t1;
    this.t2 = t2;
  }
  
  @Override
  public int getT1() {
    return this.t1;
  }

  @Override
  public int getT2() {
    return this.t2;
  }
  
  @Override 
  public Shape getShape() {
    return this.shape;
  }
}
