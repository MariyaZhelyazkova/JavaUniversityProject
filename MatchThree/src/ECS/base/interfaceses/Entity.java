package ECS.base.interfaceses;

import static ECS.base.EntityCounter.getNextEntityId;

public class Entity {
    private final int id;

    public Entity(){
        this.id = getNextEntityId();
    }

    public int getId() {
        return id;
    }

}
