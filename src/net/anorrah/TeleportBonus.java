package net.anorrah;

public class TeleportBonus extends bonus {

	public void onBeenHit(Entity user, Entity enemy, damageObject damage)
	{
		int teleSeed = ((int) (Math.random()*100));
		if(teleSeed<15)
		{
			Core.player.setTilePosition( (int)user.x + ((int) (Math.random()*10))-5, (int) user.y + ((int) (Math.random()*10))-5);
		}
	}
	
}
