package net.anorrah.items;

import net.anorrah.items.bonus.regenerationBonus;

public class bandAidObject extends otherObject {

	private int restoreATurn = 10;
	 
	public bandAidObject(int currentLevel) {
		super(currentLevel);
		charges = 3;
		myBonus.add(new regenerationBonus(restoreATurn,5));
<<<<<<< HEAD
		name = "Band-Aid";
=======
		super.itemDescription = "now with Hello Kitty. Heals 10 hp per second for 5 seconds.";

>>>>>>> f668498ae82103a5a99421ad6f358820a26c4bed
	}

	/*@Override
	public String description() {
		// TODO Auto-generated method stub
		return super.itemDescription;
	}*/

}
