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
//	public String name;
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

<<<<<<< HEAD
	@Override
	public abstract String description();
	
	
=======
	/*@Override
	public abstract String description();*/
>>>>>>> f668498ae82103a5a99421ad6f358820a26c4bed

//	public damageObject attack()
//	{
//	//	return new damageObject(damage, null);
//	}

	
}
