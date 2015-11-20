package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level 
{
	public int width = 21, height = 17;//match these to the correct width and height of the layout
	
	public Background[][] bg = new Background[width][height];
	public Solid[][] solid = new Solid[width][height];
	public Item[][] item = new Item[width][height];
	
	public final String dpath = "res/maps/testworld";
	
	public String path = dpath;
	
	public TiledMap map = null;
	
	public double myx,myy;
	
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
				int rand = (int)(Math.random() * 7 + 1);
				switch(rand)
				{
				case 1:
					bg[x][y] = new Background(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size),Tile.floor1);
					break;
				case 2:
					bg[x][y] = new Background(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size),Tile.floor2);
					break;
				case 3:
					bg[x][y] = new Background(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size),Tile.floor3);
					break;
				case 4:
					bg[x][y] = new Background(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size),Tile.floor4);
					break;
				case 5:
					bg[x][y] = new Background(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size),Tile.floor5);
					break;
				case 6:
					bg[x][y] = new Background(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size),Tile.floor6);
					break;
				case 7:
					bg[x][y] = new Background(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size),Tile.floor7);
					break;
				default:
					bg[x][y] = new Background(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size),Tile.floor1);
					break;
				}
				//bg[x][y] = new Background(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size),Tile.blank);
				solid[x][y] = new Solid(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.blank);
				item[x][y] = new Item(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.blank);
			}
		}
		setedge(Tile.floorend);
		loadworld();
	}
	
	public void setedge(int[] id)
	{
		for(int i = 0; i < width; i++)//along the top edge and bottom edge
		{
			bg[i][0] = new Background(new Rectangle(i * Tile.size, 0, Tile.size, Tile.size),Tile.floorend);
			bg[i][height-1] = new Background(new Rectangle(i * Tile.size, (height-1) * Tile.size, Tile.size, Tile.size),Tile.floorend);
		}
		for(int i = 0; i < height; i++)//along the top edge and bottom edge
		{
			bg[0][i] = new Background(new Rectangle(0, i * Tile.size, Tile.size, Tile.size),Tile.floorend);
			bg[width-1][i] = new Background(new Rectangle( (width-1) * Tile.size,i*Tile.size, Tile.size, Tile.size),Tile.floorend);
		}
		
		item[5][5] = new Item(new Rectangle(5*Tile.size, 5*Tile.size, Tile.size, Tile.size),5,5,Tile.item);
		item[7][7] = new Item(new Rectangle(7*Tile.size, 7*Tile.size, Tile.size, Tile.size),7,7,Tile.item2);
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
				/*int tileid = map.getTileId(x, y, background);
				if(tileid == 4)//look at what the tile editor says
				{
					bg[x][y].id = Tile.whitespace;
				}
				else if(tileid == 1)//look at what the tile editor says
				{
					bg[x][y].id = Tile.back;
				}
				else if(tileid == 2)//look at what the tile editor says
				{
					bg[x][y].id = Tile.path;
				}
				else if(tileid == 3)//look at what the tile editor says
				{
					bg[x][y].id = Tile.grass;
				}*/
				
				int tileid = map.getTileId(x,y,solids);
				if(tileid == 122)
				{
					//tr
					solid[x][y].id = Tile.corner_tr;
				}
				else if(tileid == 123)
				{
					//tl
					solid[x][y].id = Tile.corner_tl;
				}
				else if(tileid == 121)
				{
					//bl
					solid[x][y].id = Tile.corner_bl;
				}
				else if(tileid == 124)
				{
					//br
					solid[x][y].id = Tile.corner_br;
				}
				else if(tileid == 101)
				{
					//left edge
					solid[x][y].id = Tile.wall_left;
				}
				else if(tileid == 111)
				{
					//left edge
					solid[x][y].id = Tile.wall_right;
				}
				else if(tileid == 131)
				{
					//top edge
					solid[x][y].id = Tile.wall_top;
				}
				else if(tileid == 141)
				{
					//bot edge
					solid[x][y].id = Tile.wall_bottom;
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
					if(solid[x][y].id[0] != -1)
					{
						solid[x][y].render(g);
					}
					if(item[x][y].id[0] != -1)
					{
						item[x][y].render(g);
					}
				}
			}
		}
	}
}
