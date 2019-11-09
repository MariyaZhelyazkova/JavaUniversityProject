package ECS.base;

import ECS.base.types.ComponentType;

import java.util.HashMap;
import java.util.Map;

public class Entity {

    private static int entityId = 0;

    private static int getNextId(){
        return entityId++;
    }

    private final int id;
    private HashMap<ComponentType, Component> components;

    public int getId() {
        return id;
    }


    public Map<ComponentType, Component> getComponents() {
        return components;
    }

    public Entity(){
        this.id = getNextId();
        components = new HashMap<>();
    }

    public void registerComponent(Component c) throws Exception {
        if (components != null) {
            if (components.get(c.getComponentType()) != null) throw new Exception("Component already registered");
        }

        components.put(c.getComponentType(), c);
    }

    public void unregisterComponent(Component c) throws Exception {
        if ((components == null) || (components.get(c.getComponentType()) == null))
            throw new Exception("Component not registered");

        components.remove(c.getComponentType());
    }
}
