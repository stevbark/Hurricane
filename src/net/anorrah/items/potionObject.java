package net.anorrah.items;

import net.anorrah.Entity;

public class potionObject extends otherObject {

	public potionObject(int currentLevel) {
		super(currentLevel);
		// TODO Auto-generated constructor stub
//		myBonus.add();
		charges = 1;
		hasCharges= true;
		super.itemDescription = "actually a placebo. Instantly heals 30 hp ";
	}
	
	public void onUseOnSelf(Entity user)
	{
		user.heal(30);
		super.onUseOnSelf(user);
	}
	/*@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
