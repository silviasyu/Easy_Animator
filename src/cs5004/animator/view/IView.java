package cs5004.animator.view;

import cs5004.animator.controller.Controller;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;

import java.util.ArrayList;

/**
 * This interface represents the operations offered by the View part of 
 * this MVC animation program. 
 * @author silvia
 */
public interface IView {
  /**
   * Displays the given shapeList and animationList in this view.
   * @param shapeList the collection of Shape objects 
   * @param animationList the collection of animations that will change the shapes in the shapeList
   */
  void display(ArrayList<Shape> shapeList, ArrayList<ShapeAnimation> animationList);
  
  /**
   * Updates the shapeList with the shapes the view should show at a certain time.
   * @param shapeList the new collection of Shape objects 
   * @throws UnsupportedOperationException if this view does not support updating the shapeList.
   */
  void updateCurrentShapes(ArrayList<Shape> shapeList) throws UnsupportedOperationException;
  
  /**
   * Sets the controller as an ActionListener in the relevant views such as Playback View. 
   * @param controller the controller to be set
   * @throws UnsupportedOperationException if this view does not support setting the controller 
   *         as an ActionListener
   */
  void setController(Controller controller) throws UnsupportedOperationException;

  /**
   * Gets and returns the speed or tick inputed into the text field in the PlayView object.
   * @return the inputed speed or tick
   */
  int getInput() throws UnsupportedOperationException;
}
