package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;
import net.anorrah.items.damageObject.Type;

public class fireMeleeBonus extends bonus {

	private int weaponDamage;
	public fireMeleeBonus(int damage)
	{
		this.weaponDamage = damage;
	}
	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
//		
		int randomSeed = ((int) (Math.random()*100))%4;
		if(randomSeed == 0)
		{
			enemy.addToList(new ImOnFire(5 + (int)(weaponDamage/20),3) );
		}
	}
	
}
