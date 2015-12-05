package net.anorrah.items.bonus;

import net.anorrah.Core;
import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class knockbackWeaponBonus extends bonus {

	
		// knockback moves enemy away one sqaure only if they are next to character.
		public void onAttack(Entity user, Entity enemy, damageObject damage)
		{
			int vectorX = (int) (enemy.getX()-Core.player.getlocationX());
			int vectorY = (int) (enemy.getY()-Core.player.getlocationY());
			System.out.println("vector X:" + vectorX + "vector Y:" + vectorY);
			if(vectorX<=1 &&vectorX>=-1&&vectorY<=1&&vectorY>=-1)
			{
				enemy.move(vectorX, vectorY);
			}
				
			
		}
		
	

	
}
