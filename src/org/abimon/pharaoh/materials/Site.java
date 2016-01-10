package org.abimon.pharaoh.materials;

public interface Site {
	/**
	 * Gets the number of materials required to finish a building on this site. For Instance, Wood Material Site -> 1
	 * @return the number of materials needed to lay the foundation
	 */
	public int getBuildingCost();
	
	/**
	 * Gets the materials able to be used for the completion of this site
	 * @return an array of materials able to be used
	 */
	public Material[] getAcceptableMaterials();
	
	/** 
	 * Gets the value of the site in question. Rarer or higher-value materials should be worth more normally. Normally correlates to the cost, but doesn't have to. For Instance, Wood Site -> 1
	 * @return the value of the material
	 */
	public int getValue();
	
	/**
	 * Gets the name of the site in question. For instance, Wood -> "Wooden Site"
	 * @return
	 */
	public String getName();
}
