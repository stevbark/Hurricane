package net.anorrah.items.bonus;

import net.anorrah.Entity;

public class regenerationBonus extends bonus {

	private int healATurn;
	public regenerationBonus(int healAmount, int totalTurns)
	{
		isTemp = true;
		healATurn = healAmount;
		turnsLeft = totalTurns;
	}
	
	public void onTurn(Entity user)
	{
		user.heal(healATurn);
		System.out.println("healed:" + healATurn+ " playerHealth:" + user.getHealth());
	}
	
}
