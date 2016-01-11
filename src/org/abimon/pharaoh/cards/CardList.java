package org.abimon.pharaoh.cards;

import org.abimon.pharaoh.materials.BaseMaterial;

public class CardList {
	public static final Card HUT = new BasicCard("Hut", BaseMaterial.MUDBRICK, BasicRole.BRICKLAYER); //Vault + 2
	
	static{
		CardDB.registerCard(HUT, 6);
	}
}
