package ECS.base;

import ECS.base.interfaceses.ISystem;
import ECS.base.interfaceses.ISystemManager;
import ECS.base.types.SystemType;

import java.util.HashMap;

public class SystemManager implements ISystemManager {

    private HashMap<SystemType, ISystem> systems;

    public SystemManager(){
        systems = new HashMap<>();
    }

    @Override
    public void addSystem(ISystem system) {
        systems.computeIfAbsent(system.getSystemType(), k -> system);
    }

    @Override
    public void removeSystem(ISystem system) {
        if(systems.remove(system.getSystemType(), system));
    }
}
