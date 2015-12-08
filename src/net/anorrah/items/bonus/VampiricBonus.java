package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class VampiricBonus extends bonus {

	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		user.heal(3);
	}	
	
	public String description()
	{
		return "vampiric: hitting enemies heals 3 damage to you";
		
	} 

}
