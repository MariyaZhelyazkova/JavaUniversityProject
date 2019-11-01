package ECS.base;

import java.util.HashMap;
import java.util.Map;

public class Entity {

    private static int entityId = 0;

    private static int getNextId(){
        return entityId++;
    }

    private final int id;
    private HashMap<ComponentType, Component> components;
    private HashMap<SystemType, System> systems;

    public int getId() {
        return id;
    }

    public HashMap<SystemType, System> getSystems() {
        return systems;
    }

    public Map<ComponentType, Component> getComponents() {
        return components;
    }

    public Entity(){
        this.id = getNextId();
        components = new HashMap<ComponentType, Component>();
        systems = new HashMap<SystemType, System>();
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

    public void registerSystem(System s) throws Exception {
        if (systems != null) {
            if (systems.get(s.getSystemType()) != null) throw new Exception("System already registered");

            if (s.hasRequiredComponents()) {
                for (ComponentType ct : s.getRequiredComponents()){
                    if (components.get(ct) != null){
                        s.addComponent(components.get(ct));
                    } else {
                        throw new Exception("No Component found");
                    }
                }
            }
        }

        systems.put(s.getSystemType(), s);
    }

    public void unregisterSystem(System s) throws Exception {
        if ((systems == null) || (systems.get(s.getSystemType()) == null))
            throw new Exception("System not registered");

        systems.remove(s.getSystemType());
    }
}
