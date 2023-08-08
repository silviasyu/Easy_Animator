package cs5004.animator;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.IController;
import cs5004.animator.model.IModel;
import cs5004.animator.model.Model;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.GuiView;
import cs5004.animator.view.IView;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SvgView;
import cs5004.animator.view.TextView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * This class represents an EasyAnimator object that creates animations. 
 * @author silvia
 */
public final class EasyAnimator {
  
  /**
   * The main method to run the animation.
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    String inStr = "";
    String outStr = "";
    String viewStr = "";
    int speed = 0;
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        try {
          if (args[i + 1].charAt(0) == '-') {
            JOptionPane.showMessageDialog(null, "-in must be follow by the full input file name "
                + "including the extension.");
            return;
          }
          inStr = args[i + 1];
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "-in must be followed by the full input file name "
              + "including the extension."); 
          return;
        }
      }
      
      if (args[i].equals("-out")) {
        try {
          if (args[i + 1].charAt(0) == '-') {
            JOptionPane.showMessageDialog(null, "-out must be followed by an output file name not"
                + " including the extension."); 
            return;
          }
          outStr = args[i + 1];
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "-out must be followed by an ouput file name not "
              + "including the extension."); 
          return;
        }
      }
      
      if (args[i].equals("-view")) {
        try {
          if (args[i + 1].charAt(0) == '-') {
            JOptionPane.showMessageDialog(null, "-view must be followed by a a view type."); 
          }
          viewStr = args[i + 1];
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "-view must be followed by a a view type."); 
          return;
        }
        if (!viewStr.equals("text") && !viewStr.equals("visual") && !viewStr.equals("svg") 
            && !viewStr.equals("playback")) {
          JOptionPane.showMessageDialog(null, "view type must be text, visual, svg, or playback."); 
          return;
        }
      }
      if (args[i].equals("-speed")) {
        try {
          if (Integer.parseInt(args[i + 1]) > 0) {
            speed = Integer.parseInt(args[i + 1]);
          }
        } catch (NullPointerException e) {
          JOptionPane.showMessageDialog(null, "-speed must be followed by a number greater "
              + "than 0."); 
          return;
        } catch (NumberFormatException e2) {
          JOptionPane.showMessageDialog(null, "-speed must be followed by a number greater "
              + "than 0."); 
          return;
        }
      }
    }
    
    if (viewStr.equals("")) {
      JOptionPane.showMessageDialog(null, "The view argument must be included in the "
          + "command line arguments.");
      return;
    }
    if (inStr.equals("")) {
      JOptionPane.showMessageDialog(null, "The -in argument must be included in the "
          + "command line arguments.");
      return;
    }
    if (speed == 0) {
      speed = 1;
      JOptionPane.showMessageDialog(null, "The -speed argument was not included, "
          + "default speed = 1.");
    }
    if (outStr.equals("")) {
      outStr = "System.out";
      JOptionPane.showMessageDialog(null, "The -out argument was not included, "
          + "default output is System.out.");
    }

    IModel model = new Model();
    model.setSpeed(speed);
    try {
      model = AnimationReader.parseFile(new FileReader(inStr), new AnimationBuilderImpl(model));
    } catch (FileNotFoundException ioe) {
      JOptionPane.showMessageDialog(null, "The input file does not exist or it is inaccessible."); 
      return;
    }
    IView view;
    view = new TextView(System.out,model.getEndTime());
    FileWriter fw = null;
    if (viewStr.equals("text")) {
      if (!outStr.equalsIgnoreCase("System.out")) {
        try {
          fw = new FileWriter(outStr + ".txt");
        } 
        catch (IOException e) {
          JOptionPane.showMessageDialog(null, "The output file exists but is a directory rather "
              + "than a regular file, does not exist but cannot be created, or cannot be opened."); 
          return;
        }
        view = new TextView(fw, model.getEndTime());
      }
    }
    else if (viewStr.equals("svg")) {
      if (outStr.equalsIgnoreCase("System.out")) {
        view = new SvgView(System.out, model.getCanvasTopCorner(), model.getCanvasWidth(), 
            model.getCanvasHeight(), model.getEndTime(), model.getSpeed());
      }
      else {
        try {
          fw = new FileWriter(outStr + ".svg");
        } catch (IOException e) {
          JOptionPane.showMessageDialog(null, "The output file exists but is a directory rather "
              + "than a regular file, does not exist but cannot be created, or cannot be opened."); 
          return;
        }
        view = new SvgView(fw, model.getCanvasTopCorner(), model.getCanvasWidth(), 
            model.getCanvasHeight(), model.getEndTime(), model.getSpeed());
      }
    }
    else if (viewStr.equals("visual")) {
      view = new GuiView(model.getCanvasTopCorner(), model.getCanvasWidth(), 
          model.getCanvasHeight());
    }
    else if (viewStr.equals("playback")) {
      view = new PlaybackView(model.getCanvasTopCorner(), model.getCanvasWidth(), 
          model.getCanvasHeight());
    }
    
    IController controller = new Controller(model,view);
    controller.play();
    if (!outStr.equalsIgnoreCase("System.out") && !(viewStr.equals("visual") 
        || viewStr.equals("playback"))) {
      try {
        fw.close();
      }
      catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Problem closing output file");
        return;
      }
    }
  }
}
