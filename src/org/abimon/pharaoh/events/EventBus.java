package org.abimon.pharaoh.events;

import java.util.LinkedList;
import java.util.List;

public class EventBus {
	private List<IEventHandler> eventHandlers = new LinkedList<IEventHandler>();
	
	public void register(IEventHandler eventHandler){
		if(!this.eventHandlers.contains(eventHandler))
			this.eventHandlers.add(eventHandler);
	}
	
	public void deregister(IEventHandler eventHandler){
		if(this.eventHandlers.contains(eventHandler))
			this.eventHandlers.remove(eventHandler);
	}
	
	/**
	 * @param event
	 * @return true if the event was cancelled
	 */
	public boolean post(PharaohEvent event){
		for(IEventHandler eventHandler : eventHandlers)
			eventHandler.onEvent(event);
		return event.getCancelled();
	}
}
