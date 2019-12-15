package ECS.implementation.components;

import ECS.base.interfaceses.IComponent;
import ECS.base.types.ComponentType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CTexture implements IComponent {
    private final ComponentType componentType = ComponentType.Texture;
    private Image texture;

    public CTexture(String imageName) throws Exception {
        if (!imageName.isBlank())
            loadImage(imageName);
        else
            throw new Exception("No image name provided");
    }

    private void loadImage(String imageName){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(imageName);
        ImageIcon ii = new ImageIcon(resource);
        texture = ii.getImage();
    }

    public Image getTexture() {
        return texture;
    }

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }
}
