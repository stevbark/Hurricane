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
	public static int[] exit = {8,0};
	public static int[] sealed = {9,0};
	
	//solids
	public static int[] wall_left1 = {0,0};
	public static int[] wall_left2 = {1,0};
	public static int[] wall_left3 = {2,0};
	public static int[] wall_left4 = {3,0};
	
	public static int[] wall_right1 = {0,1};
	public static int[] wall_right2 = {1,1};
	public static int[] wall_right3 = {2,1};
	public static int[] wall_right4 = {3,1};
	
	public static int[] wall_top1 = {0,4};
	public static int[] wall_top2 = {1,4};
	public static int[] wall_top3 = {2,4};
	public static int[] wall_top4 = {3,4};
	
	public static int[] wall_bottom1 = {0,4};
	public static int[] wall_bottom2 = {1,4};
	public static int[] wall_bottom3 = {2,4};
	public static int[] wall_bottom4 = {3,4};
	
	public static int[] corner_bl = {0,2};
	public static int[] corner_tr = {1,2};
	public static int[] corner_tl = {2,2};
	public static int[] corner_br = {3,2};
	
	public static int[] wall_left_open_top = {0,5};
	public static int[] wall_left_open_bottom = {0,6};
	public static int[] wall_right_open_top = {1,5};
	public static int[] wall_right_open_bottom = {1,6};
	public static int[] wall_open_left = {2,5};
	public static int[] wall_open_right = {3,5};
	
	public static int[] pit = {0,7};
	public static int[] boulder = {1,7};
	
	//items
	public static int[] chest_open  = {0,0};
	public static int[] chest_closed  = {1,0};
	//item icons
	public static int[] armor_item = {0,2};

	public static int[] empty_item = {2,0};
	public static int[] sword_icon = {0,1};
	public static int[] hammer_icon = {1,1};
	public static int[] spear_icon = {2,1};
	public static int[] axe_icon = {3,1};
	public static int[] whip_icon = {4,1};
	public static int[] bow_icon = {5,1};
	public static int[] fireball_icon = {6,1};
	public static int[] lightning_icon = {7,1};
	public static int[] laser_icon = {8,1};
	
	public static int[] potion_icon = {3,0};
	public static int[] band_aid_icon = {4,0};
	public static int[] cast_icon = {5,0};
	public static int[] food_icon = {6,0};
	//weapon:melee enchantments, filters
	public static int[] no_enchant = {9,1};
	public static int[] fire_melee_enchant = {7,0};
	public static int[] enfeeblement = {8,0};
	public static int[] vampire_melee_enchant = {9,0};
	
	//characters
	public static int[] playertile_DOWN = {0, 0};
	public static int[] playertile_LEFT = {1, 0};
	public static int[] playertile_RIGHT = {2, 0};
	public static int[] playertile_UP = {3, 0};
	
	//enemies
	public static int[] axis_LEFT = {4, 0};
	public static int[] axis_RIGHT = {0, 1};
	public static int[] cannibal_LEFT = {1, 1};
	public static int[] cannibal_RIGHT = {2, 1};
	public static int[] cannibal_DOWN = {3, 1};
	public static int[] cannibal_UP = {4, 1};
	public static int[] charger_LEFT = {0, 2};
	public static int[] charger_RIGHT = {1, 2};
	public static int[] charger_DOWN = {2, 2};
	public static int[] charger_UP = {3, 2};
	public static int[] mage_LEFT = {4, 2};
	public static int[] mage_RIGHT = {0, 3};
	public static int[] mage_DOWN = {1, 3};
	public static int[] mage_UP = {2, 3};
	public static int[] rat_LEFT = {3, 3};
	public static int[] rat_RIGHT = {4, 3};
	public static int[] rat_DOWN = {0, 4};
	public static int[] rat_UP = {1, 4};
	public static int[] theif_LEFT = {2, 4};
	public static int[] theif_RIGHT = {3, 4};
	public static int[] theif_DOWN = {4, 4};
	public static int[] theif_UP = {5, 0};
	
	
	
	public static int size = 32;
	public static BufferedImage background, items, characters, solid;
	
	public Tile()
	{
		try
		{
			Tile.background = ImageIO.read(new File("res/images/floor_tiles.png"));
			Tile.solid = ImageIO.read(new File("res/images/wall_tiles.png"));
			Tile.items = ImageIO.read(new File("res/images/items.png"));
			Tile.characters = ImageIO.read(new File("res/images/character_test.png"));
			//Tile.cannibal = ImageIO.read(new File("res/images/cannibal.png"));
			//Tile.axis = ImageIO.read(new File("res/images/axis.png"));
		}
		catch(Exception e)
		{
			System.out.println("Error loading images");
		}
	}

}
