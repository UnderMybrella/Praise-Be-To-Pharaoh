package org.abimon.pharaoh.server;

import java.io.IOException;

public class PingThread extends Thread {

	PharaohPlayer player;
	boolean sendPings = false;

	public PingThread(PharaohPlayer player, boolean sendPings) {
		this.player = player;
		this.sendPings = sendPings;
	}

	public void run(){
		while(!player.client.isClosed()){
			try{
				if(sendPings){
					player.write("PING");
					Thread.sleep(10 * 1000);
					if(!player.unhandledInput.remove("PONG"))
						player.client.close();
				}
				else{
					if(player.unhandledInput.remove("PING"))
						player.write("PONG");
				}
			}
			catch(IOException io){
				try {
					io.printStackTrace();
					player.client.close();
				} catch (IOException e) {}
			} catch (InterruptedException e) {}
		}
	}

}
