import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Window extends Canvas
{
  private static final long serialVersionUID = 3L;

  // Constructor for the Window class
  public Window(int width, int height, Pong pong, String title)
  {
    // The window will be a JFrame
    JFrame frame = new JFrame(title);

    // Set the size
    frame.setPreferredSize(new Dimension(width, height));
    frame.setMaximumSize(new Dimension(width, height));
    frame.setMinimumSize(new Dimension(width, height));

    // Make sure program stops on the closing of the JFrame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    // Frame starts in the center of the screen
    frame.setLocationRelativeTo(null);

    // Add the game to the Frame
    frame.add(pong);
    // Make sure it can be seen
    frame.setVisible(true);
    // Start the game thread
    pong.start();
  }
}
