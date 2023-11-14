import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;


public class Paddle
{
  // Instance fields, can be accessed from the outside
  protected int yPos, xPos;
  protected int yVel;

  protected Ball ball;

  public Paddle(int x, int y, Ball b)
  {
    xPos = x;
    yPos = y;
    ball = b;
  }

  public Rectangle getBounds()
  {
    return new Rectangle(xPos, yPos, 15, 70);
  }

  public void tick()
  {
    yPos += yVel;

    if(yPos >= Pong.HEIGHT - 105){ yPos = Pong.HEIGHT - 105; }
    else if(yPos <= 0){ yPos = 0; }

    collision();
  }

  public void render(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(xPos, yPos, 15, 70);

  }

  // How the paddle acts on collision with an object
  public void collision()
  {
    if(getBounds().intersects(ball.getBounds()))
    {
      //ball.setYVel(ball.getYVel() * -1);
      ball.setXVel(ball.getXVel() * -1);
    }
  }

  public void setYVel(int y){ yVel = y; }
  public int getYVel(){ return yVel; }

  public void setYPos(int y){ yPos = y; }
  public int getYPos(){ return yPos; }

  public void setXPos(int x){ xPos = x; }
  public int getXPos(){ return xPos; }
}
