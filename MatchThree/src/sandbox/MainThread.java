package sandbox;

import ECS.implementation.ComponentManager;
import ECS.base.Entity;
import ECS.base.types.EntityType;
import ECS.implementation.components.ControllerComponent;
import ECS.implementation.entity.Layer;
import ECS.implementation.systems.AnimationSystem;
import ECS.implementation.systems.MatchingSystem;
import ECS.implementation.systems.PopulatingSystem;
import events.implementation.EventDispatcher;
import events.types.EventType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainThread extends Thread {

    private final static int MAX_FPS = 60;
    private final static int MAX_FRAME_SKIPS = 5;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;

    private final EventDispatcher eventDispatcher;
    private boolean running = false;
    private Board board;
    private AnimationSystem animationSystem;

    public MainThread(Board board) {
        super();
        this.board = board;
        this.eventDispatcher = new EventDispatcher();

        ComponentManager componentManager = new ComponentManager(eventDispatcher);
        this.board.setComponentManager(componentManager);
        eventDispatcher.subscribe(EventType.EntityDestroyed, componentManager);

        Entity controllerEntity = new Entity(EntityType.STATIC_ENTITY);
        componentManager.registerEntity(controllerEntity);
        try {
            componentManager.addComponent(controllerEntity, new ControllerComponent(30, EntityType.RED, 20));
        } catch (Exception e) {
            e.printStackTrace();
        }


        PopulatingSystem populatingSystem = new PopulatingSystem(eventDispatcher, componentManager);
        eventDispatcher.subscribe(EventType.InitPopulation, populatingSystem);
        eventDispatcher.subscribe(EventType.ArrangeEntities, populatingSystem);
        eventDispatcher.subscribe(EventType.CreateMissing, populatingSystem);

        MatchingSystem matchingSystem = new MatchingSystem(componentManager, eventDispatcher);
        eventDispatcher.subscribe(EventType.Click, matchingSystem);

        animationSystem = new AnimationSystem(eventDispatcher, componentManager);
        eventDispatcher.subscribe(EventType.Move, animationSystem);
        eventDispatcher.subscribe(EventType.Scale, animationSystem);

        Layer layer = new Layer(6, 6, 108, 208, eventDispatcher, componentManager);

        this.board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                layer.onClick(e);
            }
        });
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    private void update(){
        if (animationSystem.hasAnimations())
            animationSystem.doAnimations();
        else
            eventDispatcher.dispatchEvent();
    }

    @Override
    public void run() {
        System.out.println("Running.....");

        while (running) {
            long beginTime = System.currentTimeMillis();
            int frameSkiped = 0;
            update();

            long renTime = System.nanoTime();
            board.repaint();
//            System.out.println("Rendering time " + (System.nanoTime() - renTime) / 1000000 + "ms");

            long timeDiff = System.currentTimeMillis() - beginTime;
            int sleepTime = (int) (FRAME_PERIOD - timeDiff);
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                }
            }

            while (sleepTime < 0 && frameSkiped < MAX_FRAME_SKIPS) {
                update();
                sleepTime += FRAME_PERIOD;
                frameSkiped++;
            }
        }

    }
}
