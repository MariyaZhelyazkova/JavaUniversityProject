package events.implementation;

import events.base.IEvent;
import events.base.IEventDispatcher;
import events.base.IEventListener;
import events.types.EventType;

import java.util.HashMap;
import java.util.Vector;

public class EventDispatcher implements IEventDispatcher {
    private HashMap<EventType, Vector<IEventListener>> listeners;
    private Vector<IEvent> events;

    public EventDispatcher(){
        listeners = new HashMap<>();
        events = new Vector<>();
    }

    @Override
    public void subscribe(EventType eventType, IEventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new Vector<>());
        listeners.get(eventType).add(listener);
    }

    @Override
    public void unsubscribe(EventType eventType, IEventListener listener) {
        if(listeners.get(eventType).contains(listener))
            listeners.get(eventType).remove(listener);
    }

    @Override
    public void publish(IEvent event) {
        events.add(event);
    }

    @Override
    public void dispatchEvent() {
        if (events.isEmpty())
            return;

        events.forEach((event -> {
            Vector<IEventListener> currListeners = listeners.get(event.getEventType());
            if (!currListeners.isEmpty())
                for (IEventListener listener : currListeners) {
                    listener.onEvent(event);
                    if (event.isHandled())
                        break;
                }
        }));

        events.clear();
    }
}
