import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;


public class Pong extends Canvas implements Runnable
{
  private static final long serialVersionUID = 4L;

  // Width and Height of the game board
  public static final int WIDTH  = 640;
  public static final int HEIGHT = WIDTH/12 * 9;

  private Random r;

  // Thread for the game, only one is needed here
  private Thread game;
  private boolean isRunning;

  // GUIs
  protected Ball ball;
  protected Paddle p1;
  protected Paddle p2;
  protected HUD hud;
  private Menu menu;
  private MenuDesign[] m;

  // 0 is for menu, 1 is to initialize objects, 2 is to start multiplayer,
  //    3 is for the help button, and 4 is for the singleplayer
  public int gameState = 0;

  // Constructor
  public Pong()
  {
    new Window(WIDTH, HEIGHT, this, "Pong");

    // Add in the menu
    menu = new Menu(this);

    // Add in the mouse detection
    this.addMouseListener(menu);

    // Add in the heads up display
    hud = new HUD();

    r = new Random();
    m = new MenuDesign[50];
    for(int i = 0; i < m.length; i++)
    {
      m[i] = new MenuDesign(r.nextInt(WIDTH - 32), r.nextInt(HEIGHT - 50));
    }
  }

  // Make it so only one thread can enter each call
  public synchronized void start()
  {
    game = new Thread(this);
    game.start();
    isRunning = true;
  }

  // Make it so only one thread can go in each call
  public synchronized void stop()
  {
    try{
      game.join();
      isRunning = false;
    }catch(Exception e){
      e.printStackTrace();
    }
  }

// Method from the runnable interface
@SuppressWarnings("unused")
public void run()
  {
    // Make it so you do not have to press the screen to start the game
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double nanoSeconds = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer  = System.currentTimeMillis();
    int frames = 0;
    while(isRunning == true)
    {
      long now = System.nanoTime();
      delta += (now - lastTime)/nanoSeconds;
      lastTime = now;
      while(delta >= 1)
      {
        tick();
        delta--;
      }
      if(isRunning == true){render();}
      frames++;

      if(System.currentTimeMillis() - timer > 1000)
      {
        timer += 1000;
        // System.out.println("FPS: " + frames);
        frames = 0;
      }
    }
    stop();

  }

  // Tick method for each class is how the object should act each tick
  public void tick()
  {
    // MULTIPLAYER
    if(gameState == 2)
    {
      ball.tick();
      p1.tick();
      p2.tick();

      // If either player reaches 7, everything stops and the game returns
      //    to the menu
      if(HUD.score1 == 7)
      {
        ball.setYVel(0);
        ball.setXVel(0);
        p1.setYVel(0);
        p2.setYVel(0);
        gameState = 0;
      }
      else if(HUD.score2 == 7)
      {
        ball.setYVel(0);
        ball.setXVel(0);
        p1.setYVel(0);
        p2.setYVel(0);
        gameState = 0;
      }
      hud.tick();
    }

    // SINGLEPLAYER
    if(gameState == 4)
    {
      if(p2.getYPos() > ball.getYPos() && ball.getXVel() < 0 && ball.getXPos() <= WIDTH/2 - 50){ p2.setYVel(-5); }
      if(p2.getYPos() < ball.getYPos() && ball.getXVel() < 0 && ball.getXPos() <= WIDTH/2 - 50){ p2.setYVel(5); }
      ball.tick();
      p1.tick();
      p2.tick();

      // If either player reaches 7, everything stops and the game returns
      //    to the menu
      if(HUD.score1 == 7)
      {
        ball.setYVel(0);
        ball.setXVel(0);
        p1.setYVel(0);
        p2.setYVel(0);
        gameState = 0;
      }
      else if(HUD.score2 == 7)
      {
        ball.setYVel(0);
        ball.setXVel(0);
        p1.setYVel(0);
        p2.setYVel(0);
        gameState = 0;
      }
      hud.tick();
    }

    // Add in menu effect
    if(gameState == 0)
    {
      menu.tick();
      for(int i = 0; i < m.length; i++){ m[i].tick(); }
    }
  }

  // Render method sets how each object should look
  public void render()
  {
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null)
    {
      this.createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();

    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    // MULTIPLAYER
    if(gameState == 2)
    {
      ball.render(g);
      p1.render(g);
      p2.render(g);
      hud.render(g);
    }

    // SINGLEPLAYER
    else if(gameState == 4)
    {
      ball.render(g);
      p1.render(g);
      p2.render(g);
      hud.render(g);
    }

    // HELP BUTTON and MENU
    else if(gameState == 0 || gameState == 3)
    {
      menu.render(g);
    }

    // MENU effect
    if(gameState == 0)
    {
      for(int i = 0; i < m.length; i++)
      {
        m[i].render(g);
      }
    }
    g.dispose();
    bs.show();
  }

  public static void main(String[] args)
  {
    new Pong();
  }
}
