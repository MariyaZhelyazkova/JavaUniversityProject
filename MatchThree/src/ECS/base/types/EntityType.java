package ECS.base.types;

import java.util.Vector;

public final class EntityType {
    public static final String RED = "images/1.png";
    public static final String GREEN = "images/2.png";
    public static final String PURPLE = "images/3.png";
    public static final String YELLOW = "images/4.png";
    public static final String ORANGE = "images/5.png";
    public static final String BLUE = "images/6.png";

    public static final String APPLE = "images/apple.png";
    public static final String BANNANA = "images/bannana.png";
    public static final String BLUEBERRY = "images/blueberry.png";
    public static final String CHERRY = "images/cherry.png";
    public static final String LEMON = "images/lemon.png";
    public static final String ORNAGE = "images/orange.png";
    public static final String PEACH = "images/peach.png";
    public static final String STAWBERRY = "images/stawberry.png";

    public static final String STATIC_ENTITY = "STATIC_ENTITY";
    public static final String HOLDER = "images/holder.png";

    public static Vector<String> TILE_TYPES = new Vector<>() {{
        add(RED);
        add(GREEN);
        add(PURPLE);
        add(YELLOW);
        add(ORANGE);
        add(BLUE);
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
