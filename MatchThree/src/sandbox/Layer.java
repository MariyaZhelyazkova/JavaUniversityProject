package sandbox;

import ECS.base.ComponentManager;
import ECS.base.interfaceses.IComponent;
import ECS.base.types.ComponentType;
import ECS.implementation.components.CPosition;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Vector;

public class Layer {

    private final int xSize;
    private final int ySize;
    private final ComponentManager componentManager;

    public Layer(int xSize, int ySize, ComponentManager componentManager) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.componentManager = componentManager;

        Random rand = new Random();
        for (int y = 0; y < ySize; y++)
            for (int x = 0; x < xSize; x++) {
                new Tile(this.componentManager, TileTypes.TILE_TYPES.get(rand.nextInt(8)), x, y);
            }
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    private Tile findTile(int xPos, int yPos) {
        Vector<IComponent> components = componentManager.getComponent(ComponentType.Position);
        for (IComponent c : components) {
            CPosition cPosition = (CPosition) c;
            if ((cPosition.getX() == xPos) && (cPosition.getY() == yPos))
                return (Tile) componentManager.getEntity(c);
        }

        return null;
    }

    public boolean onClick(MouseEvent e) {
        Point position = e.getPoint();

        int xPos, yPos;
        xPos = position.x / 64;
        yPos = position.y / 64;

        System.out.println("xPos = " + xPos + ";  yPos = " + yPos);

        Tile tile = findTile(xPos, yPos);

        if (tile != null) {
            System.out.println(tile.getId());
            componentManager.unregisterEntity(tile);
            Random rand = new Random();
            new Tile(this.componentManager, TileTypes.TILE_TYPES.get(rand.nextInt(8)), xPos, yPos);
            tile = findTile(xPos, yPos);
            System.out.println("New Tile created with id = " + tile.getId());
            return true;
        } else {
            System.out.println("Tile not found!!!");
            return false;
        }
    }
}
