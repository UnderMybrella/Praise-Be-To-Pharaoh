package org.abimon.pharaoh;

import org.abimon.omnis.lanterna.LanternaGeneral;
import org.abimon.pharaoh.events.EventBus;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
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
			
			panel.addComponent(new Button("Play Game (MultiPlayer Beta)"));
			
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

	public void about() {
		try{
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
		catch(Throwable th){
			th.printStackTrace();
		}
	}
	
	

}
