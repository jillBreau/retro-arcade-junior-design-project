import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class BlockBlasterRP extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLACK); //black background

        g.fillRect(0, 0 , 806, 700);

        BBRunner bbr = GameRunner.bbRunner;


        boolean over = bbr.over;
        boolean paused = bbr.paused;
        boolean started = bbr.started;
        boolean levelingUp = bbr.levelingUp;
        boolean won = bbr.won;

        g.setColor(Color.WHITE); //white paddle

        if (started) {
            g.fillRect(bbr.getBlockBlaster().getPaddle().x * BBRunner.SCALE, bbr.getBlockBlaster().getPaddle().y * BBRunner.SCALE, 7*(BBRunner.SCALE), BBRunner.SCALE);
        }

        g.setColor(Color.RED); //red bricks //TODO


        if (started) {

            for (int i = 0; i < bbr.getBlockBlaster().getRow(); i ++){
                for (int j = 0; j < bbr.getBlockBlaster().getCol(); j ++){
                    if (bbr.getBlockBlaster().getBricks()[i][j] > 0){
                        g.fillRect(j*11*BBRunner.SCALE + 85, i*7*BBRunner.SCALE + 50, 7*BBRunner.SCALE, 5*BBRunner.SCALE);
                    }
                }
            }
        }

        g.setColor(Color.YELLOW); //yellow ball

        if (started) {
            g.fillOval(bbr.getBlockBlaster().getBall().x * BBRunner.SCALE, bbr.getBlockBlaster().getBall().y * BBRunner.SCALE, BBRunner.SCALE, BBRunner.SCALE);
        }



        String info1 = "Score: " +bbr.getBlockBlaster().getScore() + ", Blocks left: " + bbr.getBlockBlaster().getTotalBricks() + ", Level Time: " + bbr.getBlockBlaster().getTime() / 20 + ", Level: " + (bbr.getBlockBlaster().getLevel()-1) + "     Pause = Space";

        String info2 = "Score: " + bbr.getBlockBlaster().getScore() + ", Blocks left: " + bbr.getBlockBlaster().getTotalBricks() + ", Level Time: " + bbr.getBlockBlaster().getTime() / 20 + ", Level: " + bbr.getBlockBlaster().getLevel() + "     Pause = Space";

        g.setColor(Color.GREEN); //green info

        if (levelingUp == true) {
            g.drawString(info1, (int) (getWidth() / 2 - info1.length() * 2.5f), 10);
        } else {
            g.drawString(info2, (int) (getWidth() / 2 - info2.length() * 2.5f), 10);
        }

        g.setColor(Color.YELLOW); // yellow prompts


        String startGame = "Welcome to Block Blaster, press L to Load the most recently saved game or press N to start a New game";
        String loadGame = "Press L to load most recently saved game.";

        if (!started) {
            g.drawString(startGame, (int) (getWidth() / 2 - startGame.length() * 2.5f), (int) bbr.dim.getHeight() / 4);
        }

        String gameOver = "Game Over! Press space to play again. The High Score is: " + GameRunner.getHighScore().getBbHighScore() + " Your score was: " + bbr.getBlockBlaster().getScore();

        if (over) {
            g.drawString(gameOver, (int) (getWidth() / 2 - gameOver.length() * 2.5f), (int) bbr.dim.getHeight() / 4);
            g.drawString(loadGame, (int) (getWidth() / 2 - loadGame.length() * 2.5f), (int) bbr.dim.getHeight() / 3);
        }

        String pausedGame = "Paused! Press space to unpause. Press S to save";

        if (paused && !over) {
            g.drawString(pausedGame, (int) (getWidth() / 2 - pausedGame.length() * 2.5f), (int) bbr.dim.getHeight() / 4);
        }

        String levelUp = "Level up! Press space to begin level " + bbr.getBlockBlaster().getLevel() + ". Press S to save";

        if (levelingUp){
            g.drawString(levelUp, (int) (getWidth() / 2 - pausedGame.length() * 2.5f), (int) bbr.dim.getHeight() / 4);
        }

        String winner = "You win! You beat level " + (bbr.getBlockBlaster().getLevel()) + "! Press Space to restart. The High Score is: " + GameRunner.getHighScore().getBbHighScore() + " Your score was:" + bbr.getBlockBlaster().getScore();

        if (won){
            g.drawString(winner, (int) (getWidth() / 2 - winner.length() * 2.5f), (int) bbr.dim.getHeight() / 4);
            g.drawString(loadGame, (int) (getWidth() / 2 - loadGame.length() * 2.5f), (int) bbr.dim.getHeight() / 3);
        }

    }

}