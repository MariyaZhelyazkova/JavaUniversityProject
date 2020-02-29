package ECS.base.interfaceses;

import ECS.base.types.ComponentType;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class SystemBase {
    private final List<ComponentType> requiredComponents = new Vector<>();

    public SystemBase(ComponentType... componentTypes) {
        requiredComponents.addAll(Arrays.asList(componentTypes));
    }

    public List<ComponentType> getRequiredComponents() {
        return requiredComponents;
    }
}
