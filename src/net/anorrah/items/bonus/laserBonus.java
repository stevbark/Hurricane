package net.anorrah.items.bonus;

import net.anorrah.Core;
import net.anorrah.EnemyEntities;
import net.anorrah.Entity;
import net.anorrah.items.damageObject;
import net.anorrah.items.damageObject.Type;

public class laserBonus extends bonus{
	
private int weaponDamage;
	
	public laserBonus(int damage)
	{
		
		this.weaponDamage = damage;
	}

	public void onAttackPosition(Entity user,int targetX, int targetY)
	{
		int x = user.getlocationX();
		int y = user.getlocationY();
		int xSpeed =targetX-x;
		xSpeed =(int) Math.signum(xSpeed);
		int ySpeed =targetY-y;
		ySpeed=(int) Math.signum(ySpeed);
		System.out.println("ranged xSpeed: " +xSpeed + " ySpeed: " + ySpeed );
		

		
		while(!outOfBounds(x,y) )
		{
			
				x+=xSpeed;
				y+=ySpeed;
				
				for(EnemyEntities bad : Core.level.enemies)
				{
					if (bad.getlocationX() == x && bad.getlocationY()== y)
					{	
						hitEffects(user,bad,x,y);
					}
				}
			
		}
		
		System.out.println("shot from X: " + user.getlocationX() + " Y: " + user.getlocationY());
		
		System.out.println("ranged hit X: " + x + " Y: " + y);
		
		
		
		
		
	//	hitEffects(user,x,y);
	}
	
	protected void hitEffects(Entity user, Entity target, int x, int y)
	{
		target.takeDamage(new damageObject(weaponDamage,Type.dark));//takeDamage(new damageObject(weaponDamage,Type));
	}
	
}
