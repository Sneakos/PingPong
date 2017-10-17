
/**
 * Write a description of class InputHandler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Canvas;

import javax.swing.JFrame;

public class InputHandler implements KeyListener
{
    PingPong pong;
    
    public InputHandler(JFrame f, Canvas c, PingPong p)
    {
        f.addKeyListener(this);
        c.addKeyListener(this);
        pong = p;
    }
    
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W)
            pong.up = true;
        if(keyCode == KeyEvent.VK_S)
            pong.down = true;
        if(keyCode == KeyEvent.VK_UP)
            pong.rUp = true;
        if(keyCode == KeyEvent.VK_DOWN)
            pong.rDown = true;
    }
    
    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W)
            pong.up = false;
        if(keyCode == KeyEvent.VK_S)
            pong.down = false;
        if(keyCode == KeyEvent.VK_UP)
            pong.rUp = false;
        if(keyCode == KeyEvent.VK_DOWN)
            pong.rDown = false;
    }
    
    public void keyTyped(KeyEvent e){}
}
