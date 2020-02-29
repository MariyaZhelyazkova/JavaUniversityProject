package ECS.implementation.components;

import ECS.base.interfaceses.ComponentBase;
import ECS.base.types.ComponentType;

public class BoardComponent extends ComponentBase {
    private final int XSize;
    private final int ySize;
    private final int paddingTop;
    private final int paddingLeft;
    private final int entitySze;

    public BoardComponent(int XSize, int ySize, int paddingTop, int paddingLeft, int entitySze) {
        super(ComponentType.Board);

        this.XSize = XSize;
        this.ySize = ySize;
        this.paddingTop = paddingTop;
        this.paddingLeft = paddingLeft;
        this.entitySze = entitySze;
    }

    public int getXSize() {
        return XSize;
    }

    public int getySize() {
        return ySize;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public int getEntitySze() {
        return entitySze;
    }
}
