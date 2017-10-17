
/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball
{
    PingPong pong;
    Color col = Color.WHITE;
    int x, y, width, height;
    BufferedImage img;
    
    public Ball(PingPong ping, int x, int y, int width, int height)
    {
        pong = ping;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void render(Graphics g2)
    {
        g2.setColor(col);
        g2.fillOval(x, y, width, height);
    }
    
    public void tick()
    {
        checkCollision();
        if(pong.bUp)
            y -= pong.ballSpeed;
        if(pong.bDown)
            y += pong.ballSpeed;
        if(pong.bLeft)
            x -= pong.ballSpeed;
        if(pong.bRight)
            x += pong.ballSpeed;
    }
    
    public void checkCollision()
    {
        if(y <= 5) //hitting top
        {
            pong.bUp = false;
            pong.bDown = true;
        }
        if(y >= pong.getHeight() - height - 10) //hitting bottom
        {
            pong.bDown = false;
            pong.bUp = true;
        }
        if(x - 2 <= pong.player.x + pong.player.width && y >= pong.player.y && y <= pong.player.y + pong.player.height) //hitting left
        {
            pong.bLeft = false;
            pong.bRight = true;
        }
        if(x + width >= pong.player2.x - 5 && y >= pong.player2.y && y <= pong.player2.y + pong.player2.height) //hitting right
        {
            pong.bRight = false;
            pong.bLeft = true;
        }
        if(x <= 0 || x + width>= pong.getWidth())
        {
            pong.bUp = pong.bRight = pong.bLeft = pong.bDown = false;
        }
    }
}
