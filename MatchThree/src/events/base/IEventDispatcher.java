package events.base;

import events.types.EventType;

public interface IEventDispatcher {

    void subscribe(EventType eventType, IEventListener listener);

    void unsubscribe(EventType eventType, IEventListener listener);

    void publish(IEvent event);

    void dispatchEvent();
}
