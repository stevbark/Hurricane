package net.anorrah.items;

public class SpearItem extends MeleeWeaponItem
{

	public SpearItem(int levelBonus) {
		super(levelBonus);
		super.itemDescription = "Spear";
	
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Spear";
	}
	
}
