package ECS.SystemImpl.Animation.AnimationImpl;

import ECS.MovementType;
import ECS.SystemImpl.Animation.base.AnimationType;
import ECS.SystemImpl.Animation.base.SAnimation;
import ECS.base.SystemType;

public class SMoveAnimation extends SAnimation {

    private MovementType movementType = MovementType.None;
    private int steps = 0;

    private void setMovementType(){
        int startDiff = getStartPosition().getKey() - getEndPosition().getKey();
        int endDiff = getStartPosition().getValue() - getEndPosition().getValue();

        if (startDiff != 0)
        {
            steps = Math.abs(startDiff);

            if(startDiff < 0)
                movementType = MovementType.Left;
            else
                movementType = MovementType.Right;
        } else if(endDiff != 0 ) {
            if (endDiff < 0)
                movementType = MovementType.Up;
            else
                movementType = MovementType.Down;

            steps = endDiff;
            if (steps > 1) movementType = MovementType.Fall;

        } else {
            movementType = MovementType.None;
            steps = 0;
        }
    }

    private void move(){
        //TO DO
    }

    public SMoveAnimation(SystemType systemType) throws Exception {
        super(systemType, AnimationType.Move);
    }

    @Override
    public void doAnimation() {
        if (isFinished()) return;

        if (movementType == MovementType.None) setMovementType();

        move();
    }
}
