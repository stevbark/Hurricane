package net.anorrah;

public class DuelingBonus extends bonus{
	
	public void onBeenHit(Entity user, Entity enemy, damageObject damage)
	{
		int vectorX =  enemy.getlocationX()-user.getlocationX();
		int vectorY =  enemy.getlocationY()-user.getlocationY() ;
			System.out.println("vector X:" + vectorX + "vector Y:" + vectorY);
		if(vectorX<=1 &&vectorX>=-1&&vectorY<=1&&vectorY>=-1)
		{
			
		}
		else
		{
			damage.damage = 0;
		}
	}

}
