package org.abimon.pharaoh;

import java.util.LinkedList;
import java.util.List;

import org.abimon.pharaoh.cards.Card;
import org.abimon.pharaoh.cards.CardDB;
import org.abimon.pharaoh.events.ConstructDeckEvent;
import org.abimon.pharaoh.events.IEventHandler;
import org.abimon.pharaoh.events.PharaohEvent;

public class Egypt implements IEventHandler {
	
	public List<Card> pool = new LinkedList<Card>();
	public List<PharaohPlayer> pharaohs = new LinkedList<PharaohPlayer>();
	
	public Deck<Card> deck = new Deck<Card>();
	
	public Egypt(PharaohPlayer[] players){
		Pharaoh.EVENT_BUS.register(this);
		constructDeck();
	}
	
	private void constructDeck(){
		for(Card card : CardDB.getCards())
			deck.add(card);
		
		Pharaoh.EVENT_BUS.post(new ConstructDeckEvent());
	}

	@Override
	public void onEvent(PharaohEvent event) {
		
	}
}
