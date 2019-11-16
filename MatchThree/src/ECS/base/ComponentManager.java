package ECS.base;

import ECS.base.interfaceses.IComponent;
import ECS.base.interfaceses.IComponentManager;
import ECS.base.interfaceses.IEntity;
import ECS.base.types.ComponentType;

import java.util.HashMap;
import java.util.Vector;

public class ComponentManager implements IComponentManager {

    private HashMap<IEntity, Vector<IComponent>> components;

    public ComponentManager(){
        components = new HashMap<>();
    }

    @Override
    public void registerEntity(IEntity entity) {
        components.computeIfAbsent(entity, k -> new Vector<IComponent>());
    }

    @Override
    public void unregisterEntity(IEntity entity) {
        components.remove(entity);
    }

    @Override
    public void addComponent(IEntity entity, IComponent component) throws Exception {
        if(!components.containsKey(entity)) throw new Exception("Entity not found");

        if (!components.get(entity).contains(component))
            components.get(entity).add(component);
    }

    @Override
    public void RemoveComponent(IEntity entity, IComponent component) {
        components.get(entity).remove(component);
    }

    @Override
    public IComponent getComponent(IEntity entity, ComponentType componentType) {
        for (IComponent c : components.get(entity))
            if (c.getComponentType() == componentType)
                return c;

        return null;
    }

    @Override
    public Vector<IComponent> getComponent(ComponentType componentType) {
        Vector<IComponent> result = new Vector<>();

        components.forEach((key, value) -> {
            for (IComponent c : value)
                if (c.getComponentType() == componentType)
                    result.add(c);
        });

        return result;
    }

    @Override
    public Vector<IComponent> getComponent(IEntity entity) {
        return components.get(entity);
    }
}
