package net.anorrah.items;

import java.util.ArrayList;

import net.anorrah.items.bonus.ArmorBonus;
import net.anorrah.items.bonus.DuelingBonus;
import net.anorrah.items.bonus.GABonus;
import net.anorrah.items.bonus.TeleportBonus;
import net.anorrah.items.bonus.bonus;
import net.anorrah.items.bonus.knockback;
import net.anorrah.items.bonus.venganceBonus;

public class ArmorItem extends ItemObject {

//	double defense;
	bonus bon = new knockback();
	public ArmorItem( int currentLevel) {
		super(currentLevel);
		myBonus.add(new ArmorBonus( enchantment));
		name = "Armor";
		
		possibleBonuses = new ArrayList<bonus>() {{
		    add(new knockback());
		    add(new GABonus());
		    add(new TeleportBonus());
		    add(new DuelingBonus());
		    add(new venganceBonus());
		}};
		generateBonus();
		super.itemDescription = "live a while longer. blocks 2 (+"+enchantment+" level) damage per hit.";
		// TODO Auto-generated constructor stub
	}

	
	/*public String description() {
		// TODO Auto-generated method stub
		return super.itemDescription;
	}*/
	
//	public void onBeenHit(enemyEntities enemy,damageObject damage)
//	{
//		
//		TeleportBonus x = new TeleportBonus();
//		x.onBeenHit(enemy,damage);
//		System.out.println("hit for " + damage.damage + " damage");
//	}

	

	
	
	
	

}
