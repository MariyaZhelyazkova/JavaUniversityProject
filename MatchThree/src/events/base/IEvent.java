package events.base;

import ECS.base.Entity;
import events.types.EventType;

public interface IEvent {

    EventType getEventType();

    boolean isHandled();

    Entity getEntity();
}
