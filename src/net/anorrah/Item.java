package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.anorrah.items.ArmorItem;
import net.anorrah.items.AxeItem;
import net.anorrah.items.BerserkerItems;
import net.anorrah.items.FireballRod;
import net.anorrah.items.FoodItem;
import net.anorrah.items.HammerItem;
import net.anorrah.items.ItemObject;
import net.anorrah.items.LaserItem;
import net.anorrah.items.SpearItem;
import net.anorrah.items.SwordItem;
import net.anorrah.items.WhipItem;
import net.anorrah.items.bandAidObject;
import net.anorrah.items.bowAndArrowItem;
import net.anorrah.items.castObject;
import net.anorrah.items.pendentOfFleetingHealth;
import net.anorrah.items.potionObject;
import net.anorrah.items.ringOfInvisibility;
import net.anorrah.items.runeOfTeleportation;

public class Item extends Rectangle 
{
	public int[] id = {-1,-1};
	public int row,col;
	public ItemObject itemObject;
	public String name = "NONE";
	
	public Item(Rectangle rec,int r, int c, int id[])
	{
		setBounds(rec);
		this.id = id;
		this.row = r;
		this.col = c;
		
	}
	
	public Item(Item item)
	{
		//setBounds();
		this.id = item.id;
		this.row = item.row;
		this.col = item.col;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Tile.items, x - (int)Core.offset_X, 
				y - (int)Core.offset_Y, 
				x + width - (int)Core.offset_X, 
				y + height - (int)Core.offset_Y, 
				id[0] * Tile.size, id[1] * Tile.size, 
				id[0] * Tile.size + Tile.size, 
				id[1] * Tile.size + Tile.size, null);
	}
	
	public ItemObject generateItem (int currentLevel)//called when picked up
	{
		double randomSeed = Math.random();
		if(randomSeed < 0.25)
		{
			return generateArmor(currentLevel);
		}
		else if(randomSeed < 0.50)
		{
			//create melee object\
			return generateMelee(currentLevel);
		}
		else if(randomSeed < 0.75)
		{
			//create range
			return generateRange(currentLevel);
		}
		else if(randomSeed <= 1)
		{
			//create usable
			return generateUsable(currentLevel);
		}
	//	itemObject = generateMelee(currentLevel);
		return itemObject;
	}
	
	public ItemObject generateArmor(int currentLevel)
	{
		itemObject = new ArmorItem( currentLevel); 
		return itemObject;
	}
	
	public ItemObject generateMelee(int currentLevel)
	{
		int randomItemSeed = ((int) (Math.random()*100))%5;
		switch(randomItemSeed)
		{
		case 1:
			itemObject = new SwordItem("Sword", currentLevel);
			break; 
		case 2:
			itemObject = new HammerItem(currentLevel);
			break; 
		case 3:
			itemObject = new AxeItem(currentLevel);
			break; 
		case 4:
			itemObject = new SpearItem(currentLevel);
			break; 
		default:
			itemObject = new WhipItem(currentLevel);
			break; 
		}
		return itemObject;
	}
	
	public ItemObject generateRange(int currentLevel)
	{
		int randomItemSeed = ((int) (Math.random()*100))%3;
		switch(randomItemSeed)
		{
		case 1:
			itemObject = new bowAndArrowItem(currentLevel);
			break; 
		case 2:
			itemObject = new FireballRod(currentLevel);
			break; 
		default:
			itemObject = new LaserItem(currentLevel);
			break; 
		}
		return itemObject;
	}
	
	public ItemObject generateUsable(int currentLevel)
	{
		int randomItemSeed = ((int) (Math.random()*100))%8;
		switch(randomItemSeed)
		{
		case 1:
			itemObject = new potionObject( currentLevel);
			break; 
		case 2:
			itemObject = new bandAidObject(currentLevel);
			break; 
		case 3:
			itemObject = new castObject(currentLevel);
			break; 
		case 4:
			itemObject = new FoodItem(currentLevel);
			break; 
		case 5:
			itemObject = new BerserkerItems(currentLevel);
			break; 
		case 6:
			itemObject = new runeOfTeleportation(currentLevel);
			break; 
		case 7:
			itemObject = new ringOfInvisibility(currentLevel);
			break; 	
		default:
			itemObject = new pendentOfFleetingHealth(currentLevel);
			break; 
		}
		return itemObject;
	}
	
	public String getName(){
		return name;
	}
	public String itemDescription()
	{
		return itemObject.description();
	}
	

}
