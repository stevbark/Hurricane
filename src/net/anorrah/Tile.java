package net.anorrah;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tile 
{
	public static int[] blank = {-1,-1};
	
	//background stuff
	public static int[] back = {0, 0};
	public static int[] path = {1,0};
	public static int[] grass = {2,0};
	public static int[] whitespace = {3,0};
	
	//collide-able/solids
	public static int[] wall = {0,0};
	public static int[] hole = {1,0};
	public static int[] rock = {2,0};
	
	//items
	
	//characters
	public static int[] playertile = {0, 0};//temporary
	
	public static int size = 32;
	public static BufferedImage background, items, characters, solid;
	
	public Tile()
	{
		try
		{
			Tile.background = ImageIO.read(new File("res/images/tile_test.png"));
			//Tile.solid = ImageIO.read(new File("res/images/__.png"));
			//Tile.items = ImageIO.read(new File("res/images/__.png"));
			Tile.characters = ImageIO.read(new File("res/images/character_test.png"));
		}
		catch(Exception e)
		{
			System.out.println("Error loading images");
		}
	}

}
