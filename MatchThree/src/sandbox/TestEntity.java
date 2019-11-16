package sandbox;

import ECS.base.interfaceses.IComponentManager;
import ECS.base.interfaceses.IEntity;
import ECS.implementation.components.CPosition;

import static ECS.base.EntityCounter.getNextEntityId;

public class TestEntity implements IEntity {
    private final int id;

    public TestEntity (IComponentManager componentManager){
        this.id = getNextEntityId();

        try {
            componentManager.registerEntity(this);
            componentManager.addComponent(this, new CPosition(1, 1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getId() {
        return id;
    }
}
