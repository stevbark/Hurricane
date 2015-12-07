package net.anorrah.items.bonus;

import java.util.ArrayList;

import net.anorrah.Core;
import net.anorrah.EnemyEntities;
import net.anorrah.Entity;
import net.anorrah.items.damageObject;
import net.anorrah.items.damageObject.Type;

public class spearBonus extends bonus{

	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		int x = user.getlocationX();
		int y = user.getlocationY();
		int xSpeed =enemy.getlocationX()-x;
		xSpeed =(int) Math.signum(xSpeed);
		int ySpeed =enemy.getlocationY()-y;
		ySpeed=(int) Math.signum(ySpeed);
		int targetX = enemy.getlocationX()+xSpeed;
		int targetY = enemy.getlocationY()+ySpeed;
		ArrayList<Entity> presentEnemies = Core.entities;

		for(Entity e: presentEnemies)
		{
			if(e.getlocationX()==targetX&&e.getlocationY()==targetY)
			{
				e.takeDamage(new damageObject(10,Type.physical));
			}
		}
	}
}
