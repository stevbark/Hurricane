package net.anorrah;

public class EnfeeblementBonus extends bonus{

	public void onAttack(enemyEntities enemy,damageObject damage, boolean onHit)
	{
		
		if( ((int) (Math.random()%2) )==0)
		{
			enemy.addToList(new feebleBonus());
		}
		
	}
	
}
