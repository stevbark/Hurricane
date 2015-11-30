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
	
	public Level(int id)
	{	
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
				solid[x][y] = new Solid(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.blank);
				item[x][y] = new Item(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.blank);
			}
		}
		setedge(Tile.floorend);
		//loadworld();
	}
	
	public void setedge(int[] id)
	{
		for(int i = 0; i < width; i++)//along the top edge and bottom edge
		{
			bg[i][0] = new Background(new Rectangle(i * Tile.size, 0, Tile.size, Tile.size),Tile.floorend);
			bg[i][height-1] = new Background(new Rectangle(i * Tile.size, (height-1) * Tile.size, Tile.size, Tile.size),Tile.floorend);
			int rand = (int)(Math.random() * 4 + 1);
			if(rand == 1)
			{
				solid[i][0]= new Solid(new Rectangle(i*Tile.size, 0, Tile.size, Tile.size),i,0,Tile.wall_top1);
			}
			else if(rand == 2)
			{
				solid[i][0]= new Solid(new Rectangle(i*Tile.size, 0, Tile.size, Tile.size),i,0,Tile.wall_top2);
			}
			else if(rand == 3)
			{
				solid[i][0]= new Solid(new Rectangle(i*Tile.size, 0, Tile.size, Tile.size),i,0,Tile.wall_top3);
			}
			else
			{
				solid[i][0]= new Solid(new Rectangle(i*Tile.size, 0, Tile.size, Tile.size),i,0,Tile.wall_top4);
			}
			rand = (int)(Math.random()*4+1);
			if(rand == 1)
			{
				solid[i][height-1]= new Solid(new Rectangle(i*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size),i,height-1,Tile.wall_bottom1);
			}
			else if(rand == 2)
			{
				solid[i][height-1]= new Solid(new Rectangle(i*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size),i,height-1,Tile.wall_bottom3);
			}
			else if(rand == 3)
			{
				solid[i][height-1]= new Solid(new Rectangle(i*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size),i,height-1,Tile.wall_bottom2);
			}
			else
			{
				solid[i][height-1]= new Solid(new Rectangle(i*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size),i,height-1,Tile.wall_bottom4);
			}
		}
		for(int i = 0; i < height; i++)//along the top edge and bottom edge
		{
			bg[0][i] = new Background(new Rectangle(0, i * Tile.size, Tile.size, Tile.size),Tile.floorend);
			bg[width-1][i] = new Background(new Rectangle( (width-1) * Tile.size, i*Tile.size, Tile.size, Tile.size),Tile.floorend);
			
			int rand = (int)(Math.random() * 4 + 1);
			if(rand == 1)
			{
				solid[0][i] = new Solid(new Rectangle(0, i * Tile.size, Tile.size, Tile.size),0,i,Tile.wall_left1);
			}
			else if(rand == 2)
			{
				solid[0][i] = new Solid(new Rectangle(0, i * Tile.size, Tile.size, Tile.size),0,i,Tile.wall_left2);
			}
			else if(rand == 3)
			{
				solid[0][i] = new Solid(new Rectangle(0, i * Tile.size, Tile.size, Tile.size),0,i,Tile.wall_left3);
			}
			else
			{
				solid[0][i] = new Solid(new Rectangle(0, i * Tile.size, Tile.size, Tile.size),0,i,Tile.wall_left4);
			}
			
			rand = (int)(Math.random() * 4 + 1);
			if(rand == 1)
			{
				solid[width-1][i]= new Solid(new Rectangle((width-1)*Tile.size, i*Tile.size, Tile.size, Tile.size),width-1,i,Tile.wall_right1);
			}
			else if(rand == 2)
			{
				solid[width-1][i]= new Solid(new Rectangle((width-1)*Tile.size, i*Tile.size, Tile.size, Tile.size),width-1,i,Tile.wall_right2);
			}
			else if(rand == 3)
			{
				solid[width-1][i]= new Solid(new Rectangle((width-1)*Tile.size, i*Tile.size, Tile.size, Tile.size),width-1,i,Tile.wall_right3);
			}
			else
			{
				solid[width-1][i]= new Solid(new Rectangle((width-1)*Tile.size, i*Tile.size, Tile.size, Tile.size),width-1,i,Tile.wall_right4);
			}
			
		}
		
		item[5][5] = new Item(new Rectangle(5*Tile.size, 5*Tile.size, Tile.size, Tile.size),5,5,Tile.item);
		item[7][7] = new Item(new Rectangle(7*Tile.size, 7*Tile.size, Tile.size, Tile.size),7,7,Tile.item2);
		solid[0][0] = new Solid(new Rectangle(0, 0, Tile.size, Tile.size),0,0,Tile.corner_tl);
		solid[width-1][0] = new Solid(new Rectangle((width-1)*Tile.size, 0, Tile.size, Tile.size),width-1,0,Tile.corner_tr);
		solid[0][height-1]= new Solid(new Rectangle(0, (height-1)*Tile.size, Tile.size, Tile.size),0,height-1,Tile.corner_bl);
		solid[width-1][height-1]= new Solid(new Rectangle((width-1)*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size),width-1,height-1,Tile.corner_br);
	}
	
	public void loadworld()
	{
		int solids = map.getLayerIndex("solid");
		
		for(int x = 0; x < bg.length; x++)
		{
			for(int y = 0; y < bg[0].length; y++)
			{	
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
					solid[x][y].id = Tile.wall_left1;
				}
				else if(tileid == 111)
				{
					//left edge
					solid[x][y].id = Tile.wall_right1;
				}
				else if(tileid == 131)
				{
					//top edge
					solid[x][y].id = Tile.wall_top1;
				}
				else if(tileid == 141)
				{
					//bot edge
					solid[x][y].id = Tile.wall_bottom1;
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
