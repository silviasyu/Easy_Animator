import static org.junit.Assert.assertEquals;

import cs5004.animator.util.Point2D;
import org.junit.Test;

/**
 * A JUnit test for the Point2D class.
 * @author silvia
 */
public class Point2DTest {

  /** 
   * This tests that the constructor works properly when passed valid inputs.
   */
  @Test
  public void testPoint2D() {
    Point2D point = new Point2D(0,0);
    String expected = "(0,0)";
    assertEquals(expected,point.toString());
    
    for (int x = 0; x < 5000; x++) {
      for (int y = 0; y < 5000; y++) {
        point = new Point2D(x,y);
        expected = "(" + x + "," + y + ")";
        assertEquals(expected,point.toString());
      }
    }
  }

  /**
   * This tests that the getX() and getY() methods works properly.
   */
  @Test
  public void testGetValue() {
    Point2D point = new Point2D(0,0);
    int expected = 0;
    assertEquals(expected,point.getX());
    assertEquals(expected,point.getY());
    
    point = new Point2D(9,200);
    assertEquals(9,point.getX());
    assertEquals(200,point.getY());
    
    for (int x = 0; x < 5000; x++) {
      for (int y = 0; y < 5000; y++) {
        point = new Point2D(x,y);
        assertEquals(x,point.getX());
        assertEquals(y,point.getY());
      }
    }
  }
  
  /**
   * This tests that the toString() method works properly.
   */
  @Test
  public void testToString() {
    Point2D point = new Point2D(0,0);
    String expected = "(0,0)";
    assertEquals(expected,point.toString());
    
    point = new Point2D(1,0);
    expected = "(1,0)";
    assertEquals(expected,point.toString());

    for (int x = 0; x < 5000; x++) {
      for (int y = 0; y < 5000; y++) {
        point = new Point2D(x,y);
        expected = "(" + x + "," + y + ")";
        assertEquals(expected,point.toString());
      }
    }
  }
}
