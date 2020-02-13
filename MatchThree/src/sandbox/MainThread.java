package sandbox;

import java.awt.*;

public class MainThread extends Thread {

    private boolean running = false;
    private Board board;

    public MainThread(Board board) {
        super();
        this.board = board;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run(){
        System.out.println("Running.....");

        while (running){

            board.repaint();
            try{
                this.sleep(300);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
