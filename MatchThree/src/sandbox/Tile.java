package sandbox;

import ECS.base.ComponentManager;
import ECS.base.interfaceses.IEntity;
import ECS.implementation.components.CPosition;
import ECS.implementation.components.CScreenPosition;
import ECS.implementation.components.CTexture;

import static ECS.base.EntityCounter.getNextEntityId;

public class Tile implements IEntity {
    private final int id;

    private final int size = 64;
    public Tile(ComponentManager componentManager, String tileType, int x, int y){

        this.id = getNextEntityId();

        try {
            componentManager.registerEntity(this);
            componentManager.addComponent(this, new CPosition(x, y));
            componentManager.addComponent(this, new CScreenPosition(x * size, y * size));
            componentManager.addComponent(this, new CTexture(tileType));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getId() {
        return id;
    }
}
