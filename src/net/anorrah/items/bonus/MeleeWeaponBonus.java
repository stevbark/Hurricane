package net.anorrah.items.bonus;

import net.anorrah.Entity;
import net.anorrah.items.damageObject;

public class MeleeWeaponBonus extends bonus {

	private int weaponDamage;
	
	public MeleeWeaponBonus(int damage)
	{
		this.weaponDamage = damage;
	}
	
	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		damage.damage +=weaponDamage;
	}
	
}
