package net.anorrah.items.bonus;

import java.util.ArrayList;

import net.anorrah.Core;
import net.anorrah.EnemyEntities;
import net.anorrah.Entity;
import net.anorrah.Tile;
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
		
		
		//player_room_num
		for(Entity e :Core.t.entities)
		{
			if(e.getlocationX()==x &&e.getlocationY()==y)
			{
				e.takeDamage(new damageObject(weaponDamage,Type.physical));
			}
		}
	}
	
//	public void onAttackPosition(Entity user,int targetX, int targetY)
//	{
//		
//		
//		//explode(user,new ImOnFire(new damageObject(10,Type.fire),3),x,y);
//		
//		
//	}
	

	
	
}
