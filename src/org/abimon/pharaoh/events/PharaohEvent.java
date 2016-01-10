package org.abimon.pharaoh.events;

public class PharaohEvent {
	boolean cancelled = false;
	
	public void setCancelled(boolean cancelled){
		this.cancelled = cancelled;
	}
	
	public boolean getCancelled(){
		return cancelled;
	}
}
