package ECS.implementation.systems;

import ECS.base.ComponentManager;
import ECS.base.interfaceses.Entity;
import ECS.base.interfaceses.ISystem;
import ECS.base.types.ComponentType;
import ECS.base.types.SystemType;
import ECS.implementation.components.CPosition;
import ECS.implementation.components.CScreenPosition;
import events.base.IEvent;
import events.base.IEventListener;
import events.implementation.EventDispatcher;
import events.implementation.MoveEvent;
import events.implementation.instructions.MoveInstruction;
import events.types.EventType;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class AnimationSystem implements ISystem, IEventListener {
    private final SystemType systemType = SystemType.Animation;
    private final EventDispatcher eventDispatcher;
    private final ComponentManager componentManager;
    private final Vector<ComponentType> requiredComponent = new Vector<ComponentType>() {{
        add(ComponentType.Position);
    }};
    private Map<Entity, MoveInstruction> animations = new HashMap<>();

    public AnimationSystem(EventDispatcher eventDispatcher, ComponentManager componentManager) {
        this.eventDispatcher = eventDispatcher;
        this.componentManager = componentManager;
    }

    public void doAnimations(){
        System.out.println("Animations!!!");
        animations.forEach((key, value) -> {
            CScreenPosition cScreenPosition = (CScreenPosition) componentManager.getComponent(key, ComponentType.ScreenPosition);
            if (cScreenPosition.getX() != value.getX())
                cScreenPosition.setX(cScreenPosition.getX() + value.getStep());
            else if(cScreenPosition.getY() != value.getY())
                cScreenPosition.setY(cScreenPosition.getY() + value.getStep());
            else
                value.setFinished(true);
        });

        animations.entrySet().removeIf(entry -> entry.getValue().isFinished());
    }

    public boolean hasAnimations(){
        return animations.size() > 0;
    }

    @Override
    public SystemType getSystemType() {
        return systemType;
    }

    @Override
    public Vector<ComponentType> getRequiredComponents() {
        return requiredComponent;
    }

    @Override
    public void onEvent(IEvent e) {
        if (e.getEventType() == EventType.Move) {
            MoveEvent event = (MoveEvent) e;
            animations.put(e.getEntity(), new MoveInstruction(event.getX() * 64 + 100, event.getY() * 64 + 50, 2));
            CPosition cPosition = (CPosition) componentManager.getComponent(event.getEntity(), ComponentType.Position);
            cPosition.setX(event.getX());
            cPosition.setY(event.getY());
        }
    }

    @Override
    public Integer getId() {
        return null;
    }
}
