package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Solid extends Rectangle 
{
	public int[] id = {-1,-1};
	public int row,col;
	
	public Solid(Rectangle rec,int row, int col, int id[])
	{
		setBounds(rec);
		this.id = id;
		this.row = row;
		this.col = col;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Tile.solid, x - (int)Core.offset_X, 
				y - (int)Core.offset_Y, 
				x + width - (int)Core.offset_X, 
				y + height - (int)Core.offset_Y, 
				id[0] * Tile.size, id[1] * Tile.size, 
				id[0] * Tile.size + Tile.size, 
				id[1] * Tile.size + Tile.size, null);
	}
	


}
