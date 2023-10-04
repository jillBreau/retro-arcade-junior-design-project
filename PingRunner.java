import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PingRunner implements ActionListener, KeyListener
{

    private Ping ping;

    private JFrame jframe;

    private PingRP renderpanel;

    private Timer timer = new Timer(20, this);

    public static final int SCALE = 10;

    private int ticks = 0;

    public static boolean over, paused, started, newG, load, serve, p1winner, p2winner, upUp, downDown, wUp, sDown;

    public static Dimension dim;

    private Saver ps;
    private Loader pl;

    public PingRunner()
    {
        jframe = new JFrame("Ping");
        dim = Toolkit.getDefaultToolkit().getScreenSize();

        jframe.setVisible(true);
        jframe.setResizable(false);
        jframe.setSize(805,700);
        jframe.setLocation(dim.width/2 - jframe.getWidth()/2, dim.height/2 - jframe.getHeight()/2 );

        jframe.add(renderpanel = new PingRP());

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jframe.addKeyListener(this);

        ping = new Ping();

        ps = new Saver();

        pl = new Loader();

        startGame();

    }

    public Ping getPing()
    {
        return ping;
    }


    public void startGame()
    {
        if (newG) {
            over = false;
            paused = false;
            serve = false;
            p1winner = false;
            p2winner = false;
            ping.setP1Score(0);
            ping.setP2Score(0);
            ping.setRallyScore(0);
            ping.setTime(0);
            ping.setBall(40, 30);
            ping.setBallDirX(-1);
            ping.setBallDirY(-1);
            ping.setlPaddle(5, 27);
            ping.setrPaddle(75,27);
            timer.start();
        }
        if (load)
        {
            loadGame();
        }

    }

    public void loadGame()
    {
        pl.PingLoad();

        p1winner = false;
        p2winner = false;
        over = false;
        paused = false;
        serve = false;

        ping.setP1Score(pl.getLoadedPing().getP1Score());
        ping.setP2Score(pl.getLoadedPing().getP2Score());
        ping.setRallyScore(pl.getLoadedPing().getRallyScore());
        ping.setTime(pl.getLoadedPing().getTime());
        ping.setBall(pl.getLoadedPing().getBall().x, pl.getLoadedPing().getBall().y);
        ping.setBallDirX(pl.getLoadedPing().getBallDirX());
        ping.setBallDirY(pl.getLoadedPing().getBallDirY());
        ping.setlPaddle(pl.getLoadedPing().getlPaddle().x, pl.getLoadedPing().getlPaddle().y);
        ping.setrPaddle(pl.getLoadedPing().getrPaddle().x, pl.getLoadedPing().getrPaddle().y);

        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (started)
        {
            renderpanel.repaint();
            ticks++;

            if (ticks % 2 == 0 && !over && !paused && started && !serve )
            {
                ping.incTime();
            }
            if (ticks % 3 == 0 && !paused && !over && started && !serve)
            {
                if(new Rectangle(ping.getBall().x*SCALE, ping.getBall().y*SCALE, SCALE, SCALE).intersects(new Rectangle((getPing().getrPaddle().x - 1)*SCALE, (getPing().getrPaddle().y)*SCALE, (SCALE), 2*SCALE)) && ticks > 4)
                {
                    //top right paddle
                    ping.incRallyScore();
                    if(ping.getBallDirY() > 0) //coming from top
                    {
                        ping.setBallDirX(-2);
                        ping.setBallDirY(1);
                    }
                    else
                    {
                        ping.setBallDirX(-1);
                        ping.setBallDirY(-2);
                    }
                }
                if(new Rectangle(ping.getBall().x*SCALE, ping.getBall().y*SCALE, SCALE, SCALE).intersects(new Rectangle((getPing().getrPaddle().x - 1)*SCALE, (getPing().getrPaddle().y + 2)*SCALE, (SCALE), 4*SCALE)) && ticks > 4)
                {
                    //middle right paddle
                    ping.incRallyScore();
                    if(ping.getBallDirY() > 0)
                    {
                        ping.setBallDirX(-1);
                        ping.setBallDirY(1);
                    }
                    else  //coming from bottom
                    {
                        ping.setBallDirX(-1);
                        ping.setBallDirY(-1);
                    }
                }
                if(new Rectangle(ping.getBall().x*SCALE, ping.getBall().y*SCALE, SCALE, SCALE).intersects(new Rectangle((getPing().getrPaddle().x - 1)*SCALE, (getPing().getrPaddle().y + 6)*SCALE, (SCALE), 3*SCALE)) && ticks > 4)
                {
                    //bottom right paddle
                    ping.incRallyScore();
                    if(ping.getBallDirY() > 0)
                    {
                        ping.setBallDirX(-1);
                        ping.setBallDirY(2);
                    }
                    else
                    {
                        ping.setBallDirX(-2);
                        ping.setBallDirY(-1);
                    }
                }

                if(new Rectangle(ping.getBall().x*SCALE, ping.getBall().y*SCALE, SCALE, SCALE).intersects(new Rectangle((getPing().getlPaddle().x + 1)*SCALE, (getPing().getlPaddle().y)*SCALE, (SCALE), 2*SCALE)) && ticks > 4)
                {
                    //top left paddle
                    ping.incRallyScore();
                    if(ping.getBallDirY() > 0)
                    {
                        ping.setBallDirX(2);
                        ping.setBallDirY(1);
                    }
                    else
                    {
                        ping.setBallDirX(1);
                        ping.setBallDirY(-2);
                    }
                }
                if(new Rectangle(ping.getBall().x*SCALE, ping.getBall().y*SCALE, SCALE, SCALE).intersects(new Rectangle((getPing().getlPaddle().x + 1)*SCALE, (getPing().getlPaddle().y + 2)*SCALE, (SCALE), 4*SCALE)) && ticks > 4)
                {
                    //middle left paddle
                    ping.incRallyScore();
                    if(ping.getBallDirY() > 0)
                    {
                        ping.setBallDirX(1);
                        ping.setBallDirY(1);
                    }
                    else
                    {
                        ping.setBallDirX(1);
                        ping.setBallDirY(-1);
                    }
                }
                if(new Rectangle(ping.getBall().x*SCALE, ping.getBall().y*SCALE, SCALE, SCALE).intersects(new Rectangle((getPing().getlPaddle().x + 1)*SCALE, (getPing().getlPaddle().y + 6)*SCALE, (SCALE), 3*SCALE)) && ticks > 4)
                {
                    //bottom left paddle
                    ping.incRallyScore();
                    if(ping.getBallDirY() > 0)
                    {
                        ping.setBallDirX(1);
                        ping.setBallDirY(2);
                    }
                    else
                    {
                        ping.setBallDirX(2);
                        ping.setBallDirY(-1);
                    }
                }

                ping.getBall().x += ping.getBallDirX();
                ping.getBall().y += ping.getBallDirY();

                if(ping.getBall().x >= 79)  //right border
                {
                    serve = true;
                    ping.incP1Score();
                }
                if(ping.getBall().y >= 66)  //bottom border
                {
                    ping.moveBallY();
                }
                if(ping.getBall().x <= 0)   //left border
                {
                    serve = true;
                    ping.incP2Score();
                }
                if(ping.getBall().y <= 0)   //top border
                {
                    ping.moveBallY();
                }

                if(ping.getP1Score() == 10) //p1 wins
                {
                    if(getPing().getRallyScore() > GameRunner.getHighScore().getPingHighScore())
                    {
                        GameRunner.getHighScore().setPingHighScore(getPing().getRallyScore());
                        GameRunner.getHsSaver().SerializeHS(GameRunner.getHighScore());
                    }
                    over = true;
                    p1winner = true;
                }
                if(ping.getP2Score() == 10) //p2 wins
                {
                    if(getPing().getRallyScore() > GameRunner.getHighScore().getPingHighScore())
                    {
                        GameRunner.getHighScore().setPingHighScore(getPing().getRallyScore());
                        GameRunner.getHsSaver().SerializeHS(GameRunner.getHighScore());
                    }
                    over = true;
                    p2winner = true;
                }
            }
            if (ticks % 4 == 0 && !paused && !over && started && serve)
            {
                ping.setBall(40, 30);
                ping.setlPaddle(5, 27);
                ping.setrPaddle(75,27);
            }
            if(!paused && !serve && !over && started)
            {
                if(upUp)
                {
                    if (ping.getrPaddle().y  > 0) //has not hit top edge
                    {
                        ping.setrPaddle(ping.getrPaddle().x, ping.getrPaddle().y - 1);
                    }
                }
                if(downDown)
                {
                    if (ping.getrPaddle().y  <= 59) //has not hit bottom edge
                    {
                        ping.setrPaddle(ping.getrPaddle().x, ping.getrPaddle().y + 1);
                    }
                }
                if(wUp)
                {
                    if (ping.getlPaddle().y > 0) //has not hit top edge
                    {
                        ping.setlPaddle(ping.getlPaddle().x, ping.getlPaddle().y - 1);
                    }
                }
                if(sDown)
                {
                    if (ping.getlPaddle().y  <= 59) //has not hit bottom edge
                    {
                        ping.setlPaddle(ping.getlPaddle().x, ping.getlPaddle().y + 1);
                    }
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int i = e.getKeyCode();

        if (i == KeyEvent.VK_UP )//left paddle up
        {
            upUp = true;
        }
        if (i == KeyEvent.VK_DOWN )//left paddle down
        {
            downDown = true;
        }
        if (i == KeyEvent.VK_W  )//right paddle up
        {
            wUp = true;
        }
        if (i == KeyEvent.VK_S )//right paddle down
        {
            sDown = true;
        }


        if (i == KeyEvent.VK_SPACE)
        {
            if (over) {
                startGame();
            }
            else if(serve){
                serve = !serve;
            }
            else
                paused = !paused;
        }
        if (i == KeyEvent.VK_N)
        {
            if (!started)
            {
                started = true;
                load = false;
                newG = true;
                startGame();
            }
        }
        if (i == KeyEvent.VK_S)
        {
            if (paused)
            {
                ps.SerializePing(ping);
            }
        }
        if (i == KeyEvent.VK_L)
        {
            if(!started || over)
            {
                started = true;
                newG = false;
                load = true;
                loadGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int i = e.getKeyCode();

        if (i == KeyEvent.VK_UP )
        {
            upUp = false;
        }
        if (i == KeyEvent.VK_DOWN )
        {
            downDown = false;
        }
        if (i == KeyEvent.VK_W  )
        {
            wUp = false;
        }
        if (i == KeyEvent.VK_S )
        {
            sDown = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

}