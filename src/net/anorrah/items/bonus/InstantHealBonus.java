package net.anorrah.items.bonus;

import net.anorrah.Entity;

public class InstantHealBonus extends bonus {

	private int healAmount;
	
	public InstantHealBonus(int heal)
	{
		healAmount = heal;
		isTemp= true;
	}
	
	public void onUseOnSelf(Entity user)
	{
		user.heal(healAmount);
	}
}
