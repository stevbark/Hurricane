package net.anorrah.items.bonus;

import net.anorrah.Core;
import net.anorrah.Entity;
import net.anorrah.items.damageObject;
import net.anorrah.items.damageObject.Type;


public class TeleportFromItemBonus  extends bonus {
	
	public void onUseOnSelf(Entity user) 
	{
		Core.player.setTilePosition( (int)user.getX() + ((int) (Math.random()*10))-5, (int) user.getY() + ((int) (Math.random()*10))-5);
	}

}
