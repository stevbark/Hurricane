package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level 
{
	public int width = 200, height = 200;//match these to the correct width and height of the layout
	
	public Background[][] bg = new Background[width][height];
	public Solid[][] solid = new Solid[width][height];
	public Item[][] item = new Item[width][height];
	
	public final String dpath = "res/maps/testworld";
	
	public String path = dpath;
	
	public TiledMap map = null;
	
	public Level(int id)
	{
		path = dpath + Integer.toString(id) + ".tmx";
		try
		{
			map = new TiledMap(path, false);
		}
		catch(SlickException e)
		{
			System.out.println("Error loading Map");
		}
		
		for(int x = 0; x < bg.length; x++)
		{
			for(int y = 0; y < bg[0].length; y++)
			{
				bg[x][y] = new Background(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size),Tile.blank);
				solid[x][y] = new Solid(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.blank);
				item[x][y] = new Item(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.blank);
			}
		}
		loadworld();
	}
	
	public void loadworld()
	{
		int background = map.getLayerIndex("background");
		int solids = map.getLayerIndex("solid");
		int items = map.getLayerIndex("item");
		
		for(int x = 0; x < bg.length; x++)
		{
			for(int y = 0; y < bg[0].length; y++)
			{
				int tileid = map.getTileId(x, y, background);
				if(tileid == 0)//look at what the tile editor says
				{
					bg[x][y].id = Tile.back;
				}
				else if(tileid == 1)//look at what the tile editor says
				{
					bg[x][y].id = Tile.path;
				}
			}
		}
	}
	public void tick(double delta)
	{
		
	}
	
	public void render(Graphics g, int cam_x, int cam_y,int rend_x,int rend_y)
	{
		for(int x = (cam_x / Tile.size); x < (cam_x / Tile.size) + rend_x; x++)
		{
			for(int y = (cam_y / Tile.size); y < (cam_y / Tile.size) + rend_y; y++)
			{
				if(x >= 0 && y >= 0 && x < width && y < height)
				{
					bg[x][y].render(g);
				}
			}
		}
	}
}
