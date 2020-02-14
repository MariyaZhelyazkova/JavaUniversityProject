package sandbox;

import ECS.base.ComponentManager;
import ECS.implementation.components.BoardComponent;
import ECS.implementation.components.CPosition;
import ECS.implementation.components.CScreenPosition;
import ECS.implementation.components.CTexture;
import ECS.implementation.entity.Entity;
import ECS.implementation.entity.TileTypes;
import events.implementation.ClickEvent;
import events.implementation.CreateTileEntityEvent;
import events.implementation.EventDispatcher;
import events.implementation.InitPopulationEvent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Layer extends Entity {

    private final EventDispatcher eventDispatcher;
    private final int paddingTop;
    private final int paddingLeft;

    public Layer(int xSize, int ySize,  int paddingTop, int paddingLeft, EventDispatcher eventDispatcher, ComponentManager componentManager) {
        this.eventDispatcher = eventDispatcher;
        this.paddingLeft = paddingLeft;
        this.paddingTop = paddingTop;

        try {
            componentManager.registerEntity(this);
            componentManager.addComponent(this, new BoardComponent(xSize, ySize, paddingTop, paddingLeft, 64));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        eventDispatcher.publish(new InitPopulationEvent(this));
    }

    public void onClick(MouseEvent e) {
        Point position = e.getPoint();
        eventDispatcher.publish(new ClickEvent((position.x - paddingLeft) / 64, (position.y - paddingTop) / 64));
    }
}
