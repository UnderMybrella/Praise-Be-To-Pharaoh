package org.abimon.pharaoh.cards;

import org.abimon.pharaoh.materials.Material;

public interface Card {
	
	public Material getMaterial();
	public Role[] getPerformableRoles();
	public String getName();
}
