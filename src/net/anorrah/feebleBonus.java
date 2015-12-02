package net.anorrah;

public class feebleBonus extends bonus{

	// more of a curse. Applied from EnfeeblementBonus. 
	
	public feebleBonus()
	{
		isTemp = true;
		turnsLeft = 1;
	}
	//changes this to work for enemies
	public void onAttack(enemyEntities enemy,damageObject damage, boolean onHit)
	{
		damage.damage = damage.damage/2;
	}
	
}
