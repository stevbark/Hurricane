package net.anorrah.items;

import java.awt.Graphics;
import java.util.ArrayList;

import net.anorrah.items.bonus.DuelingBonus;
import net.anorrah.items.bonus.EnfeeblementBonus;
import net.anorrah.items.bonus.GABonus;
import net.anorrah.items.bonus.TeleportBonus;
import net.anorrah.items.bonus.VampiricBonus;
import net.anorrah.items.bonus.bonus;
import net.anorrah.items.bonus.fireMeleeBonus;
import net.anorrah.items.bonus.knockback;
import net.anorrah.items.bonus.venganceBonus;

public abstract class MeleeWeaponItem extends ItemObject{
	public int damage;
	public int[] id;
	
	public MeleeWeaponItem(int levelBonus)  
	{
		super(levelBonus);
		
		possibleBonuses = new ArrayList<bonus>() {{
		    add(new fireMeleeBonus(damage));
		    add(new EnfeeblementBonus());
		    add(new VampiricBonus());
		}};
		generateBonus();
	}
	
	public void render(Graphics g)
	{
		
	}


	public abstract String description();
	
	

	

	
}
