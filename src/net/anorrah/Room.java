package net.anorrah;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Room {
	public int levelNum = 0;
	public int roomid = -1;
	private double chanceEmpty = 70;
	private double chanceEnemy = 35;
	private double chanceBlocked = 30;
	
	public boolean hasExit = false;
	public boolean isStart = false;
	public boolean hasPlayer = false;
	public boolean cleared = true;
	
	public Item item = null;
	public ArrayList<enemyEntities> enemies= new ArrayList<enemyEntities>();
	public ArrayList<Solid> blocks = new ArrayList<Solid>();
	public int left=0, right=0, up=0, down=0;//adjacent rooms
	//private int chanceTrap = 100; not needed
	
	public Room(int levelnumber, int roomid)
	{
		levelNum = levelnumber;
		this.roomid = roomid;
		if (levelNum > 30)//cap to 30
			levelNum = 30;
		
		makeRoom();
	}
	
//-----------------------------------------Room content generation-------------------------------------------
	public void makeRoom()
	{
		//assign left, right, up, down if they exist
		
		
		
		for(int x = 2; x < Level.width-2; x++)
		{
			for(int y = 2; y < Level.height-2; y++)
			{
				int dice = (int) (Math.random()*100)+1;
				if(x != Level.center_w && y != Level.center_h)
				{
					if(dice > chanceEmpty-levelNum)
					{
						//place an empty tile at [x,y] 
					}
					else if(dice > chanceEnemy- Math.ceil(levelNum/2))
					{
						//place an enemy  at [x,y](roll again for enemy type)

					}
					else if(dice > chanceBlocked-Math.ceil(levelNum/2))
					{
						int flip = (int)(Math.random()*2);
						Solid block;
						if(flip == 0)
						{
							block = new Solid(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.pit);
						}
						else
						{
							block = new Solid(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.boulder);
						}
						blocks.add(block);
					}
					else {
						//place a trap tile at [x,y]
						
					}
				}
				else if(roomid != 1 && x == Level.center_w && y == Level.center_h)//no items in room 1
				{
					//determine if an item is to be placed in the room
					float chance = ((float)levelNum/10)+1;
					chance = 1/chance;
					if(Math.random() < chance)
					{
						item = new Item(new Rectangle(Level.center_w*Tile.size, Level.center_h*Tile.size, Tile.size, Tile.size),Level.center_w,Level.center_h,Tile.chest_closed);
					}
				}
			}
			
		}
	}
	
	public void render()
	{
		
	}

}
