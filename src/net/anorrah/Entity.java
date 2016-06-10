package net.anorrah;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import net.anorrah.items.MeleeWeaponItem;
import net.anorrah.items.RangedWeaponItem;
import net.anorrah.items.damageObject;
import net.anorrah.items.bonus.bonus;
import net.anorrah.items.damageObject.Type;

public abstract class Entity 
{
	
	protected double x,y;
	protected static int Rx,Ry, tX, tY;
	protected int width, height, dx,dy;
	protected int id[] = {0,0};
	public int health;
	protected int maxHealth;
	protected double moveSpeed;
	protected Image image;
	
	private Rectangle collider;
	private Rectangle other = new Rectangle();
	
	protected ArrayList<bonus> bonuses = new ArrayList<bonus>();
	protected ArrayList<bonus> toBeRemovedBonuses = new ArrayList<bonus>();
	
	protected boolean isInvisible=false;
	
	public Entity()
	{
		x = 0;
		y = 0;
		width = 32;
		height = 32;
		moveSpeed = 1;
		health = 100;
		maxHealth=100;
		collider = new Rectangle((int)x, (int)y, width,height);
	}
	
	public Entity(int[] i, double x, double y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		id = i;
		moveSpeed = 1;
		
		health = 100;
		maxHealth=100;
		collider = new Rectangle((int)x, (int)y, width,height);
	}
	
	public void attack(EnemyEntities bad)
	{
		
		System.out.println("smacked!" + tX+" " +tY);
		damageObject damage = new damageObject(0, Type.physical);
		for(bonus b:bonuses)
		{
			b.onAttack(this,bad, damage, true);
		}
		bad.takeDamage(damage);
		
	} 
	
	public boolean amIInvisible()
	{
		isInvisible = false;
		for(bonus b: bonuses)
		{
			b.invisible(this);
		}
		return isInvisible;
	}
	
	public void becomeInvisible()
	{
		isInvisible=true;
	}
	
	public void heal(int heal)
	{
		if(health + heal >maxHealth)
		{
			health = maxHealth;
		}
		else
		{
			health+=heal;
		}
	}
	
	public void onHit(Entity enemy, damageObject damage)
	{
		for(bonus b:bonuses)
		{
			b.onBeenHit(this,enemy, damage);
		}
		takeDamage(damage);
	//	equippedArmor.onBeenHit(enemy,damage);
	}
	
	public void takeTurn()
	{
		for(bonus b: bonuses)
		{
			b.doOnTurn(this);
		}
		cleanup();
	}

	public void addToList(bonus toAdd)
	{
		System.out.println(toAdd.isTemp);
		bonuses.add(toAdd);
	}
	
	// we cant remove directly from the list because we may be iteration through it and we will get an exception.
	public void removeFromList(bonus toRemove)
	{
		toBeRemovedBonuses.add(toRemove);
	}
	
	private void cleanup()
	{
		for(bonus b: toBeRemovedBonuses)
		{
			bonuses.remove(b);
		}
		toBeRemovedBonuses.clear();
	}
	
	public void move(double delta)
	{
		x += (moveSpeed*delta) * dx;
		y += (moveSpeed*delta) * dy;
	}
	
	public void move(int i, int j)
	{
		
	}
	
	public void setImage(int[] id)
	{
		image = Tile.characters.getSubimage(id[0] * Tile.size, id[1] * Tile.size, width, height);
	}
	
	public void render(Graphics g)
	{
		setImage(id);
		g.drawImage(image,  (int)x, (int)y, null);
	}
	
	public boolean collides(Entity entity)
	{
		other.setBounds((int)entity.x,(int)entity.y,entity.width,entity.height);
		return collider.intersects(other);
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public int getlocationX()
	{
		return tX;
	}
	public int getlocationY()
	{
		return tY;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public int getHealth()
	{
		return health;
	}
	public int getMaxHealth()
	{
		
		return maxHealth;
	}
	
	public void takeDamage(damageObject damage)
	{
		health -= damage.damage;
		if(health <=0)
		{
			health = 0;
			on_death();
			
		}
		
	}


	
	public abstract void on_collided(Entity entity);

	public void on_death() {
		// TODO Auto-generated method stub
		
	}
	
	
}
