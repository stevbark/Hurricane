package net.anorrah;

public class GABonus extends bonus {

	public void onDeath(Entity user)
	{
		user.heal(user.maxHealth);
	}
}
