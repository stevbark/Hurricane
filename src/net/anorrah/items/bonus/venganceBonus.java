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
	public String description()
	{
		return "if the wearer is successfully hit, the wearer has a 50 percent chance to ignore half the damage and deal half the damage back to the attacker. this effect is applied after damage reduction.";
			
	} 
	 

}
