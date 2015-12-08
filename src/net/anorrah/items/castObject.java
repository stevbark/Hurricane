package net.anorrah.items;

import net.anorrah.items.bonus.regenerationBonus;


public class castObject extends otherObject {

	private int restoreATurn = 10;
	
	public castObject(int currentLevel) {
		super(currentLevel);
		charges = 4;
		myBonus.add(new regenerationBonus(restoreATurn, 7));
<<<<<<< HEAD
		name="Cast";
=======
		super.itemDescription ="heals 10 hp per second for  7 secondsn";
>>>>>>> f668498ae82103a5a99421ad6f358820a26c4bed
	}

/*	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
