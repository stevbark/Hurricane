package net.anorrah.items;

import net.anorrah.items.bonus.invisibilityBonus;

public class ringOfInvisibility extends otherObject {

	public ringOfInvisibility(int currentLevel) {
		super(currentLevel);
		myBonus.add(new invisibilityBonus(3));
		super.itemDescription =" Ring of Invisibility:      untargetable for 3 turns.";
		// TODO Auto-generated constructor stub
	}

	/*@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
