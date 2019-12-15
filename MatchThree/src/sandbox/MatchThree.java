package sandbox;

import javax.swing.*;
import java.awt.*;

public class MatchThree extends JFrame {

    public Board board;

    private void Init(){
        setTitle("Asteroids");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public MatchThree(){
        Init();
        board = new Board();
        add(board);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            MatchThree ex = new MatchThree();
            ex.setVisible(true);

            ex.board.repaint();
        });

//        ComponentManager componentManager = new ComponentManager();
//        TestEntity t1 = new TestEntity(componentManager);
//        TestEntity t2 = new TestEntity(componentManager);
//
//        EventDispatcher eventDispatcher = new EventDispatcher();
//        try {
//            SMovement sMovement = new SMovement();
//            eventDispatcher.subscribe(EventType.Move, sMovement);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        eventDispatcher.publish(new MoveEvent(t1, 2, 2));
//        eventDispatcher.publish(new MoveEvent(t2, 3, 3));
//
//        while (true) {
//            eventDispatcher.dispatchEvent();
//        }
    }
}
