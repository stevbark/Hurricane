package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Item extends Rectangle 
{
	public int[] id = {-1,-1};
	public int row,col;
	private ItemObject itemObject;
	
	public Item(Rectangle rec,int r, int c, int id[])
	{
		setBounds(rec);
		this.id = id;
		this.row = r;
		this.col = c;
		itemObject = new SwordItem(" not done");
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
	
	public void generateItem ()
	{
		int randomItemSeed = ((int) (Math.random()*100))%5;
		switch(randomItemSeed)
		{
		case 1:
			itemObject = new SwordItem("1");
			break; 
		case 2:
			itemObject = new SwordItem("2");
			break; 
		case 3:
			itemObject = new SwordItem("3");
			break; 
		case 4:
			itemObject = new SwordItem("4");
			break; 
		case 0:
		default:
			itemObject = new SwordItem("0");
			break; 
		}
		
		
	}
	
	public String itemDescription()
	{
		return itemObject.description();
	}
	

}
