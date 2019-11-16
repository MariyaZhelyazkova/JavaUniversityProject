package sandbox;

import ECS.base.ComponentManager;
import ECS.implementation.systems.SMovement;
import events.implementation.EventDispatcher;
import events.implementation.MoveEvent;
import events.types.EventType;

public class MatchThree {

    public static void main(String[] args){

        System.out.println("Testing");

        ComponentManager componentManager = new ComponentManager();
        TestEntity t1 = new TestEntity(componentManager);
        TestEntity t2 = new TestEntity(componentManager);

        EventDispatcher eventDispatcher = new EventDispatcher();
        try {
            SMovement sMovement = new SMovement();
            eventDispatcher.subscribe(EventType.Move, sMovement);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        eventDispatcher.publish(new MoveEvent(t1, 2, 2));
        eventDispatcher.publish(new MoveEvent(t2, 3, 3));

        while (true) {
            eventDispatcher.dispatchEvent();
        }
    }
}
