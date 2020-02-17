package events.implementation;

import ECS.base.Entity;
import events.base.IEvent;
import events.types.EventType;

public class EntityDestroyedEvent implements IEvent {
    private final EventType eventType = EventType.EntityDestroyed;
    private boolean handled = false;
    private final Entity entity;

    public EntityDestroyedEvent(Entity entity) {
        this.entity = entity;
    }

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
        return entity;
    }

    @Override
    public String toString() {
        return "EntityDestroyedEvent{" +
                "eventType=" + eventType +
                '}';
    }
}
