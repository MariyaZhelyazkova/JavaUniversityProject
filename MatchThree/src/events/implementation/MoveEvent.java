package events.implementation;

import ECS.base.interfaceses.Entity;
import events.base.IInstructions;
import events.base.IEvent;
import events.implementation.instructions.MoveInstruction;
import events.types.EventType;

public class MoveEvent implements IEvent {

    private final EventType eventType = EventType.Move;
    private boolean handled = false;
    private Entity entity;
    private MoveInstruction moveInstruction;

    public MoveEvent(Entity entity, int x, int y){
        this.entity = entity;
        this.moveInstruction = new MoveInstruction(x, y);
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
        return moveInstruction;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }
}
