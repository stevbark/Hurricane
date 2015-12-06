package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;
import net.anorrah.items.damageObject.Type;

public class ImOnFire extends bonus {

	private damageObject damage;
	
	
	public ImOnFire(int damage, int duration)
	{
		this.damage = new damageObject(damage,Type.fire);
		isTemp = true;
		turnsLeft = duration;
	}
	
	 
	public void onTurn(Entity user)
	{
		user.takeDamage(damage);
	}
	
	
}
