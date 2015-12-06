package net.anorrah;

import net.anorrah.items.damageObject;

public class tempEnemy extends EnemyEntities{



	
	public tempEnemy(Core gk, double x, double y, int width, int height)
	{
		
		super(gk,x,y,width,height);

	

	}

	@Override
	public void on_collided(Entity entity) {
		
		// TODO Auto-generated method stub
		
	}
	
	
//	public boolean canMove(int i, int j)
//	{
//		System.out.println("\nCurrently at:\t" + tX + " " + tY);
//		if(i < 0 || j < 0 || i >= super.max_Xdistance || j >= super.max_Ydistance)
//		{
//			return false;
//		}
//		else if(gk.level.item[i][j].id != Tile.blank)
//		{
//			return false;
//		}
//		
//		else if(gk.level.solid[i][j].id == Tile.blank)
//		{
//			return true;
//		}
//		return false;
//	}
	
	public void canAttack()
	{
		if(gk.player.tX == 10 && gk.player.tY == 7)
		{
			attack();
		}
	}
	
	public void attack()
	{
		damageObject damage = new damageObject(10, damageObject.Type.physical);
		
		gk.player.onHit(this,damage);
		
		
	}
	
//	public void move(int i, int j)
//	{
//		if(canMove(i,j))
//		{
//			Rx = tX*32;
//			Ry = tY*32;
//			System.out.println("enemy moved to x:" + tX + " y:" +tY);
//		}
//	}

	@Override
	public void takeTurn() 
	{
		canAttack();		
	}

	@Override
	public void on_death() {
		// TODO Auto-generated method stub
		
	}
}
