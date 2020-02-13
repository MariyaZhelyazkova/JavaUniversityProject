package sandbox;

import java.util.Vector;

public final class TileTypes {
    public static final String IMG_APPLE = "images/apple.png";
    public static final String IMG_BANNANA = "images/bannana.png";
    public static final String IMG_BLUEBERRY = "images/blueberry.png";
    public static final String IMG_CHERRY = "images/cherry.png";
    public static final String IMG_LEMON = "images/lemon.png";
    public static final String IMG_ORNAGE = "images/orange.png";
    public static final String IMG_PEACH = "images/peach.png";
    public static final String IMG_STAWBERRY = "images/stawberry.png";

    public static Vector<String> TILE_TYPES = new Vector<>(){{
        add(IMG_APPLE);
        add(IMG_BANNANA);
        add(IMG_BLUEBERRY);
        add(IMG_CHERRY);
        add(IMG_LEMON);
        add(IMG_ORNAGE);
        add(IMG_PEACH);
        add(IMG_STAWBERRY);
    }};
}
