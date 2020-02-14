package ECS.base.interfaceses;

import static ECS.base.EntityCounter.getNextEntityId;

public class Entity {
    private final int id;
    private final String entityType;

    public Entity(String entityType){
        this.id = getNextEntityId();
        this.entityType = entityType;
    }

    public int getId() {
        return id;
    }

    public String getEntityType() {
        return entityType;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", entityType='" + entityType + '\'' +
                '}';
    }
}
