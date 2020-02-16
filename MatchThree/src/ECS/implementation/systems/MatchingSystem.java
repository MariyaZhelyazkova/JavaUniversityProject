package ECS.implementation.systems;

import ECS.base.ComponentManager;
import ECS.base.interfaceses.Entity;
import ECS.base.interfaceses.ISystem;
import ECS.base.types.ComponentType;
import ECS.base.types.SystemType;
import ECS.implementation.components.CPosition;
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
        List<CPosition> components = (List<CPosition>) (List<?>) componentManager.getComponent(ComponentType.Position);
        for (CPosition cPosition : components)
            if (cPosition.getY() == yPos && cPosition.getX() == xPos)
                return componentManager.getEntity(cPosition);

        return null;
    }

    private boolean findMatches(Entity entity) {
        Vector<Entity> matchedEntity = new Vector<>();

        @SuppressWarnings("unchecked")
        List<CPosition> positions = (List<CPosition>) (List<?>) componentManager.getComponents(entity.getEntityType(), ComponentType.Position);

        addNeighbour((CPosition) componentManager.getComponent(entity, ComponentType.Position), positions, matchedEntity);

        if (matchedEntity.size() > 1) {
            destroyEntities(matchedEntity);
            return true;
        }

        return false;
    }

    private void addNeighbour(CPosition cPosition, List<CPosition> positions, List<Entity> matchedEntity) {
        for (CPosition position : positions) {
            if ((position.getX() == cPosition.getX() && position.getY() == cPosition.getY() + 1) ||
                    (position.getX() == cPosition.getX() && position.getY() == cPosition.getY() - 1) ||
                    (position.getX() == cPosition.getX() + 1 && position.getY() == cPosition.getY()) ||
                    (position.getX() == cPosition.getX() - 1 && position.getY() == cPosition.getY())) {
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
