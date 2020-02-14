package events.implementation.instructions;

import events.types.EventType;

public class MoveInstruction {

    private final EventType eventType = EventType.Move;
    private int x, y, step;
    private boolean finished = false;

    public MoveInstruction(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getStep() {
        return step;
    }
}
