package events.implementation;

import ECS.base.Entity;
import events.base.IEvent;
import events.types.EventType;

public class ScaleEvent implements IEvent {
    private final EventType eventType = EventType.Scale;
    private final int height, width;
    private final int step;
    private final Entity entity;
    private boolean handled = false;
    private final IEvent onFinish;

    public ScaleEvent(int step, Entity entity, int height, int width, IEvent onFinish) {
        this.step = step;
        this.entity = entity;
        this.height = height;
        this.width = width;
        this.onFinish = onFinish;
    }

    public IEvent getOnFinish() {
        return onFinish;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getStep() {
        return step;
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
        return "ScaleEvent{" +
                "eventType=" + eventType +
                '}';
    }
}
