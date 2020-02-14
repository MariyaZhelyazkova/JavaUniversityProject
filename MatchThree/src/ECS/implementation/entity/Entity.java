package ECS.implementation.entity;

import ECS.base.interfaceses.IEntity;

import static ECS.base.EntityCounter.getNextEntityId;

public class Entity implements IEntity {
    private final int id;

    public Entity(){
        this.id = getNextEntityId();
    }

    @Override
    public int getId() {
        return id;
    }
}
