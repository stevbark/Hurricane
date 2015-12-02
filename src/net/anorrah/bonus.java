package net.anorrah;

public abstract class bonus {
	
	protected boolean isTemp= false;
	protected int turnsLeft;

	public void onBeenHit(enemyEntities enemy, damageObject damage)
	{
		
	}
	
	public void onDeath()
	{
		
	}
	
	final public void doOnTurn()
	{
		if(isTemp)
		{
			turnsLeft--;
			if(turnsLeft<=0)
			{
				Core.player.removeFromList(this);
			}
				
		}
	}
	
	public void onTurn()
	{
		
	}
	
	public void onAttack(enemyEntities enemy,damageObject damage, boolean onHit)
	{
		
	}
	
	public void onAttackPosition(int x, int y)
	{
		
	}

	public void onUseOnSelf() {
		// TODO Auto-generated method stub
		
	}

	public void onUnequipped() {
		// TODO Auto-generated method stub
		
	}
	
	// do something special at time of bonus
	public void onEquipped() {
		// TODO Auto-generated method stub
		
	}
}
