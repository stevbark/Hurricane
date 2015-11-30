package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;
//import org.newdawn.slick.SlickException;
//import org.newdawn.slick.tiled.TiledMap;
import java.util.TreeMap;

public class Level 
{
	public static int width = 21, height = 17, num_level = 1;//match these to the correct width and height of the layout
	
	public Background[][] bg = new Background[width][height];
	public Solid[][] solid = new Solid[width][height];
	public Item[][] item = new Item[width][height];
	
	private TreeMap<Integer,Set<Integer>> rooms;//contains all rooms in a single level
	private int minRooms = 4, maxRooms = 7, delta = 5;
	
	public Level()
	{	
		for(int x = 0; x < bg.length; x++)//generate general background layer to render
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
		setedge();
		generateRooms();
	}
	
	private void setedge()//turns surrounding edges into black tiles and places set
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
	
	public void generateRooms()
	{
		rooms = new TreeMap<Integer,Set<Integer>>();
		int num_rooms = (int)(Math.random()*maxRooms + minRooms);//populate the map full of rooms
		for(int i = 1; i <= num_rooms; i++)
		{
			rooms.put(i, new HashSet<Integer>());
		}
		bridgeRooms();
	}
	
	private void bridgeRooms()//assign rooms to other rooms
	{
		int exit_room = rooms.size();
		int other_room = 2;
		int r = (int)(Math.random()*(exit_room-1) + other_room);//initial value for the first room
		
		rooms.get(1).add(r);//add three rooms
		other_room++;
	}
	
	public void ExitReached()
	{
		rooms.clear();//level is cleared and next level is to load
		num_level++;
		
		//change the min and max number of rooms if appropriate
		minRooms++;
		if(minRooms >= maxRooms)
		{
			minRooms = maxRooms;//cap off the min to max 
		}
		if(num_level % delta == 0)//every x levels change the min and max rooms
		{
			minRooms += 2;
			maxRooms += minRooms;
		}
		generateRooms();
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
