package net.anorrah;

public class ImOnFire extends bonus {

	private damageObject damage;
	
	
	public ImOnFire(damageObject damage, int duration)
	{
		this.damage = damage;
		isTemp = true;
		turnsLeft = duration;
	}
	
	 
	public void onTurn(Entity user)
	{
		user.takeDamage(damage);
	}
	
	
}
