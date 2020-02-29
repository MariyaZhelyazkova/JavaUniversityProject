package ECS.implementation.components;

import ECS.base.interfaceses.ComponentBase;
import ECS.base.types.ComponentType;

public class ControllerComponent extends ComponentBase {
    private final String winEntity;
    private final int winEntityCount;
    private int moves;
    private int winEntityCollected = 0;

    public ControllerComponent(int moves, String winEntity, int winentityCount) {
        super(ComponentType.Static);
        this.moves = moves;
        this.winEntity = winEntity;
        this.winEntityCount = winentityCount;
    }

    public int getWinEntityCollected() {
        return winEntityCollected;
    }

    public void setWinEntityCollected(int winEntityCollected) {
        this.winEntityCollected = winEntityCollected;
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
}
