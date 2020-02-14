package sandbox;

import ECS.base.ComponentManager;
import ECS.base.types.ComponentType;
import ECS.implementation.components.CScreenPosition;
import ECS.implementation.components.CTexture;
import ECS.implementation.systems.SRenderer;
import events.implementation.EventDispatcher;
import org.w3c.dom.events.EventException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Board extends JPanel {
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 800;

    private Layer layer;
    private  SRenderer sRenderer;
    private ComponentManager componentManager;
    private EventDispatcher eventDispatcher;
    private boolean doPaint = false;

    public Board(){
        initBoard();
    }

    private void initBoard(){
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        sRenderer = new SRenderer(this);
    }

    public ComponentManager getComponentManager() {
        return componentManager;
    }

    public void setComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
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

    public void startGame(){
        MainThread mainThread = new MainThread(this);
        mainThread.setRunning(true);
        mainThread.start();

    }

}
