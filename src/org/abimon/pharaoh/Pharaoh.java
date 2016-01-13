package org.abimon.pharaoh;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import javax.net.SocketFactory;

import org.abimon.omnis.lanterna.LanternaGeneral;
import org.abimon.omnis.lanterna.ScrollPanel;
import org.abimon.omnis.lanterna.ScrollWindow;
import org.abimon.omnis.reflect.ReflectionHelper;
import org.abimon.pharaoh.client.PharaohTourist;
import org.abimon.pharaoh.events.EventBus;
import org.abimon.pharaoh.server.Egypt;
import org.abimon.pharaoh.server.PharaohPlayer;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Pharaoh {

	public static Terminal terminal;
	public static Screen screen;

	public static Window menu;

	public static MultiWindowTextGUI gui;

	public static Egypt egypt;

	public static EventBus EVENT_BUS;

	public static final Runnable BACK_BUTTON = new Runnable(){
		public void run(){
			gui.removeWindow(gui.getActiveWindow());
		}
	};

	public static void main(String[] args) {
		new Pharaoh();
	}

	public Pharaoh(){
		try{
			DefaultTerminalFactory dtf = new DefaultTerminalFactory();
			terminal = dtf.createTerminal();
			screen = new TerminalScreen(terminal);
			screen.startScreen();

			gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(new TextColor.RGB(242, 198, 127)));

			menu = new BasicWindow();
			Panel panel = new Panel();
			panel.addComponent(new Label("Praise be to Pharaoh"));
			panel.addComponent(new EmptySpace(new TerminalSize(0, 1)));
			panel.addComponent(new Button("Play Game (SinglePlayer Beta)"));

			panel.addComponent(new Button("Play Game (MultiPlayer Beta)", new Runnable(){
				public void run(){
					multiplayer();
				}
			}));

			panel.addComponent(new Button("About", new Runnable(){
				public void run(){
					about();
				}
			}));

			panel.addComponent(new Button("Exit", new Runnable(){
				public void run(){
					System.exit(0);
				}
			}));
			menu.setComponent(panel);

			gui.addWindowAndWait(menu);
		}
		catch(Throwable th){
			th.printStackTrace();
		}
	}

	public void multiplayer() {
		gui.addWindowAndWait(LanternaGeneral.createWindow(new Button("Host", new Runnable(){
			public void run(){
				hostGame();
			}
		}), new Button("Join", new Runnable(){
			public void run(){
				joinGame();
			}
		}), new Button("Back", BACK_BUTTON)));
	}

	public void hostGame(){
		try{
			final TextBox port = new TextBox(new TerminalSize(20, 1));

			gui.addWindowAndWait(LanternaGeneral.createWindow(new BasicWindow(), new GridLayout(2), new Panel(), new Label("Port: "), port,
					new Button("Host", new Runnable(){
						public void run(){
							try{
								host(Integer.parseInt(port.getText().trim()));
							}
							catch(Throwable th){
								th.printStackTrace();
							}
						}
					}),
					new Button("Back", BACK_BUTTON)));
		}
		catch(Throwable th){
			th.printStackTrace();
		}
	}

	public static LinkedList<PharaohPlayer> pharaohs;
	public static Window lobbyWindow;
	public static Panel panel;
	public static Button back;
	public static Button start;

	public void host(int port){
		try{
			final ServerSocket server = new ServerSocket(port);

			pharaohs = new LinkedList<PharaohPlayer>();

			lobbyWindow = new ScrollWindow();
			panel = new ScrollPanel(10);
			back = new Button("Back", BACK_BUTTON);
			start = new Button("Start", BACK_BUTTON);

			panel.addComponent(back);
			panel.addComponent(start);
			lobbyWindow.setComponent(panel);

			Thread acceptanceThread = new Thread(){
				public void run(){
					while(Pharaoh.egypt == null && !server.isClosed()){
						try{
							Socket client = server.accept();
							PharaohPlayer pharaoh = new PharaohPlayer(client);
							for(PharaohPlayer player : pharaohs)
								pharaoh.write("joined:" + player.getName().trim());
							pharaohs.add(pharaoh);
						}
						catch(Throwable th){
							th.printStackTrace();
						}
					}
				}
			};

			acceptanceThread.setDaemon(true);
			acceptanceThread.start();

			gui.addWindowAndWait(lobbyWindow);

			server.close();
		}
		catch(Throwable th){
			th.printStackTrace();
		}
	}

	public static void reloadNames(){
		boolean proceed = false;
		
		for(Component comp : panel.getChildren())
			if(comp instanceof Label){
				boolean contains = false;
				for(PharaohPlayer player : pharaohs){
					if(ReflectionHelper.getObjectFromVariable(Label.class, comp, "lines", new String[0])[0].equals(player.getName())){
						contains = true;
						break;
					}
				}
				if(!contains)
					panel.removeComponent(comp);
			}

		for(PharaohPlayer player : pharaohs){
			System.out.println("Found " + player.getName() + " of Egypt");
			if(!player.getName().equals("")){
				boolean contains = false;
				for(Component comp : panel.getChildren())
					if(comp instanceof Label)
						if(ReflectionHelper.getObjectFromVariable(Label.class, comp, "lines", new String[0])[0].equals(player.getName())){
							contains = true;
							break;
						}
				if(!contains){
					proceed = true;
					break;
				}
			}
		}

		if(!proceed)
			return;

		boolean backSelected = back.isFocused();
		boolean startSelected = start.isFocused();

		lobbyWindow.setFocusedInteractable(null);

		panel.removeComponent(back);
		panel.removeComponent(start);

		for(PharaohPlayer player : pharaohs)
			if(!player.getName().equals("")){
				boolean contains = false;
				for(Component comp : panel.getChildren())
					if(comp instanceof Label)
						if(ReflectionHelper.getObjectFromVariable(Label.class, comp, "lines", new String[0])[0].equals(player.getName())){
							contains = true;
							break;
						}
				if(!contains){
					panel.addComponent(new Label(player.getName()));
					for(PharaohPlayer pharaoh : pharaohs)
						pharaoh.write("joined:" + player.getName());
				}
			}

		panel.addComponent(back);
		panel.addComponent(start);

		if(backSelected)
			lobbyWindow.setFocusedInteractable(back);
		if(startSelected)
			lobbyWindow.setFocusedInteractable(start);
	}

	public void joinGame(){
		final TextBox text = new TextBox(new TerminalSize(20, 1));
		gui.addWindowAndWait(LanternaGeneral.createWindow(new BasicWindow(), new GridLayout(2), new Panel(), text,
				new Button("Join", new Runnable(){
					public void run(){
						join(text.getText());
					}
				}),
				new Button("Back", BACK_BUTTON),
				new EmptySpace(new TerminalSize(0, 0)) 
				));
	}

	PharaohTourist tourist = null;

	public void join(String ip){
		try{
			gui.addWindow(LanternaGeneral.createWindow(new Label("Attempting Connection...")));

			InetAddress addr = InetAddress.getByName(ip.split(":")[0]);
			final Socket socket = SocketFactory.getDefault().createSocket();
			socket.connect(new InetSocketAddress(addr, Integer.parseInt(ip.split(":")[1])), 8000);

			BACK_BUTTON.run();

			final TextBox text = new TextBox(new TerminalSize(20, 1));
			gui.addWindowAndWait(LanternaGeneral.createWindow(new BasicWindow(), new GridLayout(2), new Panel(), text,
					new Button("Join", new Runnable(){
						public void run(){
							BACK_BUTTON.run();
							join(socket, text.getText());
						}
					}),
					new Button("Back", BACK_BUTTON),
					new EmptySpace(new TerminalSize(0, 0)) 
					));
		}
		catch(Throwable th){
			th.printStackTrace();
			BACK_BUTTON.run();
			gui.addWindowAndWait(LanternaGeneral.createWindow(new Label("Connection Failed"), new Button("Back", BACK_BUTTON)));
		}
	}

	public static boolean joined = false;

	public void join(Socket server, String name){

		System.out.println("**TOURIST MODE**");

		tourist = new PharaohTourist(server);
		tourist.write("name:" + name);
		tourist.setName(name);

		final Window lobbyWindow = new ScrollWindow();
		final Panel panel = new ScrollPanel(10);
		final Button back = new Button("Back", BACK_BUTTON);
		panel.addComponent(back);
		lobbyWindow.setComponent(panel);

		final LinkedList<String> pharaohs = new LinkedList<String>();

		Thread serverRead = new Thread(){
			public void run(){
				while(!joined){
					try{
						try{
							if(tourist.unhandledInput.isEmpty())
								continue;
							String line = tourist.unhandledInput.peek();
							if(line == null)
								continue;
							System.out.println(line);
							if(line.startsWith("joined:")){
								pharaohs.add(line.split(":", 2)[1].trim());
								tourist.unhandledInput.removeFirst();
							}
							else if(line.startsWith("left:")){
								pharaohs.remove(line.split(":", 2)[1].trim());
								tourist.unhandledInput.removeFirst();
							}
						}
						catch(java.util.NoSuchElementException ex){}

						System.out.println(pharaohs);
						
						for(Component comp : panel.getChildren())
							if(comp instanceof Label){
								boolean contains = false;
								for(String player : pharaohs){
									if(ReflectionHelper.getObjectFromVariable(Label.class, comp, "lines", new String[0])[0].equals(player)){
										contains = true;
										break;
									}
								}
								if(!contains)
									panel.removeComponent(comp);
							}

						boolean proceed = false;
						for(String player : pharaohs)
							if(!player.equals("")){
								boolean contains = false;
								for(Component comp : panel.getChildren())
									if(comp instanceof Label)
										if(ReflectionHelper.getObjectFromVariable(Label.class, comp, "lines", new String[0])[0].equals(player)){
											contains = true;
											break;
										}
								if(!contains){
									proceed = true;
									break;
								}
							}

						if(!proceed)
							continue;

						boolean backSelected = back.isFocused();

						lobbyWindow.setFocusedInteractable(null);

						panel.removeComponent(back);

						for(String player : pharaohs)
							if(!player.equals("")){
								boolean contains = false;
								for(Component comp : panel.getChildren())
									if(comp instanceof Label)
										if(ReflectionHelper.getObjectFromVariable(Label.class, comp, "lines", new String[0])[0].equals(player)){
											contains = true;
											break;
										}
								if(!contains)
									panel.addComponent(new Label(player));
							}

						panel.addComponent(back);

						if(backSelected)
							lobbyWindow.setFocusedInteractable(back);
					}
					catch(Throwable th){
						th.printStackTrace();
					}
				}
			}
		};

		serverRead.setDaemon(true);
		serverRead.start();

		gui.addWindow(lobbyWindow);
	}

	public void about() {
		gui.addWindowAndWait(LanternaGeneral.createWindow(new Label("Praise be to Pharaoh"), new EmptySpace(new TerminalSize(0, 1)), new Label(
				"'Praise be to Pharaoh' is a variant of 'Glory to Rome' designed to \n" + 
						"allow for reproduction of a game similar in mechanics without\n" + 
						"violating any copyright or IP laws. While I'm not sure how effective\n" +
						"it is, I hope some enjoyment is gotten out of this. Use of the game's\n" + 
						"cards + assets is allowed and encouraged, as that's the entire point\n" +
						"of this game. So please, make a print version if you want, although\n" + 
						"note that there are no official art assets yet.\n" + 
						"Thanks for your time, \n" + 
						"~Isaac"
				), new Button("Back", BACK_BUTTON)));
	}

	public static void disconnect(PharaohPlayer pharaohPlayer) {
		if(egypt == null){
			Pharaoh.pharaohs.remove(pharaohPlayer);
			for(PharaohPlayer player : pharaohs)
				player.write("left:" + pharaohPlayer.getName());
			Pharaoh.reloadNames();
		}
	}



}
