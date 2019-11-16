package ECS.base;

public class  EntityCounter {

    private static int entityId = 0;

    public static int getNextEntityId(){
        return entityId++;
    }
}
