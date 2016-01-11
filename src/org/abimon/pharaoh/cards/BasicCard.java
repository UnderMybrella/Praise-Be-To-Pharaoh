package org.abimon.pharaoh.cards;

import org.abimon.pharaoh.materials.Material;

public class BasicCard implements Card {

	String name;
	Material material;
	Role role;

	public BasicCard(String name, Material material, Role role){
		this.name = name;
		this.material = material;
		this.role = role;
	}

	@Override
	public Material getMaterial() {
		return material;
	}

	@Override
	public Role[] getPerformableRoles() {
		return new Role[]{role};
	}

	@Override
	public String getName(){
		return name;
	}

}
