package org.abimon.pharaoh.materials;

public class BasicSite implements Site {
	
	Material material;
	
	public BasicSite(Material material){
		this.material = material;
	}

	@Override
	public int getBuildingCost() {
		return material.getBuildingCost();
	}

	@Override
	public Material[] getAcceptableMaterials() {
		return new Material[]{material};
	}

	@Override
	public int getValue() {
		return material.getValue();
	}

	@Override
	public String getName() {
		return material.getName() + " site";
	}
}
