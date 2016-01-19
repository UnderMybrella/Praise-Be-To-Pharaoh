package org.abimon.pharaoh.cards;

public class BasicRole implements Role {
	
	public static final BasicRole SLAVE = new BasicRole("Slave"); 				//Craftsman
	public static final BasicRole MASON = new BasicRole("Mason");				//Labourer
	
	public static final BasicRole RITUAL = new BasicRole("Ritual"); 		 	//Legionary
	public static final BasicRole ARCHITECT = new BasicRole("Architect");		//Architect
	public static final BasicRole SLAVEMASTER = new BasicRole("Slave Master");	//Patron
	public static final BasicRole MERCHANT = new BasicRole("Merchant"); 		//Merchant?!?

	String name;
	
	public BasicRole(String name){
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
