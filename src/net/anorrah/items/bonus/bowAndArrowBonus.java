package net.anorrah.items.bonus;

import net.anorrah.Core;
import net.anorrah.Entity;
import net.anorrah.items.damageObject;
import net.anorrah.items.damageObject.Type;

public class bowAndArrowBonus extends rangedBonus {

	
private int weaponDamage;
	
	public bowAndArrowBonus(int damage)
	{
		this.weaponDamage = damage;
	}
	
	protected void hitEffects(Entity user, int x, int y)
	{
		
		
		for(Entity e :Core.t.entities)
		{
			if(e.getlocationX()==x &&e.getlocationY()==y)
			{
				e.takeDamage(new damageObject(weaponDamage,Type.physical));
			}
		}
	}
	

	

	
	
}
