package ECS.base.interfaceses;

import ECS.base.types.ComponentType;

public class ComponentBase {
    private final ComponentType componentType;

    public ComponentBase(ComponentType componentType) {
        this.componentType = componentType;
    }

    public ComponentType getComponentType() {
        return componentType;
    }
}
