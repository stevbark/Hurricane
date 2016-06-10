package net.anorrah.items;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import net.anorrah.Core;
import net.anorrah.Entity;
import net.anorrah.items.bonus.bonus;

public abstract class ItemObject {
	
	public int[] id;
	public String name="NONE";
	public String itemDescription = "";
	protected int enchantment;
	protected ArrayList<bonus> possibleBonuses;
	protected  ArrayList<bonus> myBonus; 
	protected int charges;
	protected boolean hasCharges = false;
	protected bonus onlyForRegen = null;
	protected bonus generatedBonus = null;
	
	public ItemObject(int currentLevel)
	{
		enchantment = generateBonus(currentLevel); 	
		myBonus = new ArrayList<bonus>();
		possibleBonuses = new ArrayList<bonus>();
		
	}
	
	public boolean hasCharges()
	{
		return hasCharges;
	}
	
	public bonus getBonus()
	{
		return generatedBonus;
	}
	
	public int charges()
	{
		return charges;
	}
	
	public String description(){
		if(generatedBonus !=null)
		{
			return itemDescription + " " + generatedBonus.description(); 
		}
		else
		{
			return itemDescription;
		}
	}
	
	// This is to generate random bonuses based on the floor the player is on. 
	public int generateBonus(int currentLevel) 	
	{
		int bonusSeed = ((int) (Math.random()*100))%10;
		
			if(bonusSeed >=0 &&bonusSeed <=4)
			{
				return currentLevel;
			}				
			else if(bonusSeed >=5 &&bonusSeed <=7)
			{
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
			}
				
				else if(bonusSeed >=8 &&bonusSeed <=9)
				{
				
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
			
			return currentLevel;
		}

			
	
	
	public Object generateBonus()
	{
		 if( ((int) (Math.random()*100)) <50)
		 {
			 // Since all items have this function run but some items will have no possible bonuses, 
			 // this statement will prevent any sort of problems. 
			 if(possibleBonuses.size()>0)
			 {
				 int idx = new Random().nextInt(possibleBonuses.size());
				 generatedBonus = possibleBonuses.get(idx);
				 myBonus.add(generatedBonus);
				 System.out.println("bonus " + generatedBonus.getClass());
				 
			 }
		 }
		 return null;
	}
	
	public void onEquip(Entity user)
	{
		if(myBonus !=null)
		{
			// Items are remembered as a list of bonuses. Once equipped each item will be added to the list of bonuses for the player.
			for(bonus b:myBonus)
			{
				b.onEquipped(user);
				Core.player.addToList(b);
			}
		}
	}
	
	public void onUnequip(Entity user)
	{
		// Since items are remembered as a list of bonuses removing the item will not change the player without removing the bonuses associated with it.
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
			
			use();
			{	
				user.addToList(onlyForRegen);
			}
					charges--;
			if(hasCharges && charges <=0)
			{
				assert(Core.player.getUsableItem() == this);
				
				Core.player.setUsableItem(new NoItem(0));
			}
		}
	}
	protected void use()
	{
		
	}
	
	public String getName(){
		return name;
	}
}
