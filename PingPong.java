
/**
 * Write a description of class PingPong here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.image.*;
import javax.swing.JFrame;

public class PingPong extends Canvas implements Runnable
{
    public boolean running = false;
    Thread gameThread;
    JFrame frame;
    static final int WIDTH = 500, HEIGHT = 500;
    Dimension size = new Dimension(WIDTH, HEIGHT);
    BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    Player player, player2;
    InputHandler ih;
    static boolean up, down, rUp, rDown;
    static boolean bLeft, bUp, bRight, bDown;
    static int dist = 5;
    static int ballSpeed = 5;
    Ball ball;
    double FPS, UPS, frames, ticks, delta;
    boolean shouldRender;

    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;
        long lastTimer = System.currentTimeMillis();
        delta = 0D;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            shouldRender = false;

            while(delta >= 1) {
                ticks++;
                tick();
                delta--;
                shouldRender = true;
            }
            if(shouldRender){
                frames++;
                render();
            }

            if(System.currentTimeMillis() - lastTime >= 1000){
                lastTimer += 1000;
                FPS = frames;
                UPS = ticks;
                frames = 0;
                ticks = 0;
            }
        }
    }

    public synchronized void start()
    {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop()
    {
        running = false;
        gameThread.stop();
        System.exit(0);
    }

    public PingPong()
    {
        createWindow();
        player = new Player(this, 10, getHeight() / 2 - 50, 20, 100);
        player2 = new Player(this, getWidth() - 30, getHeight() / 2 - 50, 20, 100);
        ball = new Ball(this, getWidth()/2, getHeight()/2, 20, 20);
        ih = new InputHandler(frame, this, this);
        up = down = false;
        bUp = false; bDown = true; bRight = false; bLeft = true;
    }

    public void createWindow()
    {
        frame = new JFrame("Ping Pong");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(size);
        frame.setLocationRelativeTo(null);

        frame.setPreferredSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);

        frame.add(this);
        frame.pack();
    }

    public void tick()
    {
        if(player != null)
        {
            player.tick();
            player2.tick2();
        }
        if(ball != null)
            ball.tick();
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();

        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

        if(player != null)
            player.render(g);
        if(player2 != null)
            player2.render(g);
        if(ball != null)
            ball.render(g);

        g.dispose();
        bs.show();
    }
}
