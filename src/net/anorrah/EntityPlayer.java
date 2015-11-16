package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;

public class EntityPlayer extends Entity 
{
	public int moveSpeed = 2;
	public static boolean isMoving = false;
	
	private static int Rx,Ry, tX, tY;
	private static int moveDelta = 0;
	
	public int anim_frame = 0;
	public int anim_time = 20;
	public int anim_DELTA = 0;
	
	/*public static int[][] pIMG_UP = {{0,1},{1,1},{2,1},{3,1}};
	public static int[][] pIMG_DOWN = {{0,0},{1,0},{2,0},{3,0}};
	public static int[][] pIMG_LEFT = {{0,2},{1,2},{2,2},{3,2}};
	public static int[][] pIMG_RIGHT = {{0,3},{1,3},{2,3},{3,3}};
	public static int[][] pIMG_DEFAULT = {{0,0}};*/
	
	private Core gk;
	
	public EntityPlayer(Core gk, double x, double y, int width, int height)
	{
		super(Tile.playertile,x,y,width,height);
		Rx = (int)(x - gk.offset_X);
		Ry = (int)(y - gk.offset_Y);
		moveSpeed = 2;
		health = 100;
		tX = 100;
		tY = 100;
	}
	
	public boolean canMove(int i, int j)
	{
		System.out.println(i + " " + j);
		if(i < 0 || j < 0 || i >= 200 || j >= 200)
		{
			return false;
		}
		else if(gk.level.solid[i][j].id == Tile.blank)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void move(double delta)
	{
		anim_DELTA++;
		if(anim_DELTA >= anim_time)
		{
			anim_frame++;
			anim_DELTA = 0;
			if(anim_frame > 2)
			{
				anim_frame = 0;
			}
		}
		
		if(gk.bW)
		{
			if(canMove(tX,tY-1) && !isMoving)
			{
				isMoving = true;
				tY -= 1;
			}
			if(isMoving)
			{
				gk.offset_Y -= moveSpeed;
				moveDelta += moveSpeed;
				if(moveDelta >= 32)
				{
					gk.bW = false;
					isMoving = false;
					moveDelta = 0;
					anim_frame = 0;
				}
			}
			else
			{
				gk.bW = false;
			}
		}
		if(gk.bS)
		{
			if(canMove(tX,tY+1) && !isMoving)
			{
				isMoving = true;
				tY += 1;
			}
			if(isMoving)
			{
				gk.offset_Y += moveSpeed;
				moveDelta += moveSpeed;
				if(moveDelta >= 32)
				{
					gk.bS = false;
					isMoving = false;
					moveDelta = 0;
					anim_frame = 0;
				}
			}
			else
			{
				gk.bS = false;
			}
		}
		if(gk.bA)
		{
			if(canMove(tX-1,tY) && !isMoving)
			{
				isMoving = true;
				tX -= 1;
			}
			if(isMoving)
			{
				gk.offset_X -= moveSpeed;
				moveDelta += moveSpeed;
				if(moveDelta >= 32)
				{
					gk.bA = false;
					isMoving = false;
					moveDelta = 0;
					anim_frame = 0;
				}
			}
			else
			{
				gk.bA = false;
			}
		}
		if(gk.bD)
		{
			if(canMove(tX+1,tY) && !isMoving)
			{
				isMoving = true;
				tX += 1;
			}
			if(isMoving)
			{
				gk.offset_X += moveSpeed;
				moveDelta += moveSpeed;
				if(moveDelta >= 32)
				{
					isMoving = false;
					moveDelta = 0;
					anim_frame = 0;
					gk.bD = false;
				}
			}
			else
			{
				gk.bD = false;
			}
		}
	}
	
	public void tick(double delta)
	{
		
	}
	
	@Override
	public void render(Graphics g)
	{
		super.setImage(new int[] {0,0});
		g.drawImage(image, Rx, Ry,null);
		/*if(down)
		{
			//render the animation state
			g.drawImage(Tile.characters, this.x , 
					this.y, 
					this.x + width, 
					this.y + height, 
					pIMG_DOWN[anim_frame][0] * Tile.size, 
					pIMG_DOWN[anim_frame][1] * Tile.size, 
					pIMG_DOWN[anim_frame][0] * Tile.size + Tile.size, 
					pIMG_DOWN[anim_frame][1] * Tile.size + Tile.size, null);
		}
		else if(up)
		{
			g.drawImage(Tile.characters, this.x , 
					this.y, 
					this.x + width, 
					this.y + height, 
					pIMG_UP[anim_frame][0] * Tile.size, 
					pIMG_UP[anim_frame][1] * Tile.size, 
					pIMG_UP[anim_frame][0] * Tile.size + Tile.size, 
					pIMG_UP[anim_frame][1] * Tile.size + Tile.size, null);
		}
		else if(right)
		{
			g.drawImage(Tile.characters, this.x , 
					this.y, 
					this.x + width, 
					this.y + height, 
					pIMG_RIGHT[anim_frame][0] * Tile.size, 
					pIMG_RIGHT[anim_frame][1] * Tile.size, 
					pIMG_RIGHT[anim_frame][0] * Tile.size + Tile.size, 
					pIMG_RIGHT[anim_frame][1] * Tile.size + Tile.size, null);
		}
		else if(left)
		{
			g.drawImage(Tile.characters, this.x , 
					this.y, 
					this.x + width, 
					this.y + height, 
					pIMG_LEFT[anim_frame][0] * Tile.size, 
					pIMG_LEFT[anim_frame][1] * Tile.size, 
					pIMG_LEFT[anim_frame][0] * Tile.size + Tile.size, 
					pIMG_LEFT[anim_frame][1] * Tile.size + Tile.size, null);
		}
		else
		{
			g.drawImage(Tile.characters, this.x , 
					this.y, 
					this.x + width, 
					this.y + height, 
					pIMG_DEFAULT[0][0] * Tile.size, 
					pIMG_DEFAULT[0][1] * Tile.size, 
					pIMG_DEFAULT[0][0] * Tile.size + Tile.size, 
					pIMG_DEFAULT[0][1] * Tile.size + Tile.size, null);
		}*/
	}

	public void on_collided(Entity entity) 
	{
		
	}
	
	public void setTilePosition(int r, int c)//row and column
	{
		tX = r;
		tY = c;
	}
}
