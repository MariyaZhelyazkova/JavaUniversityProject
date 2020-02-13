package sandbox;

import com.sun.tools.javac.Main;

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

            ex.board.startGame();

        });
    }
}
