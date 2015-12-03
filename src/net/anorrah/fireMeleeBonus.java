package net.anorrah;

import net.anorrah.damageObject.Type;

public class fireMeleeBonus extends bonus {

	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
//		
		
		enemy.addToList(new ImOnFire(new damageObject(5,Type.fire),3) );
	}
	
}
