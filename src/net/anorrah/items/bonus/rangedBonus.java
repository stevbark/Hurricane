package net.anorrah.items.bonus;

import java.util.ArrayList;

import net.anorrah.Core;
import net.anorrah.EnemyEntities;
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
		
//		for(EnemyEntities cur:m)
//		{
//			System.out.println("enemy is " + cur);
//		}
		
		while(!outOfBounds(x,y) &&canMove(x+xSpeed,y+ySpeed)&& (x!=targetX||y!=targetY))
		{
			if(canMove(x+xSpeed,y+ySpeed))
			{
				x+=xSpeed;
				y+=ySpeed;
				
			}
		}
		
		System.out.println("shot from X: " + user.getlocationX() + " Y: " + user.getlocationY());
		
		
		
		
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
