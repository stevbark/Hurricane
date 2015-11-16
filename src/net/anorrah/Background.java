package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Background extends Rectangle 
{
	public int[] id = {-1,-1};
	
	public Background(Rectangle r, int id[])
	{
		setBounds(r);
		this.id = id;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Tile.background, x - (int)Core.offset_X, 
				y - (int)Core.offset_Y, 
				x + width - (int)Core.offset_X, 
				y + height - (int)Core.offset_Y, 
				id[0] * Tile.size, id[1] * Tile.size, 
				id[0] * Tile.size + Tile.size, 
				id[1] * Tile.size + Tile.size, null);
	}
	

}
