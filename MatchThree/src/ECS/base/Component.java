package ECS.base;

import ECS.base.types.ComponentType;

public class Component {

    private static int componentId = 0;

    private static int getNextId(){
        return componentId++;
    }

    private final int id;

    public int getId() {
        return id;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    private final ComponentType componentType;

    public Component(ComponentType componentType){
        this.id = getNextId();
        this.componentType = componentType;
    }

}
