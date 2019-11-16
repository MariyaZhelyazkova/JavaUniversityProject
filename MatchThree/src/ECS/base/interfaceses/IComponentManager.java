package ECS.base.interfaceses;

import ECS.base.types.ComponentType;

import java.util.Vector;

public interface IComponentManager {

    void registerEntity(IEntity entity);

    void unregisterEntity(IEntity entity);

    void addComponent(IEntity entity, IComponent component) throws Exception;

    void RemoveComponent(IEntity entity, IComponent component);

    IComponent getComponent(IEntity entity, ComponentType componentType);

    Vector<IComponent> getComponent(ComponentType componentType);

    Vector<IComponent> getComponent(IEntity entity);
}
