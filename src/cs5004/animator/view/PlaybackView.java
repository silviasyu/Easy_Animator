package cs5004.animator.view;

import cs5004.animator.controller.Controller;
import cs5004.animator.util.Point2D;
import cs5004.animator.util.Shape;
import cs5004.animator.util.ShapeAnimation;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * This class represents a PlaybackView object, a visual GUI view of the animation
 * that has playback controls.
 * It offers all the operations mandates by the IView interface
 * @author silvia
 */
public class PlaybackView extends JFrame implements IView {
  private AnimationPanel panel;
  private JButton restartButton;
  private JButton pauseButton; 
  private JButton startButton;
  private JButton setSpeedButton;
  private JButton setTickButton;
  private JButton repeatButton;
  private JButton noRepeatButton;
  private JTextField textField;
  
  /**
   * Creates a PlaybackView object with the given canvas attributes: the top corner, 
   * width, and height.
   * @param topCorner the top corner of the canvas
   * @param width the width of the canvas
   * @param height the height of the canvas
   */
  public PlaybackView(Point2D topCorner, int width, int height) {
    super("animation");
    this.setSize(width, height * 2);
    this.setLocation(0,0);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //add animation panel
    this.panel = new AnimationPanel();
    this.panel.setSize(width,height);
    this.panel.setLocation(0,40);
    
    JScrollPane scroll = new JScrollPane(this.panel);
    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(scroll);
    this.add(this.panel);
    
    
    //add buttons 
    JPanel buttonPanel = new JPanel(true);
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.setSize(width, 40);
    buttonPanel.setLocation(0,0);
    buttonPanel.setLayout(new FlowLayout());
    
    //textbox
    this.textField = new JTextField(5);
    buttonPanel.add(this.textField);
   
    //buttons 
    this.startButton = new JButton("Start");
    this.startButton.setActionCommand("Start");
    buttonPanel.add(this.startButton);
    this.restartButton = new JButton("Restart");
    this.restartButton.setActionCommand("Restart");
    buttonPanel.add(this.restartButton);
    this.pauseButton = new JButton("Pause");
    this.pauseButton.setActionCommand("Pause");
    buttonPanel.add(this.pauseButton);
    this.setSpeedButton = new JButton("Set Speed");
    this.setSpeedButton.setActionCommand("setSpeed");
    buttonPanel.add(this.setSpeedButton);
    this.setTickButton = new JButton("Set Tick");
    this.setTickButton.setActionCommand("setTick");
    buttonPanel.add(this.setTickButton);
    this.repeatButton = new JButton("Repeat");
    this.repeatButton.setActionCommand("Repeat");
    buttonPanel.add(this.repeatButton);
    this.noRepeatButton = new JButton("Disable Repeat");
    this.noRepeatButton.setActionCommand("noRepeat");
    buttonPanel.add(this.noRepeatButton);
    this.add(buttonPanel);
    
    this.setVisible(true);
    this.panel.repaint();
  }

  @Override
  public void display(ArrayList<Shape> shapeList, ArrayList<ShapeAnimation> animationList) {
    this.panel.setShapeList(shapeList);
    this.panel.repaint();
  }

  @Override
  public void updateCurrentShapes(ArrayList<Shape> shapeList) throws UnsupportedOperationException {
    this.panel.setShapeList(shapeList);
    this.panel.repaint();
  }

  @Override
  public void setController(Controller controller) {
    this.startButton.addActionListener(controller);
    this.restartButton.addActionListener(controller);
    this.pauseButton.addActionListener(controller);
    this.setSpeedButton.addActionListener(controller);
    this.setTickButton.addActionListener(controller);
    this.repeatButton.addActionListener(controller);
    this.noRepeatButton.addActionListener(controller);
  }

  @Override
  public int getInput() throws UnsupportedOperationException {
    String inputStr = this.textField.getText();
    int input = 0;
    try {
      input = Integer.parseInt(inputStr);
    } catch (NullPointerException e) {
      JOptionPane.showMessageDialog(null, "The speed or tick has to be a number."); 
    } catch (NumberFormatException e2) {
      JOptionPane.showMessageDialog(null, "The speed or tick has to be a number."); 
    }
    this.textField.setText("");
    return input;
  }
}
