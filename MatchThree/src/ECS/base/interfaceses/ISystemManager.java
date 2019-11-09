package ECS.base.interfaceses;

import ECS.base.Entity;
import ECS.base.System;
import ECS.base.types.SystemType;

public interface ISystemManager {

    void add(System system, Entity entity) throws Exception;

    void removeEntity(int entityId);

    void removeSystem(SystemType systemType, int entityid);
}
