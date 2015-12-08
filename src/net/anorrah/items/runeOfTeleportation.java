package net.anorrah.items;

import net.anorrah.items.bonus.TeleportFromItemBonus;

public class runeOfTeleportation extends otherObject {

	public runeOfTeleportation(int currentLevel) {
		super(currentLevel);
		myBonus.add(new TeleportFromItemBonus());
		charges=5;
		hasCharges = true;
		super.itemDescription = "Im gone. Teleport short distances ";
		// TODO Auto-generated constructor stub
		name="Rune of Teleportation";
	}

	/*@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
