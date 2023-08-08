package cs5004.animator.util;

/**
 * This class represents a 2D point. This point is denoted in Cartesian
 * coordinates as (x,y). 
 * @author silvia
 */
public class Point2D {
  private int x; 
  private int y;
  
  /**
   * Constructs a Point2D object with the given coordinates.
   * @param x the x-coordinate of this point
   * @param y the y-coordinate of this point
   */
  public Point2D(int x, int y) throws IllegalArgumentException {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Return the x-coordinate of this point.
   * @return x-coordinate of this point, as an int
   */
  public int getX() {
    return this.x;
  }
  
  /**
   * Return the y-coordinate of this point.
   * @return y-coordinate of this point, as an int
   */
  public int getY() {
    return this.y;
  }
  
  /**
   * Returns a string that represents the 2D point. This string has the form: "(x,y)".
   * @return the formatted string
   */
  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }
}
