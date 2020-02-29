package ECS.implementation.components;

import ECS.base.interfaceses.ComponentBase;
import ECS.base.types.ComponentType;

public class PositionComponent extends ComponentBase {
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

    public PositionComponent(int x, int y) {
        super(ComponentType.Position);
        this.x = x;
        this.y = y;
    }
}
