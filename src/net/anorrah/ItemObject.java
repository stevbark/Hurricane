package net.anorrah;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public abstract class ItemObject {
	
	private String itemDescription;
	protected int bonus;
	protected ArrayList<Object> possibleEnchantments;
	
	public ItemObject(int currentLevel)
	{
		bonus = generateBonus(currentLevel); 	
	}
	
	public abstract String description();
	
	public int generateBonus(int currentLevel) 	
	{
		int bonusSeed = ((int) (Math.random()*100))%10;
		switch(bonusSeed)
		{
			default:
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				return currentLevel;
				
			case 5:
			case 6:
			case 7:
				if( ((int) (Math.random()*100))%2 ==0)
				{
					return currentLevel +1; 
				}
				else
				{
					if(currentLevel -1 >0)
					{	
						return currentLevel -1;
					}
					else
					{
						return 0;
					}
				}
				
			case 8:
			case 9:
				
				if( ((int) (Math.random()*100))%2 ==0)
				{
					return currentLevel +2; 
				}
				else
				{
					if(currentLevel -2 >0)
					{	
						return currentLevel -2;
					}
					else
					{
						return 0;
					}
				}
		}

			
	}
	
	
	
	
	public Object generateEnchantment()
	{
		 if( ((int) (Math.random()*100)) <20)
		 {
			 if(possibleEnchantments.size()>0)
			 {
				 int idx = new Random().nextInt(possibleEnchantments.size());
				 Object random = (possibleEnchantments.get(idx));
				 return random;
			 }
		 }
		 return null;
	}
	
	
}
