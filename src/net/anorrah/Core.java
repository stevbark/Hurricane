package net.anorrah;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;
// head 17 width 21
public class Core extends Applet implements Runnable 
{
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	public static final int res = 1;//pixel to frame
	public static double offset_Y = 0, offset_X = 0;
	public static int dir = 0;
	public static boolean moving = false;
	public static boolean running = false;
	public static boolean inGame = true;
	
	public final int TARGET_FPS = 60;
	public final long OPTIMAL_TIME = 1000000000/TARGET_FPS;
	public static long lastFPSTIME = 0;
	public static int fps = 0;
	public static int renderFPS = 0;
	
	public static boolean bW, bS, bA, bD, bE, bESC, bP;
	
	private Image screen;
	public static Dimension VIEWPORT_SIZE = new Dimension(672,544);
	public static Dimension pixel = new Dimension(VIEWPORT_SIZE.width,VIEWPORT_SIZE.height);
	public static Dimension Size;
	
	public static String name = "Prisoner of Anorrah";
	
	public static Level level;
	
	public static EntityPlayer player;
	public static Core t;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Entity> removethese = new ArrayList<Entity>();
	
	public static int offset_MAX_X, offset_MAX_Y, offset_MIN_X = 0, offset_MIN_Y = 0;
	public static Rectangle camera = new Rectangle(0,0,VIEWPORT_SIZE.width,VIEWPORT_SIZE.height);
	//Constructor
	public Core()
	{
		setPreferredSize(VIEWPORT_SIZE);
		addKeyListener(new InputManager());
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
		level = new Level(1);
		new Tile();
		
		offset_MAX_X = level.width - VIEWPORT_SIZE.width;
		offset_MAX_Y = level.height - VIEWPORT_SIZE.height;
		
		initEntities();
		//player.setTilePosition(20, 20);
		running = true;
		new Thread(this).start();
	}
	
	public void stop()
	{
		running = false;
	}
	
	public void initEntities()
	{
		player = new EntityPlayer(t, 
				(VIEWPORT_SIZE.width / 2) - (Tile.size / 2) + offset_X,
				(VIEWPORT_SIZE.height / 2) - (Tile.size / 2) + offset_Y,
				Tile.size,
				Tile.size);
		entities.add(player);
	}
	
	public void remove(Entity entity)
	{
		removethese.add(entity);
	}
	
	public void tick(double delta)
	{
		frame.pack();
		
		if(inGame)
		{
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
		
		entities.remove(removethese);
		removethese.clear();
	}
	
	
	public void render()
	{
		Graphics g = screen.getGraphics();
		level.render(g, (int)(offset_X), (int)(offset_Y), (pixel.width/Tile.size), (pixel.height/Tile.size));
		
		g.setColor(Color.orange);
		g.drawString("offset_X: " + (int)offset_X , 590, 510);
		g.drawString("offset_Y: " + (int)offset_Y , 590, 525);
		g.drawString("FPS: " + renderFPS, 600, 540);
		
		//g.translate(-(player.Rx - VIEWPORT_SIZE.width/2 + Tile.size/2),-(player.Ry - VIEWPORT_SIZE.height/2 + Tile.size/2));
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).render(g);
		}
		
		g = this.getGraphics();
		g.drawImage(screen, 0, 0, VIEWPORT_SIZE.width, VIEWPORT_SIZE.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();//reset the image each tick
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
			
			try//give the thread some time to calculate
			{
				Thread.sleep((last_loop_time - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage() + " lol");
			}
		}
	}
	

}
