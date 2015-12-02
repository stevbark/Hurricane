package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


public class EntityPlayer extends Entity 
{
	public int moveSpeed;
	public static boolean isMoving = false;
	
	public static int Rx,Ry, tX, tY;
	private static int moveDelta = 0;
	
	public static int player_room_num = 1;
	
	public int anim_frame = 0;
	public int anim_time = 20;
	public int anim_DELTA = 0;
	private int[] currentImage;
	/*public static int[][] pIMG_UP = {{0,1},{1,1},{2,1},{3,1}};
	public static int[][] pIMG_DOWN = {{0,0},{1,0},{2,0},{3,0}};
	public static int[][] pIMG_LEFT = {{0,2},{1,2},{2,2},{3,2}};
	public static int[][] pIMG_RIGHT = {{0,3},{1,3},{2,3},{3,3}};
	public static int[][] pIMG_DEFAULT = {{0,0}};*/
	
	private Core gk;
	private final int max_Xdistance;
	private final int max_Ydistance;
	private int camx =  0,camy = 0;
	
	private MeleeWeaponItem equippedWeapon;
	private ArmorItem equippedArmor;
	private ItemObject useableItem;
	
	private ArrayList<bonus> bonuses = new ArrayList<bonus>();
	private ArrayList<bonus> toBeRemovedBonuses = new ArrayList<bonus>();

	
	public EntityPlayer(Core gk, double x, double y, int width, int height)
	{
		super(Tile.playertile_DOWN,x,y,width,height);
		tX = 10;
		tY = 8;
		Rx = tX*32;
		Ry = tY*32;
		super.x = Rx;
		super.y = Ry;
		moveSpeed = 2;
		health = 100;
		max_Xdistance = gk.level.width;
		max_Ydistance = gk.level.height;
		equippedWeapon = new SwordItem("stab",0);
		equippedArmor = new ArmorItem(0);
		this.gk = gk;
		currentImage = super.id;
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
	
	public void takeTurn()
	{
		for(bonus b: bonuses)
		{
			b.doOnTurn();
		}
		cleanup();
	}

	public void addToList(bonus toAdd)
	{
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
	
	public ItemObject getUsableItem()
	{
		return useableItem;
	}
	
	public void setUsableItem(ItemObject item)
	{
		useableItem = item;
	}
	
	public boolean canMove(int i, int j)
	{
		//System.out.println("\nCurrently at:\t" + tX + " " + tY);
		if(i < 0 || j < 0 || i >= max_Xdistance || j >= max_Ydistance)
		{
			return true;
		}
		else if(gk.level.item[i][j].id != Tile.blank)
		{
			equippedWeapon = (MeleeWeaponItem) gk.level.item[i][j].generateItem(0);
			String str =gk.level.item[i][j].itemDescription();
			System.out.println(str);
			gk.level.item[i][j].id = Tile.blank;
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
				currentImage = Tile.playertile_UP;
			}
			if(isMoving)
			{
				//gk.offset_Y -= moveSpeed;
				moveDelta += moveSpeed;
				if(moveDelta >= 32)
				{
					gk.bW = false;
					isMoving = false;
					moveDelta = 0;
					anim_frame = 0;
				}
			// enable if you want to enable enemy attacks when you go to X:10 Y:7 ( one sqaure above your start location) when you hit the W key
			//	gk.doATurn();
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
				currentImage = Tile.playertile_DOWN;
			}
			if(isMoving)
			{
				//gk.offset_Y += moveSpeed;
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
				currentImage = Tile.playertile_LEFT;
			}
			if(isMoving)
			{
				//gk.offset_X -= moveSpeed;
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
				currentImage = Tile.playertile_RIGHT;
			}
			if(isMoving)
			{
				//gk.offset_X += moveSpeed;
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
		Rx = tX*32;
		Ry = tY*32;
	    
	}
	
	public void attack()
	{
		System.out.println("smacked!" + tX+" " +tY);
		equippedWeapon.attack();
	}
	
	public void tick(double delta)
	{
		
	}
	
	@Override
	public void render(Graphics g)
	{
		camx = (Rx - Core.VIEWPORT_SIZE.width/2 + Tile.size/2);
		camy = (Ry - Core.VIEWPORT_SIZE.height/2 + Tile.size/2);
		
		super.setImage(currentImage);
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
		*/
	}

	public void on_collided(Entity entity) 
	{
		
	}
	
	public void onHit(enemyEntities enemy, damageObject damage)
	{
		equippedArmor.onBeenHit(enemy,damage);
	}
	
	public void setTilePosition(int r, int c)//row and column
	{
		tX = r;
		tY = c;
		Rx = tX*32;
		Ry = tY*32;
		System.out.println("Updated Tile x and y:\t" + tX + "\t" + tY);
		System.out.println("Updated Render x and y:\t" + Rx + "\t" + Ry);

	}
}
