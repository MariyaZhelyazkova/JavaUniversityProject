package ECS.implementation.systems;

import ECS.implementation.components.CPosition;
import ECS.base.types.ComponentType;
import ECS.base.System;
import ECS.base.types.SystemType;
import ECS.implementation.systems.types.MovementType;

import java.util.ArrayList;

public class SMovement extends System {
    public SMovement(SystemType systemType, ArrayList<ComponentType> requiredComponents) throws Exception {
        super(SystemType.Movement, new ArrayList<ComponentType>() {{add(ComponentType.Position);}});
    }

    public void Move(MovementType movementType){
        Move(movementType, 1);
    }

    public void Move(MovementType movementType, int steps){
        CPosition pos = (CPosition) getComponent(ComponentType.Position);

        switch (movementType){
            case Up:
                pos.setY(pos.getY() + steps);
                break;
            case Down:
            case Fall:
                pos.setY(pos.getY() - steps);
                break;
            case Left:
                pos.setX(pos.getX() - steps);
                break;
            case Right:
                pos.setX(pos.getX() + steps);
                break;
        }
    }
}
