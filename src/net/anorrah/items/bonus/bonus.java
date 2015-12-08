package net.anorrah.items.bonus;

import java.util.ArrayList;

import net.anorrah.Core;
import net.anorrah.EnemyEntities;
import net.anorrah.Entity;
import net.anorrah.Tile;
import net.anorrah.items.damageObject;

public abstract class bonus {
	
	public boolean isTemp= false;
	protected int turnsLeft;
	

	public void onBeenHit(Entity user, Entity enemy, damageObject damage)
	{
		
	}
	
	public void onDeath(Entity user)
	{
		
	}
	
	public void invisible(Entity user)
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
				user.removeFromList(this);
			}
				
		}
	}
	
	public void onTurn(Entity user)
	{
		
	}
	
	// used for Melee Attacks since 
	public void onAttack(Entity user, Entity enemy,damageObject damage, boolean onHit)
	{
		
	}
	// used for Ranged Attacks since ranged attacks can be blocked by obstacles.
	
	public void onAttackPosition(Entity user,int targetX, int targetY)
	{
		
	}
	
	protected boolean outOfBounds(int i,int j)
	{
		return i < 0 || j < 0 || i >= Core.level.width || j >= Core.level.height;

	}
	
	protected boolean canMove(int i, int j)
	{
		//System.out.println("\nCurrently at:\t" + tX + " " + tY);
		
		if(i < 0 || j < 0 || i >= Core.level.width || j >= Core.level.height)
		{
			return true;
		}
		else if(Core.level.item[i][j].id != Tile.blank)
		{
			return false;
		}
		else if(Core.level.solid[i][j].id == Tile.blank)
		{
			
			return true;
		}
		return false;
	}
	
	
	
	protected void hitEffects(Entity user, int x, int y)
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
	
	public String description()
	{
		return "";
		
	}
	
	protected void explode(Entity user, bonus effect, int x, int y, int radius)
	{
		for(int xradius = x-radius;xradius<=x+radius;xradius++)
		{
			for(int yradius = y-radius;yradius<=y+radius;yradius++)
			{
				effect.onAttackPosition(user, xradius, yradius);
				//System.out.println("explode hit X: " + xradius + " Y: " + yradius);
			}
		}
	}
}
