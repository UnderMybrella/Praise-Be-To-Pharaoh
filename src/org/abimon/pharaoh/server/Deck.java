package org.abimon.pharaoh.server;

import java.security.SecureRandom;
import java.util.LinkedList;

import org.abimon.pharaoh.Pharaoh;
import org.abimon.pharaoh.events.PharaohEvent;

public class Deck<T> {
	
	private LinkedList<T> deck = new LinkedList<T>();
	
	public T draw(){
		T card = deck.poll();
		if(Pharaoh.EVENT_BUS.post(new DrawEvent<T>(card)))
			return null;
		return card;
	}
	
	public void add(T card){
		deck.add(card);
	}
	
	public int size(){
		return deck.size();
	}

	public void shuffle(){
		LinkedList<T> newDeck = new LinkedList<T>();
		SecureRandom random = new SecureRandom();
		while(deck.size() > 0){
			newDeck.add(deck.remove(random.nextInt(deck.size())));
			random.setSeed(random.nextLong());
		}
		deck = newDeck;
	}
	
	public class DrawEvent<E> extends PharaohEvent{
		E drawn;
		
		public DrawEvent(E drawn){
			this.drawn = drawn;
		}
	}
}
