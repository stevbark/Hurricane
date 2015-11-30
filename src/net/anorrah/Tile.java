package net.anorrah;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tile 
{
	public static int[] blank = {-1,-1};
	
	//background stuff
	public static int[] floor1 = {0, 0};
	public static int[] floor2 = {1, 0};
	public static int[] floor3 = {2, 0};
	public static int[] floor4 = {3, 0};
	public static int[] floor5 = {4, 0};
	public static int[] floor6 = {5, 0};
	public static int[] floor7 = {6, 0};
	public static int[] floorend = {7,0};
	
	//solids
	public static int[] wall_left1 = {0,0};
	public static int[] wall_left2 = {1,0};
	public static int[] wall_left3 = {2,0};
	public static int[] wall_left4 = {3,0};
	
	public static int[] wall_right1 = {0,1};
	public static int[] wall_right2 = {1,1};
	public static int[] wall_right3 = {2,1};
	public static int[] wall_right4 = {3,1};
	
	public static int[] wall_top1 = {0,3};
	public static int[] wall_top2 = {1,3};
	public static int[] wall_top3 = {2,3};
	public static int[] wall_top4 = {3,3};
	
	public static int[] wall_bottom1 = {0,4};
	public static int[] wall_bottom2 = {1,4};
	public static int[] wall_bottom3 = {2,4};
	public static int[] wall_bottom4 = {3,4};
	
	public static int[] corner_bl = {0,2};
	public static int[] corner_tr = {1,2};
	public static int[] corner_tl = {2,2};
	public static int[] corner_br = {3,2};
	public static int[] item  = {0,0};
	public static int[] item2  = {1,1};
	//items
	
	//characters
	public static int[] playertile_DOWN = {0, 0};
	public static int[] playertile_LEFT = {1, 0};
	public static int[] playertile_RIGHT = {2, 0};
	public static int[] playertile_UP = {3, 0};
	
	public static int size = 32;
	public static BufferedImage background, items, characters, solid;
	
	public Tile()
	{
		try
		{
			Tile.background = ImageIO.read(new File("res/images/floor_tiles.png"));
			Tile.solid = ImageIO.read(new File("res/images/wall_tiles.png"));
			Tile.items = ImageIO.read(new File("res/images/chests_32x32.png"));
			Tile.characters = ImageIO.read(new File("res/images/character_test.png"));
		}
		catch(Exception e)
		{
			System.out.println("Error loading images");
		}
	}

}
