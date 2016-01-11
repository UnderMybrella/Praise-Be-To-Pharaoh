package org.abimon.pharaoh.actions;

import org.abimon.pharaoh.cards.Role;
import org.abimon.pharaoh.server.PharaohPlayer;

public class BasicAction implements Action{

	Role role;
	
	public BasicAction(Role role){
		this.role = role;
	}
	
	@Override
	public Role getRoleRequired() {
		return role;
	}

	@Override
	public String getName() {
		return role.getName();
	}

	@Override
	public void perform(PharaohPlayer player) {}

}
