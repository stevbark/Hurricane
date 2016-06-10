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

	public ArmorItem( int currentLevel) {
		super(currentLevel);
		myBonus.add(new ArmorBonus( enchantment));
		name = "Armor";
		
		possibleBonuses = new ArrayList<bonus>() {{
		    add(new GABonus());
		    add(new DuelingBonus());
		    add(new venganceBonus());
		}};
		generateBonus();
		super.itemDescription = "live a while longer. blocks 2 (+"+enchantment+" level) damage per hit.";
		
	}

	
	
	

	
	
	
	

}
