package cs5004.animator.util;

/**
 * This class represents a color's RGB value. This value is denoted 
 * as (R,G,B) where R is the red intensity, G is the green intensity, 
 * and B is the blue intensity of the color. Each intensity value ranges
 * from 0 to 255 inclusively. 
 * @author silvia
 */
public class RgbValue {
  private int red;
  private int green;
  private int blue;
  
  /**
   * Constructs a RGBValue object with the given RGB value.
   * @param r the red intensity value of the color
   * @param g the green intensity value of the color
   * @param b the blue intensity value of the color
   */
  public RgbValue(int r, int g, int b) {
    this.red = r;
    this.green = g;
    this.blue = b;
  }

  /**
   * Gets and returns the red intensity value of this RGBValue. 
   * @return the red intensity value, as an int
   */
  public int getR() {
    return this.red;
  }
  
  /**
   * Gets and returns the green intensity value of this RGBValue.
   * @return the green intensity value, as an int
   */
  public int getG() {
    return this.green;
  }
  
  /**
   * Gets and returns the blue intensity value of this RGBValue.
   * @return the blue intensity value, as an int
   */
  public int getB() {
    return this.blue;
  }
  
  /**
   * Returns a string that represents the RGB value. This string will has 
   * the form: "(r,g,b)".
   * @return the formatted string
   */
  @Override
  public String toString() {
    return "(" + this.red + "," + this.green + "," + this.blue + ")";
  }
}
