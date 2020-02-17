package ECS.implementation.components;

import ECS.base.interfaceses.IComponent;
import ECS.base.types.ComponentType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TextureComponent implements IComponent {
    private final ComponentType componentType = ComponentType.Texture;
    private final String imageName;
    private BufferedImage texture;
    private int height, width;

    public TextureComponent(String imageName, int width, int height) throws Exception {
        if (!imageName.isBlank()) {
            this.imageName = imageName;
            this.width = width;
            this.height = height;
            loadImage();
        } else
            throw new Exception("No image name provided");
    }

    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private void loadImage() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            texture = ImageIO.read(classLoader.getResourceAsStream(imageName));
            texture =  resize(texture, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void recalculateTexture() {
        loadImage();
    }

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }

}
