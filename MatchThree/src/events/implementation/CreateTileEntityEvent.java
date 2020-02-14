package events.implementation;

import ECS.base.interfaceses.IEntity;
import events.base.IEvent;
import events.base.IInstructions;
import events.types.EventType;

public class CreateTileEntityEvent implements IEvent {
    private final EventType eventType = EventType.CreateEntity;
    private boolean handled = false;
    private final String tileType;
    private final int xPos, yPos;

    public CreateTileEntityEvent(String tileType, int x, int y){
        this.tileType = tileType;
        this.xPos = x;
        this.yPos = y;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public String getTileType() {
        return tileType;
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
