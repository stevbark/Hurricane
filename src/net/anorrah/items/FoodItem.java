package net.anorrah.items;

import net.anorrah.items.bonus.regenerationBonus;

public class FoodItem extends ItemObject {

	private int restoreATurn = 10;
	public FoodItem(int currentLevel) {
		super(currentLevel);
		charges = 2;
		myBonus.add(new regenerationBonus(restoreATurn, 10));
<<<<<<< HEAD
		name="Food";
=======
		super.itemDescription ="SO rotten. heals 10 hp per second for 10 seconds. cannot move while eating.";
>>>>>>> f668498ae82103a5a99421ad6f358820a26c4bed
	}

/*	@Override
	public String description() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return "MMmmm Mmm good";
	}
=======
		return null;
	}*/
>>>>>>> f668498ae82103a5a99421ad6f358820a26c4bed
}
