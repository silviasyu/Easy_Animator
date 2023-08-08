import static org.junit.Assert.assertEquals;

import cs5004.animator.util.RgbValue;
import org.junit.Test;

/**
 * A JUnit test for the RGBValue class.
 * @author silvia
 */
public class RgbValueTest {

  /**
   * This tests that the constructor works properly when passed valid inputs.
   */
  @Test
  public void testRgbValue() {
    RgbValue color = new RgbValue(0,0,0);
    String expected = "(0,0,0)";
    assertEquals(expected,color.toString());
    
    color = new RgbValue(255,255,255);
    expected = "(255,255,255)";
    assertEquals(expected,color.toString());
    
    for (int r = 0; r <= 255; r++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          color = new RgbValue(r,g,b);
          expected = "(" + r + "," + g + "," + b + ")";
          assertEquals(expected,color.toString());
        }
      }
    }
  }
    
  /**
   * This tests that the getR(), getG(), and getB() methods works properly.  
   */
  @Test
  public void testGetValue() {
    int expected = 0;
    RgbValue color = new RgbValue(0,0,0);
    assertEquals(expected,color.getR());
    assertEquals(expected,color.getG());
    assertEquals(expected,color.getB());
    
    expected = 255;
    color = new RgbValue(255,255,255);
    assertEquals(expected,color.getR());
    assertEquals(expected,color.getG());
    assertEquals(expected,color.getB());
    
    color = new RgbValue(0,25,255);
    assertEquals(0,color.getR());
    assertEquals(25,color.getG());
    assertEquals(255,color.getB());
    
    for (int r = 0; r <= 255; r++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          color = new RgbValue(r,g,b);
          assertEquals(r,color.getR());
          assertEquals(g,color.getG());
          assertEquals(b,color.getB());
        }
      }
    }
  } 
  
  /**
   * This tests that the toString() method works properly.
   */
  @Test
  public void testToString() {
    RgbValue color = new RgbValue(0,0,0);
    String expected = "(0,0,0)";
    assertEquals(expected,color.toString());
    
    color = new RgbValue(255,255,255);
    expected = "(255,255,255)";
    assertEquals(expected,color.toString());
    
    for (int r = 0; r <= 255; r++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          color = new RgbValue(r,g,b);
          expected = "(" + r + "," + g + "," + b + ")";
          assertEquals(expected,color.toString());
        }
      }
    } 
  }
}
