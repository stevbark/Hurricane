package net.anorrah;

public class Room {
	public Level lev;
	public double levelNum = 0;
	private int chanceEmpty = 70;
	private int chanceEnemy = 80;
	private int chanceBlocked = 95;
	//private int chanceTrap = 100; not needed
	
	public Room(int levId, double levelnumber){
		lev = new Level(levId);
		levelNum = levelnumber;
		if (levelNum > 30)
			levelNum = 30;
	}
	
	public void makeRoom()
	{
		for(int x = 0; x < lev.width; x++)
		{
			for(int y = 0; y < lev.height; y++)
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
