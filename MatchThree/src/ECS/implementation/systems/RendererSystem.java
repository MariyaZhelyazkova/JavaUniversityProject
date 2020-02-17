package ECS.implementation.systems;

import ECS.base.interfaceses.ISystem;
import ECS.base.types.ComponentType;
import ECS.base.types.SystemType;
import ECS.implementation.components.ScreenPositionComponent;
import ECS.implementation.components.TextureComponent;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class RendererSystem implements ISystem {
    private final SystemType systemType = SystemType.Renderer;
    private final Vector<ComponentType> requiredComponents;

    private JPanel panel;

    public RendererSystem(JPanel panel){
        requiredComponents = new Vector<ComponentType>(){{
            add(ComponentType.ScreenPosition);
            add(ComponentType.Texture);
        }};

        this.panel = panel;
    }

    public void draw(Graphics2D graphics, ScreenPositionComponent screenPositionComponent, TextureComponent textureComponent){
        graphics.drawImage(textureComponent.getTexture(), null, screenPositionComponent.getX(), screenPositionComponent.getY());
    }

    @Override
    public SystemType getSystemType() {
        return systemType;
    }

    @Override
    public Vector<ComponentType> getRequiredComponents() {
        return requiredComponents;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
