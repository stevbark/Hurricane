package net.anorrah;

public class damageObject {
	
	public int damage;
	public enum Type {physical, fire, ice, lightning, dark};
	public Type type;
	
	public damageObject(int damage, Type type)
	{
		this.damage = damage;
		this.type = type;
	}
}
