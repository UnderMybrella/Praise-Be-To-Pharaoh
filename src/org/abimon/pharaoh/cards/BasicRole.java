package org.abimon.pharaoh.cards;

public class BasicRole implements Role {
	
	public static final BasicRole BRICKLAYER = new BasicRole("Brick Layer");

	String name;
	
	public BasicRole(String name){
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
