package ECS.implementation.components;

import ECS.base.interfaceses.ComponentBase;
import ECS.base.types.ComponentType;

public class ScreenPositionComponent extends ComponentBase {
    private int x, y;

    public ScreenPositionComponent(int x, int y) {
        super(ComponentType.ScreenPosition);
        this.x = x;
        this.y = y;
    }

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
}
