import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter
{
  private boolean[] keyDown = new boolean[4];
  private Paddle p1;
  private Paddle p2;

  public KeyInput(Paddle p1, Paddle p2)
  {
    this.p1 = p1;
    this.p2 = p2;

    keyDown[0] = false;
    keyDown[1] = false;
    keyDown[2] = false;
    keyDown[3] = false;
  }

  public void keyPressed(KeyEvent e)
  {
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_UP)
    {
      p1.setYVel(-5);
      keyDown[0] = true;
    }

    if(key == KeyEvent.VK_DOWN)
    {
      p1.setYVel(5);
      keyDown[1] = true;
    }

    if(key == KeyEvent.VK_W)
    {
      p2.setYVel(-5);
      keyDown[2] = true;
    }

    if(key == KeyEvent.VK_S)
    {
      p2.setYVel(5);
      keyDown[3] = true;
    }
    if(key == KeyEvent.VK_ESCAPE){ System.exit(1); }
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_UP){keyDown[0] = false;}
    if(key == KeyEvent.VK_DOWN){keyDown[1] = false;}
    if(key == KeyEvent.VK_W){keyDown[2] = false;}
    if(key == KeyEvent.VK_S){keyDown[3] = false;}

    //Vertical movement
    if(!keyDown[0] && !keyDown[1]){ p1.setYVel(0); }
    // Horizontal Movement
    if(!keyDown[2] && !keyDown[3]){ p2.setYVel(0); }
  }
}
