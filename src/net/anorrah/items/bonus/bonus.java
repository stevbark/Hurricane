package net.anorrah.items.bonus;

import net.anorrah.Core;
import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public abstract class bonus {
	
	public boolean isTemp= false;
	protected int turnsLeft;
	

	public void onBeenHit(Entity user, Entity enemy, damageObject damage)
	{
		
	}
	
	public void onDeath(Entity user)
	{
		
	}
	
	final public void doOnTurn(Entity user)
	{
		if(isTemp)
		{
			onTurn(user);
			turnsLeft--;
			if(turnsLeft<=0)
			{
				Core.player.removeFromList(this);
			}
				
		}
	}
	
	public void onTurn(Entity user)
	{
		
	}
	
	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		
	}
	
	public void onAttackPosition(Entity user,int x, int y)
	{
		
	}

	public void onUseOnSelf(Entity user) {
		// TODO Auto-generated method stub
		
	}

	public void onUnequipped(Entity user) {
		// TODO Auto-generated method stub
		
	}
	
	// do something special at time of bonus
	public void onEquipped(Entity user) {
		// TODO Auto-generated method stub
		
	}
}
