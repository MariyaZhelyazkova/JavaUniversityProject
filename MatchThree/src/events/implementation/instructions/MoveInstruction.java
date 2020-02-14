package events.implementation.instructions;

import events.base.IInstructions;
import events.types.EventType;

public class MoveInstruction implements IInstructions {

    private final EventType eventType = EventType.Move;
    private int x, y;

    public MoveInstruction(int x, int y){
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
    public String toString() {
        return "MoveInstruction{" +
                "eventType=" + eventType +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }
}
