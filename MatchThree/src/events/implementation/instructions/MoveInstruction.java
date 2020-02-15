package events.implementation.instructions;

import events.base.IEvent;

public class MoveInstruction {
    private int x, y, step;
    private boolean finished = false;
    private final IEvent onFinished;

    public MoveInstruction(int x, int y, int step, IEvent onFinished) {
        this.x = x;
        this.y = y;
        this.step = step;
        this.onFinished = onFinished;
    }

    public IEvent getOnFinished() {
        return onFinished;
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
