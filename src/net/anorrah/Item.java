package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.anorrah.items.AxeItem;
import net.anorrah.items.HammerItem;
import net.anorrah.items.ItemObject;
import net.anorrah.items.SpearItem;
import net.anorrah.items.SwordItem;
import net.anorrah.items.WhipItem;

public class Item extends Rectangle 
{
	public int[] id = {-1,-1};
	public int row,col;
	public ItemObject itemObject;
	
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
		/*if(Math.random() < 0.33)
		{
			//create melee object\
			return generateMelee(currentLevel);
		}
		else if(Math.random() < 0.66)
		{
			//create range
			return generateRange(currentLevel);
		}
		else if(Math.random() <= 1)
		{
			//create usable
			return generateUsable(currentLevel);
		}*/
		itemObject = generateMelee(currentLevel);
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
		return itemObject;
	}
	
	public ItemObject generateUsable(int currentLevel)
	{
		return itemObject;
	}
	
	public String itemDescription()
	{
		return itemObject.description();
	}
	

}
