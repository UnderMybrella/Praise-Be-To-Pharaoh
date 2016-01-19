package org.abimon.pharaoh.cards;

import org.abimon.pharaoh.actions.Action;
import org.abimon.pharaoh.actions.BasicAction;
import org.abimon.pharaoh.actions.Think;
import org.abimon.pharaoh.materials.BaseMaterial;

public class CardList {
	
	public static final Action SLAVE = new BasicAction(BasicRole.SLAVE); 				//Craftsman
	public static final Action MASON = new BasicAction(BasicRole.MASON); 				//Labourer
	
	public static final Action RITUAL = new BasicAction(BasicRole.RITUAL); 				//Legionary
	public static final Action ARCHITECT = new BasicAction(BasicRole.ARCHITECT);		//Architect
	
	public static final Action SLAVEMASTER = new BasicAction(BasicRole.SLAVEMASTER); 	//Patron
	public static final Action MERCHANT = new BasicAction(BasicRole.MERCHANT);			//Merchant
	
	public static final Action THINK = new Think();
	
	public static final Card HUT = new BasicCard("Hut", BaseMaterial.MUDBRICK, BasicRole.SLAVE); 						//May labour from the hand in addition to the pool
	public static final Card SERDAB = new BasicCard("Serdab", BaseMaterial.MUDBRICK, BasicRole.SLAVE);					//Petition using only 2 cards
	public static final Card PALISADE = new BasicCard("Palisade", BaseMaterial.MUDBRICK, BasicRole.SLAVE); 				//Immune to Rituals
	public static final Card MASTABA = new BasicCard("Mastaba", BaseMaterial.MUDBRICK, BasicRole.SLAVE); 				//Vault + 2
	
	public static final Card SLAVEMARKET = new BasicCard("Slave Market", BaseMaterial.LIMESTONE, BasicRole.MASON);		//When performing a slavemaster action, you may grab one additional slave from the deck
	
	public static final Card QUARRY = new BasicCard("Stone Quarry", BaseMaterial.STONE, BasicRole.MERCHANT); 			//When performing a mason action, grab one more stone. When performing an architect action, may move one stone from stockpile
	
	static{
		Database.registerAction(SLAVE);
		Database.registerAction(MASON);
		
		Database.registerAction(RITUAL);
		Database.registerAction(ARCHITECT);
		
		Database.registerAction(SLAVEMASTER);
		Database.registerAction(MERCHANT);
		
		Database.registerAction(THINK);
		
		
		
		Database.registerCard(HUT, 6);
		Database.registerCard(PALISADE, 6);

		Database.registerCard(QUARRY, 3);
	}
}
