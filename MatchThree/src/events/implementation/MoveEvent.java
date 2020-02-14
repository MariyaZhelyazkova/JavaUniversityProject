package events.implementation;

import ECS.base.interfaceses.Entity;
import events.base.IEvent;
import events.base.IInstructions;
import events.types.EventType;

public class MoveEvent implements IEvent {

    private final EventType eventType = EventType.Move;
    private final int x, y;
    private boolean handled = false;
    private Entity entity;

    public MoveEvent(Entity entity, int x, int y) {
        this.entity = entity;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    @Override
    public IInstructions getEventInstruction() {
        return null;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }
}
