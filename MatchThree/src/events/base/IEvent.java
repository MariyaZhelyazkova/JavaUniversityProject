package events.base;

import ECS.base.interfaceses.IEntity;
import events.types.EventType;

public interface IEvent {

    EventType getEventType();

    boolean isHandled();

    IInstructions getEventInstruction();

    IEntity getEntity();
}
