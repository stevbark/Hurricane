package net.anorrah;

public class regenerationBonus extends bonus {

	private int healATurn;
	public regenerationBonus(int healAmount)
	{
		isTemp = true;
		healATurn = healAmount;
		turnsLeft = 5;
	}
	
	public void onTurn()
	{
		Core.player.heal(healATurn);
		System.out.println("healed:" + healATurn+ " playerHealth:" + Core.player.getHealth());
	}
	
}
