package net.anorrah.items;

public class NoItem extends ItemObject
{

	public NoItem(int currentLevel) {
		super(currentLevel);
		name="NONE";
	}

	@Override
	public String description() {
		return "";
	}

}
