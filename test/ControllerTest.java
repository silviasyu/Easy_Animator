import static org.junit.Assert.assertEquals;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.IController;
import cs5004.animator.model.IModel;
import cs5004.animator.util.Move;
import cs5004.animator.util.Point2D;
import cs5004.animator.util.Rectangle;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;
import cs5004.animator.view.IView;
import org.junit.Test;

import java.util.ArrayList;

/**
 * A JUnit test for the TestView class, the textual view implementation of IView.
 * @author silvia
 */
public class ControllerTest {

  class MockModel implements IModel {
    private StringBuilder log;
    
    public MockModel() {
      this.log = new StringBuilder();
    }
    
    public String getLog() {
      return this.log.toString();
    }
    
    @Override 
    public String toString() {
      return null;
    }

    @Override
    public void addShape(Shape shape) {
      // do nothing 
    }

    @Override
    public void addShapeAnimation(ShapeAnimation animation) {
      // do nothing 
    }

    @Override
    public String timeSummary() {
      return null;
    }

    @Override
    public ArrayList<Shape> getShapeList() {
      Shape r = new Rectangle("R",1,0,0,5,5,0,0,255);
      this.log.append("Passed: " + r.toString());
      ArrayList<Shape> sList = new ArrayList<Shape>();
      sList.add(r);
      return sList;
    }

    @Override
    public ArrayList<ShapeAnimation> getAnimationList() {
      Shape r = new Rectangle("R",1,0,0,5,5,0,0,255);
      ShapeAnimation m = new Move(r,1,2,1,2);
      log.append(" and " + m.toString());
      ArrayList<ShapeAnimation> aList = new ArrayList<ShapeAnimation>();
      aList.add(m);
      return aList;
    }

    @Override
    public Point2D getCanvasTopCorner() {
      return null;
    }

    @Override
    public int getCanvasWidth() {
      return 0;
    }

    @Override
    public int getCanvasHeight() {
      return 0;
    }

    @Override
    public void setCanvasTopCorner(Point2D topCorner) {
      // do nothing
    }

    @Override
    public void setCanvasWidth(int width) {
      // do nothing
    }

    @Override
    public void setCanvasHeight(int height) {
      // do nothing
    }

    @Override
    public int getEndTime() {
      return 0;
    }

    @Override
    public int getStartTime() {
      return 0;
    }

    @Override
    public void setSpeed(int speed) {
      // do nothing
    }

    @Override
    public int getSpeed() {
      return 0;
    }
  }
  
  
  class MockView implements IView {
    private StringBuilder out;
    
    public MockView() {
      this.out = new StringBuilder();
    }
    
    public String getOut() {
      return this.out.toString();
    }
    
    @Override
    public void display(ArrayList<Shape> shapeList, ArrayList<ShapeAnimation> animationList) {
      this.out.append("added blue rectangle and move");
    }

    @Override
    public void updateCurrentShapes(ArrayList<Shape> shapeList)
        throws UnsupportedOperationException {
      // do nothing
      
    }

    @Override
    public void setController(Controller controller) throws UnsupportedOperationException {
      // do nothing
      
    }

    @Override
    public int getInput() throws UnsupportedOperationException {
      return 0;
    } 
  }
  
  
  /** 
   * This test checks that the controller correctly passes the input to the 
   * model and gives the correct outputs to the view.
   */
  @Test
  public void testController() {
    MockModel model = new MockModel();
    MockView view = new MockView();
    Shape input = new Rectangle("R",1,0,0,5,5,0,0,255);
    ShapeAnimation input2 = new Move(input,1,2,1,2);
    IController controller = new Controller(model,view);
    controller.play();
    // verify that the inputs were correctly passed to the model 
    assertEquals("Passed: " + input.toString() + " and " + input2.toString(), model.getLog());
    // verify that the controller transmitted the output to the view.
    assertEquals("added blue rectangle and move",view.getOut());
  }

}
