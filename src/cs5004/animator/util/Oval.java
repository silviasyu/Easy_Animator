package cs5004.animator.util;

/**
 * This class represents an oval. It defines all the operations mandated by 
 * the Shape interface. 
 * @author silvia
 */
public class Oval extends AbstractShape {
  
  /**
   * Constructs a oval object with the given name, location of its center,
   * dimensions, and rgb color values.
   * @param name the name of this oval
   * @param t1 the time this AbstractShape is added to the canvas
   * @param x the x coordinate of the center of this oval
   * @param y the y coordinate of the center of this oval
   * @param xRadius the xRadius of this oval
   * @param yRadius the yRadius of this oval 
   * @param redColorValue the red intensity of the color of this oval
   * @param greenColorValue the green intensity of the color of this oval
   * @param blueColorValue the blue intensity of the color of this oval
   */
  public Oval(String name, int t1, int x, int y, int xRadius, int yRadius, int redColorValue,
      int greenColorValue, int blueColorValue) {
    super(name, t1, new Point2D(x,y), xRadius, yRadius, 
        new RgbValue(redColorValue, greenColorValue, blueColorValue));
  }

  /**
   * Returns a string that represents this Oval. This string has the form: 
   * "Create (r,g,b) colored oval shape with center at (x,y), radius xRadius and yRadius", 
   * where xRadius is the width and yRadius is the height.
   * @return the formatted string
   */
  @Override 
  public String toString() {
    return "Create " + this.color.toString() + " colored oval " + this.name + " with center at " 
        + this.reference.toString() + ", radius " + this.width + " and " + this.height;
  }
  
  @Override
  public String getType() {
    return "oval";
  }
  
  @Override
  public Shape duplicate() {
    return new Oval(this.name, this.appearTime, this.reference.getX(), this.reference.getY(), 
        this.width, this.height, this.color.getR(), this.color.getG(), this.color.getB());
  }
}
