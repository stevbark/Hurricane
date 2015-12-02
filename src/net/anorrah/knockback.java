package net.anorrah;

public class knockback extends bonus {

	// knockback moves enemy away one sqaure only if they are next to character.
	public void onBeenHit(enemyEntities enemy, damageObject damage)
	{
		int vectorX = enemy.tX-Core.player.tX;
		int vectorY = enemy.tY-Core.player.tY ;
			System.out.println("vector X:" + vectorX + "vector Y:" + vectorY);
		if(vectorX<=1 &&vectorX>=-1&&vectorY<=1&&vectorY>=-1)
		{
			enemy.move(vectorX, vectorY);
		}
			
		
	}
	
}
