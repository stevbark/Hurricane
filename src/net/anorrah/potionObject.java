package net.anorrah;

public class potionObject extends otherObject {

	public potionObject(int currentLevel) {
		super(currentLevel);
		// TODO Auto-generated constructor stub
//		myBonus.add();
		charges = 1;
		hasCharges= true;
	}
	
	public void onUseOnSelf(Entity user)
	{
		user.heal(30);
		super.onUseOnSelf(user);
	}
	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

}
