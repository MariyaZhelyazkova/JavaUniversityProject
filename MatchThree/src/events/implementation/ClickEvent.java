package events.implementation;

import ECS.base.Entity;
import events.base.IEvent;
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
    public Entity getEntity() {
        return null;
    }

    @Override
    public String toString() {
        return "ClickEvent{" +
                "eventType=" + eventType +
                '}';
    }
}
