package net.anorrah;

public class InstantHealBonus extends bonus {

	private int healAmount;
	
	public InstantHealBonus(int heal)
	{
		healAmount = heal;
		isTemp= true;
	}
	
	public void onUseOnSelf()
	{
		Core.player.heal(healAmount);
	}
}
