package ECS.implementation.systems;

import ECS.base.interfaceses.ISystem;
import ECS.base.types.ComponentType;
import ECS.base.types.SystemType;
import ECS.implementation.components.CScreenPosition;
import ECS.implementation.components.CTexture;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class SRenderer implements ISystem {
    private final SystemType systemType = SystemType.Renderer;
    private final Vector<ComponentType> requiredComponents;

    private JPanel panel;

    public SRenderer(JPanel panel){
        requiredComponents = new Vector<ComponentType>(){{
            add(ComponentType.ScreenPosition);
            add(ComponentType.Texture);
        }};

        this.panel = panel;
    }

    public void draw(Graphics2D graphics, CScreenPosition cScreenPosition, CTexture cTexture){
        graphics.drawImage(cTexture.getTexture(), null, cScreenPosition.getX(), cScreenPosition.getY());
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
