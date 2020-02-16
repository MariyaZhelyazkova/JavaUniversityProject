package events.implementation.instructions;

import events.base.IEvent;

public class AnimationInstruction {
    private final IEvent onFinished;
    private int x, y, step;
    private boolean finished = false;
    private final Type type;

    public AnimationInstruction(int x, int y, int step, IEvent onFinished, Type type) {
        this.x = x;
        this.y = y;
        this.step = step;
        this.onFinished = onFinished;
        this.type = type;
    }

    public IEvent getOnFinished() {
        return onFinished;
    }

    public Type getType() {
        return type;
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

    public enum Type {Move, Scale}

    @Override
    public String toString() {
        return "AnimationInstruction{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                '}';
    }
}
