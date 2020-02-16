package ECS.implementation.systems;

import ECS.base.ComponentManager;
import ECS.base.interfaceses.Entity;
import ECS.base.interfaceses.ISystem;
import ECS.base.types.ComponentType;
import ECS.base.types.SystemType;
import ECS.implementation.components.CPosition;
import ECS.implementation.components.CScreenPosition;
import ECS.implementation.components.CTexture;
import events.base.IEvent;
import events.base.IEventListener;
import events.implementation.CreateMissingEvent;
import events.implementation.EventDispatcher;
import events.implementation.MoveEvent;
import events.implementation.ScaleEvent;
import events.implementation.instructions.AnimationInstruction;
import events.types.EventType;

import javax.swing.plaf.basic.BasicSplitPaneUI;
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
    private Map<Entity, AnimationInstruction> animations = new HashMap<>();
    private Map<EventType, IEvent> onFinishedEvents = new HashMap<>();

    public AnimationSystem(EventDispatcher eventDispatcher, ComponentManager componentManager) {
        this.eventDispatcher = eventDispatcher;
        this.componentManager = componentManager;
    }

    private void onMoveEvent(MoveEvent event) {
        animations.put(event.getEntity(), new AnimationInstruction(event.getX() * 64 + 208, event.getY() * 64 + 108, 4 * event.getStepMiltiplier(),
                event.getOnFinish(), AnimationInstruction.Type.Move));
        CPosition cPosition = (CPosition) componentManager.getComponent(event.getEntity(), ComponentType.Position);
        cPosition.setX(event.getX());
        cPosition.setY(event.getY());
    }

    private void onScaleEvent(ScaleEvent event) {
        animations.put(event.getEntity(), new AnimationInstruction(event.getWidth(), event.getHeight(), event.getStep(),
                event.getOnFinish(), AnimationInstruction.Type.Scale));
    }

    private void moveAnimation(CScreenPosition cScreenPosition, AnimationInstruction value) {
        if (cScreenPosition.getX() != value.getX())
            cScreenPosition.setX(cScreenPosition.getX() + value.getStep());

        if (cScreenPosition.getY() != value.getY())
            cScreenPosition.setY(cScreenPosition.getY() + value.getStep());

        if ((value.getStep() > 0 && cScreenPosition.getX() >= value.getX() && cScreenPosition.getY() >= value.getY()) ||
                (value.getStep() < 0 && cScreenPosition.getX() <= value.getX() && cScreenPosition.getY() <= value.getY())) {
            cScreenPosition.setX(value.getX());
            cScreenPosition.setY(value.getY());
            value.setFinished(true);
        }
    }

    private void scaleAnimation(CTexture cTexture, CScreenPosition cScreenPosition, AnimationInstruction value) {
        if (cTexture.getWidth() != value.getX()) {
            cTexture.setWidth(cTexture.getWidth() + value.getStep());
            cTexture.setHeight(cTexture.getHeight() + value.getStep());
//
//            cScreenPosition.setY(cScreenPosition.getY()  - value.getStep() /2);
//            cScreenPosition.setX(cScreenPosition.getX() - value.getStep() /2);

            if ((value.getStep() > 0 && cTexture.getHeight() >= value.getY() && cTexture.getWidth() >= value.getX()) ||
            ((value.getStep() < 0 && cTexture.getHeight() <= value.getY() && cTexture.getWidth() <= value.getX()))) {
                value.setFinished(true);
                cTexture.setHeight(value.getY());
                cTexture.setWidth(value.getX());
            }

            cTexture.recalculateTexture();

        }
    }

    public void doAnimations() {
        animations.forEach((key, value) -> {
            if (value.getType() == AnimationInstruction.Type.Move) {
                CScreenPosition cScreenPosition = (CScreenPosition) componentManager.getComponent(key, ComponentType.ScreenPosition);
                moveAnimation(cScreenPosition, value);
            } else if (value.getType() == AnimationInstruction.Type.Scale) {
                CTexture cTexture = (CTexture) componentManager.getComponent(key, ComponentType.Texture);
                CScreenPosition cScreenPosition = (CScreenPosition) componentManager.getComponent(key, ComponentType.ScreenPosition);
                scaleAnimation(cTexture, cScreenPosition, value);
            }

            if (value.isFinished() && value.getOnFinished() != null)
                eventDispatcher.publish(value.getOnFinished());
        });

        animations.entrySet().removeIf(entry -> entry.getValue().isFinished());
//        if (animations.isEmpty()){
//            eventDispatcher.publish(new CreateMissingEvent());
//        }
    }

    public boolean hasAnimations() {
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
        if (e.getEventType() == EventType.Move)
            onMoveEvent((MoveEvent) e);
        else if (e.getEventType() == EventType.Scale)
            onScaleEvent((ScaleEvent) e);

//        System.out.println(animations.toString());
    }

    @Override
    public Integer getId() {
        return null;
    }
}
