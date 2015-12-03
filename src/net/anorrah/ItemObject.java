package net.anorrah;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public abstract class ItemObject {
	
	private String itemDescription;
	protected int enchantment;
	protected ArrayList<bonus> possibleBonuses;
	protected  ArrayList<bonus> myBonus; 
	protected int charges;
	
	public ItemObject(int currentLevel)
	{
		enchantment = generateBonus(currentLevel); 	
		myBonus = new ArrayList<bonus>();
		possibleBonuses = new ArrayList<bonus>();
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
			 if(possibleBonuses.size()>0)
			 {
				 int idx = new Random().nextInt(possibleBonuses.size());
				 myBonus.add(possibleBonuses.get(idx));
				 
			 }
		 }
		 return null;
	}
	
	public void onEquip(Entity user)
	{
		if(myBonus !=null)
		{
			for(bonus b:myBonus)
			{
				b.onEquipped(user);
				Core.player.addToList(b);
			}
		}
	}
	
	public void onUnequip(Entity user)
	{
		if(myBonus !=null)
		{
			for(bonus b:myBonus)
			{
				Core.player.removeFromList(b);
				b.onUnequipped(user);
			}
		}
	}
	
	public void onUseOnSelf(Entity user)
	{
		if(myBonus!=null){
			
			for(bonus b:myBonus)
			{	
				b.onUseOnSelf(user);				
			}
			charges--;
			if(charges <=0)
			{
				assert(Core.player.getUsableItem() == this);
				Core.player.setUsableItem(null);
			}
		}
	}
	
}
