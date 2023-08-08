package cs5004.animator.view;

import cs5004.animator.controller.Controller;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents a TextView object. It offers all the operations
 * mandates by the IView interface.  
 * @author silvia
 */
public class TextView implements IView {
  private Appendable output;
  private int end;
  
  /**
   * Creates a TextView object with the given output Appendable and the end time 
   * of the animation.
   * @param out the output of this TextView
   * @param end the end time of the animation
   */
  public TextView(Appendable out, int end) {
    this.output = out;
    this.end = end;
  }

  @Override
  public void display(ArrayList<Shape> shapeList, ArrayList<ShapeAnimation> animationList) {
    for (int i = 0; i < shapeList.size(); i++) {

      try {
        this.output.append(shapeList.get(i).toString() + "\n");
      } catch (IOException e) {
        throw new IllegalStateException("Error when printing information about the shape.");
      }
    }
    
    try {
      this.output.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Error when printing information about the shape.");
    }
    
    for (int i = 0; i < shapeList.size(); i++) {
      Shape shape = shapeList.get(i);
      try {
        this.output.append(shape.getName() + " appears at time t=" + shape.getAppearTime() + " and "
            + "disappears at time t=" + this.end + "\n");
      } catch (IOException e) {
        throw new IllegalStateException("Error when printing the time summary of the shape.");
      }
    }
    
    try {
      this.output.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Error when printing information about the shape.");
    }
    
    for (int i = 0; i < animationList.size(); i++) {
      try {
        this.output.append(animationList.get(i).toString() + "\n");
      } catch (IOException e) {
        throw new IllegalStateException("Error when printing the animation changes to a shape.");
      }
    }
    
  }

  @Override
  public void updateCurrentShapes(ArrayList<Shape> shapeList) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setController(Controller controller) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getInput() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
