package net.anorrah;

import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class Map implements TileBasedMap {
	
	public int width = 21;
	public int height = 17;
	public int[][] map = new int[width][height];

	public Map(Solid[][] solid) {
		for(int i = 0; i < Core.level.bg.length; i++)
		{
			for(int j = 0; j < Core.level.bg[0].length; j++)
			{
				if(solid[i][j].id == Tile.blank)
				{
					map[i][j] = 0;
				}
				else map[i][j] = 1;
			}
		}
	}

	public boolean blocked(PathFindingContext ctx, int x, int y) {
		return map[x][y] != 0;
	}

	public float getCost(PathFindingContext ctx, int x, int y) {
		return 1.0f;
	}

	public int getHeightInTiles() {
		return 17;
	}

	public int getWidthInTiles() {
		return 21;
	}

	public void pathFinderVisited(int arg0, int arg1) {
		
	}
	
	public int[][] getMap()
	{
		return map;
	}

}
