package net.anorrah;

public class ArmorBonus extends bonus{

	int reduction=2;
	
	public ArmorBonus(int reduction)
	{
		this.reduction+=reduction;
	}
	
	public void onBeenHit(enemyEntities enemy,damageObject damage)
	{
		damage.damage -= reduction;
		System.out.println("hit for " + damage.damage + " damage");
	}
}
