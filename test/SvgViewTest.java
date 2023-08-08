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
import cs5004.animator.util.Point2D;
import cs5004.animator.util.Rectangle;
import cs5004.animator.util.Scale;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;
import cs5004.animator.view.SvgView;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * A JUnit test for the SvgView class, the svg implementation of IView.
 * @author silvia
 */
public class SvgViewTest {
  // method that gives us a helper model
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
   * This test checks the constructor for the SVGView class works properly.
   */
  @Test
  public void testConstructor() {
    StringBuilder animation = new StringBuilder();
    IModel model = helperModel();
    SvgView view = new SvgView(animation,new Point2D(0,0),700,500,
        model.getEndTime(), model.getSpeed());
    assertEquals("", animation.toString());
    String expected = "Create shapeCreate (255,0,0) colored rectangle R with corner"
        + " at (200,200), width 50 and height 100";
    animation.append(expected);
    assertEquals(expected, animation.toString());
  }
  
  /**
   * This test checks that the constructor works properly when 
   * passed an invalid Appendable.
   */
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    Appendable animation = new FailingAppendable();
    SvgView view = new SvgView(animation,new Point2D(0,0),300,200,10,1);
    view.display(helperModel().getShapeList(), helperModel().getAnimationList());
  }
  
  /**
   * This test checks that the display() method works properly.
   */
  @Test
  public void testDisplay() {
    StringBuilder animation = new StringBuilder();
    IModel model = helperModel();
    SvgView view = new SvgView(animation,new Point2D(0,0),700,500,
        model.getEndTime(), model.getSpeed());
    view.display(model.getShapeList(), model.getAnimationList());
    String expected = "<svg width=\"1400\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect>\n"
        + "   <animate id=\"base\" begin=\"0;base.end\" dur=\"11000ms\" attributeName="
        + "\"visibility\" from=\"hide\" to=\"hide\"/>\n"
        + "</rect>\n"
        + "\n"
        + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"rgb(255,0,0)\""
        + " visibility=\"hidden\" >\n"
        + "    <set attributeType=\"css\" begin=\"base.begin+100ms\" dur=\"9900ms\""
        + " attributeName=\"visibility\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.begin+1000ms\" dur=\"4000ms\""
        + " attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.begin+1000ms\" dur=\"4000ms\""
        + " attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.begin+5100ms\" dur=\"1900ms\""
        + " attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.begin+7000ms\" dur=\"3000ms\""
        + " attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.begin+7000ms\" dur=\"3000ms\""
        + " attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "    <set attributeType=\"css\" begin=\"base.begin+10000ms\" dur=\"1000ms\""
        + " attributeName=\"visibility\" to=\"hidden\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\""
        + " attributeName=\"width\" to=\"50\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,0,255)\""
        + " visibility=\"hidden\" >\n"
        + "    <set attributeType=\"css\" begin=\"base.begin+600ms\" dur=\"9400ms\""
        + " attributeName=\"visibility\" to=\"visible\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.begin+2000ms\" dur=\"5000ms\""
        + " attributeName=\"cy\" from=\"100\" to=\"400\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"css\" begin=\"base.begin+5000ms\" dur=\"3000ms\""
        + " attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
        + "    <set attributeType=\"css\" begin=\"base.begin+10000ms\" dur=\"1000ms\""
        + " attributeName=\"visibility\" to=\"hidden\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\""
        + " attributeName=\"cy\" to=\"100\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\""
        + " attributeName=\"fill\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>\n";
    assertEquals(expected, animation.toString());
    
    animation = new StringBuilder();
    view = new SvgView(animation,new Point2D(0,0),700,500,7,1);
    ArrayList<Shape> shapeList = new ArrayList<Shape>();
    Shape r = new Rectangle("P",0,200,200,50,100,128,0,128);
    Shape c = new Oval("E",0,500,100,60,30,255,128,0);
    shapeList.add(r);
    shapeList.add(c);
    ArrayList<ShapeAnimation> animationList = new ArrayList<ShapeAnimation>();
    ShapeAnimation rChange1 = new Move(r,1,5,300,200);
    ShapeAnimation cChange1 = new Move(c,2,7,600,400);
    animationList.add(rChange1);
    animationList.add(cChange1);
    view.display(shapeList,animationList);  
    expected = "<svg width=\"1400\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect>\n"
        + "   <animate id=\"base\" begin=\"0;base.end\" dur=\"8000ms\" attributeName="
        + "\"visibility\" from=\"hide\" to=\"hide\"/>\n"
        + "</rect>\n"
        + "\n"
        + "<rect id=\"P\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"rgb(128,0,128)"
        + "\" visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"base.begin+1000ms\" dur=\"4000ms\""
        + " attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "    <set attributeType=\"css\" begin=\"base.begin+7000ms\" dur=\"1000ms\""
        + " attributeName=\"visibility\" to=\"hidden\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName="
        + "\"x\" to=\"200\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"E\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(255,128,0)\""
        + " visibility=\"visible\" >\n"
        + "    <animate attributeType=\"xml\" begin=\"base.begin+2000ms\" dur=\"5000ms\""
        + " attributeName=\"cx\" from=\"500\" to=\"600\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.begin+2000ms\" dur=\"5000ms\""
        + " attributeName=\"cy\" from=\"100\" to=\"400\" fill=\"freeze\" />\n"
        + "    <set attributeType=\"css\" begin=\"base.begin+7000ms\" dur=\"1000ms\""
        + " attributeName=\"visibility\" to=\"hidden\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\""
        + " attributeName=\"cx\" to=\"500\" fill=\"freeze\" />\n"
        + "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\""
        + " attributeName=\"cy\" to=\"100\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>\n";
    assertEquals(expected, animation.toString());
    
    
    animation = new StringBuilder();
    model = new Model();
    try {
      model = AnimationReader.parseFile(new FileReader("smalldemo.txt"), 
          new AnimationBuilderImpl(model));
    } catch (FileNotFoundException e) {
      fail("An exception should have been thrown.");
    }
    model.setSpeed(10);
    view = new SvgView(animation,new Point2D(0,0),700,500,model.getEndTime(), model.getSpeed());
    view.display(model.getShapeList(), model.getAnimationList());
    expected = "<svg width=\"1400\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
      + "\n"
      + "<rect>\n"
      + "   <animate id=\"base\" begin=\"0;base.end\" dur=\"11000ms\" attributeName="
      + "\"visibility\" from=\"hide\" to=\"hide\"/>\n"
      + "</rect>\n"
      + "\n"
      + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"rgb(255,0,0)\""
      + " visibility=\"hidden\" >\n"
      + "    <set attributeType=\"css\" begin=\"base.begin+100ms\" dur=\"9900ms\""
      + " attributeName=\"visibility\" to=\"visible\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"xml\" begin=\"base.begin+1000ms\" dur=\"4000ms\""
      + " attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"xml\" begin=\"base.begin+1000ms\" dur=\"4000ms\""
      + " attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"xml\" begin=\"base.begin+5100ms\" dur=\"1900ms\""
      + " attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"xml\" begin=\"base.begin+7000ms\" dur=\"3000ms\""
      + " attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"xml\" begin=\"base.begin+7000ms\" dur=\"3000ms\""
      + " attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
      + "    <set attributeType=\"css\" begin=\"base.begin+10000ms\" dur=\"1000ms\""
      + " attributeName=\"visibility\" to=\"hidden\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\""
      + " attributeName=\"width\" to=\"50\" fill=\"freeze\" />\n"
      + "</rect>\n"
      + "\n"
      + "<ellipse id=\"C\" cx=\"440\" cy=\"70\" rx=\"120\" ry=\"60\" fill=\"rgb(0,0,255)\""
      + " visibility=\"hidden\" >\n"
      + "    <set attributeType=\"css\" begin=\"base.begin+600ms\" dur=\"9400ms\""
      + " attributeName=\"visibility\" to=\"visible\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"xml\" begin=\"base.begin+2000ms\" dur=\"3000ms\""
      + " attributeName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"xml\" begin=\"base.begin+5000ms\" dur=\"2000ms\""
      + " attributeName=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"css\" begin=\"base.begin+5000ms\" dur=\"2000ms\""
      + " attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"css\" begin=\"base.begin+7000ms\" dur=\"1000ms\""
      + " attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
      + "    <set attributeType=\"css\" begin=\"base.begin+10000ms\" dur=\"1000ms\""
      + " attributeName=\"visibility\" to=\"hidden\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\""
      + " attributeName=\"cy\" to=\"70\" fill=\"freeze\" />\n"
      + "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\""
      + " attributeName=\"fill\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n"
      + "</ellipse>\n"
      + "\n"
      + "</svg>\n";
    assertEquals(expected, animation.toString());
  }
  
  /**
   * This test checks that the updateCurrentShape() method works properly when 
   * passed a svg file as the Appendable when constructing a SVGView object. 
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testUpdateCurrentShapesSvgFile() {
    StringBuilder animation = new StringBuilder();
    IModel model = helperModel();
    SvgView view = new SvgView(animation,new Point2D(0,0),500,300,
        model.getEndTime(), model.getSpeed());
    view.updateCurrentShapes(helperModel().getShapeList());
  }
  
  /** 
   * This test checks that the setController() method works properly.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testSetController() {
    StringBuilder animation = new StringBuilder();
    IModel model = helperModel();
    SvgView view = new SvgView(animation,new Point2D(0,0),500,300,
        model.getEndTime(), model.getSpeed());
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
    SvgView view = new SvgView(animation,new Point2D(0,0),500,300,
        model.getEndTime(), model.getSpeed());
    view.getInput();
  }
}

