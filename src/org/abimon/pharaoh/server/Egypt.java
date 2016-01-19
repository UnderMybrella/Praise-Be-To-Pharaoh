package org.abimon.pharaoh.server;

import java.util.LinkedList;
import java.util.List;

import org.abimon.omnis.util.General;
import org.abimon.pharaoh.Pharaoh;
import org.abimon.pharaoh.cards.Card;
import org.abimon.pharaoh.cards.Database;
import org.abimon.pharaoh.events.ConstructDeckEvent;
import org.abimon.pharaoh.events.IEventHandler;
import org.abimon.pharaoh.events.PharaohEvent;

public class Egypt implements IEventHandler {

	public List<Card> pool = new LinkedList<Card>();
	public List<PharaohPlayer> pharaohs = new LinkedList<PharaohPlayer>();

	public Deck<Card> deck = new Deck<Card>();
	
	public int turnPlayer = -1;
	
	public boolean playGame = true;
	
	protected Egypt(){}

	public Egypt(PharaohPlayer[] players){
		
		for(PharaohPlayer player : players)
			player.write("start_game");
		
		Pharaoh.EVENT_BUS.register(this);
		constructDeck();

		String lowest = "";

		for(int i = 0; i < players.length; i++){
			Card card = deck.draw();
			if(card == null)
				break;
			String cardName = card.getName();
			if(lowest.equals("") || turnPlayer == -1){
				lowest = cardName;
				turnPlayer = i;
			}
			else{
				if(General.doesFirstComeBeforeSecond(cardName, lowest)){
					lowest = cardName;
					turnPlayer = i;
				}
			}
			pool.add(card);
		}
		
		for(int i = turnPlayer; i < players.length; i++)
			pharaohs.add(players[i]);
		
		for(int i = 0; i < turnPlayer; i++)
			pharaohs.add(players[i]);
		
		turnPlayer = 0;
		
		while(playGame){
			
			
			
			turnPlayer = Math.min(++turnPlayer, pharaohs.size());
		}
	}

	private void constructDeck(){
		for(Card card : Database.getCards())
			deck.add(card);
		deck.shuffle();

		Pharaoh.EVENT_BUS.post(new ConstructDeckEvent());
	}

	@Override
	public void onEvent(PharaohEvent event) {

	}
}
