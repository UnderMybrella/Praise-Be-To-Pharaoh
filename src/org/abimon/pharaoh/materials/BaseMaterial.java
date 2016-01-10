package org.abimon.pharaoh.materials;

public class BaseMaterial implements Material{
	
	public static final BaseMaterial WOOD = new BaseMaterial("Wood", 1);
	public static final BaseMaterial LIMESTONE = new BaseMaterial("Limestone", 1);
	
	public static final BaseMaterial BRONZE = new BaseMaterial("Bronze", 2);
	public static final BaseMaterial GRANITE = new BaseMaterial("Granite", 2);
	
	public static final BaseMaterial STONE = new BaseMaterial("Stone", 3);
	public static final BaseMaterial MARBLE = new BaseMaterial("Marble", 3);
	
	public static final BaseMaterial AMETHYST = new BaseMaterial("Amethyst", 4);
	public static final BaseMaterial MALACHITE = new BaseMaterial("Malachite", 4);

	String name;
	int value;
	
	public BaseMaterial(String name, int value){
		this.name = name;
		this.value = value;
	}
	
	@Override
	public Site getBuildingSite() {
		return new BasicSite(this);
	}

	@Override
	public int getBuildingCost() {
		return value;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getName() {
		return name;
	}
	
}
