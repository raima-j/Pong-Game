package Pong;
import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {

    int id;
    int yVelocity;
    int speed=12;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);  //extending from rect class
        this.id=id;
    }
    public void keyPressed(KeyEvent e){
        switch(id){
            case 1:
            if (e.getKeyCode()==KeyEvent.VK_W){//VK is basically virtual key codes
                setYDirection(-speed);
                move();
            }
            if (e.getKeyCode()==KeyEvent.VK_S){
                setYDirection(speed);
                move();
            }
            break;
            case -1:
            if (e.getKeyCode()==KeyEvent.VK_UP){//VK is basically virtual key codes
                setYDirection(-speed);
                move();
            }
            if (e.getKeyCode()==KeyEvent.VK_DOWN){
                setYDirection(speed);
                move();
            }
            break;
        }

    }
    public void keyReleased(KeyEvent e){
        switch(id){
            case 1:
            if (e.getKeyCode()==KeyEvent.VK_W){//when key is released
                setYDirection(0);
                move();
            }
            if (e.getKeyCode()==KeyEvent.VK_S){
                setYDirection(0);
                move();
            }
            break;
            case -1:
            if (e.getKeyCode()==KeyEvent.VK_UP){//VK is basically virtual key codes
                setYDirection(0);
                move();
            }
            if (e.getKeyCode()==KeyEvent.VK_DOWN){
                setYDirection(0);
                move();
            }
            break;
        }
    }
    public void setYDirection(int yDirection){ //paddle moves just up and down
        yVelocity=yDirection;
    }
    public void move(){
        y+=yVelocity;
    }
    public void draw(Graphics g){  //drawing the paddles
        if (id==1){//player 1
            g.setColor(Color.cyan);
        }
        else{
            g.setColor(Color.pink);
        }
        g.fillRect(x, y, width, height);
    }
}
