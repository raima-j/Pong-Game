package Pong;
import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{

    Random random;
    int xVelocity;
    int yVelocity;
    int speed=2;
    
    Ball(int x, int y, int width, int height){
        super(x,y,width,height);
        random=new Random();

        int randomXDirection=random.nextInt(2);  //so it chooses from 0 or 1 to select a direction
        if (randomXDirection==0)
        randomXDirection--;
        setXDirection(randomXDirection*speed);

        int randomYDirection=random.nextInt(2);
        if (randomYDirection==0)
        randomYDirection--;
        setYDirection(randomYDirection*speed);
    }
    public void setXDirection(int randomXDirection){
        xVelocity=randomXDirection;
    }
    public void setYDirection(int randomYDirection){
        yVelocity=randomYDirection;
    }
    public void move(){
        x+=xVelocity;
        y+=yVelocity;

    }
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}
