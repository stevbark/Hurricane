package net.anorrah;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
// head 17 width 21
public class Core extends Applet implements Runnable 
{
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	public static final int res = 1;//pixel to frame
	public static double offset_Y = 0, offset_X = 0;
	public static int dir = 0;
	public static int lol;
	public static boolean moving = false;
	public static boolean running = false;
	public static boolean inGame = true;
	
	public final int TARGET_FPS = 60;
	public final long OPTIMAL_TIME = 1000000000/TARGET_FPS;
	public static long lastFPSTIME = 0;
	public static int fps = 0;
	public static int renderFPS = 0;
	
	public static boolean bW, bS, bA, bD, bE, bUP, bDOWN, bLEFT, bRIGHT,bESC, bP;
	
	private Image screen;
	public static Dimension VIEWPORT_SIZE = new Dimension(672,544);
	public static Dimension pixel = new Dimension(VIEWPORT_SIZE.width,VIEWPORT_SIZE.height);
	public static Dimension Size;
	
	public static String name = "Prisoner of Anorrah";
	
	public static Level level;
	
	public static EntityPlayer player;
	public static Core t;
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static ArrayList<Entity> removethese = new ArrayList<Entity>();
	
	public static int offset_MAX_X, offset_MAX_Y, offset_MIN_X = 0, offset_MIN_Y = 0;
	public static Rectangle camera = new Rectangle(0,0,VIEWPORT_SIZE.width,VIEWPORT_SIZE.height);
	
	public boolean WaitForPlayer = true;
	//Constructor
	public Core()
	{
		InputManager inpt = new InputManager();
		setPreferredSize(VIEWPORT_SIZE);
		addKeyListener(inpt);
		addMouseListener(inpt);
	}
	
	public static void main(String[] args) 
	{
		t = new Core();
		
		frame = new JFrame();
		frame.add(t);
		frame.pack();
		
		Size = new Dimension(frame.getWidth(),frame.getHeight());
		
		frame.setTitle(name);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);//centered
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		t.start();
	}
	
	public void start()
	{
		requestFocus();
		
		//Class declarations here
		level = new Level();
		new Tile();
		
		offset_MAX_X = Level.width - VIEWPORT_SIZE.width;
		offset_MAX_Y = Level.height - VIEWPORT_SIZE.height;
		
		initPlayer();
		//player.setTilePosition(20, 20);
		running = true;
		new Thread(this).start();
	}
	
	public void stop()
	{
		running = false;
	}
	
	public void setWaitForPlayerToFalse()
	{
		WaitForPlayer = false;
	}
	
	public void initPlayer()
	{
		player = new EntityPlayer(t, 
				(VIEWPORT_SIZE.width / 2) - (Tile.size / 2) + offset_X,
				(VIEWPORT_SIZE.height / 2) - (Tile.size / 2) + offset_Y,
				Tile.size,
				Tile.size);
		 
		entities.add(player);
		player.setUp(player);
		
		/*ArrayList<EnemyEntities> enemies = level.retrieveEnemies(); 
		for(int i = 0; i < enemies.size(); i++)
		{
			entities.add(enemies.get(i));
		}*/
		

	}
	
	public static void addEntity(Entity entity)
	{
		entities.add(entity);
	}
	
	public static void remove(Entity entity)
	{
		removethese.add(entity);
	}
	
	public void tick(double delta)
	{
		frame.pack();
		
		if(inGame)
		{
			if(player.isDead()){
				player.stopGame();
			}
			for(int i = 0; i < entities.size(); i++)
			{
				Entity ent = entities.get(i);
				ent.move(delta);
			}
			
			for(int i = 0; i < entities.size(); i++)
			{
				Entity thisobj = entities.get(i);
				for(int j = 0; j < entities.size(); j++)
				{
					Entity otherobj = entities.get(j);
					
					
					
					if(thisobj.collides(otherobj))
					{
						thisobj.on_collided(otherobj);
						otherobj.on_collided(thisobj);
					}
				}
			}
		}
		level.tick();
		entities.remove(removethese);
		removethese.clear();
	}
	
	
	public void render()
	{
		Graphics g = screen.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, VIEWPORT_SIZE.width, VIEWPORT_SIZE.height);
		level.render(g, (int)(offset_X), (int)(offset_Y), (pixel.width/Tile.size), (pixel.height/Tile.size));
		
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).render(g);
		}
		
		// UI DRAWING STARTS HERE
		//g.setFont(new Font("Arial",Font.PLAIN,10));
		
		int stringOffsetY = 23;
		int weaponOffsetX = 400;
		//HP BAR
		g.setColor(Color.BLACK);
		g.fillRect(40, 10, 150, 20);
		
		g.setColor(Color.YELLOW);
		g.drawRect(40, 10, 150, 20);
		
		if(!player.isDead()){
			g.setColor(Color.RED);
			g.fill3DRect(40, 10, (int)(150*((double)player.getHealth()/(double)player.maxHealth)), 20, false);
		}
		
		g.setColor(Color.YELLOW);
		g.drawString("HP:   " + player.getHealth() + "/" + player.maxHealth, 17,stringOffsetY);
		g.drawRect(40, 10, 150, 20);
		
		// Item
		g.drawString("ITEM: ",220, stringOffsetY);
		g.drawRoundRect(255, 0, 33, 32, 5, 5);
		
		// Primary Weapon 
		g.drawString("MELEE: ", weaponOffsetX, stringOffsetY);
		g.drawRoundRect(weaponOffsetX+47, 0, 33, 32, 5, 5);
		
		// Secondary Weapon
		g.drawString("RANGED: ", weaponOffsetX+ 117, stringOffsetY);
		g.drawRoundRect(weaponOffsetX+175, 0, 33, 32, 5, 5);
		
		g.setColor(Color.red);
		g.drawString("LEVEL: " + level.num_level , 590, 510);
		g.drawString("FPS: " + renderFPS, 600, 540);
		
		if(!inGame)
		{
			g.setColor(new Color(1,1,1,0.3f));
			g.fillRect(0, 0, VIEWPORT_SIZE.width, VIEWPORT_SIZE.height);
			g.setColor(Color.white);
			g.drawString("PAUSED", (VIEWPORT_SIZE.width/2)-25, VIEWPORT_SIZE.height/2);
		}
		
		if(player.isDead()){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, VIEWPORT_SIZE.width, VIEWPORT_SIZE.height);
			g.setColor(Color.RED);
			g.drawString("YOU ARE DEAD", (VIEWPORT_SIZE.width/2)-25, VIEWPORT_SIZE.height/2);

		}

		g = this.getGraphics();
		g.drawImage(screen, 0, 0, VIEWPORT_SIZE.width, VIEWPORT_SIZE.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();//reset the image each tick
	}
	
	public void doATurn()
	{
		for(int j = 0; j < entities.size(); j++)
		{
			Entity otherobj = entities.get(j);
			
			otherobj.takeTurn();
			
			//System.out.println("Someone took a turn, " + otherobj.x + " " + otherobj.y);
		}
		
	}
	
	public void run() 
	{
		screen = createVolatileImage(pixel.width,pixel.height);
		long last_loop_time = System.nanoTime();
		while(running)
		{
			long now = System.nanoTime();
			long update_length = now - last_loop_time;
			last_loop_time = now;
			
			double delta = update_length / (double)OPTIMAL_TIME; 
			lastFPSTIME += update_length;
			fps++;
			if(lastFPSTIME >= 1000000000)
			{ 
				renderFPS = fps;
				fps = 0;
				lastFPSTIME = 0;
			}
			
			tick(delta);
			render();
			if(!WaitForPlayer)
			{
				doATurn();
				level.enemiesMove();
				//level.moveEnemies();
				WaitForPlayer = true;
				
			}
			//doATurn();
			try//give the thread some time to calculate
			{
				Thread.sleep((last_loop_time - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			}
			catch(Exception e)
			{
				//System.out.println(e.getMessage());
			}
		}
	}
	

}
