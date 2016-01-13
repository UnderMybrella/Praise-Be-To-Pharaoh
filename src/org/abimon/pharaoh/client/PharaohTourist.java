package org.abimon.pharaoh.client;

import java.net.Socket;

import org.abimon.pharaoh.server.PharaohPlayer;

public class PharaohTourist extends PharaohPlayer{

	public PharaohTourist(Socket server) {
		super(server, false);
	}
	
	public void handleName(String name){}

	public void setName(String name) {
		this.name = name;
	}

}
