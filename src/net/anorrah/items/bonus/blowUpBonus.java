package net.anorrah.items.bonus;

import net.anorrah.Core;
import net.anorrah.Entity;
import net.anorrah.items.damageObject;
import net.anorrah.items.damageObject.Type;

// this really isn't a bonus. its the damage and fire for fireball.
public class blowUpBonus extends bonus {

	private int onHitDamage;
	private ImOnFire burnEffect;
	public blowUpBonus(int damage)
	{
		this.onHitDamage = damage;
		this.burnEffect = new ImOnFire(5,3);
		
	}
	
	
	public void onAttackPosition(Entity user,int targetX, int targetY)	
	{
		for(Entity e :Core.t.entities)
		{
			if(e.getlocationX()==targetX &&e.getlocationY()==targetY)
			{
				
				e.takeDamage(new damageObject(onHitDamage,Type.fire));
				e.addToList(burnEffect);
				System.out.println("enemy hit at X:" + targetX + " Y: "+ targetY);
			}
			System.out.println("hit at X:" + targetX + " Y: "+ targetY);
		}
	}
	
}
