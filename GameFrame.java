package Pong;
import java.awt.*;
import javax.swing.*;
//the game frame holds the game panel

public class GameFrame extends JFrame{

    GamePanel panel;

    //a constructor
    GameFrame(){
        panel=new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game!");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); //we dont need to set the size of the frame
        this.setVisible(true);
        this.setLocationRelativeTo(null); //in the middle of the screen
    }
    
}
