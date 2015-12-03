package net.anorrah;

import net.anorrah.damageObject.Type;

public class berserkerBoost extends bonus {

	public berserkerBoost()
	{
		isTemp=true;
		turnsLeft = 3;
	}
	
	public void onUseOnSelf(Entity user) 
	{
		user.takeDamage(new damageObject((int) (user.health*.3),Type.physical));
	}
	
	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		damage.damage +=10;
	}
}
