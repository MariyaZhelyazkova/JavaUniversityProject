package events.implementation;

import ECS.base.Entity;
import events.base.IEvent;
import events.types.EventType;

public class CreateMissingEvent implements IEvent {
    private final EventType eventType = EventType.CreateMissing;
    private boolean handled = false;

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public boolean isHandled() {
        return handled;
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public String toString() {
        return "CreateMissingEvent{" +
                "eventType=" + eventType +
                '}';
    }
}
