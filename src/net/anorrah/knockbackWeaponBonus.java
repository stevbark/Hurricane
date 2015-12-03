package net.anorrah;

public class knockbackWeaponBonus extends bonus {

	
		// knockback moves enemy away one sqaure only if they are next to character.
		public void onAttack(Entity user, Entity enemy, damageObject damage)
		{
			int vectorX = (int) (enemy.x-Core.player.tX);
			int vectorY = (int) (enemy.y-Core.player.tY);
			System.out.println("vector X:" + vectorX + "vector Y:" + vectorY);
			if(vectorX<=1 &&vectorX>=-1&&vectorY<=1&&vectorY>=-1)
			{
				enemy.move(vectorX, vectorY);
			}
				
			
		}
		
	

	
}
