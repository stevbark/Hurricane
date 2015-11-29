package net.anorrah;

public class TeleportBonus extends bonuses {

	public void effect(enemyEntities enemy, damageObject damage)
	{
		int teleSeed = ((int) (Math.random()*100));
		if(teleSeed<15)
		{
			Core.player.setTilePosition(Core.player.tX + ((int) (Math.random()*10))-5, Core.player.tY + ((int) (Math.random()*10))-5);
		}
	}
	
}
