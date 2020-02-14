package ECS.base.types;

import java.util.Vector;

public final class EntityType {
    public static final String APPLE = "images/apple.png";
    public static final String BANNANA = "images/bannana.png";
    public static final String BLUEBERRY = "images/blueberry.png";
    public static final String CHERRY = "images/cherry.png";
    public static final String LEMON = "images/lemon.png";
    public static final String ORNAGE = "images/orange.png";
    public static final String PEACH = "images/peach.png";
    public static final String STAWBERRY = "images/stawberry.png";

    public static final String STATIC_ENTITY = "STATIC_ENTITY";

    public static Vector<String> TILE_TYPES = new Vector<>() {{
        add(APPLE);
        add(BANNANA);
        add(BLUEBERRY);
        add(CHERRY);
        add(LEMON);
        add(ORNAGE);
        add(PEACH);
        add(STAWBERRY);
    }};
}
