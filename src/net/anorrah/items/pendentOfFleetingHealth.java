package net.anorrah.items;

import net.anorrah.items.bonus.tempHealthBonus;

public class pendentOfFleetingHealth extends otherObject {

	public pendentOfFleetingHealth(int currentLevel) {
		super(currentLevel);
		super.itemDescription = "A shield that blocks 30 points of damage that lasts for 4 turns. ";
		myBonus.add(new tempHealthBonus(4,30));
		// TODO Auto-generated constructor stub
	}

	/*@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
