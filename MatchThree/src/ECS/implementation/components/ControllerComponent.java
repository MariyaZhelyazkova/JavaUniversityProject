package ECS.implementation.components;

import ECS.base.interfaceses.IComponent;
import ECS.base.types.ComponentType;
import ECS.base.types.EntityType;

public class ControllerComponent implements IComponent {
    private final ComponentType componentType = ComponentType.Static;

    private final String winEntity;
    private final int winEntityCount;
    private int moves;
    private int winEntityCollected = 0;

    public int getWinEntityCollected() {
        return winEntityCollected;
    }

    public void setWinEntityCollected(int winEntityCollected) {
        this.winEntityCollected = winEntityCollected;
    }

    public ControllerComponent(int moves, String winEntity, int winentityCount) {
        this.moves = moves;
        this.winEntity = winEntity;
        this.winEntityCount = winentityCount;
    }

    public String getWinEntity() {
        return winEntity;
    }

    public int getWinEntityCount() {
        return winEntityCount;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    @Override
    public ComponentType getComponentType() {
        return null;
    }
}
