package net.anorrah;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Entity 
{
	
	protected double x,y;
	protected int width, height, dx,dy;
	protected int id[] = {0,0};
	protected int health;
	protected int maxHealth;
	protected double moveSpeed;
	protected Image image;
	
	private Rectangle collider;
	private Rectangle other = new Rectangle();
	
	protected ArrayList<bonus> bonuses = new ArrayList<bonus>();
	protected ArrayList<bonus> toBeRemovedBonuses = new ArrayList<bonus>();
	
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
//		if(canMove(i,j))
//		{
//			tX =i;
//			tY=j;
//			Rx = tX*32;
//			Ry = tY*32;
//			System.out.println("enemy moved to x:" + tX + " y:" +tY);
//		}
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
	
	public void takeDamage(damageObject damage)
	{
		health -= damage.damage;
	}

	
	public abstract void on_collided(Entity entity);
	
	
}
