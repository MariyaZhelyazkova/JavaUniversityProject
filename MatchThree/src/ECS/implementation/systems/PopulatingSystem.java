package ECS.implementation.systems;

import ECS.base.ComponentManager;
import ECS.base.interfaceses.ISystem;
import ECS.base.types.ComponentType;
import ECS.base.types.SystemType;
import ECS.implementation.components.BoardComponent;
import ECS.implementation.components.CPosition;
import ECS.implementation.components.CScreenPosition;
import ECS.implementation.components.CTexture;
import ECS.base.interfaceses.Entity;
import ECS.implementation.entity.TileTypes;
import events.base.IEvent;
import events.base.IEventListener;
import events.implementation.CreateTileEntityEvent;
import events.implementation.EventDispatcher;
import events.types.EventType;
import sandbox.Layer;

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

    private void createEntityAt(int xPos, int yPos, String tileType, BoardComponent boardComponent) {
        Entity entity = new Entity(tileType);

        try {
            componentManager.registerEntity(entity);
            componentManager.addComponent(entity, new CPosition(xPos, yPos));
            componentManager.addComponent(entity, new CScreenPosition(
                    xPos * boardComponent.getEntitySze() + boardComponent.getPaddingLeft(),
                    yPos * boardComponent.getEntitySze() + boardComponent.getPaddingTop()));
            componentManager.addComponent(entity, new CTexture(tileType));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void initPopulation(BoardComponent boardComponent){
        this.boardComponent = boardComponent;
        Random rand = new Random();
        for (int y = 0; y < boardComponent.getySize(); y++)
            for (int x = 0; x < boardComponent.getXSize(); x++)
                createEntityAt(x, y, TileTypes.TILE_TYPES.get(rand.nextInt(6)), boardComponent);
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
        } else if (e.getEventType() == EventType.CreateEntity){
            System.out.println("In creation");
            Random rand = new Random();
            CreateTileEntityEvent event = (CreateTileEntityEvent) e;
            createEntityAt(event.getxPos(), event.getyPos(), TileTypes.TILE_TYPES.get(rand.nextInt(6)), boardComponent);
        }
    }

    @Override
    public Integer getId() {
        return null;
    }
}
