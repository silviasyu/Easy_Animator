package cs5004.animator.util;

/**
 * This interface represents the operations offered by animations of shapes such 
 * as adding a shape to the canvas, moving the shape within the canvas, changing 
 * the color of the shape, and scaling the shape. 
 * .
 * @author silvia
 */
public interface ShapeAnimation {
  /**
   * Gets and returns the start time of the shape modification.
   * @return the start time, as an int
   */
  int getT1();
  
  /**
   * Gets and returns the end time of the shape modification. 
   * @return the end time, as an int
   */
  int getT2();
  
  /**
   * Gets and returns the shape that is being modified. 
   * @return the shape, as a Shape
   */
  Shape getShape();
  
  /**
   * Gets and returns the new modified shape.
   * @return the new modified shape, as a Shape
   */
  Shape getNewShape();
  
  /**
   * Creates a copy of this ShapeAnimation.
   * @return the clone of this ShapeAnimation
   */
  ShapeAnimation duplicate();
}
