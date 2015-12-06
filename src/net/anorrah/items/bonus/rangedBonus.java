package net.anorrah.items.bonus;

import net.anorrah.Entity;

public abstract class rangedBonus extends bonus {

	public void onAttackPosition(Entity user,int targetX, int targetY)
	{
		int x = user.getlocationX();
		int y = user.getlocationY();
		int xSpeed =targetX-x;
		xSpeed =(int) Math.signum(xSpeed);
		int ySpeed =targetY-y;
		ySpeed=(int) Math.signum(ySpeed);
		System.out.println("ranged xSpeed: " +xSpeed + " ySpeed: " + ySpeed );
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
		
		hitEffects(user,x,y);
	}
	
}
