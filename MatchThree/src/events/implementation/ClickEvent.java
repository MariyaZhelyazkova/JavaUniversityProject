package events.implementation;

import ECS.base.interfaceses.IEntity;
import events.base.IEvent;
import events.base.IInstructions;
import events.types.EventType;

public class ClickEvent implements IEvent {
    private final EventType eventType = EventType.Click;
    private boolean handled = false;
    private final int xPos, yPos;

    public ClickEvent( int x, int y){
        this.xPos = x;
        this.yPos = y;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
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
        return null;
    }
}
