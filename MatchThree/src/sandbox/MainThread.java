package sandbox;

import ECS.base.ComponentManager;
import ECS.implementation.systems.PopulatingSystem;
import ECS.implementation.systems.SWorld;
import events.implementation.EventDispatcher;
import events.types.EventType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainThread extends Thread {

    private boolean running = false;
    private Board board;
    private final EventDispatcher eventDispatcher;

    public MainThread(Board board) {
        super();
        this.board = board;
        this.eventDispatcher = new EventDispatcher();

        ComponentManager componentManager = new ComponentManager();
        this.board.setComponentManager(componentManager);

        SWorld sWorld = new SWorld(componentManager);
        eventDispatcher.subscribe(EventType.Click, sWorld);
        eventDispatcher.subscribe(EventType.CreateEntity, sWorld);

        PopulatingSystem populatingSystem = new PopulatingSystem(eventDispatcher, componentManager);
        eventDispatcher.subscribe(EventType.InitPopulation, populatingSystem);

        Layer layer = new Layer(8, 4, 50, 100, eventDispatcher, componentManager);

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
    public void run(){
        System.out.println("Running.....");

        while (running){
            eventDispatcher.dispatchEvent();
            board.repaint();
            try{
                this.sleep(16);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
