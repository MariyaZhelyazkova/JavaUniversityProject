package ECS.implementation.systems;

import ECS.base.interfaceses.ISystem;
import ECS.base.types.ComponentType;
import ECS.base.types.SystemType;
import ECS.implementation.systems.types.MovementType;
import events.base.IEvent;
import events.base.IEventListener;
import events.implementation.instructions.MoveInstruction;
import events.types.EventType;

import java.util.Vector;

public class SMovement implements ISystem, IEventListener {
    private final SystemType systemType = SystemType.Movement;
    private final Vector<ComponentType> requiredComponents;

    public SMovement() throws Exception {
        requiredComponents = new Vector<ComponentType>(){{add(ComponentType.Position);}};
    }

    public void Move(MovementType movementType){
        //TO DO
    }

    public void Move(MovementType movementType, int steps){
        // TO DO
    }

    @Override
    public SystemType getSystemType() {
        return systemType;
    }

    @Override
    public Vector<ComponentType> getRequiredComponents() {
        return requiredComponents;
    }

    @Override
    public void onEvent(IEvent e) {
        if (e.getEventType() != EventType.Move)
            return;

        MoveInstruction mi = (MoveInstruction) e.getEventInstruction();
        System.out.println(mi.toString());
    }

    @Override
    public Integer getId() {
        return null;
    }
}
