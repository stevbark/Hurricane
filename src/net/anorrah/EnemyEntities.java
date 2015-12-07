package net.anorrah;

import java.awt.Graphics;

import net.anorrah.items.MeleeWeaponItem;
import net.anorrah.items.RangedWeaponItem;
import net.anorrah.items.damageObject;

public class EnemyEntities extends Entity {

	protected Core gk;
	public int Rx,Ry, tX, tY;
	
	protected final int max_Xdistance;
	protected final int max_Ydistance;
	private int[] currentImage;
	public int time = 0;
	
	double hp;
	private boolean turned;
	
	public EnemyEntities(Core gk, double x, double y, int width, int height)
	{
		super(Tile.playertile_DOWN,x,y,width,height);
		tX = (int) x;
		tY = (int) y;
		//System.out.println("Enemy X: "+tX/Tile.size);
		//System.out.println("Enemy Y: "+tY/Tile.size);
		max_Xdistance = gk.level.width;
		max_Ydistance = gk.level.height;
		this.gk = gk;
		currentImage = super.id;
	}
	@Override
	public void on_collided(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	
	public void move(int i, int j)
	{
		if(canMove(i,j))
		{
			tX =i;
			tY=j;
			Rx = tX*32;
			Ry = tY*32;
			//System.out.println("enemy moved to x:" + tX + " y:" +tY);
		}
		turned = false;
	}
	
	public boolean canMove(int i, int j)
	{
		if(turned)
		{
			System.out.println("\n enemy Currently at:\t" + tX + " " + tY);
			if(i < 0 || j < 0 || i >= max_Xdistance || j >= max_Ydistance)
			{
				return false;
			}
			else if((gk.level.solid[i][j].id == Tile.blank)  && (Core.level.canMove(i,j)))
			{
				return true;
			}
		}
		return false;
	}
	
	public void canAttack()
	{
		int enemyX = (int) x/Tile.size;
		int enemyY = (int) y/Tile.size;
		//System.out.println("Enemy is here: (" + x + ", " + y + ")");
		if(gk.player.tX == enemyX-1 && gk.player.tY == enemyY)
		{
			attack();
		}
		else if(gk.player.tX == enemyX && gk.player.tY == enemyY-1)
		{
			attack();
		}
		else if(gk.player.tX == enemyX+1 && gk.player.tY == enemyY)
		{
			attack();
		}
		else if(gk.player.tX == enemyX && gk.player.tY == enemyY+1)
		{
			attack();
		}
	}
	
	public void attack()
	{
		damageObject damage = new damageObject(10, damageObject.Type.physical);
		
		Core.player.onHit(this,damage);
	}
	
	/*public void render(Graphics g)
	{
		currentImage = Tile.fireball_icon;
		super.setImage(currentImage);
		g.drawImage(image, Rx, Ry,null);
		
	}*/
	public void takeTurn() 
	{
		Core.level.findPathTowardsPlayer(tX/Tile.size, tY/Tile.size);
		canAttack();		
	}
	
	public void on_death() 
	{
		Core.level.enemies.remove(this);
		Core.level.removeEnemyFromRoom(this);
	}
	
	public int getlocationX()
	{
		return tX/Tile.size;
	}
	
	public int getlocationY()
	{
		return tY/Tile.size;
	}
	public void updateX(int x) 
	{
		tX = x;	
	}
	public void updateY(int y) 
	{
		tY = y;
	}
	
	public void takeDamage(damageObject damage)
	{
		health -= damage.damage;
		if(health <=0)
		{
			health = 0;
			
			if(health<=0)
			{
				on_death();
			}
		}
		System.out.println("Enemy got hurt! Took " + damage.damage + " Damage. Now has " + health + " health left");
	}
	
}
