package cs5004.animator.controller;

import cs5004.animator.util.Shape;

import java.util.ArrayList;

/**
 * This interface represents the operations offered by the Controller part of 
 * this MVC animation program. 
 * @author silvia
 */
public interface IController {
  /**
   * Tells the controller to play the animation and display it in the view.
   */
  void play();
  
  /**
   * Pauses the animation.
   */
  void pause();
  
  /**
   * Starts the animation, if it's not playing already.
   */
  void start();
  
  /**
   * Restarts the animation from the beginning.
   */
  void restart();
  
  /**
   * Gets and returns the current speed of the animation.
   * @return the speed of the animation
   */
  int getSpeed();
  
  /**
   * Sets the speed to the given speed.
   * @param speed the new speed
   */
  void setSpeed(int speed);
  
  /**
   * Sets the animation to the given tick.
   * @param tick the tick to show the animation
   */
  void setTick(int tick);
  
  /**
   * Enables the animation to repeat after it is complete.
   */
  void enableRepeat();
  
  /**
   * Disables the animation to repeat.
   */
  void disableRepeat();

  /**
   * Gets and returns an ArrayList of the shapes at the given tick.
   * @param t the tick 
   * @return an ArrayList of shapes at tick t
   */
  ArrayList<Shape> getShapesAtTime(int t);
}
