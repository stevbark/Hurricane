package net.anorrah.items;

import net.anorrah.items.bonus.invisibilityBonus;

public class ringOfInvisibility extends otherObject {
	
	
	public ringOfInvisibility(int currentLevel) {
		super(currentLevel);
		myBonus.add(new invisibilityBonus(3));
		// TODO Auto-generated constructor stub
		name="Ring of Invisibility";
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

}
