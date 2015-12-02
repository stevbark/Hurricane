package net.anorrah;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Room {
	public int levelNum = 0;
	public int roomid = -1;
	private double chanceEmpty = 70;
	private double chanceEnemy = 35;
	private double chanceBlocked = 15;
	
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
				
					if(dice > chanceEmpty-levelNum)
					{
						//place an empty tile at [x,y] 
					}
					else if(dice > chanceEnemy- Math.ceil(levelNum/2)){
						//place an enemy  at [x,y](roll again for enemy type)

					}
					else if(dice > chanceBlocked-Math.ceil(levelNum/2)){
						Rectangle rec = new Rectangle();
						int[] sol = {1,1};
						Solid block = new Solid(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.item);
						blocks.add(block);
					}
					else {
						//place a trap tile at [x,y]
						
					}
				
			}
			
		}
	}
	
	public void render()
	{
		
	}

}
