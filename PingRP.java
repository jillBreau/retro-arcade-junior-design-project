import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class PingRP extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        g.fillRect(0, 0 , 805, 700);

        PingRunner pingr = GameRunner.pingRunner;

        boolean p1winner = pingr.p1winner;
        boolean p2winner = pingr.p2winner;
        boolean over = pingr.over;
        boolean paused = pingr.paused;
        boolean started = pingr.started;
        boolean serve = pingr.serve;


        if(started)
        {
            g.setColor(Color.PINK);//left paddle
            g.fillRect(pingr.getPing().getlPaddle().x * PingRunner.SCALE, pingr.getPing().getlPaddle().y * PingRunner.SCALE, PingRunner.SCALE,  8*(PingRunner.SCALE));

            g.setColor(Color.CYAN);//right paddle
            g.fillRect(pingr.getPing().getrPaddle().x * PingRunner.SCALE, pingr.getPing().getrPaddle().y * PingRunner.SCALE, PingRunner.SCALE, 8*(PingRunner.SCALE));
        }
        g.setColor(Color.WHITE); //ball

        if (started)
        {
            g.fillOval(pingr.getPing().getBall().x * PingRunner.SCALE, pingr.getPing().getBall().y * PingRunner.SCALE, PingRunner.SCALE, PingRunner.SCALE);
        }

        String string = " Player1 Score: " + pingr.getPing().getP1Score() + ",              Rallies Score: " + pingr.getPing().getRallyScore() + ", Time: " + pingr.getPing().getTime() / 20 + ",  (Pause = Space)" + "              Player2 Score: " + pingr.getPing().getP2Score();

        g.setColor(Color.GREEN);

        g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);

        g.setColor(Color.YELLOW);

        String startGame = "Welcome to Ping, press L to Load the most recently saved game or press N to start a New game";
        String controls = "Player1 controls: w = (up), s = (down)      Player2 controls: up arrow = (up), down arrow = (down) ";

        if (!started) {
            g.drawString(startGame, (int) (getWidth() / 2 - startGame.length() * 2.5f), (int) pingr.dim.getHeight() / 4);
            g.drawString(controls, (int) (getWidth() / 2 - startGame.length() * 2.5f), (int) pingr.dim.getHeight() / 3);
        }

        String gameOverP1 = "Player 1 Wins! Press space to play again. The overall rallies high score is: " + GameRunner.getHighScore().getPingHighScore() + " Your rallies score was: " + pingr.getPing().getRallyScore();
        String gameOverP2 = "Player 2 Wins! Press space to play again. The overall rallies high score is: " + GameRunner.getHighScore().getPingHighScore() + " Your rallies score was: " + pingr.getPing().getRallyScore();
        String loadGame = "Press L to load most recently saved game.";
        if (over) {
            if(p1winner)
            {
                g.drawString(gameOverP1, (int) (getWidth() / 2 - gameOverP1.length() * 2.5f), (int) pingr.dim.getHeight() / 4);
                g.drawString(loadGame, (int) (getWidth() / 2 - loadGame.length() * 2.5f), (int) pingr.dim.getHeight() / 3);
            }
            if(p2winner)
            {
                g.drawString(gameOverP2, (int) (getWidth() / 2 - gameOverP2.length() * 2.5f), (int) pingr.dim.getHeight() / 4);
                g.drawString(loadGame, (int) (getWidth() / 2 - loadGame.length() * 2.5f), (int) pingr.dim.getHeight() / 3);
            }
        }

        String pausedGame = "Paused! Press space to unpause. Press S to save";

        if (paused && !over) {
            g.drawString(pausedGame, (int) (getWidth() / 2 - pausedGame.length() * 2.5f), (int) pingr.dim.getHeight() / 4);
        }

        String serveBall = "Press the space bar to serve the ball!";

        if (serve && !over && !paused) {
            g.drawString(serveBall, (int) (getWidth() / 2 - serveBall.length() * 2.5f), (int) pingr.dim.getHeight() / 4);
        }
    }
}