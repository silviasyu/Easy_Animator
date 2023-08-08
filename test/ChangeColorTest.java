import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs5004.animator.util.ChangeColor;
import cs5004.animator.util.Oval;
import cs5004.animator.util.Rectangle;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;
import org.junit.Test;

/**
 * A JUnit test for the ChangeColor concrete class of the ShapeAnimation
 * interface.
 * @author silvia
 */
public class ChangeColorTest {
  
  /**
   * This tests that the constructor works properly.
   */
  @Test
  public void testChangeColor() {
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    ShapeAnimation change = new ChangeColor(c,50,80,0,255,0);
    String expected = "C changes color from (0,0,255) to (0,255,0) from time t=50 to t=80";
    assertEquals(expected,change.toString());

    change = new ChangeColor(c,90,200,255,255,0);
    expected = "C changes color from (0,0,255) to (255,255,0) from time t=90 to t=200";
    assertEquals(expected,change.toString());

    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    change = new ChangeColor(r,1,10,100,100,100);
    expected = "R changes color from (255,0,0) to (100,100,100) from time t=1 to t=10";
    assertEquals(expected,change.toString());
    
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          int t1 = b;
          int t2 = b + 10;
          change = new ChangeColor(c,t1,t2,red,g,b);
          expected = "C changes color from (0,0,255) to (" + red + "," + g + "," + b + ") "
              + "from time t=" + t1 + " to t=" + t2;
          assertEquals(expected,change.toString());
          
          change = new ChangeColor(r,t1,t2,red,g,b);
          expected = "R changes color from (255,0,0) to (" + red + "," + g + "," + b + ") "
              + "from time t=" + t1 + " to t=" + t2;
          assertEquals(expected,change.toString());
        }
      }
    }
  }
  
  /**
   * This tests that the toString() method works properly.
   */
  @Test
  public void testToString() {
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    ShapeAnimation change = new ChangeColor(c,50,80,0,255,0);
    String expected = "C changes color from (0,0,255) to (0,255,0) from time t=50 to t=80";
    assertEquals(expected,change.toString());

    change = new ChangeColor(c,90,200,255,255,0);
    expected = "C changes color from (0,0,255) to (255,255,0) from time t=90 to t=200";
    assertEquals(expected,change.toString());

    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    change = new ChangeColor(r,1,10,100,100,100);
    expected = "R changes color from (255,0,0) to (100,100,100) from time t=1 to t=10";
    assertEquals(expected,change.toString());
    
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          int t1 = b;
          int t2 = b + 10;
          change = new ChangeColor(c,t1,t2,red,g,b);
          expected = "C changes color from (0,0,255) to (" + red + "," + g + "," + b + ") "
              + "from time t=" + t1 + " to t=" + t2;
          assertEquals(expected,change.toString());
          
          change = new ChangeColor(r,t1,t2,red,g,b);
          expected = "R changes color from (255,0,0) to (" + red + "," + g + "," + b + ") "
              + "from time t=" + t1 + " to t=" + t2;
          assertEquals(expected,change.toString());
        }
      }
    }
  }
  
  /**
   * This tests that the getNewShape() method works properly. 
   */
  @Test 
  public void testGetNewShape() {
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    ShapeAnimation change = new ChangeColor(c,50,80,0,255,0);
    Shape expected = new Oval("C",6,500,100,60,30,0,255,0);
    assertEquals(expected.toString(),change.getNewShape().toString());
    
    change = new ChangeColor(c,90,200,255,255,0);
    expected = new Oval("C",6,500,100,60,30,255,255,0);
    assertEquals(expected.toString(),change.getNewShape().toString());

    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    change = new ChangeColor(r,1,10,100,100,100);
    expected = new Rectangle("R",1,200,200,50,100,100,100,100);
    assertEquals(expected.toString(),change.getNewShape().toString());
    
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          int t1 = b;
          int t2 = b + 10;
          change = new ChangeColor(c,t1,t2,red,g,b);
          expected = new Oval("C",6,500,100,60,30,red,g,b);
          assertEquals(expected.toString(),change.getNewShape().toString());
          
          change = new ChangeColor(r,t1,t2,red,g,b);
          expected = new Rectangle("R",1,200,200,50,100,red,g,b);
          assertEquals(expected.toString(),change.getNewShape().toString());
        }
      }
    }
  }

  /**
   * This test that the duplicate() method works properly.
   */
  @Test 
  public void testDuplicate() {
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    ShapeAnimation change = new ChangeColor(c,50,80,0,255,0);
    String expected = "C changes color from (0,0,255) to (0,255,0) from time t=50 to t=80";
    assertEquals(expected,change.toString());
    assertNotEquals(change,change.duplicate());
    assertEquals(change.toString(),change.duplicate().toString());
   
    change = new ChangeColor(c,90,200,255,255,0);
    expected = "C changes color from (0,0,255) to (255,255,0) from time t=90 to t=200";
    assertEquals(expected,change.toString());
    assertNotEquals(change,change.duplicate());
    assertEquals(change.toString(),change.duplicate().toString());
    
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    change = new ChangeColor(r,1,10,100,100,100);
    expected = "R changes color from (255,0,0) to (100,100,100) from time t=1 to t=10";
    assertEquals(expected,change.toString());
    assertNotEquals(change,change.duplicate());
    assertEquals(change.toString(),change.duplicate().toString());
    
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          int t1 = b;
          int t2 = b + 10;
          change = new ChangeColor(c,t1,t2,red,g,b);
          expected = "C changes color from (0,0,255) to (" + red + "," + g + "," + b + ") "
              + "from time t=" + t1 + " to t=" + t2;
          assertEquals(expected,change.toString());
          assertNotEquals(change,change.duplicate());
          assertEquals(change.toString(),change.duplicate().toString());
          
          change = new ChangeColor(r,t1,t2,red,g,b);
          expected = "R changes color from (255,0,0) to (" + red + "," + g + "," + b + ") "
              + "from time t=" + t1 + " to t=" + t2;
          assertEquals(expected,change.toString());
          assertNotEquals(change,change.duplicate());
          assertEquals(change.toString(),change.duplicate().toString());
        }
      }
    }
  }
  
  /**
   * This tests that the getT1() and getT2() methods work properly.
   */
  @Test
  public void testGetT1T2() {
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    ShapeAnimation change = new ChangeColor(c,50,80,0,255,0);
    assertEquals(50,change.getT1());
    assertEquals(80,change.getT2());

    change = new ChangeColor(c,90,200,255,255,0);
    assertEquals(90,change.getT1());
    assertEquals(200,change.getT2());

    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    change = new ChangeColor(r,1,10,100,100,100);
    assertEquals(1,change.getT1());
    assertEquals(10,change.getT2());
    
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          int t1 = b;
          int t2 = b + 10;
          change = new ChangeColor(c,t1,t2,red,g,b);
          assertEquals(t1,change.getT1());
          assertEquals(t2,change.getT2());
              
          change = new ChangeColor(r,t1,t2,red,g,b);
          assertEquals(t1,change.getT1());
          assertEquals(t2,change.getT2());
        }
      }
    }
  }
  
  /**
   * This tests that the getNewShape() method works properly. 
   */
  @Test 
  public void testGetShape() {
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    ShapeAnimation change = new ChangeColor(c,50,80,0,255,0);
    Shape expected = c;
    assertEquals(expected.toString(),change.getShape().toString());
    
    change = new ChangeColor(c,50,80,255,255,0);
    assertEquals(expected.toString(),change.getShape().toString());

    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    change = new ChangeColor(r,1,10,100,100,100);
    expected = r;
    assertEquals(expected.toString(),change.getShape().toString());
    
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          int t1 = b;
          int t2 = b + 10;
          change = new ChangeColor(c,t1,t2,red,g,b);
          expected = c;
          assertEquals(expected.toString(),change.getShape().toString());
          
          change = new ChangeColor(r,t1,t2,red,g,b);
          expected = r;
          assertEquals(expected.toString(),change.getShape().toString());
        }
      }
    }
  }
}
