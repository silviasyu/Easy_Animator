package cs5004.animator.view;

import cs5004.animator.controller.Controller;
import cs5004.animator.util.Point2D;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents a SVGView object. It offers all the operations
 * mandates by the IView interface.  
 * @author silvia
 */
public class SvgView implements IView {
  private Appendable output;
  private int canvasW; 
  private int canvasH;
  private int speed;
  private int end;
  private int canvasX;
  private int canvasY;
  
  /**
   * Creates a SvgView object with the given output Appendable, the canvas's top corner, width 
   * and height, the end time of the whole animation, and the speed of the animation. 
   * @param out the output of this SVGVIew
   * @param width the width of the canvas
   * @param height the height of the canvas
   * @param end the end time of the whole animation
   */
  public SvgView(Appendable out, Point2D topCorner, int width, int height, int end, int speed) {
    this.output = out;
    this.canvasW = width;
    this.canvasH = height;
    this.speed = speed;
    this.end = end;
    this.canvasX = topCorner.getX();
    this.canvasY = topCorner.getY();
  }

  @Override
  public void display(ArrayList<Shape> shapeList, ArrayList<ShapeAnimation> animationList) {
    //start the svg file by adding the dimensions of the svg 
    int svgW = this.canvasW * 2;
    int svgH = this.canvasH * 2;
    if (this.canvasX < 0) {
      svgW += Math.abs(this.canvasX);
    }
    if (this.canvasY < 0) {
      svgH += Math.abs(this.canvasY);
    }
    
    String str = "<svg width=\"" + svgW + "\" height=\"" + svgH + "\" version=\"1.1\""
          + " xmlns=\"http://www.w3.org/2000/svg\">\n\n";
    
    int disappear = Math.round((1000 / this.speed) * end);
    int restart = disappear + 1000;
    str += "<rect>\n"
      + "   <animate id=\"base\" begin=\"0;base.end\" dur=\"" + restart + "ms\" attributeName="
      + "\"visibility\" from=\"hide\" to=\"hide\"/>\n"
      + "</rect>\n\n";
    for (int i = 0; i < shapeList.size(); i++) {
      Shape shape = shapeList.get(i);
      String name = shape.getName();
      int x = shape.getReferencePoint().getX();
      int y = shape.getReferencePoint().getY();
      if (this.canvasX < 0) {
        x += Math.abs(this.canvasX);
      }
      if (this.canvasY < 0) {
        y += Math.abs(this.canvasY);
      }
      int w = shape.getWidth();
      int h = shape.getHeight();
      int r = shape.getColor().getR();
      int g = shape.getColor().getG();
      int b = shape.getColor().getB();
      int currX = x;
      int currY = y;
      int currW = w;
      int currH = h;
      int currR = r;
      int currG = g;
      int currB = b;
      
      // printing shape 
      if (shape.getAppearTime() == 0) {
        if (shape.getType().equals("oval")) {
          str += "<ellipse id=\"" + name + "\" cx=\"" + x + "\" cy=\"" + y + "\" rx=\"" 
              + w + "\" ry=\"" + h + "\" fill=\"rgb(" + r + "," + g + "," + b + ")\""
              + " visibility=\"visible\" >\n";
        }
        if (shape.getType().equals("rectangle")) {
          str += "<rect id=\"" + name + "\" x=\"" + x + "\" y=\"" + y + "\" width=\"" 
              + w + "\" height=\"" + h + "\" fill=\"rgb(" + r + "," + g + "," + b + ")\""
              + " visibility=\"visible\" >\n";
        }
      }
      else {
        if (shape.getType().equals("oval")) {
          str += "<ellipse id=\"" + name + "\" cx=\"" + x + "\" cy=\"" + y + "\" rx=\"" 
              + w + "\" ry=\"" + h + "\" fill=\"rgb(" + r + "," + g + "," + b + ")\""
              + " visibility=\"hidden\" >\n";
        }
        if (shape.getType().equals("rectangle")) {
          str += "<rect id=\"" + name + "\" x=\"" + x + "\" y=\"" + y + "\" width=\"" 
              + w + "\" height=\"" + h + "\" fill=\"rgb(" + r + "," + g + "," + b + ")\""
              + " visibility=\"hidden\" >\n";
        }
        int duration = Math.round((1000 / this.speed) * (end - shape.getAppearTime()));
        int begin = Math.round((1000 / this.speed) * shape.getAppearTime());
        str += "    <set attributeType=\"css\" begin=\"base.begin+" + begin + "ms\" dur=\"" 
            + duration + "ms\" attributeName=\"visibility\" to=\"visible\" fill=\"freeze\" />\n";
      }
      try {
        this.output.append(str);
      } catch (IOException e) {
        throw new IllegalStateException("Error when printing shapes to the svg file.");
      }
      
      // printing shape animations 
      ArrayList<ShapeAnimation> animate = getAnimationOfShape(shape, animationList);
      str = "";
      String xName = "";
      String yName = "";
      String wName = "";
      String hName = "";
      for (int j = 0; j < animate.size(); j++) {
        ShapeAnimation motion = animate.get(j);
        int start = Math.round(((1000 * motion.getT1())) / this.speed);
        int dur = Math.round((1000 / this.speed) * (motion.getT2() - motion.getT1()));
        if (shape.getType().equals("oval")) {
          xName = "cx";
          yName = "cy";
          wName = "rx";
          hName = "ry";
        }
        if (shape.getType().equals("rectangle")) {
          xName = "x";
          yName = "y";
          wName = "width";
          hName = "height";
        }
        Shape oldShape = motion.getShape();
        Shape newShape = motion.getNewShape();
        
        // move
        int x1 = oldShape.getReferencePoint().getX();
        int y1 = oldShape.getReferencePoint().getY();
        int x2 = newShape.getReferencePoint().getX();
        int y2 = newShape.getReferencePoint().getY();
        if (this.canvasX < 0) {
          x1 += Math.abs(this.canvasX);
        }
        if (this.canvasY < 0) {
          y1 += Math.abs(this.canvasY);
        }
        if (this.canvasX < 0) {
          x2 += Math.abs(this.canvasX);
        }
        if (this.canvasY < 0) {
          y2 += Math.abs(this.canvasY);
        }
        if (x1 != x2) {
          currX = x2;
          str += "    <animate attributeType=\"xml\" begin=\"base.begin+" + start + "ms\" dur=\""
              + dur + "ms\" attributeName=\"" + xName + "\" from=\"" + x1 + "\" to=\"" + x2 + "\""
              + " fill=\"freeze\" />\n";
        }
        if (y1 != y2) {
          currY = y2;
          str += "    <animate attributeType=\"xml\" begin=\"base.begin+" + start + "ms\" dur=\""
              + dur + "ms\" attributeName=\"" + yName + "\" from=\"" + y1 + "\" to=\"" + y2 + "\""
              + " fill=\"freeze\" />\n";
        }
        
        // scale
        int w1 = oldShape.getWidth();
        int h1 = oldShape.getHeight();
        int w2 = newShape.getWidth();
        int h2 = newShape.getHeight();
        if (w1 != w2) {
          currW = w2;
          str += "    <animate attributeType=\"xml\" begin=\"base.begin+" + start + "ms\" dur=\"" 
              + dur + "ms\" attributeName=\"" + wName + "\" from=\"" + w1 + "\" to=\"" + w2 + "\""
              + " fill=\"freeze\" />\n";
        }
        if (h1 != h2) {
          currH = h2;
          str += "    <animate attributeType=\"xml\" begin=\"base.begin+" + start + "ms\" dur=\""
              + dur + "ms\" attributeName=\"" + hName + "\" from=\"" + h1 + "\" to=\"" + h2 + "\""
              + " fill=\"freeze\" />\n";
        }
        
        // changeColor
        int r1 = oldShape.getColor().getR();
        int g1 = oldShape.getColor().getG();
        int b1 = oldShape.getColor().getB();
        int r2 = newShape.getColor().getR();
        int g2 = newShape.getColor().getG();
        int b2 = newShape.getColor().getB();
        if (r1 != r2 || g1 != g2 || b1 != b2) { 
          currR = r2;
          currG = g2;
          currB = b2;
          str += "    <animate attributeType=\"css\" begin=\"base.begin+" + start + "ms\" dur=\""
              + dur + "ms\" attributeName=\"fill\" from=\"rgb(" + r1 + "," + g1 + "," + b1 + ")\""
              + " to=\"rgb(" + r2 + "," + g2 + "," + b2 + ")\" fill=\"freeze\" />\n";
        }
      }
      
      try {
        this.output.append(str);
      } catch (IOException e) {
        throw new IllegalStateException("Error when printing shape transformations to "
            + "the svg file.");
      }
       
      // hide shape and restore all changed attributes
      str = "    <set attributeType=\"css\" begin=\"base.begin+" + disappear + "ms\""
          + " dur=\"1000ms\" attributeName=\"visibility\" to=\"hidden\" fill=\"freeze\" />\n";
      if (currX != x) {
        str += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName"
            + "=\"" + xName + "\" to=\"" + x + "\" fill=\"freeze\" />\n";
      }
      if (currY != y) {
        str += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName"
            + "=\"" + yName + "\" to=\"" + y + "\" fill=\"freeze\" />\n";
      }
      if (currW != w) {
        str += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName"
            + "=\"" + wName + "\" to=\"" + w + "\" fill=\"freeze\" />\n";
      }
      if (currH != h) {
        str += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName"
            + "=\"" + hName + "\" to=\"" + h + "\" fill=\"freeze\" />\n";
      }
      if (currR != r || currG != g || currB != b) {
        str += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName"
            + "=\"fill\" to=\"rgb(" + r + "," + g + "," + b + ")\" fill=\"freeze\" />\n";
      }
      if (shape.getType().equals("oval")) {
        str += "</ellipse>\n\n";
      }
      if (shape.getType().equals("rectangle")) {
        str += "</rect>\n\n";
      }
    }
    str += "</svg>\n";

    try {
      this.output.append(str);
    } catch (IOException e) {
      throw new IllegalStateException("Error when printing the svg file.");
    }
  }
  
  // method that gets an ArrayList of the animations for the given shape.
  private ArrayList<ShapeAnimation> getAnimationOfShape(Shape shape, 
      ArrayList<ShapeAnimation> animationList) {
    ArrayList<ShapeAnimation> listOfAnimations = new ArrayList<ShapeAnimation>();
    for (int i = 0; i < animationList.size(); i++) {
      ShapeAnimation animation = animationList.get(i);
      if (shape.getName().equals(animation.getShape().getName())) {
        listOfAnimations.add(animation);
      }
    }
    return listOfAnimations;
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
