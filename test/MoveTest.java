import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs5004.animator.util.Move;
import cs5004.animator.util.Oval;
import cs5004.animator.util.Rectangle;
import cs5004.animator.util.RgbValue;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;
import org.junit.Test;

/**
 * A JUnit test for the Move concrete class of the ShapeAnimation
 * interface.
 * @author silvia
 */
public class MoveTest {
  
  /**
   * This test checks that the constructor works properly when given valid inputs.
   */
  @Test
  public void testMove() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation move = new Move(r,10,50,300,300);
    String expected = "R moves from (200,200) to (300,300) from time t=10 to t=50";
    assertEquals(expected,move.toString());
    
    move = new Move(move.getNewShape(),70,100,200,200);
    expected = "R moves from (300,300) to (200,200) from time t=70 to t=100";
    assertEquals(expected,move.toString());
    
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    move = new Move(c,20,70,500,400);
    expected = "C moves from (500,100) to (500,400) from time t=20 to t=70";
    assertEquals(expected,move.toString());
    
    for (int x = 0; x < 1000; x++) {
      for (int y = 0; y < 1000; y++) {
        int t1 = y;
        int t2 = y + 10;
        move = new Move(r,t1,t2,x,y);
        expected = "R moves from (200,200) to (" + x + "," + y + ") from time t=" + t1
            + " to t=" + t2;
        assertEquals(expected,move.toString());
        
        move = new Move(c,t1,t2,x,y);
        expected = "C moves from (500,100) to (" + x + "," + y + ") from time t=" + t1
            + " to t=" + t2;
        assertEquals(expected,move.toString());
      }
    }
  }

  
  /**
   * This test checks that the toString() method works properly.
   */
  @Test
  public void testToString() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation move = new Move(r,10,50,300,300);
    String expected = "R moves from (200,200) to (300,300) from time t=10 to t=50";
    assertEquals(expected,move.toString());
    
    move = new Move(move.getNewShape(),70,100,200,200);
    expected = "R moves from (300,300) to (200,200) from time t=70 to t=100";
    assertEquals(expected,move.toString());
    
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    move = new Move(c,20,70,500,400);
    expected = "C moves from (500,100) to (500,400) from time t=20 to t=70";
    assertEquals(expected,move.toString());
    
    for (int x = 0; x < 1000; x++) {
      for (int y = 0; y < 1000; y++) {
        int t1 = y;
        int t2 = y + 10;
        move = new Move(r,t1,t2,x,y);
        expected = "R moves from (200,200) to (" + x + "," + y + ") from time t=" + t1
            + " to t=" + t2;
        assertEquals(expected,move.toString());
        
        move = new Move(c,t1,t2,x,y);
        expected = "C moves from (500,100) to (" + x + "," + y + ") from time t=" + t1
            + " to t=" + t2;
        assertEquals(expected,move.toString());
      }
    }
  }
  
  /**
   * This test checks that the getNewShape() method works properly. 
   */
  @Test 
  public void testGetNewShape() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation move = new Move(r,10,50,300,300);
    Shape expected = new Rectangle("R",1,300,300,50,100,255,0,0);
    assertEquals(expected.toString(),move.getNewShape().toString());
    
    move = new Move(move.getNewShape(),70,100,200,200);
    expected = new Rectangle("R",1,200,200,50,100,255,0,0);
    assertEquals(expected.toString(),move.getNewShape().toString());
    
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    move = new Move(c,20,70,500,400);
    expected = new Oval("C",6,500,400,60,30,0,0,255);
    assertEquals(expected.toString(),move.getNewShape().toString());
    
    
    for (int x = 0; x < 1000; x++) {
      for (int y = 0; y < 1000; y++) {
        int t1 = y;
        int t2 = y + 10;
        move = new Move(r,t1,t2,x,y);
        RgbValue color = r.getColor();
        expected = new Rectangle(r.getName(),r.getAppearTime(),x,y,r.getWidth(), r.getHeight(),
            color.getR(), color.getG(), color.getB());
        assertEquals(expected.toString(),move.getNewShape().toString());
        
        color = c.getColor();
        move = new Move(c,t1,t2,x,y);
        expected = new Oval(c.getName(),c.getAppearTime(),x,y,c.getWidth(), c.getHeight(),
            color.getR(), color.getG(), color.getB());
        assertEquals(expected.toString(),move.getNewShape().toString());
      }
    }
  }

  /**
   * This test checks that the duplicate() method works properly.
   */
  @Test 
  public void testDuplicate() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation move = new Move(r,10,50,300,300);
    String expected = "R moves from (200,200) to (300,300) from time t=10 to t=50";
    assertEquals(expected,move.toString());
    assertNotEquals(move,move.duplicate());
    assertEquals(move.toString(),move.duplicate().toString());
    
    move = new Move(move.getNewShape(),70,100,200,200);
    expected = "R moves from (300,300) to (200,200) from time t=70 to t=100";
    assertEquals(expected,move.toString());
    assertNotEquals(move,move.duplicate());
    assertEquals(move.toString(),move.duplicate().toString());
    
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    move = new Move(c,20,70,500,400);
    expected = "C moves from (500,100) to (500,400) from time t=20 to t=70";
    assertEquals(expected,move.toString());
    assertNotEquals(move,move.duplicate());
    assertEquals(move.toString(),move.duplicate().toString());
    
    for (int x = 0; x < 1000; x++) {
      for (int y = 0; y < 1000; y++) {
        int t1 = y;
        int t2 = y + 10;
        move = new Move(r,t1,t2,x,y);
        expected = "R moves from (200,200) to (" + x + "," + y + ") from time t=" + t1
            + " to t=" + t2;
        assertEquals(expected,move.toString());
        assertNotEquals(move,move.duplicate());
        assertEquals(move.toString(),move.duplicate().toString());
        
        move = new Move(c,t1,t2,x,y);
        expected = "C moves from (500,100) to (" + x + "," + y + ") from time t=" + t1
            + " to t=" + t2;
        assertEquals(expected,move.toString());
        assertNotEquals(move,move.duplicate());
        assertEquals(move.toString(),move.duplicate().toString());
      }
    }
  }
  
  /**
   * This test checks that the getT1() and getT2() methods work properly.
   */
  @Test
  public void testGetT1T2() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation move = new Move(r,10,50,300,300);
    assertEquals(10,move.getT1());
    assertEquals(50,move.getT2());
    
    move = new Move(move.getNewShape(),70,100,200,200);
    assertEquals(70,move.getT1());
    assertEquals(100,move.getT2());
    
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    move = new Move(c,20,70,500,400);
    assertEquals(20,move.getT1());
    assertEquals(70,move.getT2());
    
    for (int x = 0; x < 1000; x++) {
      for (int y = 0; y < 1000; y++) {
        int t1 = y;
        int t2 = y + 10;
        move = new Move(r,t1,t2,x,y);
        assertEquals(t1,move.getT1());
        assertEquals(t2,move.getT2());
        
        move = new Move(c,t1,t2,x,y);
        assertEquals(t1,move.getT1());
        assertEquals(t2,move.getT2());
      }
    }
  }
  
  /**
   * This test checks that the getShape() method works properly. 
   */
  @Test 
  public void testGetShape() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation move = new Move(r,10,50,300,300);
    Shape expected = r;
    assertEquals(expected.toString(),move.getShape().toString());
    Shape old = move.getNewShape();
    move = new Move(old,70,100,200,200);
    assertEquals(old.toString(),move.getShape().toString());
    
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    move = new Move(c,20,70,500,400);
    expected = c;
    assertEquals(expected.toString(),move.getShape().toString());
    
    
    for (int x = 0; x < 1000; x++) {
      for (int y = 0; y < 1000; y++) {
        int t1 = y;
        int t2 = y + 10;
        move = new Move(r,t1,t2,x,y);
        expected = r;
        assertEquals(expected.toString(),move.getShape().toString());

        move = new Move(c,t1,t2,x,y);
        expected = c;
        assertEquals(expected.toString(),move.getShape().toString());
      }
    }
  }
}
