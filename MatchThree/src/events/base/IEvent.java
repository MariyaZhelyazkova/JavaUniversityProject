package events.base;

import ECS.base.interfaceses.Entity;
import events.types.EventType;

public interface IEvent {

    EventType getEventType();

    boolean isHandled();

    IInstructions getEventInstruction();

    Entity getEntity();
}
