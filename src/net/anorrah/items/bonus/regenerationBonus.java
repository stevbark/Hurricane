package net.anorrah.items.bonus;

import net.anorrah.Entity;

public class regenerationBonus extends bonus {

	private int healATurn;
	public regenerationBonus(int healAmount)
	{
		isTemp = true;
		healATurn = healAmount;
		turnsLeft = 5;
	}
	
	public void onTurn(Entity user)
	{
		user.heal(healATurn);
		System.out.println("healed:" + healATurn+ " playerHealth:" + user.getHealth());
	}
	
}
