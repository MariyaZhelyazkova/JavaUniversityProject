package ECS.implementation.components;

import ECS.base.interfaceses.IComponent;
import ECS.base.types.ComponentType;

public class CScreenPosition implements IComponent {
    private final ComponentType componentType = ComponentType.ScreenPosition;
    private int x, y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CScreenPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }
}
