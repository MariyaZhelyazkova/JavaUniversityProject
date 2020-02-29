package ECS.implementation.systems;

import ECS.base.Entity;
import ECS.base.interfaceses.SystemBase;
import ECS.base.types.ComponentType;
import ECS.implementation.ComponentManager;
import ECS.implementation.components.PositionComponent;
import ECS.implementation.components.ScreenPositionComponent;
import ECS.implementation.components.TextureComponent;
import events.base.IEvent;
import events.base.IEventListener;
import events.implementation.EventDispatcher;
import events.implementation.MoveEvent;
import events.implementation.ScaleEvent;
import events.types.EventType;

import java.util.HashMap;
import java.util.Map;

public class AnimationSystem extends SystemBase implements IEventListener {
    private final EventDispatcher eventDispatcher;
    private final ComponentManager componentManager;
    private Map<Entity, AnimationInstruction> animations = new HashMap<>();

    public AnimationSystem(EventDispatcher eventDispatcher, ComponentManager componentManager) {
        super(ComponentType.Position);
        this.eventDispatcher = eventDispatcher;
        this.componentManager = componentManager;
    }

    private void onMoveEvent(MoveEvent event) {
        animations.put(event.getEntity(), new AnimationInstruction(event.getX() * 64 + 208, event.getY() * 64 + 108, 4 * event.getStepMiltiplier(),
                event.getOnFinish(), AnimationInstruction.Type.Move));
        PositionComponent positionComponent = (PositionComponent) componentManager.getComponent(event.getEntity(), ComponentType.Position);
        positionComponent.setX(event.getX());
        positionComponent.setY(event.getY());
    }

    private void onScaleEvent(ScaleEvent event) {
        animations.put(event.getEntity(), new AnimationInstruction(event.getWidth(), event.getHeight(), event.getStep(),
                event.getOnFinish(), AnimationInstruction.Type.Scale));
    }

    private void moveAnimation(ScreenPositionComponent screenPositionComponent, AnimationInstruction value) {
        if (screenPositionComponent.getX() != value.getX())
            screenPositionComponent.setX(screenPositionComponent.getX() + value.getStep());

        if (screenPositionComponent.getY() != value.getY())
            screenPositionComponent.setY(screenPositionComponent.getY() + value.getStep());

        if ((value.getStep() > 0 && screenPositionComponent.getX() >= value.getX() && screenPositionComponent.getY() >= value.getY()) ||
                (value.getStep() < 0 && screenPositionComponent.getX() <= value.getX() && screenPositionComponent.getY() <= value.getY())) {
            screenPositionComponent.setX(value.getX());
            screenPositionComponent.setY(value.getY());
            value.setFinished(true);
        }
    }

    private void scaleAnimation(TextureComponent textureComponent, ScreenPositionComponent screenPositionComponent, AnimationInstruction value) {
        if (textureComponent.getWidth() != value.getX()) {
            textureComponent.setWidth(textureComponent.getWidth() + value.getStep());
            textureComponent.setHeight(textureComponent.getHeight() + value.getStep());
//
//            cScreenPosition.setY(cScreenPosition.getY()  - value.getStep() /2);
//            cScreenPosition.setX(cScreenPosition.getX() - value.getStep() /2);

            if ((value.getStep() > 0 && textureComponent.getHeight() >= value.getY() && textureComponent.getWidth() >= value.getX()) ||
                    ((value.getStep() < 0 && textureComponent.getHeight() <= value.getY() && textureComponent.getWidth() <= value.getX()))) {
                value.setFinished(true);
                textureComponent.setHeight(value.getY());
                textureComponent.setWidth(value.getX());
            }

            textureComponent.recalculateTexture();

        }
    }

    public void doAnimations() {
        animations.forEach((key, value) -> {
            if (value.getType() == AnimationInstruction.Type.Move) {
                ScreenPositionComponent screenPositionComponent = (ScreenPositionComponent) componentManager.getComponent(key, ComponentType.ScreenPosition);
                moveAnimation(screenPositionComponent, value);
            } else if (value.getType() == AnimationInstruction.Type.Scale) {
                TextureComponent textureComponent = (TextureComponent) componentManager.getComponent(key, ComponentType.Texture);
                ScreenPositionComponent screenPositionComponent = (ScreenPositionComponent) componentManager.getComponent(key, ComponentType.ScreenPosition);
                scaleAnimation(textureComponent, screenPositionComponent, value);
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
