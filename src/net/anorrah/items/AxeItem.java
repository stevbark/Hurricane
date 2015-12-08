package net.anorrah.items;

public class AxeItem extends MeleeWeaponItem {

	public AxeItem(int levelBonus) {
		super(levelBonus);
		super.itemDescription = "Axe";
		
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Axe";
	}

}
