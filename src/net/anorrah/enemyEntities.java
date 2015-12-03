package net.anorrah;

public abstract class enemyEntities extends Entity {

	protected Core gk;
	public static int Rx,Ry, tX, tY;
	
	protected final int max_Xdistance;
	protected final int max_Ydistance;
	
	double hp;
	
	public enemyEntities(Core gk, double x, double y, int width, int height)
	{
		super(Tile.playertile_DOWN,x,y,width,height);
		tX = 10;
		tY = 6;
		max_Xdistance = gk.level.width;
		max_Ydistance = gk.level.height;
		this.gk = gk;
	}
	@Override
	public void on_collided(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	public abstract void takeTurn();

	
	public void move(int i, int j)
	{
		if(canMove(i,j))
		{
			tX =i;
			tY=j;
			Rx = tX*32;
			Ry = tY*32;
			System.out.println("enemy moved to x:" + tX + " y:" +tY);
		}
	}
	
	public boolean canMove(int i, int j)
	{
		System.out.println("\n enemy Currently at:\t" + tX + " " + tY);
		if(i < 0 || j < 0 || i >= max_Xdistance || j >= max_Ydistance)
		{
			return false;
		}
		else if(gk.level.item[i][j].id != Tile.blank)
		{
			return false;
		}
		
		else if(gk.level.solid[i][j].id == Tile.blank)
		{
			return true;
		}
		return false;
	}
	
	
	
	
}
