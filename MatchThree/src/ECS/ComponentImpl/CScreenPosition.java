package ECS.ComponentImpl;

import ECS.base.Component;
import ECS.base.ComponentType;

public class CScreenPosition extends Component {
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
        super(ComponentType.ScreenPosition);
        this.x = x;
        this.y = y;
    }
}
