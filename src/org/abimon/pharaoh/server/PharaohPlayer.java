package org.abimon.pharaoh.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;

import org.abimon.omnis.io.Data;
import org.abimon.pharaoh.Pharaoh;

public class PharaohPlayer implements Runnable{

	protected Socket client;
	public String name = "";

	protected PrintStream out;
	protected InputStream in;

	private Thread internalThread;
	private Thread pingThread;

	private LinkedList<String> outputBuffer = new LinkedList<String>();

	/** Remove input once handled */
	public LinkedList<String> unhandledInput = new LinkedList<String>();

	public PharaohPlayer(Socket client) {
		this(client, true);
	}

	public PharaohPlayer(Socket client, boolean sendPings){
		this.client = client;
		internalThread = new Thread(this);
		internalThread.setDaemon(true);
		internalThread.start();

		pingThread = new PingThread(this, sendPings);
		pingThread.setDaemon(true);
		pingThread.start();
	}

	public void run(){
		while(!client.isClosed()){
			try{
				if(out == null)
					out = new PrintStream(client.getOutputStream());
				if(in == null)
					in = client.getInputStream();

				printBuffer();

				Data read = new Data(in, false);
				for(String line : read.getAsString().split("\n")){
					line = line.trim();
					if(line.equalsIgnoreCase(""))
						continue;
					if(line.startsWith("name:"))
						handleName(line);
					else if(line.equalsIgnoreCase("DISCONNECT"))
						client.close();
					else
						if(!handleInput(line))
							unhandledInput.add(line);
				}
			}
			catch(IOException exception){
			}
			catch(Throwable th){
				th.printStackTrace();
			}
		}
		System.out.println(name + " of Egypt has left");
		//HandleDisconnect
		Pharaoh.disconnect(this);
	}

	public boolean handleInput(String line) {
		return false;
	}

	public void handleName(String line){
		name = line.split(":", 2)[1];
		internalThread.setName(name + " of Egypt");
		Pharaoh.reloadNames();
	}

	public void printBuffer(){
		if(out != null){
			while(!outputBuffer.isEmpty())
				out.println(outputBuffer.poll());
			out.flush();
		}
	}

	public void write(Object... objs){
		String line = "";
		for(Object obj : objs)
			if(obj != null)
				line += obj.toString() + " ";
		outputBuffer.add(line);
	}

	public String getName(){
		return name;
	}

}
