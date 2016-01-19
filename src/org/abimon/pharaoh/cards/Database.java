package org.abimon.pharaoh.cards;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.abimon.pharaoh.actions.Action;

public class Database {
	private static Map<Card, Integer> cards = new HashMap<Card, Integer>();
	private static LinkedList<Action> actions = new LinkedList<Action>();

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

	public static Action[] getActions(){
		LinkedList<Action> actionList = new LinkedList<Action>();
		for(Action action : actions)
			actionList.add(action);
		return actionList.toArray(new Action[0]);
	}

	public static void registerAction(Action action){
		registerAction(action, false);
	}

	public static void registerAction(Action action, boolean override){
		if(!actions.contains(actions) || override){
			actions.remove(action);
			actions.add(action);
		}
	}
}
