package net.anorrah;

public abstract class bonus {
	
	protected boolean isTemp= false;
	protected int turnsLeft;
	

	public void onBeenHit(Entity user, Entity enemy, damageObject damage)
	{
		
	}
	
	public void onDeath(Entity user)
	{
		
	}
	
	public void invisible()
	{
		
	}
	
	final public void doOnTurn(Entity user)
	{
		if(isTemp)
		{
			onTurn(user);
			turnsLeft--;
			if(turnsLeft<=0)
			{
				Core.player.removeFromList(this);
			}
				
		}
	}
	
	public void onTurn(Entity user)
	{
		
	}
	
	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		
	}
	
	public void onAttackPosition(Entity user,int x, int y)
	{
		
	}

	public void onUseOnSelf(Entity user) {
		// TODO Auto-generated method stub
		
	}

	public void onUnequipped(Entity user) {
		// TODO Auto-generated method stub
		
	}
	
	// do something special at time of bonus
	public void onEquipped(Entity user) {
		// TODO Auto-generated method stub
		
	}
	
	protected void explode(Entity user, bonus effect, int x, int y)
	{
		for(int xradius = x-1;xradius<=x+1;xradius++)
		{
			for(int yradius = y-1;yradius<=y+1;yradius++)
			{
				
				System.out.println("explode hit X: " + xradius + " Y: " + yradius);
			}
		}
	}
}
