package net.anorrah.items.bonus;

import net.anorrah.Entity;

public class GABonus extends bonus {

	boolean canBeUsed = true;
	public void onDeath(Entity user)
	{
		if(canBeUsed&&user.health<=0)
		{
			user.heal(user.getMaxHealth());
			canBeUsed=false;
			System.out.println("You were saved by your armor!");
		}
	}
	
	public String description()
	{
		return "When the wearer is reduced to 0 hit points or less, the wearer loses all status effects and is returned to full life in the square they had died immediately after death. If the player is revived in this way, the armor loses its bonus.";
			
	} 
}
