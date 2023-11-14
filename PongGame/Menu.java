import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;


public class Menu extends MouseAdapter
{
  private Pong pong;

  public Menu(Pong p)
  {
    pong = p;
  }

  public void mousePressed(MouseEvent e)
  {
    int x = e.getX();
    int y = e.getY();

    // SINGLEPLAYER
    if(mouseOver(x, y, 160, 200, 300, 43) == true && pong.gameState == 0)
    {
      // After button is clicked, you have to initialize everything, or you will
      //    get a NullPointerException
      pong.gameState = 1;
      pong.ball = new Ball(Pong.WIDTH/2 - 32, Pong.HEIGHT/2 - 50);
      pong.p1 = new Paddle(Pong.WIDTH - 29, Pong.HEIGHT/2 - 50, pong.ball);
      pong.p2 = new Paddle(0, Pong.HEIGHT/2 - 50, pong.ball);
      pong.addKeyListener(new KeyInput(pong.p1, pong.p2));
      pong.hud = new HUD();
      pong.gameState = 4;
    }

    // MULTIPLAYER
    if(mouseOver(x, y, 160, 140, 300, 43) == true && pong.gameState == 0)
    {
      // After button is clicked, you have to initialize everything, or you will
      //    get a NullPointerException
      pong.gameState = 1;
      pong.ball = new Ball(Pong.WIDTH/2 - 32, Pong.HEIGHT/2 - 50);
      pong.p1 = new Paddle(Pong.WIDTH - 29, Pong.HEIGHT/2 - 50, pong.ball);
      pong.p2 = new Paddle(0, Pong.HEIGHT/2 - 50, pong.ball);
      pong.addKeyListener(new KeyInput(pong.p1, pong.p2));
      pong.hud = new HUD();
      pong.gameState = 2;
    }

    // Make sure you cannot click buttons after you are out of the menu
    if(mouseOver(x, y, 210, 150, 200, 64) == true && pong.gameState == 3)
    { pong.gameState = 3; }

    // Help button
    if(mouseOver(x, y, 160, 240, 300, 43) == true && pong.gameState == 0)
    { pong.gameState = 3; }

    // Quit button
    if(mouseOver(x, y, 160, 320, 300, 43) == true && pong.gameState == 0)
    { System.exit(1); }

    if(mouseOver(x, y, 10, 10, 100, 32) == true)
    {
      pong.gameState = 0;
      HUD.score1 = 0;
      HUD.score2 = 0;
    }
  }

  public void mouseReleased(MouseEvent e)
  {

  }

  // Check if the mouse is within the bounds of a certain place in the frame
  private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
  {
    if(mx > x && mx < x + width)
    {
      if(my > y && my < y + height){ return true; }
      else{ return false; }
    }
    else{ return false; }
  }

  public void tick()
  {
    ;
  }

  public void render(Graphics g)
  {
    // Draw the menu items
    if(pong.gameState == 0)
    {
      Font font = new Font("Arial", 1, 50);
      Font font2 = new Font("Arial", 1, 30);
      g.setFont(font);
      g.setColor(Color.WHITE);
      g.drawString("PONG", 240, 70);

      g.setFont(font2);
      g.setColor(Color.WHITE);
      g.drawRect(160, 140, 300, 43);
      g.drawString("Multiplayer", 232, 170);

      g.drawRect(160, 200, 300, 43);
      g.drawString("Singleplayer", 222, 230);

      g.drawRect(160, 260, 300, 43);
      g.drawString("Help", 280, 290);

      g.drawRect(160, 320, 300, 43);
      g.drawString("Quit", 280, 350);
    }

    else if(pong.gameState == 3)
    {
      // Help Menu
      Font font3 = new Font("Arial", 1, 50);
      Font font4 = new Font("Arial", 1, 15);
      g.setFont(font3);
      g.setColor(Color.WHITE);
      g.drawString("Help Menu", 190, 70);

      g.setFont(font4);
      g.drawRect(10, 10, 100, 32);
      g.drawString("<-- To Menu", 15, 30);
      g.drawString("Press the up and down arrows to control player 1. Press W and S to control player 2. ", 0, Pong.HEIGHT/2 - 50);
      g.drawString("First to 7 wins.", Pong.WIDTH/2 - 64, Pong.HEIGHT/2 - 30);
    }
  }
}
