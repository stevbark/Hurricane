package net.anorrah;

public class VampiricBonus extends bonus {

	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		user.heal(damage.damage/20);
	}	

}
