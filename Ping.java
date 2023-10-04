import java.awt.*;
import java.io.Serializable;

public class Ping implements Serializable {

    public static final long serialVersionUID = 42L;

    private int p1score, p2score, rallyscore, time, ballXdir, ballYdir;
  
    
	private Point lpaddle, rpaddle, ball;
	
    public void setlPaddle(int x, int y)
    {
        lpaddle = new Point(x, y);
    }

    public Point getlPaddle()
    {
        return lpaddle;
    }
    
    public void setrPaddle(int x, int y)
    {
        rpaddle = new Point(x, y);
    }

    public Point getrPaddle()
    {
        return rpaddle;
    }
	
    public void setBall(int x, int y)
    {
        ball = new Point(x, y);
    }
    public Point getBall()
    {
        return ball;
    }
    
    public void setBallDirX(int dir)
    {
       ballXdir = dir;
    }
    public void setBallDirY(int dir)
    {
       ballYdir = dir;
    }
    public void moveBallX()
    {
    	ballXdir = -ballXdir;
    }
    public void moveBallY()
    {
    	ballYdir = -ballYdir;
    }
    public int getBallDirX()
    {
       return ballXdir;
    }
    public int getBallDirY()
    {
       return ballYdir;
    }
   
    public void setRallyScore(int sc)
    {
        rallyscore = sc;
    }
    public void incRallyScore()
    {
        rallyscore += 1;
    }
    public int getRallyScore()
    {
        return rallyscore;
    }
    public void setP1Score(int sc)
    {
        p1score = sc;
    }
    public void incP1Score()
    {
        p1score += 1;
    }
    public int getP1Score()
    {
        return p1score;
    }
    public void setP2Score(int sc)
    {
        p2score = sc;
    }
    public void incP2Score()
    {
        p2score += 1;
    }
    public int getP2Score()
    {
        return p2score;
    }

    public void incTime()
    {
        time++;
    }
    public void setTime(int t)
    {
        time = t;
    }
    public int getTime()
    {
        return time;
    }
   
}