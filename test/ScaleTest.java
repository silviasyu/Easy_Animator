import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs5004.animator.util.Oval;
import cs5004.animator.util.Rectangle;
import cs5004.animator.util.Scale;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;
import org.junit.Test;

/**
 * A JUnit test for the Scale concrete class of the ShapeAnimation
 * interface.
 * @author silvia
 */
public class ScaleTest {
  
  /**
   * This tests that the constructor works properly.
   */
  @Test
  public void testScale() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation scale = new Scale(r,51,70,25,100);
    String expected = "R changes width from 50 to 25 from time t=51 to t=70";
    assertEquals(expected,scale.toString());
   
    Shape c = new Oval("C",1,200,200,50,100,255,0,0);
    scale = new Scale(c,20,30,50,25);
    expected = "C changes height from 100 to 25 from time t=20 to t=30";
    assertEquals(expected,scale.toString());
    
    scale = new Scale(r,100,105,25,10);
    expected = "R changes width and height respectively from 50 and 100 "
        + "to 25 and 10 from time t=100 to t=105";
    assertEquals(expected,scale.toString());
    
    for (int w = 0; w < 1000; w++) {
      for (int h = 0; h < 1000; h++) {
        int t1 = h;
        int t2 = h + 10;
        
        if (w != 50 && h != 100) {
          scale = new Scale(r,t1,t2,w,100);
          expected = "R changes width from 50 to " + w + " from time t=" + t1 
              + " to t=" + t2;
          assertEquals(expected,scale.toString());

          scale = new Scale(r,t1,t2,50,h);
          expected = "R changes height from 100 to " + h + " from time t=" + t1 
              + " to t=" + t2;
          assertEquals(expected,scale.toString());

          scale = new Scale(r,t1,t2,w,h);
          expected = "R changes width and height respectively from 50 and 100 to " + w 
              + " and " + h  + " from time t=" + t1 + " to t=" + t2;
          assertEquals(expected,scale.toString());
        }
      }
    }
  }
  
  /**
   * This tests that the toString() method works properly.
   */
  @Test
  public void testToString() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation scale = new Scale(r,51,70,25,100);
    String expected = "R changes width from 50 to 25 from time t=51 to t=70";
    assertEquals(expected,scale.toString());
    
    scale = new Scale(r,100,105,25,10);
    expected = "R changes width and height respectively from 50 and 100 "
        + "to 25 and 10 from time t=100 to t=105";
    assertEquals(expected,scale.toString());
    
    Shape c = new Oval("C",1,200,200,50,100,255,0,0);
    scale = new Scale(c,20,30,50,25);
    expected = "C changes height from 100 to 25 from time t=20 to t=30";
    assertEquals(expected,scale.toString());
    
    for (int w = 0; w < 1000; w++) {
      for (int h = 0; h < 1000; h++) {
        int t1 = h;
        int t2 = h + 10;
        
        if (w != 50 && h != 100) {
          scale = new Scale(r,t1,t2,w,100);
          expected = "R changes width from 50 to " + w + " from time t=" + t1 
              + " to t=" + t2;
          assertEquals(expected,scale.toString());

          scale = new Scale(r,t1,t2,50,h);
          expected = "R changes height from 100 to " + h + " from time t=" + t1 
              + " to t=" + t2;
          assertEquals(expected,scale.toString());

          scale = new Scale(r,t1,t2,w,h);
          expected = "R changes width and height respectively from 50 and 100 to " + w 
              + " and " + h  + " from time t=" + t1 + " to t=" + t2;
          assertEquals(expected,scale.toString());
        }
      }
    }
  }
  
  /**
   * This tests that the getNewShape() method works properly. 
   */
  @Test 
  public void testGetNewShape() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation scale = new Scale(r,51,70,25,100);
    Shape expected = new Rectangle("R",1,200,200,25,100,255,0,0);
    assertEquals(expected.toString(),scale.getNewShape().toString());
    
    scale = new Scale(r,100,105,25,10);
    expected = new Rectangle("R",1,200,200,25,10,255,0,0);
    assertEquals(expected.toString(),scale.getNewShape().toString());
    
    Shape c = new Oval("C",1,200,200,50,100,255,0,0);
    scale = new Scale(c,20,30,50,25);
    expected = new Oval("C",1,200,200,50,25,255,0,0);
    assertEquals(expected.toString(),scale.getNewShape().toString());
    
    for (int w = 0; w < 1000; w++) {
      for (int h = 0; h < 1000; h++) {
        int t1 = h;
        int t2 = h + 10;
        scale = new Scale(r,t1,t2,w,100);
        expected = new Rectangle("R",1,200,200,w,100,255,0,0);
        assertEquals(expected.toString(),scale.getNewShape().toString());
        
        scale = new Scale(r,t1,t2,50,h);
        expected = new Rectangle("R",1,200,200,50,h,255,0,0);
        assertEquals(expected.toString(),scale.getNewShape().toString());
        
        scale = new Scale(r,t1,t2,w,h);
        expected = new Rectangle("R",1,200,200,w,h,255,0,0);
        assertEquals(expected.toString(),scale.getNewShape().toString());
      }
    }
  }

  /**
   * This test that the duplicate() method works properly.
   */
  @Test 
  public void testDuplicate() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation scale = new Scale(r,51,70,25,100);
    String expected = "R changes width from 50 to 25 from time t=51 to t=70";
    assertEquals(expected,scale.toString());
    assertNotEquals(scale,scale.duplicate());
    assertEquals(scale.toString(),scale.duplicate().toString());

    scale = new Scale(r,100,105,25,10);
    expected = "R changes width and height respectively from 50 and 100 "
        + "to 25 and 10 from time t=100 to t=105";
    assertEquals(expected,scale.toString());
    assertNotEquals(scale,scale.duplicate());
    assertEquals(scale.toString(),scale.duplicate().toString());
    
    Shape c = new Oval("C",1,200,200,50,100,255,0,0);
    scale = new Scale(c,20,30,50,25);
    expected = "C changes height from 100 to 25 from time t=20 to t=30";
    assertEquals(expected,scale.toString());
    assertNotEquals(scale,scale.duplicate());
    assertEquals(scale.toString(),scale.duplicate().toString());
    
    for (int w = 0; w < 1000; w++) {
      for (int h = 0; h < 1000; h++) {
        int t1 = h;
        int t2 = h + 10;
        
        if (w != 50 && h != 100) {
          scale = new Scale(r,t1,t2,w,100);
          expected = "R changes width from 50 to " + w + " from time t=" + t1 
              + " to t=" + t2;
          assertEquals(expected,scale.toString());
          assertNotEquals(scale,scale.duplicate());
          assertEquals(scale.toString(),scale.duplicate().toString());

          scale = new Scale(r,t1,t2,50,h);
          expected = "R changes height from 100 to " + h + " from time t=" + t1 
              + " to t=" + t2;
          assertEquals(expected,scale.toString());
          assertNotEquals(scale,scale.duplicate());
          assertEquals(scale.toString(),scale.duplicate().toString());

          scale = new Scale(r,t1,t2,w,h);
          expected = "R changes width and height respectively from 50 and 100 to " + w 
              + " and " + h  + " from time t=" + t1 + " to t=" + t2;
          assertEquals(expected,scale.toString());
          assertNotEquals(scale,scale.duplicate());
          assertEquals(scale.toString(),scale.duplicate().toString());
        }
      }
    }
  }
  
  /**
   * This test checks that the getT1() and getT2() methods work properly. 
   */
  @Test 
  public void testGetT1T2() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation scale = new Scale(r,51,70,25,100);
    assertEquals(51,scale.getT1());
    assertEquals(70,scale.getT2());
   
    Shape c = new Oval("C",1,200,200,50,100,255,0,0);
    scale = new Scale(c,20,30,50,25);
    assertEquals(20,scale.getT1());
    assertEquals(30,scale.getT2());
    
    scale = new Scale(r,100,105,25,10);
    assertEquals(100,scale.getT1());
    assertEquals(105,scale.getT2());
    
    for (int w = 0; w < 1000; w++) {
      for (int h = 0; h < 1000; h++) {
        int t1 = h;
        int t2 = h + 10;
        
        if (w != 50 && h != 100) {
          scale = new Scale(r,t1,t2,w,100);
          assertEquals(t1,scale.getT1());
          assertEquals(t2,scale.getT2());

          scale = new Scale(r,t1,t2,50,h);
          assertEquals(t1,scale.getT1());
          assertEquals(t2,scale.getT2());

          scale = new Scale(r,t1,t2,w,h);
          assertEquals(t1,scale.getT1());
          assertEquals(t2,scale.getT2());
        }
      }
    }
  }
  
  /**
   * This tests that the getShape() method works properly. 
   */
  @Test 
  public void testGetShape() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation scale = new Scale(r,51,70,25,100);
    Shape expected = r;
    assertEquals(expected.toString(),scale.getShape().toString());
    
    scale = new Scale(r,100,105,25,10);
    assertEquals(expected.toString(),scale.getShape().toString());
    
    Shape c = new Oval("C",1,200,200,50,100,255,0,0);
    scale = new Scale(c,20,30,50,25);
    expected = c;
    assertEquals(expected.toString(),scale.getShape().toString());
    
    for (int w = 0; w < 1000; w++) {
      for (int h = 0; h < 1000; h++) {
        int t1 = h;
        int t2 = h + 10;
        scale = new Scale(r,t1,t2,w,100);
        expected = r;
        assertEquals(expected.toString(),scale.getShape().toString());

        scale = new Scale(r,t1,t2,50,h);
        assertEquals(expected.toString(),scale.getShape().toString());

        scale = new Scale(r,t1,t2,w,h);
        assertEquals(expected.toString(),scale.getShape().toString());
      }
    }
  }
}
