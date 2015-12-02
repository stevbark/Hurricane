package net.anorrah;

public class bandAidObject extends otherObject {

	private int restoreATurn = 10;
	 
	public bandAidObject(int currentLevel) {
		super(currentLevel);
		charges = 3;
		myBonus.add(new regenerationBonus(restoreATurn));
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

}
