package net.anorrah;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import net.anorrah.items.*;

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
	public static boolean itempicked = false;
	public static boolean restart = false;
	
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
	private static Item itemtorender;
	private static ItemObject tempholding;
	private static String description;
	
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
	
	public static void item(ItemObject io, String des)
	{
		tempholding = io;
		itempicked = true;
		int[] i = {-1,-1};
		if(io instanceof SwordItem)
			i = Tile.sword_icon;
		else if(io instanceof HammerItem)
			i = Tile.hammer_icon;
		else if(io instanceof SpearItem)
			i = Tile.spear_icon;
		else if(io instanceof AxeItem)
			i = Tile.axe_icon;
		else if(io instanceof WhipItem)
			i = Tile.whip_icon;
		
		else if(io instanceof bowAndArrowItem)
			i = Tile.bow_icon;
		else if(io instanceof FireballRod)
			i = Tile.fireball_icon;
		else if(io instanceof LaserItem)
			i = Tile.laser_icon;
		
		else if(io instanceof potionObject)
			i = Tile.empty_item;
		else if(io instanceof bandAidObject)
			i = Tile.empty_item;
		else if(io instanceof castObject)
			i = Tile.empty_item;
		else if(io instanceof FoodItem)
			i = Tile.empty_item;
		
		else if(io instanceof BerserkerItems)
			i = Tile.empty_item;
		else if(io instanceof runeOfTeleportation)
			i = Tile.empty_item;
		else if(io instanceof ringOfInvisibility)
			i = Tile.empty_item;
		else if(io instanceof pendentOfFleetingHealth)
			i = Tile.empty_item;
		
		itemtorender = new Item(new Rectangle(Level.center_w*32,(Level.center_h-1)*32,32,32),Level.center_w,Level.center_h,i);
		description = des;
	}
	
	public static void itemclear()
	{
		itempicked = false;
		level.item[Level.center_w][Level.center_h].id = Tile.blank;
	}
	
	public static void equipitem()
	{
		if(tempholding instanceof ArmorItem)
		{
			EntityPlayer.setArmor((ArmorItem)tempholding);
		}
		else if(tempholding instanceof MeleeWeaponItem)
		{
			EntityPlayer.setMeleeItem((MeleeWeaponItem)tempholding);
		}
		else if(tempholding instanceof RangedWeaponItem)
		{
			EntityPlayer.setRangeItem((RangedWeaponItem)tempholding);
		}
		else if(tempholding instanceof ItemObject)
		{
			EntityPlayer.setUsableItem(tempholding);
		}
		itemclear();
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
			
			player.changeDirection();
			// player.onUseOnSelf();
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
		for(Entity toRemove:removethese)
		{
			entities.remove(toRemove);
		}
	//	entities.remove(removethese);
		removethese.clear();
		level.getPlayerRoom().cleanup();
		level.cleanup();
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
		
		if(player.health >0){
			g.setColor(Color.RED);
			g.fill3DRect(40, 10, (int)(150*((double)player.getHealth()/(double)player.maxHealth)), 20, false);
		}
		if(!player.isDead()){
			g.setColor(Color.RED);
			g.fill3DRect(40, 10, (int)(150*((double)player.getHealth()/(double)player.maxHealth)), 20, false);
		}
		
		g.setColor(Color.YELLOW);
		g.drawString("HP:   " + player.getHealth() + "/" + player.maxHealth, 17,stringOffsetY);
		g.drawRect(40, 10, 150, 20);
		
		g.drawRect(40, 10, 150, 20);
		
		// Item
		ItemObject usableItem = player.getUsableItem();
		
		g.drawString("ITEM: ",220, stringOffsetY);
		g.drawRoundRect(255, 0, 33, 32, 5, 5);
		
		if(usableItem != null){
			if(usableItem.hasCharges()){
				g.setColor(Color.WHITE);
				g.drawString(" "+usableItem.charges(), 277, 28);
				g.setColor(Color.YELLOW);
			}
			
		}
		
		// Primary Weapon 
		g.drawString("MELEE: ", weaponOffsetX, stringOffsetY);
		g.drawRoundRect(weaponOffsetX+47, 0, 33, 32, 5, 5);
		if(player.meleeEquipped){
			g.setColor(Color.WHITE);
			g.drawString("MELEE: ", weaponOffsetX, stringOffsetY);
			g.drawRoundRect(weaponOffsetX+47, 0, 33, 32, 5, 5);
			g.setColor(Color.YELLOW);
		}
		
		// Secondary Weapon
		g.drawString("RANGED: ", weaponOffsetX+ 117, stringOffsetY);
		g.drawRoundRect(weaponOffsetX+175, 0, 33, 32, 5, 5);
		if(!player.meleeEquipped){
			g.setColor(Color.WHITE);
			g.drawString("RANGED: ", weaponOffsetX+ 117, stringOffsetY);
			g.drawRoundRect(weaponOffsetX+175, 0, 33, 32, 5, 5);
		}
		
		g.setColor(Color.red);
		g.drawString("LEVEL: " + level.num_level , 590, 510);
		g.drawString("FPS: " + renderFPS, 600, 540);
		
		if(itempicked)
		{
			g.setColor(Color.blue);
			g.fillRoundRect(158, 308, 357, 65, 5, 5);
			
			g.setColor(Color.white);
			g.fillRoundRect(318, 222, 36, 36,5,5);
			g.fillRoundRect(160, 310, 352, 60, 5, 5);
			
			itemtorender.render(g);
			String des1 ="";
			String des2 ="";
			String des3 ="";
			g.setColor(Color.black);
			
			
			if(description.length() > 108)
			{
				des1 = description.substring(0, 54);
				des2 = description.substring(54, 108);
				des3 = description.substring(108, description.length()-1);
				
				
				if(description.substring(54).startsWith(" "))
				{
					g.drawString(des1, 162, 322);
					g.drawString(des2, 162, 334);
				}
				else
				{
					g.drawString(des1.concat("-"), 162, 322);
					if(description.substring(108).startsWith(" "))
					{
						g.drawString(des2, 162, 334);
						g.drawString(des3, 162, 346);
					}
					else
					{
						g.drawString(des2.concat("-"), 162, 334);
						g.drawString(des3, 162, 346);
					}
				}
				
				
			}
			else if(description.length() > 54)
			{
				des1 = description.substring(0, 54);
				des2 = description.substring(54, description.length()-1);
				
				if(description.substring(54).startsWith(" "))
				{
					g.drawString(des1, 162, 322);
					g.drawString(des2, 162, 334);
				}
				else
				{
					g.drawString(des1.concat("-"), 162, 322);
					g.drawString(des2, 162, 334);
				}
			}
			else g.drawString(description, 162, 322);
			
		}
		
		
		if(!inGame)
		{
			g.setColor(new Color(1,1,1,0.3f));
			g.setColor(Color.black);
			
			g.fillRect(0, 0, VIEWPORT_SIZE.width, VIEWPORT_SIZE.height);
			
			/*
			g.setColor(Color.white);
			g.drawString("PAUSED", (VIEWPORT_SIZE.width/2)-25, VIEWPORT_SIZE.height/2);
			*/
			

			g.setColor(Color.white);
			// Stats on ESC
			int statsOffsetX = 17;

			String itemTitleOffset = "  ";
			String itemDescOffset = "    ";
			
			g.drawRoundRect(10, 10, VIEWPORT_SIZE.width-20, VIEWPORT_SIZE.height-20, 20, 20);
			g.drawString("STATS: ", statsOffsetX, stringOffsetY);
			g.drawString("HP:   " + player.getHealth() + "/" + player.maxHealth, statsOffsetX,stringOffsetY+20);
			g.drawString(itemTitleOffset + "MELEE WEAPON: ", statsOffsetX, stringOffsetY+40);
			
			// MELEE
			MeleeWeaponItem melee = player.getMeleeItem();
			g.drawString(itemDescOffset+melee.getName(), statsOffsetX+100, stringOffsetY+40);
			g.drawString(itemDescOffset+ "DMG: "+melee.damage, statsOffsetX, stringOffsetY+60);
			g.drawString(itemDescOffset+ melee.itemDescription, statsOffsetX, stringOffsetY+80);

			//bonuses unknown
			//String meleeItemBonuses = player.getMeleeItem().b.
			
			// RANGED
			RangedWeaponItem ranged = player.getRangeItem();
			g.drawString(itemTitleOffset+"RANGED WEAPON:", statsOffsetX, stringOffsetY+120);
			// name, stats, desc
			if(ranged != null){
				g.drawString(itemDescOffset+ranged.getName(), statsOffsetX+110, stringOffsetY+120);
				g.drawString(itemDescOffset+ranged.damage, statsOffsetX, stringOffsetY+140);
				g.drawString(itemDescOffset+ranged.itemDescription, statsOffsetX, stringOffsetY+160);
			}
			// Items
			g.drawString(itemTitleOffset+"ITEM: ", statsOffsetX, stringOffsetY+200);
			// name, stats, desc
			ItemObject item = player.getUsableItem();
			if(item != null){
				g.drawString(itemDescOffset+item.getName(), statsOffsetX+30, stringOffsetY+200);
				g.drawString(itemDescOffset+item.itemDescription, statsOffsetX, stringOffsetY+220);
			}
			// Armor
			g.drawString(itemTitleOffset+"ARMOR: ", statsOffsetX, stringOffsetY+280);
			// name, stats, desc?
			ArmorItem armor = player.getArmorItem();
			if(armor != null){
				g.drawString(itemDescOffset+armor.getName(), statsOffsetX+45, stringOffsetY+280);
				g.drawString(itemDescOffset+armor.itemDescription, statsOffsetX, stringOffsetY+300);
			}
			
			
			
			/*
			g.drawString("ITEM: " ,itemOffsetX+10 , 20);
			if(player.usableitem != null){
				g.drawString("Desc: "+ player.getUsableItem().description(), itemOffsetX+10, 40);
			}
			*/
			

		}
		
		if(player.isDead()){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, VIEWPORT_SIZE.width, VIEWPORT_SIZE.height);
			g.setColor(Color.RED);
			g.drawString("YOU ARE DEAD", (VIEWPORT_SIZE.width/2)-25, VIEWPORT_SIZE.height/2);
			/*
			if(restart){
				t = new Core();
				player = new EntityPlayer(t, 
						(VIEWPORT_SIZE.width / 2) - (Tile.size / 2) + offset_X,
						(VIEWPORT_SIZE.height / 2) - (Tile.size / 2) + offset_Y,
						Tile.size,
						Tile.size);
				
			}
			*/

		}

		g = this.getGraphics();
		g.drawImage(screen, 0, 0, VIEWPORT_SIZE.width, VIEWPORT_SIZE.height, 0, 0, pixel.width, pixel.height, null);
//		removethese.clear();
//		level.getPlayerRoom().cleanup();
//		level.cleanup();
		g.dispose();//reset the image each tick
	}
	
	public void doATurn()
	{
		for(int j = 0; j < entities.size(); j++)
		{
			Entity otherobj = entities.get(j);
			otherobj.takeTurn();
		}
		
		// cleanup and delete dead
//		removethese.clear();
//		level.getPlayerRoom().cleanup();
//		level.cleanup();
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
