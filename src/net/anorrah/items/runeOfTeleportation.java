package net.anorrah.items;

import net.anorrah.items.bonus.TeleportFromItemBonus;

public class runeOfTeleportation extends otherObject {

	public runeOfTeleportation(int currentLevel) {
		super(currentLevel);
		myBonus.add(new TeleportFromItemBonus());
		charges=5;
		hasCharges = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

}
