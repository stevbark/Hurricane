package net.anorrah;

import java.util.ArrayList;

public class ArmorItem extends ItemObject {

//	double defence;
	bonus bon = new knockback();
	public ArmorItem( int currentLevel) {
		super(currentLevel);
		
		myBonus.add(new ArmorBonus( enchantment));
		myBonus.add(bon);
		
		possibleBonuses = new ArrayList<bonus>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public void onBeenHit(enemyEntities enemy,damageObject damage)
//	{
//		
//		TeleportBonus x = new TeleportBonus();
//		x.onBeenHit(enemy,damage);
//		System.out.println("hit for " + damage.damage + " damage");
//	}

	

	
	
	
	

}
