package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class venganceBonus extends bonus {
	
	public void onBeenHit(Entity user,Entity enemy, damageObject damage)
	{
		int doIReflect = ((int) (Math.random()*100))%2;
		{
			switch(doIReflect)
			{
				case 0:
					break;
				case 1:
					damage.damage = damage.damage/2;
					enemy.takeDamage(new damageObject(damage.damage,damage.type));
					break;
			}
		
			
		}
	}

}
