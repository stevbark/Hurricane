package net.anorrah;

import net.anorrah.damageObject.Type;

public class rangedBonus extends bonus {

	
	
	
	public void onAttackPosition(Entity user,int targetX, int targetY)
	{
		int x = user.getlocationX();
		int y = user.getlocationY();
		int xSpeed =targetX-x;
		xSpeed =(int) Math.signum(xSpeed);
		int ySpeed =targetY-y;
		ySpeed=(int) Math.signum(ySpeed);
		System.out.println("ranged xSpeed: " +xSpeed + "ySpeed: " + ySpeed );
		while(!outOfBounds(x,y) &&canMove(x+xSpeed,y+ySpeed)&& (x!=targetX||y!=targetY))
		{
			if(canMove(x+xSpeed,y+ySpeed))
			{
				x+=xSpeed;
				y+=ySpeed;
				
			}
		}
		if(x==targetX&&y==targetY)
		{
			System.out.println("ranged hit X: " + x + " Y: " + y);
		}
		else
		{
			System.out.println("ranged miss... Hit:X " + x + " Y: "+ y);
		}
		
		explode(user,new ImOnFire(new damageObject(10,Type.fire),3),x,y);
		
		
	}
	
	private boolean outOfBounds(int i,int j)
	{
		return i < 0 || j < 0 || i >= Core.level.width || j >= Core.level.height;

	}
	
	private boolean canMove(int i, int j)
	{
		//System.out.println("\nCurrently at:\t" + tX + " " + tY);
		
		if(i < 0 || j < 0 || i >= Core.level.width || j >= Core.level.height)
		{
			return true;
		}
		else if(Core.level.item[i][j].id != Tile.blank)
		{
			return false;
		}
		
		else if(Core.level.solid[i][j].id == Tile.blank)
		{
			return true;
		}
		return false;
	}
	
	
}
