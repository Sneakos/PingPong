
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player
{
    PingPong pong;
    Color col = Color.RED;
    int x, y, width, height;
    BufferedImage img;
    
    public Player(PingPong ping, int x, int y, int width, int height)
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
        g2.fillRect(x, y, width, height);
    }
    
    public void tick()
    {
        if(pong.up)
            y -= pong.dist;
        if(pong.down)
            y += pong.dist;
        checkCollision();
    }
    
    public void tick2()
    {
        if(pong.rUp)
            y -= pong.dist;
        if(pong.rDown)
            y += pong.dist;
        checkCollision();
    }
    
    public void checkCollision()
    {
        if(y <= 0)
            y += pong.dist;
        if(y > pong.getHeight() - height - 5)
            y -= pong.dist;
    }
}
