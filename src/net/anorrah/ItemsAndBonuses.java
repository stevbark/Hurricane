package net.anorrah;

import java.awt.Rectangle;

public class ItemsAndBonuses 
{
	private static final int meleeicon_x = 14, icon_y = 0, itemicon_x = 8, rangedicon_x = 18;
	
	//Melee
	public static final Item no_meleeitem = new Item(new Rectangle(meleeicon_x*Tile.size, icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.empty_item);
	public static final Item sworditem = new Item(new Rectangle(meleeicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.sword_icon);
	public static final Item hammeritem = new Item(new Rectangle(meleeicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.hammer_icon);
	public static final Item spearitem = new Item(new Rectangle(meleeicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.spear_icon);
	public static final Item axeitem = new Item(new Rectangle(meleeicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.axe_icon);
	public static final Item whipitem = new Item(new Rectangle(meleeicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.whip_icon);
	
	//Ranged
	public static final Item no_rangeitem = new Item(new Rectangle(rangedicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.empty_item);
	public static final Item bowitem = new Item(new Rectangle(rangedicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.bow_icon);
	public static final Item fireballitem = new Item(new Rectangle(rangedicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.fireball_icon);
	public static final Item lightningitem = new Item(new Rectangle(rangedicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.lightning_icon);
	public static final Item laseritem = new Item(new Rectangle(rangedicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.laser_icon);
	
	//Usable Items
	public static final Item no_item = new Item(new Rectangle(itemicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.empty_item);
	public static final Item potionitem = new Item(new Rectangle(rangedicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.potion_icon);
	public static final Item bandaiditem = new Item(new Rectangle(rangedicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.band_aid_icon);
	public static final Item castitem = new Item(new Rectangle(rangedicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.cast_icon);
	public static final Item fooditem = new Item(new Rectangle(rangedicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.food_icon);
	//Bonuses
	public static final Item no_bonus = new Item(new Rectangle(meleeicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.no_enchant);
	//melee Bonuses
	public static final Item fire_meleebonus = new Item(new Rectangle(meleeicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.fire_melee_enchant);
	public static final Item enfeeble_meleebonus = new Item(new Rectangle(meleeicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.enfeeblement);
	public static final Item vampire_meleebonus = new Item(new Rectangle(meleeicon_x*Tile.size,icon_y*Tile.size,Tile.size,Tile.size),0,0,Tile.vampire_melee_enchant);
}
