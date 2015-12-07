package net.anorrah;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Stack;

import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class Level 
{
	public static final int width = 21, height = 17, center_w = 10, center_h = 8;
	public static int num_level = 1;
	
	public Background[][] bg = new Background[width][height];
	public Solid[][] solid = new Solid[width][height];
	public Item[][] item = new Item[width][height];
	//public Enemies[][] enemyMatrix = new Enemies[width][height];
	public ArrayList<EnemyEntities> enemies = new ArrayList<EnemyEntities>();
	
	private TreeMap<Integer,Room> rooms;//contains all rooms in a single level
	private int minRooms = 4, maxRooms = 7, delta = 5;
	public int playerlocation = 1;
	private boolean left = false, right = false, up = false, down = false;
	private int nextleft = -1, nextright = -1, nexttop = -1, nextbottom = -1;
	private static final int MAX_PATH_LENGTH = 100;
	
	public Level()
	{	
		rooms = new TreeMap<Integer,Room>();
		setbackgroundtiles();
		setedge();
		generateRooms();
		//System.out.println(enemies.size());
	}
	private void setbackgroundtiles()
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
				//enemyMatrix[x][y] = new Enemies(new Rectangle(x*Tile.size, y*Tile.size, Tile.size, Tile.size),x,y,Tile.blank);
				
			}
		}
		
	}
	
	private void setedge()//turns surrounding edges into black tiles and places set
	{
		for(int i = 0; i < width; i++)//along the top edge and bottom edge
		{
			bg[i][0] = new Background(new Rectangle(i * Tile.size, 0, Tile.size, Tile.size),Tile.floorend);
			bg[i][height-1] = new Background(new Rectangle(i * Tile.size, (height-1) * Tile.size, Tile.size, Tile.size),Tile.floorend);
			int rand = (int)(Math.random() * 4 + 1);
			if(rand == 1)
				solid[i][0]= new Solid(new Rectangle(i*Tile.size, 0, Tile.size, Tile.size),i,0,Tile.wall_top1);
			else if(rand == 2)
				solid[i][0]= new Solid(new Rectangle(i*Tile.size, 0, Tile.size, Tile.size),i,0,Tile.wall_top2);
			else if(rand == 3)
				solid[i][0]= new Solid(new Rectangle(i*Tile.size, 0, Tile.size, Tile.size),i,0,Tile.wall_top3);
			else
				solid[i][0]= new Solid(new Rectangle(i*Tile.size, 0, Tile.size, Tile.size),i,0,Tile.wall_top4);
			
			rand = (int)(Math.random()*4+1);
			
			if(rand == 1)
				solid[i][height-1]= new Solid(new Rectangle(i*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size),i,height-1,Tile.wall_bottom1);
			else if(rand == 2)
				solid[i][height-1]= new Solid(new Rectangle(i*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size),i,height-1,Tile.wall_bottom3);
			else if(rand == 3)
				solid[i][height-1]= new Solid(new Rectangle(i*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size),i,height-1,Tile.wall_bottom2);
			else
				solid[i][height-1]= new Solid(new Rectangle(i*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size),i,height-1,Tile.wall_bottom4);
		}
		for(int i = 0; i < height; i++)//along the top edge and bottom edge
		{
			bg[0][i] = new Background(new Rectangle(0, i * Tile.size, Tile.size, Tile.size),Tile.floorend);
			bg[width-1][i] = new Background(new Rectangle( (width-1) * Tile.size, i*Tile.size, Tile.size, Tile.size),Tile.floorend);
			
			int rand = (int)(Math.random() * 4 + 1);
			if(rand == 1)
				solid[0][i] = new Solid(new Rectangle(0, i * Tile.size, Tile.size, Tile.size),0,i,Tile.wall_left1);
			else if(rand == 2)
				solid[0][i] = new Solid(new Rectangle(0, i * Tile.size, Tile.size, Tile.size),0,i,Tile.wall_left2);
			else if(rand == 3)
				solid[0][i] = new Solid(new Rectangle(0, i * Tile.size, Tile.size, Tile.size),0,i,Tile.wall_left3);
			else
				solid[0][i] = new Solid(new Rectangle(0, i * Tile.size, Tile.size, Tile.size),0,i,Tile.wall_left4);
			
			rand = (int)(Math.random() * 4 + 1);
			
			if(rand == 1)
				solid[width-1][i]= new Solid(new Rectangle((width-1)*Tile.size, i*Tile.size, Tile.size, Tile.size),width-1,i,Tile.wall_right1);
			else if(rand == 2)
				solid[width-1][i]= new Solid(new Rectangle((width-1)*Tile.size, i*Tile.size, Tile.size, Tile.size),width-1,i,Tile.wall_right2);
			else if(rand == 3)
				solid[width-1][i]= new Solid(new Rectangle((width-1)*Tile.size, i*Tile.size, Tile.size, Tile.size),width-1,i,Tile.wall_right3);
			else
				solid[width-1][i]= new Solid(new Rectangle((width-1)*Tile.size, i*Tile.size, Tile.size, Tile.size),width-1,i,Tile.wall_right4);
			
		}
		
		//item[5][5] = new Item(new Rectangle(5*Tile.size, 5*Tile.size, Tile.size, Tile.size),5,5,Tile.chest_open);
		//item[7][7] = new Item(new Rectangle(7*Tile.size, 7*Tile.size, Tile.size, Tile.size),7,7,Tile.chest_closed);
		solid[0][0] = new Solid(new Rectangle(0, 0, Tile.size, Tile.size),0,0,Tile.corner_tl);
		solid[width-1][0] = new Solid(new Rectangle((width-1)*Tile.size, 0, Tile.size, Tile.size),width-1,0,Tile.corner_tr);
		solid[0][height-1]= new Solid(new Rectangle(0, (height-1)*Tile.size, Tile.size, Tile.size),0,height-1,Tile.corner_bl);
		solid[width-1][height-1]= new Solid(new Rectangle((width-1)*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size),width-1,height-1,Tile.corner_br);
	}
	
	public void generateRooms()
	{
		int num_rooms = (int)(Math.random()*((maxRooms - minRooms)+1)+minRooms);//populate the map full of rooms
		System.out.println("Total # of Rooms: "+num_rooms);
		for(int i = 1; i <= num_rooms; i++)
		{
			rooms.put(i, new Room(num_level, i));
		}
		bridgeConnections();
		showRooms();
		loadroom();
	}
	
	private void bridgeConnections()//assign rooms to other rooms
	{
		Stack<Integer> chosen = new Stack<Integer>();//used to store numbers that have been chosen, helps to tell us the remaining numbers that need to be chosen
		LinkedBlockingQueue<RoomNumbertoDirectionPair> queued = new LinkedBlockingQueue<RoomNumbertoDirectionPair>();//represents numbers that need to be "processed"
		
		//int num_connections = 0, max_connections = rooms.size()-1;//number of connections between rooms, max is rooms.size()-1
		int prevlocation = -1;
		
		prevlocation = firstroom(2);//1 = left, 2 = right, 3 = up, 4 = down //1 and 2 are bridged
		//num_connections++;
		queued.add(new RoomNumbertoDirectionPair(2,prevlocation));//2nd room is next to be processed
		chosen.push(2);
		
		while(queued.peek().room_number != rooms.size() && chosen.peek() != rooms.size())
		{	
			int currentroom = queued.peek().room_number;
			prevlocation = queued.peek().direction;
			int sum = 0;
			//find out which directions we want; some, all, or none
			if(prevlocation != 2 && coinflip() && chosen.peek() != rooms.size())//left //if the previous location was from the right, we shouldn't go left
			{
				sum++;
				chosen.push(chosen.peek()+1);
				leftandright(currentroom,chosen.peek());
				queued.add(new RoomNumbertoDirectionPair(chosen.peek(),1));
			}
			if(prevlocation != 1 && coinflip()&& chosen.peek() != rooms.size())//right
			{
				sum++;
				chosen.push(chosen.peek()+1);
				leftandright(chosen.peek(),currentroom);
				queued.add(new RoomNumbertoDirectionPair(chosen.peek(),2));
			}
			if(prevlocation != 4 && coinflip()&& chosen.peek() != rooms.size())//up
			{
				sum++;
				chosen.push(chosen.peek()+1);
				upanddown(currentroom, chosen.peek());
				queued.add(new RoomNumbertoDirectionPair(chosen.peek(),3));
			}
			if(prevlocation != 3 && coinflip()&& chosen.peek() != rooms.size())//down
			{
				sum++;
				chosen.push(chosen.peek()+1);
				upanddown(chosen.peek(),currentroom);
				queued.add(new RoomNumbertoDirectionPair(chosen.peek(),4));
			}
			if(sum != 0)//none picked, at most 3
			{
				queued.remove();//dequeue
			}
			else
			{
				System.out.println("Nothing chosen");
				//need to decide if we are to leave it as a "leaf"
				//check (num_connections/max_connections)				
			}
		}
	}
	
	private void loadroom()
	{
		
		setbackgroundtiles();
		setedge();
		//System.out.println(playerlocation);
		if(rooms.get(playerlocation).left != 0)
		{
			solid[0][center_h-1] = new Solid(new Rectangle(0, (center_h-1)*Tile.size, Tile.size, Tile.size), 0, center_h-1, Tile.wall_left_open_top);
			solid[0][center_h+1] = new Solid(new Rectangle(0, (center_h+1)*Tile.size, Tile.size, Tile.size), 0, center_h+1, Tile.wall_left_open_bottom);
			solid[0][center_h] = new Solid(new Rectangle(0, (center_h)*Tile.size, Tile.size, Tile.size), 0, center_h, Tile.blank);
			bg[0][center_h] = new Background(new Rectangle(0, (center_h)*Tile.size, Tile.size, Tile.size),Tile.floor1);
			left = true;
			nextleft = rooms.get(playerlocation).left;
			
					}
		if(rooms.get(playerlocation).right != 0)
		{
			solid[width-1][center_h-1] = new Solid(new Rectangle((width-1)*Tile.size, (center_h-1)*Tile.size, Tile.size, Tile.size), (width-1), center_h-1, Tile.wall_right_open_top);
			solid[width-1][center_h+1] = new Solid(new Rectangle((width-1)*Tile.size, (center_h+1)*Tile.size, Tile.size, Tile.size), (width-1), center_h+1, Tile.wall_right_open_bottom);
			solid[width-1][center_h] = new Solid(new Rectangle((width-1)*Tile.size, (center_h)*Tile.size, Tile.size, Tile.size), (width-1), center_h, Tile.blank);
			bg[width-1][center_h] = new Background(new Rectangle((width-1)*Tile.size, (center_h)*Tile.size, Tile.size, Tile.size), Tile.floor2);
			right = true;
			nextright = rooms.get(playerlocation).right;
		}
		if(rooms.get(playerlocation).up != 0)
		{
			solid[center_w-1][0] = new Solid(new Rectangle((center_w-1)*Tile.size, 0, Tile.size, Tile.size), center_w-1, 0, Tile.wall_open_left);
			solid[center_w+1][0] = new Solid(new Rectangle((center_w+1)*Tile.size, 0, Tile.size, Tile.size), center_w+1, 0, Tile.wall_open_right);
			solid[center_w][0] = new Solid(new Rectangle((center_w)*Tile.size, 0, Tile.size, Tile.size), center_w, 0, Tile.blank);
			bg[center_w][0] = new Background(new Rectangle((center_w)*Tile.size, 0, Tile.size, Tile.size), Tile.floor3);
			up = true;
			nexttop = rooms.get(playerlocation).up;
		}
		if(rooms.get(playerlocation).down != 0)
		{
			solid[center_w-1][height-1] = new Solid(new Rectangle((center_w-1)*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size), center_w-1, (height-1), Tile.wall_open_left);
			solid[center_w+1][height-1] = new Solid(new Rectangle((center_w+1)*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size), center_w+1, (height-1), Tile.wall_open_right);
			solid[center_w][height-1] = new Solid(new Rectangle((center_w)*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size), center_w, (height-1), Tile.blank);
			bg[center_w][height-1] = new Background(new Rectangle((center_w)*Tile.size, (height-1)*Tile.size, Tile.size, Tile.size), Tile.floor4);
			down = true;
			nextbottom = rooms.get(playerlocation).down;
		}
		
		if(playerlocation == rooms.size())
			bg[center_w][center_h] = new Background(new Rectangle((center_w)*Tile.size, (center_h)*Tile.size, Tile.size, Tile.size), Tile.exit);
		else if(playerlocation == 1)
			bg[center_w][center_h] = new Background(new Rectangle((center_w)*Tile.size, (center_h)*Tile.size, Tile.size, Tile.size), Tile.sealed);
		else
			bg[center_w][center_h] = new Background(new Rectangle((center_w)*Tile.size, (center_h)*Tile.size, Tile.size, Tile.size), Tile.floor1);
		
		if(rooms.get(playerlocation).item != null)
		{
			item[rooms.get(playerlocation).item.row][rooms.get(playerlocation).item.col] = rooms.get(playerlocation).item;
		}
		
		for(Solid s: rooms.get(playerlocation).blocks)
		{
			solid[s.row][s.col]= s;
		}
		
		enemies =  new ArrayList<EnemyEntities>();
		for(EnemyEntities e: rooms.get(playerlocation).enemies)
		{
			enemies.add(e);
		//	Core.addEntity(e);
		}
		//Repeat this for rooms.get(playerlocation).enemies , items, and traps (when added)
	}
	
	private int firstroom(int r)
	{		
		//left -> right -> up -> down 
		int direction = 1;
		if(coinflip())//else if's used for four actual coin flips		
		{
			leftandright(1,r);
			direction = 1;
			return direction;
		}
		else if(coinflip())
		{
			leftandright(r,1);
			direction = 2;
			return direction;
		}
		else if(coinflip())
		{
			upanddown(1,r);
			direction = 3;
			return direction;
		}
		else if(coinflip())
		{
			upanddown(r,1);
			direction = 4;
			return direction;
		}
		//in the rare case that all four flips fail
		leftandright(1,r);
		return direction;
	}
	
	public void showRooms()//debug purposes
	{
		for(int i = 1; i <= rooms.size(); i++)
		{
			Room r = rooms.get(i);
			System.out.println(i+"\t: left{"+ r.left+"}\tright{" + r.right + "}\tup{" + r.up +"}\tdown{"+ r.down +"}");
		}
	}
	
	private void leftandright(int left, int right)
	{
		rooms.get(left).left = right;
		rooms.get(right).right = left;
	}
	
	private void upanddown(int top, int bottom)
	{
		rooms.get(top).up = bottom;
		rooms.get(bottom).down = top;
	}
	
	private boolean coinflip()
	{
		return (Math.random() > 0.5);
	}
	
	public void ExitReached()
	{
		rooms.clear();//level is cleared and next level is to load
		num_level++;
		playerlocation = 1;
		EntityPlayer.player_room_num = playerlocation;
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
		for(EnemyEntities e: rooms.get(playerlocation).enemies)
		{
			Core.remove(e);
		}
		setbackgroundtiles();
		setedge();
		generateRooms();
	}
	
	public void tick()
	{
		if(left)//check if this room in particular has an exit, then check if the player is at this location
		{
			if(EntityPlayer.tX == -1 && EntityPlayer.tY == center_h)
			{
				EntityPlayer.player_room_num = nextleft;
				playerlocation = EntityPlayer.player_room_num;
				EntityPlayer.tX = width-1;//load player at the right
				setedge();
				loadroom();
			}
		}
		if(right)
		{
			if(EntityPlayer.tX == width && EntityPlayer.tY == center_h)
			{
				EntityPlayer.player_room_num = nextright;
				playerlocation = EntityPlayer.player_room_num;
				EntityPlayer.tX = 0;//load player at the right
				setedge();
				loadroom();
			}
		}
		if(up)
		{
			if(EntityPlayer.tX == center_w && EntityPlayer.tY == -1)
			{
				EntityPlayer.player_room_num = nexttop;
				playerlocation = EntityPlayer.player_room_num;
				EntityPlayer.tY = height-1;//load player at the right
				setedge();
				loadroom();
			}
		}
		if(down)
		{
			if(EntityPlayer.tX == center_w && EntityPlayer.tY == height)
			{
				EntityPlayer.player_room_num = nextbottom;
				playerlocation = EntityPlayer.player_room_num;
				EntityPlayer.tY = 0;//load player at the right
				setedge();
				loadroom();
			}
		}
		if(playerlocation == rooms.size())
		{
			if(EntityPlayer.tX == center_w && EntityPlayer.tY == center_h)
			{
				ExitReached();
			}
		}
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
		
		for(EnemyEntities e: rooms.get(playerlocation).enemies)
		{ 	
			e.render(g);
		}
		
	}
	
	private class RoomNumbertoDirectionPair
	{
		public int room_number;
		public int direction;
		public RoomNumbertoDirectionPair(int room_number, int direction)
		{
			this.room_number = room_number;
			this.direction = direction;
		}
	}
	
	//Testing Purposes. Probably dont need this
	public ArrayList<EnemyEntities> retrieveEnemies()
	{
		return enemies;
	}
	
	//Enemies move right after the Player takes there turn
	public void enemiesMove()
	{
		for(EnemyEntities e: rooms.get(playerlocation).enemies)
		{ 	
			e.takeTurn();
			
			// Arraylist<Int> = findpath(e.x,e.y);
	//		e.move(updateX.get(i), updateY.get(i));
		}
		//Updates the coordinates where the enemies are suppose to move
		/*for(int i = 0; i < enemies.size()-1; i++)
		{ 	
			//System.out.println(enemies.get(i))
			//
    		enemies.get(i).updateX(updateX.get(i));
    		enemies.get(i).updateY(updateY.get(i));	
    	}*/
	}
	
	//Player checks whether there is any enemy in this space or not
	public boolean canMove(int x, int y) {
		for(EnemyEntities e: rooms.get(playerlocation).enemies)
		{ 	
			if((e.getlocationX()) == x && (e.getlocationY() == y)) 
			{
				return false;
			}
		}
		return true;
	}
	
	//Holds the coordinates of where the enemies will move next
	//ArrayList<Integer> updateX;// = new ArrayList<Integer>();
	//ArrayList<Integer> updateY;// = new ArrayList<Integer>();
	
	//Where the pathfinding happens
	public void findPathTowardsPlayer(int enemyX, int enemyY)
	{
		Map map = new Map(solid);

		AStarPathFinder pathFinder = new AStarPathFinder(map, MAX_PATH_LENGTH, false);
        Path path = pathFinder.findPath(null, enemyX, enemyY, EntityPlayer.tX, EntityPlayer.tY);
		
        //System.out.println(path.getX(0) + ", "+ path.getY(0));
        //Crashes when an enemy is trapped between Obstacles
        int length = path.getLength();
        //System.out.println("Found path of length: " + length + ".");
        
        //updateX = new ArrayList<Integer>();
        //updateY = new ArrayList<Integer>();
        
        //updateX.add(path.getX(0));
        //updateY.add(path.getY(0));
        
        for(int i = 0; i < length; i++) 
        {
        	//System.out.println("Move to: " + path.getX(i) + "," + path.getY(i) + ".");
        }
		
	}
	
	//Somehow need to call the move method in EnemyEntities
}
