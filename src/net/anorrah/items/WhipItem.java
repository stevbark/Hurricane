package net.anorrah.items;

public class WhipItem extends MeleeWeaponItem 
{

	public WhipItem(int levelBonus) 
	{
		super(levelBonus);
		super.itemDescription = "Whip";
		// TODO Auto-generated constructor stub
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Whip";
	}
	
}
