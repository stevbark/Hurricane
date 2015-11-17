package net.anorrah;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Entity 
{
	
	protected double x,y;
	protected int width, height, dx,dy;
	protected int id[] = {0,0};
	protected int health;
	protected double moveSpeed;
	protected Image image;
	
	private Rectangle collider;
	private Rectangle other = new Rectangle();
	
	public Entity()
	{
		x = 0;
		y = 0;
		width = 32;
		height = 32;
		moveSpeed = 1;
		health = 100;
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
		collider = new Rectangle((int)x, (int)y, width,height);
	}
	
	public void move(double delta)
	{
		x += (moveSpeed*delta) * dx;
		y += (moveSpeed*delta) * dy;
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
	
	public abstract void on_collided(Entity entity);
}
