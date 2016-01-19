package org.abimon.pharaoh.client;

import java.net.Socket;

import org.abimon.omnis.lanterna.LanternaGeneral;
import org.abimon.pharaoh.Pharaoh;
import org.abimon.pharaoh.server.PharaohPlayer;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;

public class PharaohTourist extends PharaohPlayer{

	public static MultiWindowTextGUI gui = Pharaoh.gui;

	public final Button PERFORM_ACTION = new Button("Perform Action", new Runnable(){
		public void run(){
			performAction();
		}
	});

	public final Button HAND = new Button("Hand", new Runnable(){
		public void run(){
			hand();
		}
	});

	public Window mainMenu;

	public PharaohTourist(Socket server) {
		super(server, false);

		mainMenu = LanternaGeneral.createWindow(PERFORM_ACTION, HAND);

		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				closeClient();
			}
		});
	}

	public void handleName(String name){}

	public void setName(String name) {
		this.write("name:" + name);
		this.name = name;
	}

	public void closeClient(){
		try{
			write("DISCONNECT");
			printBuffer();
			client.close();
		}
		catch(Throwable th){}
	}

	@Override
	public boolean handleInput(String line){
		System.out.println(line);
		if(line.equalsIgnoreCase("start_game")){
			System.out.println("Starting Game");
			Pharaoh.egypt = new TouristEgypt();
			return true;
		}
		return false;
	}

	protected void performAction() {

	}

	protected void hand(){

	}

}
