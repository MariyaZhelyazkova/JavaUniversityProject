package ECS.implementation.systems;

import ECS.base.interfaceses.SystemBase;
import ECS.base.types.ComponentType;
import ECS.implementation.components.ScreenPositionComponent;
import ECS.implementation.components.TextureComponent;

import javax.swing.*;
import java.awt.*;

public class RendererSystem extends SystemBase {

    private JPanel panel;

    public RendererSystem(JPanel panel) {
        super(ComponentType.ScreenPosition, ComponentType.Texture);
        this.panel = panel;
    }

    public void draw(Graphics2D graphics, ScreenPositionComponent screenPositionComponent, TextureComponent textureComponent) {
        graphics.drawImage(textureComponent.getTexture(), null, screenPositionComponent.getX(), screenPositionComponent.getY());
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
