package ECS.base.interfaceses;

import ECS.base.Entity;
import ECS.base.types.ComponentType;
import events.base.IEvent;
import events.base.IEventListener;
import events.implementation.ArrangeAntitiesEvent;
import events.implementation.EventDispatcher;
import events.types.EventType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public final class ComponentManager implements IEventListener {
    private final EventDispatcher eventDispatcher;
    private Map<Entity, List<ComponentBase>> components = new HashMap<>();

    public ComponentManager(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    public void registerEntity(Entity entity, ComponentBase... componentBases) {
        components.computeIfAbsent(entity, k -> new Vector<>(List.of(componentBases)));
    }

    public void unregisterEntity(Entity entity) {
        components.remove(entity);
    }

    public void addComponent(Entity entity, ComponentBase component) throws Exception {
        if (!components.containsKey(entity)) throw new Exception("Entity not found");

        if (!components.get(entity).contains(component))
            components.get(entity).add(component);
    }

    public void RemoveComponent(Entity entity, ComponentBase component) {
        components.get(entity).remove(component);
    }

    public ComponentBase getComponent(Entity entity, ComponentType componentType) {
        for (ComponentBase c : components.get(entity))
            if (c.getComponentType() == componentType)
                return c;

        return null;
    }

    public List<ComponentBase> getComponent(ComponentType componentType) {
        List<ComponentBase> result = new Vector<>();

        components.forEach((key, value) -> {
            for (ComponentBase c : value)
                if (c.getComponentType() == componentType)
                    result.add(c);
        });

        return result;
    }

    public List<ComponentBase> getComponent(Entity entity) {
        return components.get(entity);
    }

    public Entity getEntity(ComponentBase componentBase) {
        for (HashMap.Entry entry : components.entrySet()) {
            if (((List<ComponentBase>) entry.getValue()).contains(componentBase))
                return (Entity) entry.getKey();
        }

        return null;
    }

    public List<ComponentType> getComponentsType(Entity entity) {
        List<ComponentType> componentTypes = new Vector<>();

        components.get(entity).forEach((c) -> componentTypes.add(c.getComponentType()));

        return componentTypes;
    }

    public boolean entityHasComponents(Entity entity, List<ComponentType> reqComponents) {
        List<ComponentType> componentTypes = getComponentsType(entity);
        for (ComponentType ct : reqComponents) {
            if (!componentTypes.contains(ct))
                return false;
        }

        return true;
    }

    public Map<Entity, List<ComponentBase>> getComponents() {
        return components;
    }

    public Vector<ComponentBase> getComponents(String entityType, ComponentType componentType) {
        Vector<ComponentBase> result = new Vector<>();

        components.forEach((key, value) -> {
            for (ComponentBase c : value)
                if (c.getComponentType() == componentType && key.getEntityType().equals(entityType))
                    result.add(c);
        });

        return result;
    }

    @Override
    public void onEvent(IEvent e) {
        if (e.getEventType() == EventType.EntityDestroyed) {
            unregisterEntity(e.getEntity());
            eventDispatcher.publish(new ArrangeAntitiesEvent());
        }
    }

    @Override
    public Integer getId() {
        return null;
    }
}
