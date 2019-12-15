package sandbox;

import ECS.base.ComponentManager;
import ECS.base.interfaceses.IComponent;
import ECS.base.interfaceses.IEntity;
import ECS.base.types.ComponentType;
import ECS.implementation.components.CScreenPosition;
import ECS.implementation.components.CTexture;
import ECS.implementation.systems.SRenderer;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 800;

    private  SRenderer sRenderer;
    private  TestEntity t1, t2;
    private ComponentManager componentManager;

    public Board(){
        initBoard();
    }

    private void initBoard(){
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        componentManager = new ComponentManager();
        new TestEntity(componentManager, TileTypes.IMG_APPLE, 10, 10);
        new TestEntity(componentManager,  TileTypes.IMG_BANNANA, 10, 80);
        new TestEntity(componentManager,  TileTypes.IMG_BLUEBERRY, 10, 150);
        new TestEntity(componentManager,  TileTypes.IMG_CHERRY, 10, 220);
        new TestEntity(componentManager,  TileTypes.IMG_LEMON, 10, 290);
        sRenderer = new SRenderer(this);
    }

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);


        componentManager.getComponents().forEach((key, value) -> {
                if (componentManager.entityHasComponents(key, sRenderer.getRequiredComponents()))
                    sRenderer.draw(g,
                            (CScreenPosition) componentManager.getComponent(key, ComponentType.ScreenPosition),
                            (CTexture) componentManager.getComponent(key, ComponentType.Texture));
        });

        Toolkit.getDefaultToolkit().sync();
    }

}
