package org.abimon.pharaoh.cards;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CardDB {
	private static Map<Card, Integer> cards = new HashMap<Card, Integer>();
	
	public static Card[] getCards(){
		LinkedList<Card> cardList = new LinkedList<Card>();
		for(Card card : cards.keySet())
			for(int i = 0; i < cards.get(card); i++)
				cardList.add(card);
		return cardList.toArray(new Card[0]);
	}
	
	public static void registerCard(Card card){
		registerCard(card, 1, false);
	}
	
	public static void registerCard(Card card, int num){
		registerCard(card, num, false);
	}
	
	public static void registerCard(Card card, int num, boolean override){
		if(!cards.containsKey(card) || override)
			cards.put(card, num);
	}
}
