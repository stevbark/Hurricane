package net.anorrah.items.bonus;

import net.anorrah.Core;
import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class DuelingBonus extends bonus{
	
	public void onBeenHit(Entity user, Entity enemy, damageObject damage)
	{
		int vectorX = (int) (enemy.getlocationX()-Core.player.getlocationX());
		int vectorY = (int) (enemy.getlocationY()-Core.player.getlocationY()) ;
			System.out.println("vector X:" + vectorX + "vector Y:" + vectorY);
		if(vectorX<=1 &&vectorX>=-1&&vectorY<=1&&vectorY>=-1)
		{
			
		}
		else
		{
			damage.damage = 0;
		}
	}
	
	public String description()
	{
		return "Wearer can only take damage from enemies directly adjacent to the wearer. This effect does not apply to ongoing damage (fire, poison, etc.) that originated from a source not adjacent to the wearer.";
			
	} 

}
