package events.implementation;

import ECS.base.Entity;
import events.base.IEvent;
import events.types.EventType;

public class MoveEvent implements IEvent {

    private final EventType eventType = EventType.Move;
    private final int x, y;
    private final IEvent onFinish;
    private final int stepMiltiplier;
    private boolean handled = false;
    private Entity entity;

    public MoveEvent(Entity entity, int x, int y, int multiplier, IEvent onFinish) {
        this.x = x;
        this.y = y;
        this.entity = entity;
        this.stepMiltiplier = multiplier;
        this.onFinish = onFinish;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public IEvent getOnFinish() {
        return onFinish;
    }

    public int getStepMiltiplier() {
        return stepMiltiplier;
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
    public Entity getEntity() {
        return entity;
    }

    @Override
    public String toString() {
        return "MoveEvent{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
