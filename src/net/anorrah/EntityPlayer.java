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
	public boolean isDead = false;
	public boolean meleeEquipped = true;
	
	private static int moveDelta = 0;
	
	public static int player_room_num = 1;
	
	// facing/direction
	public final int UP = 3;
	public final int DOWN = 4;
	public final int LEFT = 1;
	public final int RIGHT = 2;
	
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
	
	//public MeleeWeaponItem weapon = new SwordItem("rat-stabber", 0);
	public MeleeWeaponItem equippedWeapon;
	public RangedWeaponItem equippedRanged;
	
	private ArmorItem equippedArmor;
	private ItemObject useableItem;
	
	public static int facing = 4;//default is facing downward
	
//	private ArrayList<bonus> bonuses = new ArrayList<bonus>();
//	private ArrayList<bonus> toBeRemovedBonuses = new ArrayList<bonus>();
	
	private static class PersonalItem
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
		public ItemObject getItem(){
			return io;
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
		health = 100;
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
		setMeleeItem(new SwordItem("",0));
	//	bandAidObject regenTest= new bandAidObject(0);
		//System.out.println("bandaid");
	//	regenTest.onEquip(user);
	//	tempHealthBonus b = new tempHealthBonus(4,100);
	//	addToList(b);
//		fireballBonus z = new fireballBonus();
//		addToList(z);
//		GABonus ga = new GABonus();
//		addToList(ga);
//		invisibilityBonus iv = new invisibilityBonus(3);
//		addToList(iv);
	}
	
	/*public static void setUsableItem(ItemObject item)
	{
		usableitem.io.onUnequip(Core.player); 
		
		
		if(item instanceof potionObject)
		{
			// items never have independent bonuses
			usableitem = new PersonalItem(ItemsAndBonuses.potionitem,item, new NoBonus());
		}
	}*/
	
	public ItemObject getUsableItem()
	{
		return usableitem.io;
	}
	
	public static void setUsableItem(ItemObject item)
	{
		usableitem.io.onUnequip(Core.player); 
		
		
		if(item instanceof potionObject)
		{
			// items never have independent bonuses
			usableitem = new PersonalItem(ItemsAndBonuses.potionitem,item, new NoBonus());
		}
		else if(item instanceof bandAidObject)
		{
			// items never have independent bonuses
			usableitem = new PersonalItem(ItemsAndBonuses.bandaiditem,item, new NoBonus());
		}
		else if(item instanceof castObject)
		{
			// items never have independent bonuses
			usableitem = new PersonalItem(ItemsAndBonuses.castitem,item, new NoBonus());
		}
		else if(item instanceof FoodItem)
		{
			// items never have independent bonuses
			usableitem = new PersonalItem(ItemsAndBonuses.fooditem,item, new NoBonus());
		}
		else if(item instanceof BerserkerItems)
		{
			// items never have independent bonuses
			usableitem = new PersonalItem(ItemsAndBonuses.spearitem,item, new NoBonus());
		}
		else if(item instanceof runeOfTeleportation)
		{
			// items never have independent bonuses
			usableitem = new PersonalItem(ItemsAndBonuses.spearitem,item, new NoBonus());
		}
		else if(item instanceof ringOfInvisibility)
		{
			// items never have independent bonuses
			usableitem = new PersonalItem(ItemsAndBonuses.axeitem,item, new NoBonus());
		}
		else if(item instanceof pendentOfFleetingHealth)
		{
			// items never have independent bonuses
			usableitem = new PersonalItem(ItemsAndBonuses.whipitem,item, new NoBonus());
		}
		usableitem.io.onEquip(Core.player);
	}
	
	// removes damage taken. For testing only
	/*public void onHit(Entity enemy, damageObject damage)
	{
		for(bonus b:bonuses)
		{
			b.onBeenHit(this,enemy, damage);
		}
	//	takeDamage(damage);
	//	equippedArmor.onBeenHit(enemy,damage);
	}*/
	public static void setMeleeItem(MeleeWeaponItem item)
	{
		meleeitem.io.onUnequip(Core.player);
		if(item instanceof SwordItem)
		{
			meleeitem = new PersonalItem(ItemsAndBonuses.sworditem,item, new NoBonus());
		}
		else if(item instanceof HammerItem)
		{
			meleeitem = new PersonalItem(ItemsAndBonuses.hammeritem,item, new NoBonus());
		}
		else if(item instanceof SpearItem)
		{
			meleeitem = new PersonalItem(ItemsAndBonuses.spearitem,item, new NoBonus());
		}
		else if(item instanceof AxeItem)
		{
			meleeitem = new PersonalItem(ItemsAndBonuses.axeitem,item, new NoBonus());
		}
		else if(item instanceof WhipItem)
		{
			meleeitem = new PersonalItem(ItemsAndBonuses.whipitem,item, new NoBonus());
		}
		meleeitem.io.onEquip(Core.player);
	}
	
	public MeleeWeaponItem getMeleeItem(){
		if(meleeitem.io instanceof MeleeWeaponItem)
			return (MeleeWeaponItem) meleeitem.io;
		else return null;
	}
	
	public static RangedWeaponItem getRangeItem(){
		if(rangeditem.io instanceof RangedWeaponItem)
			return (RangedWeaponItem) rangeditem.io;
		else return null;
	}
	
	public ArmorItem getArmorItem(){
		return equippedArmor;
	}
	
	public static ItemObject getItem(){
		if(usableitem.io instanceof ItemObject)
			return (ItemObject)usableitem.io;
		else return null;
	}
	
	public static void setRangeItem(RangedWeaponItem item)
	{
		rangeditem.io.onUnequip(Core.player);
		if(item instanceof bowAndArrowItem)
		{
			rangeditem = new PersonalItem(ItemsAndBonuses.bowitem,item, new NoBonus());
		}
		else if(item instanceof FireballRod)
		{
			rangeditem = new PersonalItem(ItemsAndBonuses.fireballitem,item, new NoBonus());
		}
		
		else if(item instanceof LaserItem)
		{
			rangeditem = new PersonalItem(ItemsAndBonuses.laseritem,item, new NoBonus());
		}
		
		rangeditem.io.onEquip(Core.player);
	}
	
	public boolean canMove(int i, int j)
	{
		//System.out.println("\nCurrently at:\t" + tX + " " + tY);
		
		if(i < 0 || j < 0 || i >= max_Xdistance || j >= max_Ydistance)
			return true;
		else if(gk.level.item[i][j].id != Tile.blank)
		{
			gk.level.item[i][j].id = Tile.chest_open;
			//meleeitem.io = (MeleeWeaponItem) gk.level.item[i][j].generateItem(Level.num_level);
			Core.item(gk.level.item[i][j].generateItem(Level.num_level),gk.level.item[i][j].itemDescription());
			//gk.level.item[i][j].id = Tile.blank;
			//Core.itempicked = false; 
			return false;
		}
		else if((gk.level.solid[i][j].id == Tile.blank) && (Core.level.canMove(i,j)))
			return true;
		return false;
	}
	
	public void changeDirection(){
		if(gk.bUP){
			currentImage = Tile.playertile_UP;
			facing = UP;
			gk.bUP = false;
		}
		if(gk.bDOWN){
			currentImage = Tile.playertile_DOWN;
			facing = DOWN;
			gk.bDOWN = false;
		}
		if(gk.bLEFT){
			currentImage = Tile.playertile_LEFT;
			facing = LEFT;
			gk.bLEFT = false;
			
		}
		if(gk.bRIGHT){
			currentImage = Tile.playertile_RIGHT;

			facing = RIGHT;
			gk.bRIGHT = false;
		}
		
	}
	
	public char getDirection(){	
		if(facing == UP) return 'U';
		if(facing == DOWN) return 'D';
		if(facing == LEFT) return 'L';
		else return 'R';
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
	
//	public void attack(//int xloc, int yloc) I think we should attack a space, not an enemy. 
//			//How do we target a specific enemy?
//			EnemyEntities bad)
//	{
//		
//		System.out.println("smacked!" + tX+" " +tY);
//		damageObject damage = new damageObject(weapon.damage, Type.physical);
//		for(bonus b:bonuses)
//		{
//			b.onAttack(this,bad, damage, true);
//		}
//		bad.takeDamage(damage);
//		
//		//equippedWeapon.attack();
//	}
	
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
		health=0;
		for(bonus b:bonuses)
		{
			b.onDeath(this);
		}
		
		if(health<=0)
		{
			isDead = true;
		}
	}
	
	public boolean isDead(){
		return isDead;
	}
	
	public void stopGame(){
		gk.stop();
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
