import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs5004.animator.util.Oval;
import cs5004.animator.util.Point2D;
import cs5004.animator.util.Rectangle;
import cs5004.animator.util.RgbValue;
import cs5004.animator.util.Shape;
import org.junit.Test;

import java.util.Random;

/**
 * A JUnit test for the Rectangle and Circle concrete classes of the Shape
 * interface.
 * @author silvia
 */
public class ShapeTest {
  
  /**
   * This tests that the constructor for the Rectangle concrete class works properly 
   * when passed valid inputs.
   */
  @Test
  public void testRectangle() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    String expected = "Create (255,0,0) colored rectangle R with corner at "
        + "(200,200), width 50 and height 100";
    assertEquals(expected,r.toString());

    Random rand = new Random();
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          String randName = rand.nextInt(1000) + "";
          r = new Rectangle(randName,b,g,b,b,g,red, g, b);
          expected = "Create (" + red + "," + g + "," + b + ") colored rectangle " + randName
              + " with corner at (" + g + "," + b + "), width " + b + " and height " + g;
          assertEquals(expected,r.toString());
        }
      }
    }
  }

  /**
   * This tests that the constructor for the Oval concrete class works properly 
   * when passed valid inputs.
   */
  @Test 
  public void testOval() {
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    String expected = "Create (0,0,255) colored oval C with center at "
        + "(500,100), radius 60 and 30";
    assertEquals(expected, c.toString()); 
    
    Random rand = new Random();
    for (int r = 0; r <= 255; r++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          String randName = rand.nextInt(1000) + "";
          c = new Oval(randName,r,g,b,b,g,r, g, b);
          expected = "Create (" + r + "," + g + "," + b + ") colored oval " + randName
              + " with center at (" + g + "," + b + "), radius " + b + " and " + g;
          assertEquals(expected,c.toString());
        }
      }
    }
  }
  
  /**
   * This tests that the toString() method works properly for each concrete class.
   */
  @Test 
  public void testToString() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    String expected = "Create (255,0,0) colored rectangle R with corner at "
        + "(200,200), width 50 and height 100";
    assertEquals(expected,r.toString());
    
    r = new Rectangle("r",1,300,300,25,10,0,255,100);
    expected = "Create (0,255,100) colored rectangle r with corner at "
        + "(300,300), width 25 and height 10";
    assertEquals(expected,r.toString());
    
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    expected = "Create (0,0,255) colored oval C with center at "
        + "(500,100), radius 60 and 30";
    assertEquals(expected, c.toString());
    
    c = new Oval("c",1,300,400,80,30,90,255,0);
    expected = "Create (90,255,0) colored oval c with center at "
        + "(300,400), radius 80 and 30";
    assertEquals(expected, c.toString());
    
    Random rand = new Random();
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          String randName = rand.nextInt(1000) + "";
          r = new Rectangle(randName,b,g,b,b,g,red,g,b);
          expected = "Create (" + red + "," + g + "," + b + ") colored rectangle " + randName
              + " with corner at (" + g + "," + b + "), width " + b + " and height " + g;
          assertEquals(expected,r.toString());
          
          c = new Oval(randName,red,g,b,b,g,red,g,b);
          expected = "Create (" + red + "," + g + "," + b + ") colored oval " + randName
              + " with center at (" + g + "," + b + "), radius " + b + " and " + g;
          assertEquals(expected,c.toString());
        }
      }
    }
  }
  
  /**
   * This tests that the getName() method works properly for each concrete class.
   */
  @Test
  public void testGetName() {
    Shape r = new Rectangle("r",1,200,200,50,100,255,0,0);
    String expected = "r";
    assertEquals(expected,r.getName());
    
    r = new Rectangle("R",6,300,300,25,100,0,255,100);
    expected = "R";
    assertEquals(expected,r.getName());
    
    Shape c = new Oval("C",1,500,100,60,30,0,0,255);
    expected = "C";
    assertEquals(expected, c.getName());
    
    c = new Oval("c",6,500,400,80,30,90,255,0);
    expected = "c";
    assertEquals(expected, c.getName());
    
    Random rand = new Random();
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          String randName = rand.nextInt(1000) + "";
          r = new Rectangle(randName,b,g,b,b,g,red,g,b);
          assertEquals(randName,r.getName());
          
          c = new Oval(randName,red,g,b,b,g,red,g,b);
          assertEquals(randName,c.getName());
        }
      }
    }
  }
  
  /**
   * This tests that the getType() method works properly for each concrete class.
   */
  @Test
  public void testGetType() {
    String expected = "rectangle";
    Shape r = new Rectangle("r",1,200,200,50,100,255,0,0);
    assertEquals(expected,r.getType());
    
    r = new Rectangle("R",1,300,300,25,100,0,255,100);
    assertEquals(expected,r.getType());

    expected = "oval";
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    assertEquals(expected, c.getType());
    
    c = new Oval("c",1,500,400,80,30,90,255,0);
    assertEquals(expected, c.getType());
    
    Random rand = new Random();
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          String randName = rand.nextInt(1000) + "";
          r = new Rectangle(randName,b,g,b,b,g,red,g,b);
          expected = "Create (" + red + "," + g + "," + b + ") colored rectangle " + randName
              + " with corner at (" + g + "," + b + "), width " + b + " and height " + g;
          assertEquals("rectangle",r.getType());
          
          c = new Oval(randName,red,g,b,b,g,red,g,b);
          expected = "Create (" + red + "," + g + "," + b + ") colored oval " + randName
              + " with center at (" + g + "," + b + "), radius " + b + " and " + g;
          assertEquals("oval",c.getType());
        }
      }
    }
  }
  
  /**
   * This tests that the getReferencePoint() and setReferencePoint methods work properly
   * for each concrete class.
   */
  @Test 
  public void testGetAndSetReferencePoint() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    String expected = "(200,200)";
    assertEquals(expected,r.getReferencePoint().toString());
    r.setReferencePoint(new Point2D(0,0));
    assertEquals("(0,0)",r.getReferencePoint().toString());
    
    r = new Rectangle("R",6,300,300,25,100,0,255,100);
    expected = "(300,300)";
    assertEquals(expected,r.getReferencePoint().toString());
    r.setReferencePoint(new Point2D(10,2));
    assertEquals("(10,2)",r.getReferencePoint().toString());
    
    Shape c = new Oval("C",1,500,100,60,30,0,0,255);
    expected = "(500,100)";
    assertEquals(expected, c.getReferencePoint().toString());
    c.setReferencePoint(new Point2D(3,1));
    assertEquals("(3,1)",c.getReferencePoint().toString());
    
    c = new Oval("C",6,500,100,60,30,2,100,22);
    expected = "(500,100)";
    assertEquals(expected, c.getReferencePoint().toString());
    c.setReferencePoint(new Point2D(0,0));
    assertEquals("(0,0)",c.getReferencePoint().toString());
    
    Random rand = new Random();
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          String randName = rand.nextInt(1000) + "";
          r = new Rectangle(randName,b,g,b,b,g,red,g,b);
          expected = new Point2D(g,b).toString();
          assertEquals(expected, r.getReferencePoint().toString());
          r.setReferencePoint(new Point2D(b,red));
          expected = new Point2D(b,red).toString();
          assertEquals(expected,r.getReferencePoint().toString());
          
          c = new Oval(randName,red,g,b,b,g,red,g,b);
          expected = new Point2D(g,b).toString();
          assertEquals(expected, c.getReferencePoint().toString());
          c.setReferencePoint(new Point2D(b,red));
          expected = new Point2D(b,red).toString();
          assertEquals(expected,c.getReferencePoint().toString());
        }
      }
    }
  }
  
  /**
   * This tests that the getWidth(), setWidth(), getHeight(), and setHeight() methods 
   * work properly for each concrete class.
   */
  @Test 
  public void testGetAndSetDimensions() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    assertEquals(50,r.getWidth());
    r.setWidth(80);
    assertEquals(80,r.getWidth());
    assertEquals(100,r.getHeight());
    r.setHeight(80);
    assertEquals(80,r.getHeight());
    
    r = new Rectangle("R",6,300,300,25,10,0,255,100);
    assertEquals(25,r.getWidth());
    r.setWidth(1);
    assertEquals(1,r.getWidth());
    assertEquals(10,r.getHeight());
    r.setHeight(0);
    assertEquals(0,r.getHeight());
    
    Shape c = new Oval("C",1,500,100,60,30,0,0,255);
    assertEquals(60, c.getWidth());
    c.setWidth(1000);
    assertEquals(1000,c.getWidth());
    assertEquals(30,c.getHeight());
    c.setHeight(80);
    assertEquals(80,c.getHeight());
    
    c = new Oval("C",6,500,100,50,100,2,100,22);
    assertEquals(50, c.getWidth());
    c.setWidth(80);
    assertEquals(80,c.getWidth());
    assertEquals(100,c.getHeight());
    c.setHeight(2);
    assertEquals(2,c.getHeight());
    
    Random rand = new Random();
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          String randName = rand.nextInt(1000) + "";
          r = new Rectangle(randName,b,g,b,b,g,red,g,b);
          assertEquals(b, r.getWidth());
          r.setWidth(g);
          assertEquals(g,r.getWidth());
          assertEquals(g,r.getHeight());
          r.setHeight(red);
          assertEquals(red,r.getHeight());
          
          c = new Oval(randName,red,g,b,b,g,red,g,b);
          assertEquals(b, c.getWidth());
          c.setWidth(red);
          assertEquals(red,c.getWidth());
          assertEquals(g,c.getHeight());
          c.setHeight(b);
          assertEquals(b,c.getHeight());
        }
      }
    }
  }
  
  /**
   * This tests that the getColor() and setColor() methods work properly for each concrete class.
   */
  @Test 
  public void testGetAndSetColor() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    String expected = "(255,0,0)";
    assertEquals(expected,r.getColor().toString());
    RgbValue nColor = new RgbValue(1,2,3);
    r.setColor(nColor);
    assertEquals(nColor.toString(),r.getColor().toString());
    
    r = new Rectangle("R",6,300,300,25,100,0,255,100);
    expected = "(0,255,100)";
    assertEquals(expected,r.getColor().toString());
    nColor = new RgbValue(100,11,0);
    r.setColor(nColor);
    assertEquals(nColor.toString(),r.getColor().toString());    
    
    Shape c = new Oval("C",1,500,100,60,30,0,0,255);
    expected = "(0,0,255)";
    assertEquals(expected, c.getColor().toString());
    nColor = new RgbValue(0,0,0);
    r.setColor(nColor);
    assertEquals(nColor.toString(),r.getColor().toString());
    
    c = new Oval("C",6,500,100,60,30,2,100,22);
    expected = "(2,100,22)";
    assertEquals(expected, c.getColor().toString());
    nColor = new RgbValue(250,0,8);
    r.setColor(nColor);
    assertEquals(nColor.toString(),r.getColor().toString());
    
    Random rand = new Random();
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          String randName = rand.nextInt(1000) + "";
          r = new Rectangle(randName,b,g,b,b,g,red,g,b);
          expected = "(" + red + "," + g + "," + b + ")";
          assertEquals(expected, r.getColor().toString());
          nColor = new RgbValue(b,g,red);
          r.setColor(nColor);
          assertEquals(nColor.toString(),r.getColor().toString());
          
          c = new Oval(randName,red,g,b,b,g,red,g,b);
          assertEquals(expected, c.getColor().toString());
          nColor = new RgbValue(g,b,red);
          c.setColor(nColor);
          assertEquals(nColor.toString(),c.getColor().toString());
        }
      }
    }
  }
  
  /**
   * This tests that the getAppearTime() method work properly for each concrete class.
   */
  @Test 
  public void testGetAppearTime() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    assertEquals(1,r.getAppearTime());
    
    r = new Rectangle("R",6,300,300,25,100,0,255,100);
    assertEquals(6,r.getAppearTime());
    
    Shape c = new Oval("C",0,500,100,60,30,0,0,255);
    assertEquals(0, c.getAppearTime());
    
    c = new Oval("C",10,500,400,80,30,90,255,0);
    assertEquals(10, c.getAppearTime());
    
    Random rand = new Random();
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          String randName = rand.nextInt(1000) + "";
          r = new Rectangle(randName,b,g,b,b,g,red,g,b);
          assertEquals(b,r.getAppearTime());
          
          c = new Oval(randName,red,g,b,b,g,red,g,b);
          assertEquals(red,c.getAppearTime());
        }
      }
    }
  }
  
  /**
   * This tests that the getDisappearTime() and updateDisappearTime() methods work properly for 
   * each concrete class.
   */
  @Test 
  public void testGetAndUpdateDisappearTime() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    assertEquals(0,r.getDisappearTime());
    r.updateDisappearTime(10);
    assertEquals(10,r.getDisappearTime());
    r.updateDisappearTime(100);
    assertEquals(100,r.getDisappearTime());

    Shape c = new Oval("C",0,500,100,60,30,0,0,255);
    assertEquals(0,c.getDisappearTime());
    c.updateDisappearTime(90);
    assertEquals(90,c.getDisappearTime());
    c.updateDisappearTime(7);
    assertEquals(7,c.getDisappearTime());
    
    Random rand = new Random();
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          String randName = rand.nextInt(1000) + "";
          r = new Rectangle(randName,b,g,b,b,g,red,g,b);
          assertEquals(0,r.getDisappearTime());
          r.updateDisappearTime(b);
          assertEquals(b,r.getDisappearTime());
          r.updateDisappearTime(red);
          assertEquals(red,r.getDisappearTime());
          
          c = new Oval(randName,red,g,b,b,g,red,g,b);
          assertEquals(0,c.getDisappearTime());
          c.updateDisappearTime(g);
          assertEquals(g,c.getDisappearTime());
          c.updateDisappearTime(b);
          assertEquals(b,c.getDisappearTime());
        }
      }
    }
  }
  
  /**
   * This tests that the duplicate() method work properly for each concrete class.
   */
  @Test 
  public void testDuplicate() {
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    String expected = "Create (255,0,0) colored rectangle R with corner at "
        + "(200,200), width 50 and height 100";
    assertEquals(expected,r.toString());
    assertNotEquals(r,r.duplicate());
    assertEquals(r.toString(),r.duplicate().toString());
    
    r = new Rectangle("R",1,300,300,25,10,0,255,100);
    expected = "Create (0,255,100) colored rectangle R with corner at "
        + "(300,300), width 25 and height 10";
    assertEquals(expected,r.toString());
    assertNotEquals(r,r.duplicate());
    assertEquals(r.toString(),r.duplicate().toString());
    
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    expected = "Create (0,0,255) colored oval C with center at "
        + "(500,100), radius 60 and 30";
    assertEquals(expected, c.toString());
    assertNotEquals(c,c.duplicate());
    assertEquals(c.toString(),c.duplicate().toString());
    
    c = new Oval("C",1,300,400,80,30,90,255,0);
    expected = "Create (90,255,0) colored oval C with center at "
        + "(300,400), radius 80 and 30";
    assertEquals(expected, c.toString());
    assertNotEquals(c,c.duplicate());
    assertEquals(c.toString(),c.duplicate().toString());
    
    Random rand = new Random();
    for (int red = 0; red <= 255; red++) {
      for (int g = 0; g <= 255; g++) {
        for (int b = 0; b <= 255; b++) {
          String randName = rand.nextInt(1000) + "";
          r = new Rectangle(randName,b,g,b,b,g,red,g,b);
          assertNotEquals(r,r.duplicate());
          assertEquals(r.toString(),r.duplicate().toString());
          
          c = new Oval(randName,red,g,b,b,g,red,g,b);
          assertNotEquals(c,c.duplicate());
          assertEquals(c.toString(),c.duplicate().toString());
        }
      }
    }
  }
}
