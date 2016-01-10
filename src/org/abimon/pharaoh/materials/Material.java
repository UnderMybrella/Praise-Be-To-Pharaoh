package org.abimon.pharaoh.materials;

public interface Material {
	/**
	 * Gets the appropriate building site for a card of this material. For instance, Wood Material -> Basic Site
	 * @return a building site for this card
	 */
	public Site getBuildingSite();
	
	/**
	 * Gets the number of materials required to finish a building on a site for this material. For Instance, Wood Material Site -> 1
	 * @return the number of materials needed to lay the foundation
	 */
	public int getBuildingCost();
	
	/** 
	 * Gets the value of the material in question. Rarer or higher-value materials should be worth more normally. Normally correlates to the cost, but doesn't have to. For Instance, Wood -> 1
	 * @return the value of the material
	 */
	public int getValue();
	
	/**
	 * Gets the name of the material in question. For instance, Wood -> "Wood"
	 * @return
	 */
	public String getName();
}
