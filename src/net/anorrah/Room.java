package net.anorrah;

public class Room {
	public int levelNum = 0;
	public int roomid = -1;
	private double chanceEmpty = 0.70;
	private double chanceEnemy = 0.80;
	private double chanceBlocked = 0.95;
	
	public boolean hasExit = false;
	public boolean isStart = false;
	public boolean hasPlayer = false;
	public boolean cleared = true;
	
	public int left=0, right=0, up=0, down=0;//adjacent rooms
	//private int chanceTrap = 100; not needed
	
	public Room(int levelnumber, int roomid)
	{
		levelNum = levelnumber;
		this.roomid = roomid;
		if (levelNum > 30)//cap to 30
			levelNum = 30;
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
	
	public void render()
	{
		
	}

}
