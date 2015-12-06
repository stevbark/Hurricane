package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import net.anorrah.items.*;
import net.anorrah.items.bonus.*;
import net.anorrah.items.damageObject.Type;


public class EntityPlayer extends Entity 
{
	public int moveSpeed;
	public static boolean isMoving = false;
	
	private static int moveDelta = 0;
	
	public static int player_room_num = 1;
	
	public int anim_frame = 0;
	public int anim_time = 20;
	public int anim_DELTA = 0;
	private int[] currentImage;
	
	private Core gk;
	private final int max_Xdistance;
	private final int max_Ydistance;
	
	public static PersonalItem meleeitem;
	public static PersonalItem usableitem;
	public static PersonalItem rangeditem;
	
	private ArmorItem equippedArmor;
	private ItemObject useableItem;
	private boolean isInvisible;
	
	public static int facing = 4;//default is facing downward
	
//	private ArrayList<bonus> bonuses = new ArrayList<bonus>();
//	private ArrayList<bonus> toBeRemovedBonuses = new ArrayList<bonus>();
	
	private class PersonalItem
	{
		public Item i;//for rendering the icon
		public Item ib;//for rendering the icon
		public ItemObject io;//Item represented in the data
		public bonus b;
		
		public PersonalItem(Item i, ItemObject io, bonus b)
		{
			this.i = i;
			this.io = io;
			this.b = b;
			if(b instanceof NoBonus)
			{
				ib = ItemsAndBonuses.no_bonus;
			}
		}
		public PersonalItem(Item i, Item ib, ItemObject io, bonus b)
		{
			this.i = i;
			this.ib = ib;
			this.io = io;
			this.b = b;
		}
		public void render(Graphics g)
		{
			i.render(g);
			ib.render(g);
		}
	}
	
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
		health = 50;
		maxHealth = 100;
		max_Xdistance = gk.level.width;
		max_Ydistance = gk.level.height;
		meleeitem = new PersonalItem(ItemsAndBonuses.sworditem,new SwordItem("",0), new NoBonus());
		rangeditem = new PersonalItem(ItemsAndBonuses.no_rangeitem, new NoItem(0), new NoBonus());
		usableitem = new PersonalItem(ItemsAndBonuses.no_item, new NoItem(0), new NoBonus());
		equippedArmor = new ArmorItem(0);
		this.gk = gk;
		currentImage = super.id;
		//meleeitem.itemObject = new SwordItem("",Level.num_level);
	}
	

	public void setUp(Entity user)
	{
		equippedArmor.onEquip(user);
		bandAidObject regenTest= new bandAidObject(0);
		//System.out.println("bandaid");
		regenTest.onEquip(user);
		tempHealthBonus b = new tempHealthBonus(4,100);
		addToList(b);
		rangedBonus z = new rangedBonus();
		addToList(z);
		GABonus ga = new GABonus();
		addToList(ga);
		
	}
	
	public boolean isInvisible()
	{
		return isInvisible;
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
			return true;
		else if(gk.level.item[i][j].id != Tile.blank)
		{
			meleeitem.io = (MeleeWeaponItem) gk.level.item[i][j].generateItem(0);
			String str =gk.level.item[i][j].itemDescription();
			//System.out.println(str);
			gk.level.item[i][j].id = Tile.blank;
			return false;
		}
		else if((gk.level.solid[i][j].id == Tile.blank) && (Core.level.canMove(i,j)))
			return true;
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
				anim_frame = 0;
		}
		
		if(gk.bW)
		{
			currentImage = Tile.playertile_UP;
			facing = 3;
			if(canMove(tX,tY-1) && !isMoving)
			{
				isMoving = true;
				tY -= 1;
			}
			if(isMoving)
			{
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
			currentImage = Tile.playertile_DOWN;
			facing = 4;
			if(canMove(tX,tY+1) && !isMoving)
			{
				isMoving = true;
				tY += 1;
			}
			if(isMoving)
			{
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
			currentImage = Tile.playertile_LEFT;
			facing = 1;
			if(canMove(tX-1,tY) && !isMoving)
			{
				isMoving = true;
				tX -= 1;
			}
			if(isMoving)
			{
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
			currentImage = Tile.playertile_RIGHT;
			facing = 2;
			if(canMove(tX+1,tY) && !isMoving)
			{
				isMoving = true;
				tX += 1;
			}
			if(isMoving)
			{
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
	
	public void attack(//int xloc, int yloc) I think we should attack a space, not an enemy. 
			//How do we target a specific enemy?
			EnemyEntities enemy)
	{
		
		System.out.println("smacked!" + tX+" " +tY);
		damageObject damage = new damageObject(0, Type.physical);
		for(bonus b:bonuses)
		{
			b.onAttack(this,enemy, damage, true);
		}
		enemy.takeDamage(damage);
		
		//equippedWeapon.attack();
	}
	
	public void attack(int targX, int targY)
	{
		for(bonus b:bonuses)
		{
			b.onAttackPosition(this, targX,targY);
		}
	}
	
	public void tick(double delta)
	{
		
	}
	
	@Override
	public void render(Graphics g)
	{
		
		super.setImage(currentImage);
		meleeitem.render(g);
		rangeditem.render(g);
		usableitem.render(g);
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
	
	public void on_death()
	{
		
		for(bonus b:bonuses)
		{
			b.onDeath(this);
		}
		
		if(health<=0)
		{
			System.out.println("You are Dead");
			gk.stop();
		}
	}


	public void setTilePosition(int i, int j) 
	{
		tX = i;
		tY = j;
	}
	
//	public void onHit(Entity enemy, damageObject damage)
//	{
//		for(bonus b:bonuses)
//		{
//			b.onBeenHit(this,enemy, damage);
//		}
//		takeDamage(damage);
//	//	equippedArmor.onBeenHit(enemy,damage);
//	}
	

	
	// this was moved to the Entity baseclass.  
//	public void heal(int heal)
//	{
//		if(health + heal >maxHealth)
//		{
//			health = maxHealth;
//		}
//		else
//		{
//			health+=heal;
//		}
//	}
	
//	public void takeTurn()
//	{
//		for(bonus b: bonuses)
//		{
//			b.doOnTurn();
//		}
//		cleanup();
//	}
//
//	public void addToList(bonus toAdd)
//	{
//		System.out.println(toAdd.isTemp);
//		bonuses.add(toAdd);
//	}
//	
//	// we cant remove directly from the list because we may be iteration through it and we will get an exception.
//	public void removeFromList(bonus toRemove)
//	{
//		toBeRemovedBonuses.add(toRemove);
//	}
//	
//	private void cleanup()
//	{
//		for(bonus b: toBeRemovedBonuses)
//		{
//			bonuses.remove(b);
//		}
//		toBeRemovedBonuses.clear();
//	}
}
