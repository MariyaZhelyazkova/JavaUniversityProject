package events.implementation;

import ECS.base.interfaceses.Entity;
import events.base.IEvent;
import events.base.IInstructions;
import events.types.EventType;

public class InitPopulationEvent implements IEvent {
    private final Entity entity;
    private final EventType eventType = EventType.InitPopulation;
    private final boolean handled = false;

    public InitPopulationEvent(Entity entity) {
        this.entity = entity;
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
    public IInstructions getEventInstruction() {
        return null;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public String toString() {
        return "InitPopulationEvent{" +
                "eventType=" + eventType +
                '}';
    }
}
