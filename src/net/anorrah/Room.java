package net.anorrah;

public class Room {
	public int levelNum = 0;
	private int chanceEmpty = 70;
	private int chanceEnemy = 80;
	private int chanceBlocked = 95;
	
	private boolean hasExit = false;
	private boolean isStart = false;
	private boolean hasPlayer = false;
	//private int chanceTrap = 100; not needed
	
	public Room(int levId, int levelnumber){
		levelNum = levelnumber;
		if (levelNum > 30)//cap to 30
			levelNum = 30;
	}
	
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
	
	public void makeRoom()
	{
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
