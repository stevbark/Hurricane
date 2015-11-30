package net.anorrah;

public class knockback extends bonuses {

	// knockback moves enemy away one sqaure only if they are next to character.
	public void effect(enemyEntities enemy, damageObject damage)
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
