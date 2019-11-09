package ECS.base;

import ECS.base.interfaceses.ISystemManager;
import ECS.base.types.ComponentType;
import ECS.base.types.SystemType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SystemManager implements ISystemManager {

    private HashMap<SystemType, HashMap<Integer, System>> systems;

    public SystemManager(){
        systems = new HashMap<>();
    }

    @Override
    public void add(System system, Entity entity) throws Exception {
        //Checking if Entity meets System requarments
        if (system.hasRequiredComponents()) {
            for (ComponentType ct : system.getRequiredComponents()){
                if (entity.getComponents().get(ct) != null){
                    system.addComponent(entity.getComponents().get(ct));
                } else {
                    throw new Exception("No Component found");
                }
            }
        }

        //Checking if System exists
        if (systems.get(system.getSystemType()) != null){
            if (systems.get(system.getSystemType()).get(entity.getId()) != null)
                throw new Exception("Entity already exists");

            systems.get(system.getSystemType()).put(entity.getId(), system);
        }

        systems.put(system.getSystemType(), new HashMap<>());
        systems.get(system.getSystemType()).put(entity.getId(), system);
    }

    @Override
    public void removeEntity(int entityId) {
        Iterator it = systems.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();

            HashMap<Integer, System> system = (HashMap<Integer, System>)pair.getValue();
            if (system.get(entityId) != null)
                system.remove(entityId);
        }
    }

    @Override
    public void removeSystem(SystemType systemType, int entityid) {
        if ((systems.get(systemType) != null) && systems.get(systemType).get(entityid) != null)
            systems.get(systemType).remove(entityid);
    }
}
