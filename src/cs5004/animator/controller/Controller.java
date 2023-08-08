package cs5004.animator.controller;

import cs5004.animator.model.IModel;
import cs5004.animator.util.Point2D;
import cs5004.animator.util.RgbValue;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;
import cs5004.animator.view.IView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This class represents a Controller object. It offers all the operations mandated
 * by the IController interface.   
 * @author silvia
 */
public class Controller implements IController, ActionListener {
  IModel model;
  IView view;
  int speed;
  int tick;
  Timer timer;
  HashMap<Integer,ArrayList<Shape>> allShapes;
  boolean repeat;
  
  /**
   * Creates a Controller object with the given model and view. 
   */
  public Controller(IModel model,IView view) {
    this.model = model;
    this.view = view;
    this.speed = this.model.getSpeed(); 
    if (this.speed == 0) {
      this.speed++;
    }
    this.tick = 0; 
    this.timer = new Timer(Math.round(1000 / this.speed), this);
    this.timer.setActionCommand("timer");
    this.repeat = false;
  }  
  
  @Override
  public ArrayList<Shape> getShapesAtTime(int t) {
    //ArrayList to return 
    ArrayList<Shape> allShapesT = new ArrayList<Shape>();
    // find end of whole animation 
    int startT = this.model.getStartTime();
    int endT = this.model.getEndTime();
    // if time is not between startT and endT, then return the empty ArrayList
    if (t < startT || endT < t) {
      return allShapesT;
    }
    else {
      // there is at least one shape in the ArrayList
      // loop through all shapes to update the shape to the current t and add to the ArrayList.
      ArrayList<Shape> modelShapeList = this.model.getShapeList();
      for (int i = 0; i < modelShapeList.size(); i++) {
        // if t is = > appear time of shape then duplicate shape
        int aT = modelShapeList.get(i).getAppearTime(); 
        if (t >= aT) {
          Shape s = modelShapeList.get(i).duplicate();
          // update shape changes if any 
          ArrayList<ShapeAnimation> animationList = this.model.getAnimationList();
          for (int j = 0; j < animationList.size(); j++) {
            //look at transformation if it is for this shape
            ShapeAnimation animation = animationList.get(j);
            if (s.getName().equals(animation.getShape().getName())) {
              int t1 = animation.getT1();
              int t2 = animation.getT2();
              // update changes that happened before t
              if (t > t1 && t > t2) {
                s = updateShape(animation,s);
              }
              //if t is between t1 and t2 then tween 
              if (t >= t1 && t <= t2) {
                s = tweenShape(animation,t,s).duplicate();
              }         
            }  
          } 
          // add shape to array
          allShapesT.add(s);
        } 
      } 
      ArrayList<Shape> clone = new ArrayList<Shape>(allShapesT);
      return clone;
    }
  }
  
  // this method tweens the Shape according to the ShapeAnimation and returns the new shape
  private Shape tweenShape(ShapeAnimation animate, int t, Shape currShape) {
    Shape oldShape = animate.getShape();
    int x1 = oldShape.getReferencePoint().getX();
    int y1 = oldShape.getReferencePoint().getY();
    int w1 = oldShape.getWidth();
    int h1 = oldShape.getHeight();
    int r1 = oldShape.getColor().getR();
    int g1 = oldShape.getColor().getG();
    int b1 = oldShape.getColor().getB();
    Shape newShape = animate.getNewShape();
    int x2 = newShape.getReferencePoint().getX();
    int y2 = newShape.getReferencePoint().getY();
    int w2 = newShape.getWidth();
    int h2 = newShape.getHeight();
    int r2 = newShape.getColor().getR();
    int g2 = newShape.getColor().getG();
    int b2 = newShape.getColor().getB();
    Shape addThisShape = currShape.duplicate();
    int t1 = animate.getT1();
    int t2 = animate.getT2();

    // move
    if (x1 != x2 || y1 != y2) {
      int x = (int)Math.round(x1 * (t2 - t) / (t2 - t1) + x2 * (t - t1) / (t2 - t1));
      int y = (int)Math.round(y1 * (t2 - t) / (t2 - t1) + y2 * (t - t1) / (t2 - t1));
      addThisShape.setReferencePoint(new Point2D(x,y));
    }
    
    // scale
    if (w1 != w2 || h1 != h2) {
      int w = (int)Math.round(w1 * (t2 - t) / (t2 - t1) + w2 * (t - t1) / (t2 - t1));
      int h = (int)Math.round(h1 * (t2 - t) / (t2 - t1) + h2 * (t - t1) / (t2 - t1));
      addThisShape.setWidth(w);
      addThisShape.setHeight(h);
    }
    
    // change color
    if (r1 != r2 || g1 != g2 || b1 != b2) { 
      int r = (int)Math.round(r1 * (t2 - t) / (t2 - t1) + r2 * (t - t1) / (t2 - t1));
      int g = (int)Math.round(g1 * (t2 - t) / (t2 - t1) + g2 * (t - t1) / (t2 - t1));
      int b = (int)Math.round(b1 * (t2 - t) / (t2 - t1) + b2 * (t - t1) / (t2 - t1));
      addThisShape.setColor(new RgbValue(r,g,b));
    }
    return addThisShape.duplicate();
  }
  
  // this method updates the shape according to the ShapeAnimation and returns the new shape
  private Shape updateShape(ShapeAnimation animate, Shape currShape) {
    Shape oldShape = animate.getShape();
    int x1 = oldShape.getReferencePoint().getX();
    int y1 = oldShape.getReferencePoint().getY();
    int w1 = oldShape.getWidth();
    int h1 = oldShape.getHeight();
    int r1 = oldShape.getColor().getR();
    int g1 = oldShape.getColor().getG();
    int b1 = oldShape.getColor().getB();
    Shape newShape = animate.getNewShape();
    int x2 = newShape.getReferencePoint().getX();
    int y2 = newShape.getReferencePoint().getY();
    int w2 = newShape.getWidth();
    int h2 = newShape.getHeight();
    int r2 = newShape.getColor().getR();
    int g2 = newShape.getColor().getG();
    int b2 = newShape.getColor().getB();
    Shape addThisShape = currShape.duplicate();
    
    // move
    if (x1 != x2 || y1 != y2) {
      addThisShape.setReferencePoint(new Point2D(x2,y2));
    }
    
    // scale
    if (w1 != w2 || h1 != h2) {
      addThisShape.setWidth(w2);
      addThisShape.setHeight(h2);
    }
    
    // change color
    if (r1 != r2 || g1 != g2 || b1 != b2) { 
      addThisShape.setColor(new RgbValue(r2,g2,b2));
    }
    return addThisShape.duplicate();
  }
  
  @Override
  public void play() {
    this.timer.start();
    this.view.display(this.model.getShapeList(), this.model.getAnimationList()); 
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("timer")) {
      try {
        this.view.updateCurrentShapes(getShapesAtTime(this.tick));
      } catch (UnsupportedOperationException ce) {
        //do nothing 
      }
      try {
        this.view.setController(this);
      } catch (UnsupportedOperationException ce) {
        //do nothing 
      }
      this.tick++;
      if (this.tick == this.model.getEndTime() && !repeat) {
        this.pause();
        JOptionPane.showMessageDialog(null, "The animation is finished."); 
      }
      while (repeat) {
        this.enableRepeat();
      }
    }
    if (e.getActionCommand().equals("Start")) {
      this.start();
    }
    if (e.getActionCommand().equals("Pause")) {
      this.pause();
    }
    if (e.getActionCommand().equals("Restart")) {
      this.restart();
    }
    if (e.getActionCommand().equals("setSpeed")) {
      this.timer.stop();
      int newSpeed = 0; 
      try {
        newSpeed = this.view.getInput();
      } catch (UnsupportedOperationException ce) {
        //do nothing 
      }
      if (newSpeed > 0) {
        this.setSpeed(newSpeed);
      }
      else {
        JOptionPane.showMessageDialog(null, "The speed cannot be less than or equal to 0.");
      }
      this.timer.restart();
    }
    if (e.getActionCommand().equals("setTick")) {
      this.timer.stop();
      int newTick = 0;
      try {
        newTick = this.view.getInput();
      } catch (UnsupportedOperationException ce) {
        //do nothing 
      }
      if (newTick > 0 && newTick <= this.model.getEndTime()) {
        this.setTick(newTick);
      }
      else {
        JOptionPane.showMessageDialog(null, "The tick cannot be less than 0 or greater than the "
            + "end of the animation, " + this.model.getEndTime() + "."); 
      }
      this.timer.restart();
    }
    if (e.getActionCommand().equals("Repeat")) {
      enableRepeat();
    }
    if (e.getActionCommand().equals("noRepeat")) {
      disableRepeat();
    }
  }

  @Override
  public void pause() {
    this.timer.stop();
  }

  @Override
  public void start() {
    if (!this.timer.isRunning()) {
      this.timer.start();
    }
    if (this.tick == this.model.getEndTime()) {
      this.tick = 0;
      this.timer.start();
    }
  }

  @Override
  public void restart() {
    this.tick = 0;
    this.timer.restart();
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
    this.timer.setDelay(this.speed);
  }

  @Override
  public void setTick(int tick) {
    this.tick = tick;
  }

  @Override
  public void enableRepeat() {
    repeat = true;
    if (this.tick == this.model.getEndTime()) {
      this.restart();
    }
  }

  @Override
  public void disableRepeat() {
    repeat = false;
    if (this.tick == this.model.getEndTime()) {
      this.timer.stop();
    }
  }
}
