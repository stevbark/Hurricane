package net.anorrah;

public class venganceBonus extends bonus {
	
	public void onBeenHit(Entity user,Entity enemy, damageObject damage)
	{
		int doIReflect = ((int) (Math.random()*100))%2;
		{
			switch(doIReflect)
			{
				case 0:
					break;
				case 1:
					damage.damage = damage.damage/2;
					enemy.takeDamage(new damageObject(damage.damage,damage.type));
					break;
			}
		
			
		}
	}

}
