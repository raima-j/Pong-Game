package Pong;
import java.awt.*;

public class Score extends Rectangle{
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;

    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH=GAME_WIDTH;
        Score.GAME_HEIGHT=GAME_HEIGHT;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Baskerville Old Face",Font.PLAIN,50));
        g.drawLine((GAME_WIDTH/2),0,(GAME_WIDTH/2),GAME_HEIGHT);
        g.drawString(String.valueOf(player1), (GAME_WIDTH/2)-65, 50);
        g.drawString(String.valueOf(player2), (GAME_WIDTH/2)+30, 50);
    }
}
