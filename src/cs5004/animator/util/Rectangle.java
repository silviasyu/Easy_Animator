package cs5004.animator.util;

/**
 * This class represents a rectangle. It defines all the operations mandated by 
 * the Shape interface. 
 * @author silvia
 */
public class Rectangle extends AbstractShape {
  /**
   * Constructs a rectangle object with the given name, location of its 
   * lower-left corner, dimensions and rgb color values.
   * @param name the name of this rectangle
   * @param t1 the time this AbstractShape is added to the canvas
   * @param x the x coordinate of the lower-left corner of this rectangle
   * @param y the y coordinate of the lower-left corner of this rectangle
   * @param width the width of this rectangle
   * @param height the height of this rectangle
   * @param redColorValue the red intensity of the color of this rectangle
   * @param greenColorValue the green intensity of the color of this rectangle
   * @param blueColorValue the blue intensity of the color of this rectangle
   */
  public Rectangle(String name, int t1, int x, int y, int width, int height, int redColorValue,
      int greenColorValue, int blueColorValue) {
    super(name, t1, new Point2D(x,y), width, height,
        new RgbValue(redColorValue, greenColorValue, blueColorValue));
  }
  
  /**
   * Returns a string that represents this Rectangle. This string has the form: 
   * "Create (r,g,b) colored rectangle shape with corner at (x,y), width w and height h", 
   * where w is the width value and h is the height value.
   * @return the formatted string
   */
  @Override 
  public String toString() {
    return "Create " + this.color.toString() + " colored rectangle " + this.name + " with "
        + "corner at " + this.reference.toString() + ", width " + this.width + " and height " 
        + this.height;
  }

  @Override
  public String getType() {
    return "rectangle";
  }
  
  @Override
  public Shape duplicate() {
    return new Rectangle(this.name, this.appearTime, this.reference.getX(), this.reference.getY(), 
        this.width, this.height, this.color.getR(), this.color.getG(), this.color.getB());
  }
}
