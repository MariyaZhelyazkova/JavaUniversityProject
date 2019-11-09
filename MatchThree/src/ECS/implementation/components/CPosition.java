package ECS.implementation.components;

import ECS.base.Component;
import ECS.base.types.ComponentType;

public class CPosition extends Component {
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

    public CPosition(int x, int y) {
        super(ComponentType.Position);
        this.x = x;
        this.y = y;
    }
}
