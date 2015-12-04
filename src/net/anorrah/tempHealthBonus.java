package net.anorrah;

public class tempHealthBonus extends bonus{

	private int tempHealth;
	
	public tempHealthBonus(int turns, int tempHealth)
	{
		isTemp=true;
		turnsLeft= turns;
		this.tempHealth = tempHealth;
	}
	
	public void onBeenHit(Entity user, Entity enemy, damageObject damage)
	{
		int d = damage.damage;
		if(damage.damage <=tempHealth)
		{
			tempHealth -= damage.damage;
			damage.damage=0;
		}
		else
		{
			damage.damage-=tempHealth;
			tempHealth = 0;
			
		}
		System.out.println("Shields UP! temp Health: "+ tempHealth + " damage is: " + d);
	} 
}
