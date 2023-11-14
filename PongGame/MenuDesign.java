import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class MenuDesign
{
  // This class is the same thing as a ball, except the colors and speed are
  //    randomized. Some methods are also not included because they are not
  //    needed.
  
    // Instance fields
    private int xPos, yPos;
    private int xVel, yVel;
    private Color col;
    private Random r;

    public MenuDesign(int x, int y)
    {
      xPos = x;
      yPos = y;
      r = new Random();
      xVel = (r.nextInt(5 - -5) + -5);
      yVel = (r.nextInt(5 - -5) + -5);
      if(xVel == 0){ xVel = 1; }
      if(yVel == 0){ yVel = 1; }
      col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public void tick()
    {
      xPos += xVel;
      yPos += yVel;

      if(yPos <= 0 || yPos >= Pong.HEIGHT - 50){ yVel *= -1; }
      if(xPos <= 0 || xPos >= Pong.WIDTH - 32){ xVel *= -1; }
    }

    public void render(Graphics g)
    {
      g.setColor(col);
      g.fillRect(xPos, yPos, 15, 15);
    }
  }
