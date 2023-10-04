import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class PythonRP extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        g.fillRect(0, 0 , 805, 700);

        PythonRunner pr = GameRunner.pythonRunner;

        boolean over = pr.over;
        boolean paused = pr.paused;
        boolean started = pr.started;

        g.setColor(Color.WHITE);


           if (started) {

               for (Point point : pr.getPython().getPython()) {
                   g.fillRect(point.x * PythonRunner.SCALE, point.y * PythonRunner.SCALE, PythonRunner.SCALE, PythonRunner.SCALE);
               }
           }

           if (started)
               g.fillRect(pr.getPython().getHead().x * PythonRunner.SCALE, pr.getPython().getHead().y * PythonRunner.SCALE, PythonRunner.SCALE, PythonRunner.SCALE);

           g.setColor(Color.RED);

           if (started)
               g.fillRect(pr.getPython().getCherry().x * PythonRunner.SCALE, pr.getPython().getCherry().y * PythonRunner.SCALE, PythonRunner.SCALE, PythonRunner.SCALE);

           String string = "Score: " + pr.getPython().getScore() + ", Length: " + pr.getPython().getTailLength() + ", Time: " + pr.getPython().getTime() / 20 + "      Pause = Space" ;

           g.setColor(Color.GREEN);

           g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);

           g.setColor(Color.YELLOW);

           String startGame = "Welcome to Python, press L to Load the most recently saved game or press N to start a New game";

           if (!started) {
               g.drawString(startGame, (int) (getWidth() / 2 - startGame.length() * 2.5f), (int) pr.dim.getHeight() / 4);
           }

           String gameOver = "Game Over! Press space to play again." + " Highscore is: " + GameRunner.getHighScore().getPyHighScore() + " Your score was: " + pr.getPython().getScore();
           String loadGame = "Press L to load most recently saved game.";
           if (over) {
               g.drawString(gameOver, (int) (getWidth() / 2 - gameOver.length() * 2.5f), (int) pr.dim.getHeight() / 4);
               g.drawString(loadGame, (int) (getWidth() / 2 - loadGame.length() * 2.5f), (int) pr.dim.getHeight() / 3);
           }

           String pausedGame = "Paused! Press space to unpause. Press S to save";

           if (paused && !over) {
               g.drawString(pausedGame, (int) (getWidth() / 2 - pausedGame.length() * 2.5f), (int) pr.dim.getHeight() / 4);
           }

    }

}
