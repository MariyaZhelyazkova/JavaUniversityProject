package events.implementation;

import ECS.base.interfaceses.Entity;
import events.base.IEvent;
import events.base.IInstructions;
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
    public IInstructions getEventInstruction() {
        return null;
    }

    @Override
    public Entity getEntity() {
        return null;
    }
}
