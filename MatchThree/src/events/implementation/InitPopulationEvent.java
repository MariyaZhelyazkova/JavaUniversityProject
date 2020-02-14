package events.implementation;

import ECS.base.interfaceses.IEntity;
import events.base.IEvent;
import events.base.IInstructions;
import events.types.EventType;

public class InitPopulationEvent implements IEvent {
    private final IEntity entity;
    private final EventType eventType = EventType.InitPopulation;
    private final boolean handled = false;

    public InitPopulationEvent(IEntity entity) {
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
    public IEntity getEntity() {
        return entity;
    }
}
