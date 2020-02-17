package ECS.base;

public class Entity {
    private static int entityId = 0;
    private final int id;
    private final String entityType;
    public Entity(String entityType) {
        this.id = getNextEntityId();
        this.entityType = entityType;
    }

    public static int getNextEntityId() {
        return entityId++;
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
