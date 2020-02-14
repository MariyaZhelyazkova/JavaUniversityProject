package ECS.base;

import ECS.base.interfaceses.IComponent;
import ECS.base.interfaceses.IComponentManager;
import ECS.base.interfaceses.Entity;
import ECS.base.types.ComponentType;
import events.base.IEvent;
import events.base.IEventListener;
import events.types.EventType;

import java.util.HashMap;
import java.util.Vector;

public class ComponentManager implements IComponentManager, IEventListener {

    private HashMap<Entity, Vector<IComponent>> components;

    public ComponentManager(){
        components = new HashMap<>();
    }

    public Vector<ComponentType> getComponentsType(Entity entity){
        Vector<ComponentType> componentTypes = new Vector<>();

        components.get(entity).forEach((c) -> componentTypes.add(c.getComponentType()));

        return componentTypes;
    }

    public HashMap<Entity, Vector<IComponent>> getComponents() {
        return components;
    }

    @Override
    public void registerEntity(Entity entity) {
        components.computeIfAbsent(entity, k -> new Vector<IComponent>());
    }

    @Override
    public void unregisterEntity(Entity entity) {
        components.remove(entity);
    }

    @Override
    public void addComponent(Entity entity, IComponent component) throws Exception {
        if(!components.containsKey(entity)) throw new Exception("Entity not found");

        if (!components.get(entity).contains(component))
            components.get(entity).add(component);
    }

    @Override
    public void RemoveComponent(Entity entity, IComponent component) {
        components.get(entity).remove(component);
    }

    @Override
    public IComponent getComponent(Entity entity, ComponentType componentType) {
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
    public Vector<IComponent> getComponent(Entity entity) {
        return components.get(entity);
    }

    @Override
    public Entity getEntity(IComponent iComponent) {
        for(HashMap.Entry entry : components.entrySet()){
            if (((Vector<IComponent>) entry.getValue()).contains(iComponent))
                return (Entity) entry.getKey();
        }

        return null;
    }

    public boolean entityHasComponents(Entity entity, Vector<ComponentType> reqComponents){
        Vector<ComponentType> componentTypes = getComponentsType(entity);
        for (ComponentType ct : reqComponents) {
            if (!componentTypes.contains(ct))
                return false;
        }

        return true;
    }

    @Override
    public Vector<IComponent> getComponents(String entityType, ComponentType componentType) {
        Vector<IComponent> result = new Vector<>();

        components.forEach((key, value) -> {
            for (IComponent c : value)
                if (c.getComponentType() == componentType && key.getEntityType().equals(entityType))
                    result.add(c);
        });

        return result;
    }

    @Override
    public void onEvent(IEvent e) {
        if (e.getEventType() == EventType.EntityDestroyed){
            unregisterEntity(e.getEntity());
        }
    }

    @Override
    public Integer getId() {
        return null;
    }
}
