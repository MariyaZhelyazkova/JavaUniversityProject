package ECS.SystemImpl.Animation.base;

import ECS.ComponentImpl.CScreenPosition;
import ECS.base.ComponentType;
import ECS.base.System;
import ECS.base.SystemType;
import javafx.util.Pair;

import java.util.ArrayList;

public abstract class SAnimation extends System {

    Pair<Integer, Integer> startPosition;
    Pair<Integer, Integer> endPosition;
    private final AnimationType animationType;

    public boolean isFinished() {
        return (startPosition.getKey() == endPosition.getKey()) &&
                (startPosition.getValue() == endPosition.getValue());
    }

    public Pair<Integer, Integer> getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Pair<Integer, Integer> startPosition) {
        this.startPosition = startPosition;
    }

    public Pair<Integer, Integer> getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Pair<Integer, Integer> endPosition) {
        this.endPosition = endPosition;
    }

    public AnimationType getAnimationType() {
        return animationType;
    }

    public SAnimation(SystemType systemType, AnimationType animationType) throws Exception {
        super(systemType, new ArrayList<ComponentType>() {{add(ComponentType.ScreenPosition);}});

        this.animationType = animationType;

        CScreenPosition scrPos = (CScreenPosition) getComponent(ComponentType.ScreenPosition);
        startPosition = new Pair<>(scrPos.getX(),scrPos.getY());
        endPosition = new Pair<>(scrPos.getX(),scrPos.getY());
    }

    public abstract void doAnimation();
}
