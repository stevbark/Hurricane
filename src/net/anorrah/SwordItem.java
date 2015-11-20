package net.anorrah;

public class SwordItem extends ItemObject {

	double damage;
	String str;
	public SwordItem(String r)
	{
		super();
		damage = 5;
		str = r;
	}

	public void use()
	{
		
	}
	
	@Override
	public String description() {
		
		return "stabby stabby stab! " + str;
	}
	
	
}
