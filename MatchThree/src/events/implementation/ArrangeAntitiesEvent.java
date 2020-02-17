package events.implementation;

import ECS.base.Entity;
import events.base.IEvent;
import events.types.EventType;

public class ArrangeAntitiesEvent implements IEvent {
    private final EventType eventType = EventType.ArrangeEntities;
    private boolean handled = false;

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
}
