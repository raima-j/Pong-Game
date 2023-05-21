package Pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH=1000; //different instances will have the same width, becomes a constant
    static final int GAME_HEIGHT=(int)(GAME_WIDTH*(0.56));//casting ration as integer
    static final Dimension SCREEN_SIZE= new Dimension(GAME_WIDTH,GAME_HEIGHT);//creates an object 
    static final int BALL_DIAMETER=20;
    static final int PADDLE_WIDTH=25;
    static final int PADDLE_HEIGHT=100;

    //creating it as threads, initializing variables
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1,paddle2;
    Ball ball;
    Score score;


    GamePanel(){
        //we need a new ball and some paddles when the new panel for game initializes
        newPaddles();
        newBall();
        score=new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);//enables reading the keys
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread=new Thread(this);//implementing the runnabale interface
        gameThread.start();

    }

    //to reset
    public void newBall(){
        //random=new Random();
        ball=new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);//instantiating a ball
    }
    public void newPaddles(){
        paddle1=new Paddle(0, ((GAME_HEIGHT/2)-(PADDLE_HEIGHT/2)) , PADDLE_WIDTH , PADDLE_HEIGHT , 1);
        paddle2=new Paddle((GAME_WIDTH-PADDLE_WIDTH), ((GAME_HEIGHT/2)-(PADDLE_HEIGHT/2)) , PADDLE_WIDTH , PADDLE_HEIGHT , -1);
    }
    public void paint(Graphics g){
        image=createImage(getWidth(),getHeight());  //takes the screen width height
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){  //as they're in the same package, we invoke paddle's draw method into the panel
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move(){ 
        paddle1.move();  //calling paddle moves again to make the movement smooth into the panel
        paddle2.move();
        ball.move();
    }
    public void checkCollision(){

        //checks bounce of the ball to the edges
        if (ball.y<=0){
            ball.setYDirection(-ball.yVelocity);  //reverse the velocity
        }
        if (ball.y>=(GAME_HEIGHT-BALL_DIAMETER)){
            ball.setYDirection(-ball.yVelocity);
        }


        //ball bounces off paddle 1
        if (ball.intersects(paddle1)){  //inheriting superclass method
            ball.xVelocity= Math.abs(ball.xVelocity);  //returns the reverse of the number
            ball.xVelocity++;  //increases speed after hitting
            if (ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //ball bounces off paddle 2
        if (ball.intersects(paddle2)){  //inheriting superclass method
            ball.xVelocity= Math.abs(ball.xVelocity);  //returns the reverse of the number
            ball.xVelocity++;  //increases speed after hitting
            if (ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //stops paddles from moving out of the screen
        if (paddle1.y>=(GAME_HEIGHT-PADDLE_HEIGHT)){
            paddle1.y=(GAME_HEIGHT-PADDLE_HEIGHT);
        }
        if (paddle1.y<=0){
            paddle1.y=0;
        }
        if (paddle2.y>=(GAME_HEIGHT-PADDLE_HEIGHT)){
            paddle2.y=(GAME_HEIGHT-PADDLE_HEIGHT);
        }
        if (paddle2.y<=0){
            paddle2.y=0;
        }

        //a point and reset
        if (ball.x<=0){
        score.player2++;
        newPaddles();
        newBall();
        System.out.println("PLAYER 2:"+score.player2);
        }
        if (ball.x>=(GAME_WIDTH-BALL_DIAMETER)){
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("PLAYER 1:"+score.player1);
            }
    }
    public void run(){
        //game loop for running
        long lastTime=System.nanoTime();
        double amountOfTicks=60.0;
        double ns=1000000000/amountOfTicks;
        double delta=0; //amount of elapsed game time since last frame

        while(true){
            long now=System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            if (delta>=1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    //a keyAdaptyer class to receive keyboard events
    public class ActionListener extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
