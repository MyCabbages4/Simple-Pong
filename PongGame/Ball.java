
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;


public class Ball
{
  // Instance fields
  private int ballTracker;
  private int xPos, yPos;
  private int xVel, yVel;

  public Ball(int x, int y)
  {
    xPos = x;
    yPos = y;
    xVel = 3;
    yVel = 3;
    ballTracker = 0;
  }

  public Rectangle getBounds()
  {
    return new Rectangle(xPos, yPos, 15, 15);
  }

  public void tick()
  {
    xPos += xVel;
    yPos += yVel;
    ballTracker++;

    if(xPos <= 8)
    {
      reset(-3, -3);
      ballTracker = 0;
      HUD.score1++;
    }
    if(xPos >= Pong.WIDTH - 32)
    {
      reset(3, 3);
      ballTracker = 0;
      HUD.score2++;
    }

    if(yPos <= 0 || yPos >= Pong.HEIGHT - 50){ yVel *= -1; }
    if(ballTracker == 1500)
    {
      if(xVel > 0 && yVel > 0)
      {
        xVel += 1;
        yVel += 1;
      }

      if(xVel < 0 && yVel < 0)
      {
        xVel += -1;
        yVel += -1;
      }

      if(xVel > 0 && yVel < 0)
      {
        xVel += 1;
        yVel += -1;
      }

      if(xVel < 0 && yVel > 0)
      {
        xVel += -1;
        yVel += 1;
      }
      ballTracker = 0;
    }
  }

  public void render(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(xPos, yPos, 15, 15);
  }

  private void reset(int velX, int velY)
  {
    xPos = Pong.WIDTH/2 - 32;
    yPos = Pong.HEIGHT/2 - 50;
    xVel = velX;
    yVel = velY;
  }

  public void setYPos(int y){ yPos = y; }
  public void setYVel(int y){ yVel = y; }
  public void setXVel(int x){ xVel = x; }
  public void setXPos(int x){ xPos = x; }

  public int getYPos(){ return yPos; }
  public int getYVel(){ return yVel; }
  public int getXVel(){ return xVel; }
  public int getXPos(){ return xPos; }
}
