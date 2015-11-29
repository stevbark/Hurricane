package net.anorrah;

public class damageObject {
	
	public double damage;
	public enum Type {physical, fire, ice, lightning, dark};
	public Type type;
	
	public damageObject(double damage, Type type)
	{
		this.damage = damage;
		this.type = type;
	}
}
