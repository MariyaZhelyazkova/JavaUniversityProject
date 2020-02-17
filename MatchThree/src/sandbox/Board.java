package sandbox;

import ECS.base.ComponentManager;
import ECS.base.types.ComponentType;
import ECS.implementation.components.ScreenPositionComponent;
import ECS.implementation.components.TextureComponent;
import ECS.implementation.systems.RendererSystem;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 800;

    private RendererSystem rendererSystem;
    private ComponentManager componentManager;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        rendererSystem = new RendererSystem(this);
    }

    public void setComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        componentManager.getComponents().forEach((key, value) -> {
            if (componentManager.entityHasComponents(key, rendererSystem.getRequiredComponents()))
                rendererSystem.draw(graphics2D,
                        (ScreenPositionComponent) componentManager.getComponent(key, ComponentType.ScreenPosition),
                        (TextureComponent) componentManager.getComponent(key, ComponentType.Texture));
        });

        Toolkit.getDefaultToolkit().sync();
    }

    public void startGame() {
        MainThread mainThread = new MainThread(this);
        mainThread.setRunning(true);
        mainThread.start();

    }

}
