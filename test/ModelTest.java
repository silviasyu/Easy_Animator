import static org.junit.Assert.assertEquals;

import cs5004.animator.model.IModel;
import cs5004.animator.model.Model;
import cs5004.animator.util.ChangeColor;
import cs5004.animator.util.Move;
import cs5004.animator.util.Oval;
import cs5004.animator.util.Point2D;
import cs5004.animator.util.Rectangle;
import cs5004.animator.util.Scale;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;
import org.junit.Test;

/**
 * A JUnit test for the Model class, the model implementation of IModel.
 * @author silvia
 */
public class ModelTest {

  /**
   * This test checks the constructor for the Model class works properly. 
   */
  @Test
  public void testModel() {
    Shape rect = new Rectangle("R",1,200,200,50,100,255,0,0);
    Shape oval = new Oval("C",6,500,100,60,30,0,0,255);

    ShapeAnimation rChange1 = new Move(rect,10,50,300,300);
    ShapeAnimation cChange1 = new Move(oval,20,70,500,400);
    ShapeAnimation cChange2 = new ChangeColor(cChange1.getNewShape(),50,80,0,255,0);
    ShapeAnimation rChange2 = new Scale(rChange1.getNewShape(),51,70,25,100);
    ShapeAnimation rChange3 = new Move(rChange2.getNewShape(),70,100,200,200);
    
    IModel model = new Model();
    model.addShape(rect);
    model.addShape(oval);
    model.addShapeAnimation(rChange1);
    model.addShapeAnimation(cChange1);
    model.addShapeAnimation(cChange2);
    model.addShapeAnimation(rChange2);
    model.addShapeAnimation(rChange3);
    
    String expected;
    expected = "Create (255,0,0) colored rectangle R with corner at (200,200), width 50 "
        + "and height 100\n"
        + "Create (0,0,255) colored oval C with center at (500,100), radius 60 and 30\n"
        + "\n"
        + "R appears at time t=1 and disappears at time t=100\n"
        + "C appears at time t=6 and disappears at time t=100\n"
        + "\n"
        + "R moves from (200,200) to (300,300) from time t=10 to t=50\n"
        + "C moves from (500,100) to (500,400) from time t=20 to t=70\n"
        + "C changes color from (0,0,255) to (0,255,0) from time t=50 to t=80\n"
        + "R changes width from 50 to 25 from time t=51 to t=70\n"
        + "R moves from (300,300) to (200,200) from time t=70 to t=100";
    assertEquals(expected, model.toString());
    
    
    
    rect = new Rectangle("R",10,0,0,5,10,1,2,3);
    oval = new Oval("C",0,100,0,6,32,225,3,2);

    rChange1 = new Move(rect,20,25,30,20);
    cChange1 = new Move(oval,20,60,5,4);
    cChange2 = new ChangeColor(cChange1.getNewShape(),50,80,225,3,1);
    rChange2 = new Scale(rChange1.getNewShape(),40,70,5,100);
    rChange3 = new Move(rChange2.getNewShape(),70,90,200,200);
    
    model = new Model();
    model.addShape(rect);
    model.addShape(oval);
    model.addShapeAnimation(rChange1);
    model.addShapeAnimation(cChange1);
    model.addShapeAnimation(cChange2);
    model.addShapeAnimation(rChange2);
    model.addShapeAnimation(rChange3);
    
    expected = "Create (1,2,3) colored rectangle R with corner at (0,0), width 5 "
        + "and height 10\n"
        + "Create (225,3,2) colored oval C with center at (100,0), radius 6 and 32\n"
        + "\n"
        + "R appears at time t=10 and disappears at time t=90\n"
        + "C appears at time t=0 and disappears at time t=90\n"
        + "\n"
        + "R moves from (0,0) to (30,20) from time t=20 to t=25\n"
        + "C moves from (100,0) to (5,4) from time t=20 to t=60\n"
        + "R changes height from 10 to 100 from time t=40 to t=70\n"
        + "C changes color from (225,3,2) to (225,3,1) from time t=50 to t=80\n"
        + "R moves from (30,20) to (200,200) from time t=70 to t=90";
    assertEquals(expected, model.toString());
    
    // check if the animation properly puts the transformations in chronological order by start time
    model = new Model();
    model.addShape(rect);
    model.addShape(oval);    
    model.addShapeAnimation(rChange2);
    model.addShapeAnimation(rChange3);
    model.addShapeAnimation(cChange2);
    model.addShapeAnimation(rChange1);
    model.addShapeAnimation(cChange1);
    
    expected = "Create (1,2,3) colored rectangle R with corner at (0,0), width 5 "
        + "and height 10\n"
        + "Create (225,3,2) colored oval C with center at (100,0), radius 6 and 32\n"
        + "\n"
        + "R appears at time t=10 and disappears at time t=90\n"
        + "C appears at time t=0 and disappears at time t=90\n"
        + "\n"
        + "R moves from (0,0) to (30,20) from time t=20 to t=25\n"
        + "C moves from (100,0) to (5,4) from time t=20 to t=60\n"
        + "R changes height from 10 to 100 from time t=40 to t=70\n"
        + "C changes color from (225,3,2) to (225,3,1) from time t=50 to t=80\n"
        + "R moves from (30,20) to (200,200) from time t=70 to t=90";
    assertEquals(expected, model.toString());
  }
  
  /**
   * This test checks that the toString() and timeSummary() methods work properly. 
   */
  @Test 
  public void testToStringAndTimeSummary() {
    Shape rect = new Rectangle("R",1,200,200,50,100,255,0,0);
    Shape oval = new Oval("C",6,500,100,60,30,0,0,255);
    
    ShapeAnimation rChange1 = new Move(rect,10,50,300,300);
    ShapeAnimation cChange1 = new Move(oval,20,70,500,400);
    ShapeAnimation cChange2 = new ChangeColor(cChange1.getNewShape(),50,80,0,255,0);
    ShapeAnimation rChange2 = new Scale(rChange1.getNewShape(),51,70,25,100);
    ShapeAnimation rChange3 = new Move(rChange2.getNewShape(),70,100,200,200);
    
    IModel model = new Model();
    model.addShape(rect);
    model.addShape(oval);
    model.addShapeAnimation(rChange1);
    model.addShapeAnimation(cChange1);
    model.addShapeAnimation(cChange2);
    model.addShapeAnimation(rChange2);
    model.addShapeAnimation(rChange3);
    
    String expected;
    expected = "Create (255,0,0) colored rectangle R with corner at (200,200), width 50 "
        + "and height 100\n"
        + "Create (0,0,255) colored oval C with center at (500,100), radius 60 and 30\n"
        + "\n"
        + "R appears at time t=1 and disappears at time t=100\n"
        + "C appears at time t=6 and disappears at time t=100\n"
        + "\n"
        + "R moves from (200,200) to (300,300) from time t=10 to t=50\n"
        + "C moves from (500,100) to (500,400) from time t=20 to t=70\n"
        + "C changes color from (0,0,255) to (0,255,0) from time t=50 to t=80\n"
        + "R changes width from 50 to 25 from time t=51 to t=70\n"
        + "R moves from (300,300) to (200,200) from time t=70 to t=100";
    assertEquals(expected, model.toString());
    
    expected = "R appears at time t=1 and disappears at time t=100\n"
        + "C appears at time t=6 and disappears at time t=100\n";
    assertEquals(expected, model.timeSummary());
    
    
    
    rect = new Rectangle("R",10,0,0,5,10,1,2,3);
    oval = new Oval("C",0,100,0,6,32,225,3,2);

    rChange1 = new Move(rect,20,25,30,20);
    cChange1 = new Move(oval,20,60,5,4);
    cChange2 = new ChangeColor(cChange1.getNewShape(),50,80,225,3,1);
    rChange2 = new Scale(rChange1.getNewShape(),40,70,5,100);
    rChange3 = new Move(rChange2.getNewShape(),70,90,200,200);
    
    model = new Model();
    model.addShape(rect);
    model.addShape(oval);
    model.addShapeAnimation(rChange1);
    model.addShapeAnimation(cChange1);
    model.addShapeAnimation(cChange2);
    model.addShapeAnimation(rChange2);
    model.addShapeAnimation(rChange3);
    
    expected = "Create (1,2,3) colored rectangle R with corner at (0,0), width 5 "
        + "and height 10\n"
        + "Create (225,3,2) colored oval C with center at (100,0), radius 6 and 32\n"
        + "\n"
        + "R appears at time t=10 and disappears at time t=90\n"
        + "C appears at time t=0 and disappears at time t=90\n"
        + "\n"
        + "R moves from (0,0) to (30,20) from time t=20 to t=25\n"
        + "C moves from (100,0) to (5,4) from time t=20 to t=60\n"
        + "R changes height from 10 to 100 from time t=40 to t=70\n"
        + "C changes color from (225,3,2) to (225,3,1) from time t=50 to t=80\n"
        + "R moves from (30,20) to (200,200) from time t=70 to t=90";
    assertEquals(expected, model.toString());
    
    expected = "R appears at time t=10 and disappears at time t=90\n"
        + "C appears at time t=0 and disappears at time t=90\n";
    assertEquals(expected, model.timeSummary());
  }
  
  /**
   * This test checks that the addShape() method works properly.
   */
  @Test 
  public void testAddShape() {
    Shape rect = new Rectangle("R",1,200,200,50,100,255,0,0);
    Shape oval = new Oval("C",6,500,100,60,30,0,0,255);
    IModel model = new Model();
    
    model.addShape(rect);
    String expected = "[Create (255,0,0) colored rectangle R with corner at (200,200), width 50"
        + " and height 100]";
    assertEquals(expected, model.getShapeList().toString());   

    model.addShape(oval);
    expected = "[Create (255,0,0) colored rectangle R with corner at (200,200), width 50 and"
        + " height 100, Create (0,0,255) colored oval C with center at (500,100), radius 60 "
        + "and 30]";
    assertEquals(expected, model.getShapeList().toString());  
    
    model = new Model();
    rect = new Rectangle("R",10,0,0,5,10,1,2,3);
    model.addShape(rect);
    oval = new Oval("C",0,100,0,6,32,225,3,2);
    model.addShape(oval);
    expected = "[Create (1,2,3) colored rectangle R with corner at (0,0), width 5 "
        + "and height 10, "
        + "Create (225,3,2) colored oval C with center at (100,0), radius 6 and 32]";
    assertEquals(expected, model.getShapeList().toString());
  }
  
  /**
   * This test checks that the addAnimation() methods works properly.
   */
  @Test
  public void testAddShapeAnimation() {
    IModel model = new Model();
    Shape rect = new Rectangle("R",1,200,200,50,100,255,0,0);
    model.addShape(rect);
    ShapeAnimation rChange1 = new Move(rect,10,50,300,300);
    model.addShapeAnimation(rChange1);
    assertEquals(50,rect.getDisappearTime());
    String expected = "[R moves from (200,200) to (300,300) from time t=10 to t=50]";
    assertEquals(expected, model.getAnimationList().toString());
    
    ShapeAnimation rChange2 = new Scale(rChange1.getNewShape(),51,70,25,100);
    model.addShapeAnimation(rChange2);
    assertEquals(70,rect.getDisappearTime());
    expected = "[R moves from (200,200) to (300,300) from time t=10 to t=50, "
        + "R changes width from 50 to 25 from time t=51 to t=70]";
    assertEquals(expected, model.getAnimationList().toString());
    
    ShapeAnimation rChange3 = new ChangeColor(rChange2.getNewShape(),70,100,0,255,0);
    model.addShapeAnimation(rChange3);    

    expected = "[R moves from (200,200) to (300,300) from time t=10 to t=50, "
        + "R changes width from 50 to 25 from time t=51 to t=70, "
        + "R changes color from (255,0,0) to (0,255,0) from time t=70 to t=100]";
    assertEquals(expected, model.getAnimationList().toString());
   
    model = new Model();
    rect = new Rectangle("R",10,0,0,5,10,1,2,3);
    model.addShape(rect);
    Shape oval = new Oval("C",0,100,0,6,32,225,3,2);
    model.addShape(oval);
    rChange1 = new Move(rect,20,25,30,20);
    model.addShapeAnimation(rChange1);
    assertEquals(25,rect.getDisappearTime());
    assertEquals(25,oval.getDisappearTime());
    ShapeAnimation cChange1 = new Move(oval,20,60,5,4);
    model.addShapeAnimation(cChange1);
    assertEquals(60,rect.getDisappearTime());
    assertEquals(60,oval.getDisappearTime());
    ShapeAnimation cChange2 = new ChangeColor(cChange1.getNewShape(),50,80,225,3,1);
    model.addShapeAnimation(cChange2);
    rChange2 = new Scale(rChange1.getNewShape(),40,70,5,100);
    model.addShapeAnimation(rChange2);
    rChange3 = new Move(rChange2.getNewShape(),70,90,200,200);
    model.addShapeAnimation(rChange3);
    
    expected = "[R moves from (0,0) to (30,20) from time t=20 to t=25, "
        + "C moves from (100,0) to (5,4) from time t=20 to t=60, "
        + "R changes height from 10 to 100 from time t=40 to t=70, "
        + "C changes color from (225,3,2) to (225,3,1) from time t=50 to t=80, "
        + "R moves from (30,20) to (200,200) from time t=70 to t=90]";
    assertEquals(expected, model.getAnimationList().toString());
    
    model = new Model();
    model.addShapeAnimation(rChange3);
    assertEquals(90,rect.getDisappearTime());
    assertEquals(90,oval.getDisappearTime());
    model.addShapeAnimation(rChange2);
    assertEquals(90,rect.getDisappearTime());
    assertEquals(90,oval.getDisappearTime());
    model.addShapeAnimation(rChange1);
    model.addShapeAnimation(cChange1);
    model.addShapeAnimation(cChange2);    
    expected = "[R moves from (0,0) to (30,20) from time t=20 to t=25, "
        + "C moves from (100,0) to (5,4) from time t=20 to t=60, "
        + "R changes height from 10 to 100 from time t=40 to t=70, "
        + "C changes color from (225,3,2) to (225,3,1) from time t=50 to t=80, "
        + "R moves from (30,20) to (200,200) from time t=70 to t=90]";
    assertEquals(expected, model.getAnimationList().toString());
    
    
    // when the animations added have the same start time
    model = new Model();
    Shape r = new Rectangle("R",10,0,0,5,10,1,2,3);
    model.addShape(r);
    ShapeAnimation c2 = new Scale(r,10,40,5,5);
    model.addShapeAnimation(c2);
    ShapeAnimation c1 = new Move(r,10,50,30,20);
    model.addShapeAnimation(c1);
    expected = "[R changes height from 10 to 5 from time t=10 to t=40, "
        + "R moves from (0,0) to (30,20) from time t=10 to t=50]";
    assertEquals(expected, model.getAnimationList().toString());
  }
  
  /**
   * This test checks that the getShapeList() method works properly.
   */
  @Test 
  public void testGetShapeList() {
    IModel model = new Model();
    String expected = "[]";
    assertEquals(expected, model.getShapeList().toString());
    
    Shape shape = new Rectangle("e",1,2,3,4,5,6,3,0);
    model.getShapeList().add(shape);
    assertEquals(expected, model.getShapeList().toString());
    
    Shape rect = new Rectangle("R",1,200,200,50,100,255,0,0);
    model.addShape(rect);
    expected = "[Create (255,0,0) colored rectangle R with corner at (200,200), width 50 "
        + "and height 100]";
    assertEquals(expected, model.getShapeList().toString());
    
    Shape oval = new Oval("C",6,500,100,60,30,0,0,255);
    model.addShape(oval);
    expected = "[Create (255,0,0) colored rectangle R with corner at (200,200), width 50 "
        + "and height 100, Create (0,0,255) colored oval C with center at (500,100), radius "
        + "60 and 30]";
    assertEquals(expected, model.getShapeList().toString());
    
    
    model = new Model();
    rect = new Rectangle("R",10,0,0,5,10,1,2,3);
    model.addShape(rect);
    oval = new Oval("C",0,100,0,6,32,225,3,2);
    model.addShape(oval);
    expected = "[Create (1,2,3) colored rectangle R with corner at (0,0), width 5 "
        + "and height 10, "
        + "Create (225,3,2) colored oval C with center at (100,0), radius 6 and 32]";
    assertEquals(expected, model.getShapeList().toString());
    shape = new Rectangle("e",1,1,1,1,1,1,1,1);
    model.getShapeList().add(shape);
    assertEquals(expected, model.getShapeList().toString());
  }
  
  /**
   * This test checks that the getShapeAnimation() method works properly.
   */
  @Test
  public void testGetShapeAnimationList() {
    IModel model = new Model();
    String expected = "[]";
    assertEquals(expected, model.getAnimationList().toString());
    
    Shape rect = new Rectangle("R",1,200,200,50,100,255,0,0);
    ShapeAnimation rChange1 = new Move(rect,10,50,300,300);
    model.addShapeAnimation(rChange1);
    expected = "[R moves from (200,200) to (300,300) from time t=10 to t=50]";
    assertEquals(expected, model.getAnimationList().toString());
    
    Shape oval = new Oval("C",6,500,100,60,30,0,0,255);
    ShapeAnimation cChange1 = new Move(oval,20,70,500,400);
    model.addShapeAnimation(cChange1);
    expected = "[R moves from (200,200) to (300,300) from time t=10 to t=50, "
        + "C moves from (500,100) to (500,400) from time t=20 to t=70]";
    assertEquals(expected, model.getAnimationList().toString());
    
    ShapeAnimation cChange2 = new ChangeColor(cChange1.getNewShape(),50,80,0,255,0);
    model.addShapeAnimation(cChange2);
    expected = "[R moves from (200,200) to (300,300) from time t=10 to t=50, "
        + "C moves from (500,100) to (500,400) from time t=20 to t=70, "
        + "C changes color from (0,0,255) to (0,255,0) from time t=50 to t=80]";
    assertEquals(expected, model.getAnimationList().toString());
    
    ShapeAnimation rChange2 = new Scale(rChange1.getNewShape(),51,70,25,100);
    model.addShapeAnimation(rChange2);
    expected = "[R moves from (200,200) to (300,300) from time t=10 to t=50, "
        + "C moves from (500,100) to (500,400) from time t=20 to t=70, "
        + "C changes color from (0,0,255) to (0,255,0) from time t=50 to t=80, "
        + "R changes width from 50 to 25 from time t=51 to t=70]";
    assertEquals(expected, model.getAnimationList().toString());
    
    ShapeAnimation rChange3 = new Move(rChange2.getNewShape(),70,100,200,200);
    model.addShapeAnimation(rChange3);
    expected = "[R moves from (200,200) to (300,300) from time t=10 to t=50, "
        + "C moves from (500,100) to (500,400) from time t=20 to t=70, "
        + "C changes color from (0,0,255) to (0,255,0) from time t=50 to t=80, "
        + "R changes width from 50 to 25 from time t=51 to t=70, "
        + "R moves from (300,300) to (200,200) from time t=70 to t=100]";
    assertEquals(expected, model.getAnimationList().toString());
    ShapeAnimation scale = new Scale(cChange2.getNewShape(),90,100,25,100);
    model.getAnimationList().add(scale);
    assertEquals(expected, model.getAnimationList().toString());
    
    model = new Model();
    rect = new Rectangle("R",10,0,0,5,10,1,2,3);
    model.addShape(rect);
    oval = new Oval("C",0,100,0,6,32,225,3,2);
    model.addShape(oval);
    rChange1 = new Move(rect,20,25,30,20);
    model.addShapeAnimation(rChange1);
    cChange1 = new Move(oval,20,60,5,4);
    model.addShapeAnimation(cChange1);
    cChange2 = new ChangeColor(cChange1.getNewShape(),50,80,225,3,1);
    model.addShapeAnimation(cChange2);
    rChange2 = new Scale(rChange1.getNewShape(),40,70,5,100);
    model.addShapeAnimation(rChange2);
    rChange3 = new Move(rChange2.getNewShape(),70,90,200,200);
    model.addShapeAnimation(rChange3);
    
    expected = "[R moves from (0,0) to (30,20) from time t=20 to t=25, "
        + "C moves from (100,0) to (5,4) from time t=20 to t=60, "
        + "R changes height from 10 to 100 from time t=40 to t=70, "
        + "C changes color from (225,3,2) to (225,3,1) from time t=50 to t=80, "
        + "R moves from (30,20) to (200,200) from time t=70 to t=90]";
    assertEquals(expected, model.getAnimationList().toString());
    ShapeAnimation move = new Move(rChange3.getNewShape(),100,101,25,100);
    model.getAnimationList().add(move);
    assertEquals(expected, model.getAnimationList().toString());
    
    model = new Model();
    model.addShapeAnimation(rChange3);
    model.addShapeAnimation(rChange2);
    model.addShapeAnimation(rChange1);
    model.addShapeAnimation(cChange1);
    model.addShapeAnimation(cChange2);    
    expected = "[R moves from (0,0) to (30,20) from time t=20 to t=25, "
        + "C moves from (100,0) to (5,4) from time t=20 to t=60, "
        + "R changes height from 10 to 100 from time t=40 to t=70, "
        + "C changes color from (225,3,2) to (225,3,1) from time t=50 to t=80, "
        + "R moves from (30,20) to (200,200) from time t=70 to t=90]";
    assertEquals(expected, model.getAnimationList().toString());
  }
  
  /**
   * This test checks that the getCanvasTopCorner() and setCanvasTopCorner() 
   * methods work properly.
   */
  @Test 
  public void testGetAndSetCanvasTopCorner() {
    IModel model = new Model();
    Point2D point = model.getCanvasTopCorner();
    assertEquals(0,point.getX());
    assertEquals(0,point.getY());
    
    model.setCanvasTopCorner(new Point2D(2,3));
    point = model.getCanvasTopCorner();
    assertEquals(2,point.getX());
    assertEquals(3,point.getY());
    
    for (int x = 1; x < 5000; x++) {
      for (int y = 1; y < 5000; y++) {
        model.setCanvasTopCorner(new Point2D(x,y));
        point = model.getCanvasTopCorner();
        assertEquals(x,point.getX());
        assertEquals(y,point.getY());
      }
    }
  }
  
  /**
   * This test checks that the getCanvasHeight() and setCanvasHeight() 
   * methods work properly.
   */
  @Test 
  public void testGetAndSetCanvasWidth() {
    IModel model = new Model();
    assertEquals(0,model.getCanvasWidth());
    
    model.setCanvasWidth(100);
    assertEquals(100,model.getCanvasWidth());
    
    for (int w = 1; w < 5000; w++) {
      model.setCanvasWidth(w);
      assertEquals(w,model.getCanvasWidth());
    }
  }
  
  /**
   * This test checks that the getCanvasHeight() and setCanvasHeight() 
   * methods work properly.
   */
  @Test 
  public void testGetAndSetCanvasHeight() {
    IModel model = new Model();
    assertEquals(0,model.getCanvasHeight());
    
    model.setCanvasHeight(1000);
    assertEquals(1000,model.getCanvasHeight());
    
    for (int h = 1; h < 5000; h++) {
      model.setCanvasHeight(h);
      assertEquals(h,model.getCanvasHeight());
    }
  }
  
  /**
   * This test checks that the getStartTime() and getEndTime() methods work properly.
   */
  @Test
  public void testGetStartAndEndTime() {
    IModel model = new Model();
    assertEquals(0,model.getEndTime());
    assertEquals(-1,model.getStartTime());

    Shape oval = new Oval("C",6,500,100,60,30,0,0,255);
    model.addShape(oval);
    assertEquals(6,model.getStartTime());
    ShapeAnimation cChange1 = new Move(oval,20,70,500,400);
    ShapeAnimation cChange2 = new ChangeColor(cChange1.getNewShape(),50,80,0,255,0);
    model.addShapeAnimation(cChange1);
    assertEquals(70,model.getEndTime());
    model.addShapeAnimation(cChange2);
    assertEquals(80,model.getEndTime());
    assertEquals(6,model.getStartTime());
    Shape rect = new Rectangle("R",1,200,200,50,100,255,0,0);
    model.addShape(rect);
    assertEquals(1,model.getStartTime());
    ShapeAnimation rChange1 = new Move(rect,10,50,300,300);
    model.addShapeAnimation(rChange1);
    assertEquals(80,model.getEndTime());
    ShapeAnimation rChange2 = new Scale(rChange1.getNewShape(),51,70,25,100);
    model.addShapeAnimation(rChange2);
    assertEquals(80,model.getEndTime());
    ShapeAnimation rChange3 = new Move(rChange2.getNewShape(),70,100,200,200);
    model.addShapeAnimation(rChange3);
    assertEquals(100,model.getEndTime());
    assertEquals(1,model.getStartTime());
  }
  
  /**
   * This test checks that the getSpeed() and setSpeed() methods work properly. 
   */
  @Test 
  public void testGetAndSetSpeed() {
    IModel model = new Model();
    assertEquals(1,model.getSpeed());
    model.setSpeed(10);
    assertEquals(10,model.getSpeed());
    
    for (int s = 1; s < 5000; s++) {
      model.setSpeed(s);
      assertEquals(s,model.getSpeed());
    }
  }
}
