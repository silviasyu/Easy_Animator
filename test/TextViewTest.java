import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.controller.Controller;
import cs5004.animator.model.IModel;
import cs5004.animator.model.Model;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.ChangeColor;
import cs5004.animator.util.Move;
import cs5004.animator.util.Oval;
import cs5004.animator.util.Rectangle;
import cs5004.animator.util.Scale;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;
import cs5004.animator.view.TextView;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * A JUnit test for the TestView class, the textual view implementation of IView.
 * @author silvia
 */
public class TextViewTest {
  // this method gets a helper model
  private IModel helperModel() {
    IModel model = new Model();
    model.setSpeed(10);
    Shape r = new Rectangle("R",1,200,200,50,100,255,0,0);
    Shape c = new Oval("C",6,500,100,60,30,0,0,255);
    model.addShape(r);
    model.addShape(c);
    
    ShapeAnimation rChange1 = new Move(r,10,50,300,300);
    ShapeAnimation cChange1 = new Move(c,20,70,500,400);
    ShapeAnimation cChange2 = new ChangeColor(cChange1.getNewShape(),50,80,0,255,0);
    ShapeAnimation rChange2 = new Scale(rChange1.getNewShape(),51,70,25,100);
    ShapeAnimation rChange3 = new Move(rChange2.getNewShape(),70,100,200,200);
    model.addShapeAnimation(rChange1);
    model.addShapeAnimation(cChange1);
    model.addShapeAnimation(cChange2);
    model.addShapeAnimation(rChange2);
    model.addShapeAnimation(rChange3);
    return model;
  }
  
  /**
   * This test checks the constructor for the TextView class works properly. 
   */
  @Test
  public void testConstructor() {
    StringBuilder animation = new StringBuilder();
    IModel model = helperModel();
    TextView view = new TextView(animation,model.getEndTime());
    assertEquals("", animation.toString());
    String expected = "Create shapeCreate (255,0,0) colored rectangle R with corner"
        + " at (200,200), width 50 and height 100";
    animation.append(expected);
    assertEquals(expected, animation.toString());
  }
  
  /**
   * This test checks that the constructor works properly when passed an invalid Appendable.
   */
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    Appendable animation = new FailingAppendable();
    TextView view = new TextView(animation,helperModel().getEndTime());
    view.display(helperModel().getShapeList(), helperModel().getAnimationList());
  }
  
  /**
   * This test checks that the display() method works properly.
   */
  @Test
  public void testDisplay() {
    StringBuilder animation;
    IModel model;
    TextView view;
    animation = new StringBuilder();
    model = helperModel();
    view = new TextView(animation,model.getEndTime());
    view.display(model.getShapeList(), model.getAnimationList());
    String expected = "Create (255,0,0) colored rectangle R with corner at (200,200), width 50 "
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
        + "R moves from (300,300) to (200,200) from time t=70 to t=100\n";
    assertEquals(expected, animation.toString());
    
    animation = new StringBuilder();
    model = new Model();
    try {
      model = AnimationReader.parseFile(new FileReader("smalldemo.txt"), 
          new AnimationBuilderImpl(model));
    } catch (FileNotFoundException e) {
      fail("An exception should not have been thrown.");
    }
    view = new TextView(animation,model.getEndTime()); 
    view.display(model.getShapeList(), model.getAnimationList());
    expected = "Create (255,0,0) colored rectangle R with corner at (200,200), width 50 "
        + "and height 100\n"
        + "Create (0,0,255) colored oval C with center at (440,70), radius 120 and 60\n"
        + "\n"
        + "R appears at time t=1 and disappears at time t=100\n"
        + "C appears at time t=6 and disappears at time t=100\n"
        + "\n"
        + "R moves from (200,200) to (300,300) from time t=10 to t=50\n"
        + "C moves from (440,70) to (440,250) from time t=20 to t=50\n"
        + "C moves from (440,250) to (440,370) from time t=50 to t=70\n"
        + "C changes color from (0,0,255) to (0,170,85) from time t=50 to t=70\n"
        + "R changes width from 50 to 25 from time t=51 to t=70\n"
        + "R moves from (300,300) to (200,200) from time t=70 to t=100\n"
        + "C changes color from (0,170,85) to (0,255,0) from time t=70 to t=80\n";
    assertEquals(expected, animation.toString());
  }
  
  /**
   * This test checks that the updateCurrentShape() method works properly when 
   * passed a System.out as the Appendable when constructing a TextView object. 
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testUpdateCurrentShapesConsole() {
    StringBuilder animation = new StringBuilder();
    TextView view = new TextView(animation,10);
    view.updateCurrentShapes(helperModel().getShapeList());
  }
  
  /** 
   * This test checks that the setController() method works properly.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testSetController() {
    StringBuilder animation = new StringBuilder();
    IModel model = helperModel();
    TextView view = new TextView(animation,model.getEndTime());
    Controller control = new Controller(model,view);
    view.setController(control);
  }
  
  /** 
   * This test checks that the getInput() method works properly.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testGetInput() {
    StringBuilder animation = new StringBuilder();
    IModel model = helperModel();
    TextView view = new TextView(animation, model.getSpeed());
    view.getInput();
  }
}
