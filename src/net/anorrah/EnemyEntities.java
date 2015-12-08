package net.anorrah;

import java.awt.Graphics;
import java.awt.Image;

import net.anorrah.items.MeleeWeaponItem;
import net.anorrah.items.RangedWeaponItem;
import net.anorrah.items.damageObject;

public class EnemyEntities extends Entity {

	protected Core gk;
	public int Rx,Ry, tX, tY;
	
	protected final int max_Xdistance;
	protected final int max_Ydistance;
	private int[] currentImage;
	protected Image image;
	
	public int time = 0;
	
	double hp;
	private boolean turned;
	
	public EnemyEntities(Core gk, int[] enemyType, double x, double y, int width, int height)
	{
		super(enemyType,x,y,width,height);
		tX = (int) x/Tile.size;
		tY = (int) y/Tile.size;
		Rx = (int) tX*Tile.size;
		Ry = (int) tY*Tile.size;
		max_Xdistance = gk.level.width;
		max_Ydistance = gk.level.height;
		this.gk = gk;
		currentImage = enemyType;
		health=10;
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
		else canAttack();
		turned = false;

	}
	
	public boolean canMove(int i, int j)
	{
	//	if(turned)
		{
			System.out.println("\n enemy Currently at:\t" + tX + " " + tY);
			if(i < 0 || j < 0 || i >= max_Xdistance || j >= max_Ydistance)
			{
				return false;
			}
			else if((gk.level.solid[i][j].id == Tile.blank)  && (Core.level.checkIfEnemyCanMove(i,j)))
			{
				System.out.println("\n enemy Moved to:\t" + i + " " + j);
				return true;
			}
		}
		return false;
	}
	
	public void canAttack()
	{
		//int enemyX = (int) x/Tile.size;
		//int enemyY = (int) y/Tile.size;
		//System.out.println("Enemy is here: (" + x + ", " + y + ")");
		if(gk.player.tX == tX-1 && gk.player.tY == tY)
		{
			attack();
		}
		else if(gk.player.tX == tX && gk.player.tY == tY-1)
		{
			attack();
		}
		else if(gk.player.tX == tX+1 && gk.player.tY == tY)
		{
			attack();
		}
		else if(gk.player.tX == tX && gk.player.tY == tY+1)
		{
			attack();
		}
	}
	
	public void attack()
	{
		damageObject damage = new damageObject(3, damageObject.Type.physical);
		
		Core.player.onHit(this,damage);
	}
	
	public void setImage(int[] id)
	{
		image = Tile.characters.getSubimage(id[0] * Tile.size, id[1] * Tile.size, width, height);
	}
	
	public void render(Graphics g)
	{
		setImage(currentImage);
		g.drawImage(image, Rx, Ry, null);
	}
	
	//Look for the player if you are not next to them. If you are attack instead of move
	public void takeTurn() 
	{

		int[] c = Core.level.findPathTowardsPlayer(tX, tY);
		if(c != null)
		{
			move(c[0],c[1]);
		}
		else canAttack();
	
	}
	
	public void on_death() 
	{
		//Core.level.enemies.remove(this);
		Core.level.removeEnemyFromRoom(this);
	}
	
	public int getlocationX()
	{
		return tX;
	}
	
	public int getlocationY()
	{
		return tY;
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
