package org.abimon.pharaoh.actions;

import org.abimon.pharaoh.PharaohPlayer;
import org.abimon.pharaoh.cards.Role;

public interface Action {
	
	/**
	 * Used to determine what cards can perform this action
	 * @return Role required
	 */
	public Role getRoleRequired();
	
	/**
	 * Get the name of this action
	 * @return Name of the action
	 */
	public String getName();
	
	public void perform(PharaohPlayer player);
}
