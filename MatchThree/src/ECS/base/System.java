package ECS.base;

import java.util.HashMap;
import java.util.ArrayList;

public class System {

    private static int systemId = 0;

    private static int getNextId(){
        return systemId++;
    }

    private final int id;
    private final ArrayList<ComponentType> requiredComponents;
    private final SystemType systemType;
    private HashMap<ComponentType, Component> components;

    public int getId() {
        return id;
    }

    public ArrayList<ComponentType> getRequiredComponents() {
        return requiredComponents;
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public Component getComponent(ComponentType ct) {
        return components.get(ct);
    }

    public System(SystemType systemType, ArrayList<ComponentType> requiredComponents) throws Exception {
        if (systemType == null) throw new Exception("System type unspecified");

        this.id = getNextId();
        this.requiredComponents = requiredComponents;
        this.systemType = systemType;

        this.components = new HashMap<ComponentType, Component>();
    }

    public boolean hasRequiredComponents(){
        return (requiredComponents != null);
    }

    public final void addComponent(Component c) throws Exception {
        if (c == null) throw new Exception("Component is null");

        components.put(c.getComponentType(), c);
    }

}
