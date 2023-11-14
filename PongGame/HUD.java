
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// Class for the display on the screen (i.e. score)
public class HUD
{
  // Instance fields
  public static int score1, score2;
  //private static JFrame winFrame;

  public void tick()
  {
    if(score1 == 7 || score2 == 7)
    {
      score1 = 0;
      score2 = 0;
    }
  }

  public void render(Graphics g)
  {
    Font font = new Font("SansSerif", Font.PLAIN, 50);
    g.setFont(font);
    for(int i = 0; i < Pong.HEIGHT - 50; i += 40)
    {
      g.setColor(Color.WHITE);
      g.fillRect(Pong.WIDTH/2 - 25, i, 10, 20);
    }

    g.drawString("" + score2, 140, 50);
    g.drawString("" + score1, 457, 50);

    Font font2 = new Font("Arial", 1, 15);
    g.setFont(font2);
    g.drawRect(10, 10, 100, 32);
    g.drawString("<-- To Menu", 15, 30);
  }
}
