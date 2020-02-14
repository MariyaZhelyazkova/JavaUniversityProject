package ECS.base.interfaceses;

import ECS.base.types.ComponentType;

import java.util.Vector;

public interface IComponentManager {

    void registerEntity(Entity entity);

    void unregisterEntity(Entity entity);

    void addComponent(Entity entity, IComponent component) throws Exception;

    void RemoveComponent(Entity entity, IComponent component);

    IComponent getComponent(Entity entity, ComponentType componentType);

    Vector<IComponent> getComponent(ComponentType componentType);

    Vector<IComponent> getComponent(Entity entity);

    Entity getEntity(IComponent iComponent);
}
