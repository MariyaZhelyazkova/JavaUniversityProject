package ECS.implementation.systems;

import ECS.base.ComponentManager;
import ECS.base.Entity;
import ECS.base.interfaceses.ISystem;
import ECS.base.types.ComponentType;
import ECS.base.types.SystemType;
import ECS.implementation.components.PositionComponent;
import events.base.IEvent;
import events.base.IEventListener;
import events.implementation.*;
import events.types.EventType;

import java.util.List;
import java.util.Vector;

public class MatchingSystem implements ISystem, IEventListener {
    private final SystemType systemType = SystemType.Matching;
    private final ComponentManager componentManager;
    private final EventDispatcher eventDispatcher;
    private final Vector<ComponentType> requiredComponent = new Vector<ComponentType>() {{
        add(ComponentType.Position);
    }};

    public MatchingSystem(ComponentManager componentManager, EventDispatcher eventDispatcher) {
        this.componentManager = componentManager;
        this.eventDispatcher = eventDispatcher;
    }

    private Entity findEntityAt(int xPos, int yPos) {
        @SuppressWarnings("unchecked")
        List<PositionComponent> components = (List<PositionComponent>) (List<?>) componentManager.getComponent(ComponentType.Position);
        for (PositionComponent positionComponent : components)
            if (positionComponent.getY() == yPos && positionComponent.getX() == xPos)
                return componentManager.getEntity(positionComponent);

        return null;
    }

    private boolean findMatches(Entity entity) {
        Vector<Entity> matchedEntity = new Vector<>();

        @SuppressWarnings("unchecked")
        List<PositionComponent> positions = (List<PositionComponent>) (List<?>) componentManager.getComponents(entity.getEntityType(), ComponentType.Position);

        addNeighbour((PositionComponent) componentManager.getComponent(entity, ComponentType.Position), positions, matchedEntity);

        if (matchedEntity.size() > 1) {
            destroyEntities(matchedEntity);
            return true;
        }

        return false;
    }

    private void addNeighbour(PositionComponent positionComponent, List<PositionComponent> positions, List<Entity> matchedEntity) {
        for (PositionComponent position : positions) {
            if ((position.getX() == positionComponent.getX() && position.getY() == positionComponent.getY() + 1) ||
                    (position.getX() == positionComponent.getX() && position.getY() == positionComponent.getY() - 1) ||
                    (position.getX() == positionComponent.getX() + 1 && position.getY() == positionComponent.getY()) ||
                    (position.getX() == positionComponent.getX() - 1 && position.getY() == positionComponent.getY())) {
                Entity entity = componentManager.getEntity(position);
                if (!matchedEntity.contains(entity)) {
                    matchedEntity.add(entity);
                    addNeighbour(position, positions, matchedEntity);
                }
            }
        }
    }

    private void destroyEntities(List<Entity> destroyEntities) {
        for (Entity entity : destroyEntities)
            eventDispatcher.publish(new ScaleEvent(-4, entity, 2, 2, new EntityDestroyedEvent(entity)));
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
        if (e.getEventType() == EventType.Click) {
            ClickEvent clickEvent = (ClickEvent) e;
            Entity entity = findEntityAt(clickEvent.getxPos(), clickEvent.getyPos());
            if (entity != null)
                findMatches(entity);
        }
    }

    @Override
    public Integer getId() {
        return null;
    }
}
