package org.abimon.pharaoh.actions;

import org.abimon.pharaoh.cards.Role;
import org.abimon.pharaoh.server.PharaohPlayer;

public class Think implements Action {

	@Override
	public Role getRoleRequired() {
		return null;
	}

	@Override
	public String getName() {
		return "Think";
	}

	@Override
	public void perform(final PharaohPlayer player) {
		
	}

}
