package ECS.base.interfaceses;

import ECS.base.types.ComponentType;
import ECS.base.types.SystemType;

import java.util.Vector;

public interface ISystem {

    SystemType  getSystemType();
    Vector<ComponentType> getRequiredComponents();
}
