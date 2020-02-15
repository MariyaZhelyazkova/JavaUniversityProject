package sandbox;

import ECS.base.ComponentManager;
import ECS.implementation.systems.AnimationSystem;
import ECS.implementation.systems.MatchingSystem;
import ECS.implementation.systems.PopulatingSystem;
import ECS.implementation.systems.SWorld;
import events.implementation.EventDispatcher;
import events.types.EventType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainThread extends Thread {

    private final EventDispatcher eventDispatcher;
    private boolean running = false;
    private Board board;
    private AnimationSystem animationSystem;

    public MainThread(Board board) {
        super();
        this.board = board;
        this.eventDispatcher = new EventDispatcher();

        ComponentManager componentManager = new ComponentManager();
        this.board.setComponentManager(componentManager);
        eventDispatcher.subscribe(EventType.EntityDestroyed, componentManager);

//        SWorld sWorld = new SWorld(componentManager);
//        eventDispatcher.subscribe(EventType.Click, sWorld);

        PopulatingSystem populatingSystem = new PopulatingSystem(eventDispatcher, componentManager);
        eventDispatcher.subscribe(EventType.InitPopulation, populatingSystem);
        eventDispatcher.subscribe(EventType.CreateMissing, populatingSystem);

        MatchingSystem matchingSystem = new MatchingSystem(componentManager, eventDispatcher);
        eventDispatcher.subscribe(EventType.Click, matchingSystem);

        animationSystem = new AnimationSystem(eventDispatcher, componentManager);
        eventDispatcher.subscribe(EventType.Move, animationSystem);

        Layer layer = new Layer(8, 8, 50, 100, eventDispatcher, componentManager);

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

    @Override
    public void run() {
        System.out.println("Running.....");

        while (running) {
            if (animationSystem.hasAnimations())
                animationSystem.doAnimations();
            else
                eventDispatcher.dispatchEvent();

            board.repaint();
            try {
                this.sleep(16);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
