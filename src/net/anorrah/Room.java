package net.anorrah;

public class Room {
	public int levelNum = 0;
	public int roomid = -1;
	private double chanceEmpty = 0.70;
	private double chanceEnemy = 0.80;
	private double chanceBlocked = 0.95;
	
	private boolean hasExit = false;
	private boolean isStart = false;
	private boolean hasPlayer = false;
	public boolean cleared = true;
	
	public int left, right, up, down;//adjacent rooms
	//private int chanceTrap = 100; not needed
	
	public Room(int levelnumber, int roomid)
	{
		levelNum = levelnumber;
		this.roomid = roomid;
		if (levelNum > 30)//cap to 30
			levelNum = 30;
	}
//-------------------------------------------Adjacent Rooms--------------------------------------------------	
	public void assignLeft(int i)
	{
		left = i;
	}
	public void assignRight(int i)
	{
		right = i;
	}
	public void assignUp(int i)
	{
		up = i;
	}
	public void assignDown(int i)
	{
		down = i;
	}
	
	public int getLeft()
	{
		return left;
	}
	public int getRight()
	{
		return right;
	}
	public int getUp()
	{
		return up;
	}
	public int getDown()
	{
		return down;
	}
	
//-------------------------------------Boolean checks---------------------------------------------------------
	public boolean containsPlayer()
	{
		return hasPlayer;
	}
	
	public boolean isStartRoom()
	{
		return isStart;
	}
	
	public boolean containsExit()
	{
		return hasExit;
	}
	
	public void thisistheexit_YN(boolean yn)
	{
		hasExit = yn;
	}
	
	public void thisisthestart_YN(boolean yn)
	{
		isStart = yn;
	}
	
	public void player_YN(boolean yn)
	{
		hasPlayer = yn;
	}
//-----------------------------------------Room content generation-------------------------------------------
	public void makeRoom()
	{
		//assign left, right, up, down if they exist
		for(int x = 0; x < Level.width; x++)
		{
			for(int y = 0; y < Level.height; y++)
			{
				int dice = (int) (Math.random()*100)+1;
				
					if(dice < chanceEmpty-levelNum)
					{
						//place an empty tile at [x,y] 
					}
					else if(dice < chanceEnemy- Math.ceil(levelNum/2)){
						//place an enemy  at [x,y](roll again for enemy type)

					}
					else if(dice<chanceBlocked-Math.ceil(levelNum/2)){
						//place a wall tile at [x,y]

					}
					else {
						//place a trap tile at [x,y]
						
					}
				
			}
			
		}
		
		
		
	}

}
