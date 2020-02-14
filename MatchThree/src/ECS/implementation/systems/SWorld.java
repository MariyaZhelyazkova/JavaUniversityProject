package ECS.implementation.systems;

import ECS.base.ComponentManager;
import ECS.base.interfaceses.Entity;
import ECS.base.interfaceses.ISystem;
import ECS.base.types.ComponentType;
import ECS.base.types.SystemType;
import ECS.implementation.components.CPosition;
import events.base.IEvent;
import events.base.IEventListener;
import events.implementation.ClickEvent;
import events.implementation.CreateTileEntityEvent;

import java.util.List;
import java.util.Vector;

public class SWorld implements ISystem, IEventListener {
    private final SystemType systemType = SystemType.Renderer;
    private final Vector<ComponentType> requiredComponents;
    private final ComponentManager componentManager;

    public SWorld(ComponentManager componentManager){
        this.componentManager = componentManager;

        requiredComponents = new Vector<ComponentType>(){{
            add(ComponentType.Position);
        }};
    }

    private Entity findEntityAtPos(int xPos, int yPos){
        List<CPosition> components = (List<CPosition>)(List<?>)componentManager.getComponent(ComponentType.Position);
        for (CPosition cPosition : components)
            if(cPosition.getY() == yPos && cPosition.getX() == xPos)
                return componentManager.getEntity(cPosition);

        return null;
    }

    private void createTileEntity(String tileType, int x, int y){

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

    }

    @Override
    public Integer getId() {
        return null;
    }
}
