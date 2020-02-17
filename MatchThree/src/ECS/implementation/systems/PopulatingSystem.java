package ECS.implementation.systems;

import ECS.base.ComponentManager;
import ECS.base.Entity;
import ECS.base.interfaceses.ISystem;
import ECS.base.types.ComponentType;
import ECS.base.types.EntityType;
import ECS.base.types.SystemType;
import ECS.implementation.components.BoardComponent;
import ECS.implementation.components.PositionComponent;
import ECS.implementation.components.ScreenPositionComponent;
import ECS.implementation.components.TextureComponent;
import events.base.IEvent;
import events.base.IEventListener;
import events.implementation.CreateMissingEvent;
import events.implementation.EventDispatcher;
import events.implementation.MoveEvent;
import events.implementation.ScaleEvent;
import events.types.EventType;
import ECS.implementation.entity.Layer;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class PopulatingSystem implements ISystem, IEventListener {
    private final SystemType systemType = SystemType.Population;
    private final Vector<ComponentType> requiredComponents = new Vector<ComponentType>() {{
        add(ComponentType.Position);
    }};
    private final EventDispatcher eventDispatcher;
    private final ComponentManager componentManager;
    private BoardComponent boardComponent;

    public PopulatingSystem(EventDispatcher eventDispatcher, ComponentManager componentManager) {
        this.eventDispatcher = eventDispatcher;
        this.componentManager = componentManager;
    }

    private void createEntityAt(int xPos, int yPos, String tileType) {
        createEntityAt(xPos, yPos, tileType, boardComponent.getEntitySze(), boardComponent.getEntitySze());
    }

    private Entity createEntityAt(int xPos, int yPos, String tileType, int width, int heigth) {
        Entity entity = new Entity(tileType);

        try {
            componentManager.registerEntity(entity);
            componentManager.addComponent(entity, new PositionComponent(xPos, yPos));
            componentManager.addComponent(entity, new ScreenPositionComponent(
                    xPos * boardComponent.getEntitySze() + boardComponent.getPaddingLeft(),
                    yPos * boardComponent.getEntitySze() + boardComponent.getPaddingTop()));
            componentManager.addComponent(entity, new TextureComponent(tileType, width, heigth));

            return entity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private void initPopulation(BoardComponent boardComponent) {
        this.boardComponent = boardComponent;
        Random rand = new Random();
        for (int y = 0; y < boardComponent.getySize(); y++)
            for (int x = 0; x < boardComponent.getXSize(); x++)
                createEntityAt(x, y, EntityType.TILE_TYPES.get(rand.nextInt(6)));
    }

    private Entity findEntityAt(int xPos, int yPos) {
        @SuppressWarnings("unchecked")
        List<PositionComponent> components = (List<PositionComponent>) (List<?>) componentManager.getComponent(ComponentType.Position);
        for (PositionComponent positionComponent : components)
            if (positionComponent.getY() == yPos && positionComponent.getX() == xPos)
                return componentManager.getEntity(positionComponent);

        return null;
    }

    private void createMissing() {
        Random rand = new Random();

        for (int y = 0; y < boardComponent.getySize(); y++)
            for (int x = 0; x < boardComponent.getXSize(); x++)
                if (findEntityAt(x, y) == null) {
                    Entity entity = createEntityAt(x, y, EntityType.TILE_TYPES.get(rand.nextInt(6)), 2, 2);
                    if (entity != null)
                        eventDispatcher.publish(new ScaleEvent(4, entity, boardComponent.getEntitySze(), boardComponent.getEntitySze(), null));
                }
    }

    private void arrangeEntities() {
        int events = 0;
        for (int x = 0; x < boardComponent.getXSize(); x++) {
            int stepY = 0;
            for (int y = boardComponent.getySize() -1; y >= 0; y--) {
                Entity entity = findEntityAt(x, y);
                if (entity == null)
                    stepY++;
                else if (stepY > 0) {
                    events++;
                    eventDispatcher.publish(new MoveEvent(entity, x, y + stepY, stepY, new CreateMissingEvent()));
                }
            }
        }

        if (events == 0)
            eventDispatcher.publish(new CreateMissingEvent());
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
        if (e.getEventType() == EventType.InitPopulation) {
            Layer layer = (Layer) e.getEntity();
            initPopulation((BoardComponent) componentManager.getComponent(layer, ComponentType.Board));
        } else if (e.getEventType() == EventType.CreateMissing)
            createMissing();
        else if (e.getEventType() == EventType.ArrangeEntities)
            arrangeEntities();
    }

    @Override
    public Integer getId() {
        return null;
    }
}
