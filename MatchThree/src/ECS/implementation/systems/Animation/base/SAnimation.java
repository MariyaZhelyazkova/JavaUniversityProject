package ECS.implementation.systems.Animation.base;

import ECS.implementation.components.CScreenPosition;
import ECS.base.types.ComponentType;
import ECS.base.System;
import ECS.base.types.SystemType;
import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;

public abstract class SAnimation extends System {

    Vec2d startPosition;
    Vec2d endPosition;
    private final AnimationType animationType;

    public Vec2d getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Vec2d startPosition) {
        this.startPosition = startPosition;
    }

    public Vec2d getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Vec2d endPosition) {
        this.endPosition = endPosition;
    }

    public boolean isFinished() {
        return (startPosition.x == endPosition.x) &&
                (startPosition.y == endPosition.y);
    }
    public AnimationType getAnimationType() {
        return animationType;
    }

    public SAnimation(SystemType systemType, AnimationType animationType) throws Exception {
        super(systemType, new ArrayList<ComponentType>() {{add(ComponentType.ScreenPosition);}});

        this.animationType = animationType;

        CScreenPosition scrPos = (CScreenPosition) getComponent(ComponentType.ScreenPosition);
        startPosition = new Vec2d(scrPos.getX(),scrPos.getY());
        endPosition = new Vec2d(scrPos.getX(),scrPos.getY());
    }

    public abstract void doAnimation();
}
