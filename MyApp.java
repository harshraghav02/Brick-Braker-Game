package Brick_Breaker;

import javax.swing.*;

public class MyApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GamePlay game = new GamePlay();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setBounds(10,10,700,600);
        frame.setTitle("Brick Braker");
        frame.setVisible(true);
        frame.add(game);
    }
}
