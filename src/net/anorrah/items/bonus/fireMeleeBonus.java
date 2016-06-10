package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class fireMeleeBonus extends bonus {

	public fireMeleeBonus(int damage)
	{
	}
	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		
		int randomSeed = ((int) (Math.random()*100))%4;
		if(randomSeed == 0)
		{
			enemy.addToList(new ImOnFire(5 ,3) );
		}
	}
	
	public String description()
	{
		return "Fire: sets enemy on fire dealing 5 damage for 3 turns ";
		
	} 
	
}
