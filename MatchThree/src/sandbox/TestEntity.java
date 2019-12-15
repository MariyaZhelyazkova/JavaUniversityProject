package sandbox;

import ECS.base.interfaceses.IComponentManager;
import ECS.base.interfaceses.IEntity;
import ECS.implementation.components.CPosition;
import ECS.implementation.components.CScreenPosition;
import ECS.implementation.components.CTexture;

import static ECS.base.EntityCounter.getNextEntityId;

public class TestEntity implements IEntity {
    private final int id;

    public TestEntity (IComponentManager componentManager, String imageName, int x, int y){
        this.id = getNextEntityId();

        try {
            componentManager.registerEntity(this);
            componentManager.addComponent(this, new CPosition(1, 1));
            componentManager.addComponent(this, new CScreenPosition(x, y));
            componentManager.addComponent(this, new CTexture(imageName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getId() {
        return id;
    }
}
