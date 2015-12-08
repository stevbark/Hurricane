package net.anorrah.items.bonus;

import net.anorrah.Core;
import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class TeleportBonus extends bonus {

	public void onBeenHit(Entity user, Entity enemy, damageObject damage)
	{
		int teleSeed = ((int) (Math.random()*100));
		if(teleSeed<15)
		{
			Core.player.setTilePosition( (int)user.getX() + ((int) (Math.random()*10))-5, (int) user.getY() + ((int) (Math.random()*10))-5);
		}
	}
	
	public String description()
	{
		return "when the wearer takes damage, the wearer has a 15 percent chance to teleport to a random square within 5 squares of wearer’s current location. This cannot teleport the wearer into or through walls, enemies or other obstacles.";
			
	} 
	 

	
}
