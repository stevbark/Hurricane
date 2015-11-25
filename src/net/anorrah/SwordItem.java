package net.anorrah;

public class SwordItem extends MeleeWeaponItem {

	double damage;
	String str;
	public SwordItem(String r, int currentLevel)
	{
		super(currentLevel);
		damage = 5;
		str = r;
	}

	public void use()
	{
		
	}
	
	@Override
	public String description() {
		
		return "stabby stabby stab! " + bonus;
	}
	
	
}
