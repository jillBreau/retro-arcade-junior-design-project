import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class BBRunner implements ActionListener, KeyListener //ActionListener moves ball, KeyListener for arrows
{

    private BlockBlaster blockBlaster, levelUpBB;

    private JFrame jframe;

    private BlockBlasterRP renderpanel;

    private Timer timer = new Timer(80, this);

    public static final int SCALE = 10;

    private int ticks = 0;

    private int nonExistentLevel = 6;

    public static boolean over, paused, started, levelingUp, won; //stages of the game
    public static boolean newG, load, newLevel; //ways to start a game

    public static Dimension dim;

    private Saver bbs;
    private Loader bbl;

    public Random random;


    public BBRunner()
    {
        jframe = new JFrame("Block Blaster");
        dim = Toolkit.getDefaultToolkit().getScreenSize();

        jframe.setVisible(true);
        jframe.setResizable(false);
        jframe.setSize(805,700);
        jframe.setLocation(dim.width/2 - jframe.getWidth()/2, dim.height/2 - jframe.getHeight()/2 );

        jframe.add(renderpanel = new BlockBlasterRP());

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jframe.addKeyListener(this);
        jframe.setFocusable(true);
        jframe.setFocusTraversalKeysEnabled(false);

        blockBlaster = new BlockBlaster();

        bbs = new Saver();

        bbl = new Loader();

        startGame();

    }

    public BlockBlaster getBlockBlaster()
    {
        return blockBlaster;
    }

    public void startGame()
    {
        if (newG) {
            over = false;
            paused = false;
            levelingUp = false;
            won = false;
            blockBlaster.setScore(0);
            blockBlaster.setBall(40, 63);
            blockBlaster.setPaddle(37, 64);
            blockBlaster.setTime(0);
            blockBlaster.setDirection(0);
            blockBlaster.setRow(3);
            blockBlaster.setCol(6);
            blockBlaster.setTotalBricks(blockBlaster.getCol()*blockBlaster.getRow());
            blockBlaster.setBricksOG(blockBlaster.getRow(), blockBlaster.getCol());
            blockBlaster.setBallXdir((int)(Math.random()*5) - 2);
            while(blockBlaster.getBallXdir() == 0)
            {
                blockBlaster.setBallXdir((int)(Math.random()*(5) - 2));
            }
            blockBlaster.setBallYdir(-1);
            blockBlaster.setLevel(1);

            timer.start();
        }
        else if (load)
        {

            loadGame();
        }

        else if(newLevel)
        {

            over = false;
            paused = false;
            blockBlaster.setScore(blockBlaster.getScore());
            blockBlaster.setTime(0);
            blockBlaster.setDirection(0);
            blockBlaster.setBall(40, 63);
            blockBlaster.setPaddle(37, 64);
            blockBlaster.setRow(2 + blockBlaster.getLevel());
            blockBlaster.setCol(6);
            blockBlaster.setTotalBricks(blockBlaster.getCol() * blockBlaster.getRow());
            blockBlaster.setBricksOG(blockBlaster.getRow(), blockBlaster.getCol());
            blockBlaster.setBallXdir((int) (Math.random() * (5) - 2));
            while (blockBlaster.getBallXdir() == 0) {
                blockBlaster.setBallXdir((int) (Math.random() * (5) - 2));
            }
            blockBlaster.setBallYdir(-1);
            blockBlaster.setLevel(blockBlaster.getLevel());

            timer.start();

        }

    }

    public void loadGame()
    {
        bbl.BBLoad();

        started = true;
        over = false;
        paused = false;
        levelingUp = false;

        blockBlaster.setScore(bbl.getLoadedBB().getScore());
        blockBlaster.setTime(bbl.getLoadedBB().getTime());
        blockBlaster.setTotalBricks(bbl.getLoadedBB().getTotalBricks());
        blockBlaster.setCol(bbl.getLoadedBB().getCol());
        blockBlaster.setRow(bbl.getLoadedBB().getRow());
        blockBlaster.setPaddle(bbl.getLoadedBB().getPaddle().x, bbl.getLoadedBB().getPaddle().y);
        blockBlaster.setBall(bbl.getLoadedBB().getBall().x, bbl.getLoadedBB().getBall().y);
        blockBlaster.setDirection(bbl.getLoadedBB().getDirection());
        blockBlaster.setBricks(bbl.getLoadedBB().getBricks());
        blockBlaster.setBallXdir(bbl.getLoadedBB().getBallXdir());
        blockBlaster.setBallYdir(bbl.getLoadedBB().getBallYdir());
        blockBlaster.setLevel(bbl.getLoadedBB().getLevel());




        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (started)
        {
            renderpanel.repaint();
            ticks++;

            if (ticks % 1 == 0 && !paused && !over  && !levelingUp && started && !won) {

                blockBlaster.incTime();

                if (new Rectangle(blockBlaster.getBall().x * SCALE, blockBlaster.getBall().y * SCALE, SCALE, SCALE).intersects(new Rectangle(getBlockBlaster().getPaddle().x * SCALE, (getBlockBlaster().getPaddle().y - 1) * SCALE, 2 * (SCALE), SCALE)) && ticks > 4) {
                    //hit paddle left side

                    if (blockBlaster.getBallXdir() > 0) //coming from left
                    {
                        blockBlaster.setBallXdir(1);
                        blockBlaster.setBallYdir(-2);
                    } else //coming from right
                    {
                        blockBlaster.setBallXdir(-2);
                        blockBlaster.setBallYdir(-1);
                    }
                }
                if (new Rectangle(blockBlaster.getBall().x * SCALE, blockBlaster.getBall().y * SCALE, SCALE, SCALE).intersects(new Rectangle((getBlockBlaster().getPaddle().x + 2) * SCALE, (getBlockBlaster().getPaddle().y - 1) * SCALE, 3 * (SCALE), SCALE)) && ticks > 4) {
                    //hit paddle middle

                    if (blockBlaster.getBallXdir() > 0) //coming from left
                    {
                        blockBlaster.setBallXdir(1);
                        blockBlaster.setBallYdir(-1);
                    } else //coming from right
                    {
                        blockBlaster.setBallXdir(-1);
                        blockBlaster.setBallYdir(-1);
                    }
                }
                if (new Rectangle(blockBlaster.getBall().x * SCALE, blockBlaster.getBall().y * SCALE, SCALE, SCALE).intersects(new Rectangle((getBlockBlaster().getPaddle().x + 5) * SCALE, (getBlockBlaster().getPaddle().y - 1) * SCALE, 2 * (SCALE), SCALE)) && ticks > 4) {
                    //hit paddle right side

                    if (blockBlaster.getBallXdir() > 0) //coming from left
                    {
                        blockBlaster.setBallXdir(2);
                        blockBlaster.setBallYdir(-1);
                    } else //coming from right
                    {
                        blockBlaster.setBallXdir(-1);
                        blockBlaster.setBallYdir(-2);
                    }
                }

                //generating brick rectangles to hit
                for (int i = 0; i < getBlockBlaster().getRow(); i++) {
                    for (int j = 0; j < getBlockBlaster().getCol(); j++) {

                        //if the ball is going very fast and could get inside brick from a side AND an up or down simultaneously
                        if ((((new Rectangle((blockBlaster.getBall().x) * SCALE, (blockBlaster.getBall().y - 1) * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE))
                                || new Rectangle((blockBlaster.getBall().x) * SCALE, (blockBlaster.getBall().y + 1) * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE)))
                                && (new Rectangle((blockBlaster.getBall().x + 1) * SCALE, (blockBlaster.getBall().y) * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE))
                                || new Rectangle((blockBlaster.getBall().x - 1) * SCALE, (blockBlaster.getBall().y) * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE))))
                                || new Rectangle(blockBlaster.getBall().x * SCALE, (blockBlaster.getBall().y) * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE))) && (blockBlaster.getOneBrick(i, j) == 1)) {
                            //going faster side to side
                            if (blockBlaster.getBallXdir() == -2 || blockBlaster.getBallXdir() == 2) {
                                blockBlaster.setBallXdir(-blockBlaster.getBallXdir());
                                blockBlaster.eraseBrick(i, j);
                                blockBlaster.incScore();
                                blockBlaster.decTotalBricks();
                            }
                            //going faster up and down
                            else if (blockBlaster.getBallYdir() == -2 || blockBlaster.getBallYdir() == 2) {
                                blockBlaster.setBallYdir(-blockBlaster.getBallYdir());
                                blockBlaster.eraseBrick(i, j);
                                blockBlaster.incScore();
                                blockBlaster.decTotalBricks();
                            }
                        } else {
                            //hit bottom of brick
                            if (new Rectangle(blockBlaster.getBall().x * SCALE, (blockBlaster.getBall().y - 1) * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE))
                                    && blockBlaster.getOneBrick(i, j) == 1) {
                                blockBlaster.setBallYdir(-blockBlaster.getBallYdir());
                                blockBlaster.eraseBrick(i, j);
                                blockBlaster.incScore();
                                blockBlaster.decTotalBricks();
                            }
                            //hit top of brick
                            if (new Rectangle(blockBlaster.getBall().x * SCALE, (blockBlaster.getBall().y + 1) * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE))
                                    && blockBlaster.getOneBrick(i, j) == 1) {
                                blockBlaster.setBallYdir(-blockBlaster.getBallYdir());
                                blockBlaster.eraseBrick(i, j);
                                blockBlaster.incScore();
                                blockBlaster.decTotalBricks();
                            }
                            //hit left of brick
                            if (new Rectangle((blockBlaster.getBall().x + 1) * SCALE, blockBlaster.getBall().y * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE)) && blockBlaster.getOneBrick(i, j) == 1) {
                                blockBlaster.setBallXdir(-blockBlaster.getBallXdir());
                                blockBlaster.eraseBrick(i, j);
                                blockBlaster.incScore();
                                blockBlaster.decTotalBricks();
                            }
                            //hit right of brick
                            if (new Rectangle((blockBlaster.getBall().x - 1) * SCALE, blockBlaster.getBall().y * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE)) && blockBlaster.getOneBrick(i, j) == 1) {
                                blockBlaster.setBallXdir(-blockBlaster.getBallXdir());
                                blockBlaster.eraseBrick(i, j);
                                blockBlaster.incScore();
                                blockBlaster.decTotalBricks();
                            }
                            //hit bottom left corner of brick
                            if (new Rectangle((blockBlaster.getBall().x + 1) * SCALE, (blockBlaster.getBall().y - 1) * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE))
                                    && blockBlaster.getBallXdir() > 0 && blockBlaster.getBallYdir() < 0 && blockBlaster.getOneBrick(i, j) == 1) {
                                blockBlaster.setBallYdir(-blockBlaster.getBallYdir());
                                blockBlaster.setBallXdir(-blockBlaster.getBallXdir());
                                blockBlaster.eraseBrick(i, j);
                                blockBlaster.incScore();
                                blockBlaster.decTotalBricks();
                            }
                            //hit top right corner of brick
                            if (new Rectangle((blockBlaster.getBall().x - 1) * SCALE, (blockBlaster.getBall().y + 1) * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE))
                                    && blockBlaster.getBallXdir() < 0 && blockBlaster.getBallYdir() > 0 && blockBlaster.getOneBrick(i, j) == 1) {
                                blockBlaster.setBallYdir(-blockBlaster.getBallYdir());
                                blockBlaster.setBallXdir(-blockBlaster.getBallXdir());
                                blockBlaster.eraseBrick(i, j);
                                blockBlaster.incScore();
                                blockBlaster.decTotalBricks();
                            }
                            //hit top left corner of brick
                            if (new Rectangle((blockBlaster.getBall().x + 1) * SCALE, (blockBlaster.getBall().y + 1) * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE))
                                    && blockBlaster.getBallXdir() > 0 && blockBlaster.getBallYdir() > 0 && blockBlaster.getOneBrick(i, j) == 1) {
                                blockBlaster.setBallYdir(-blockBlaster.getBallYdir());
                                blockBlaster.setBallXdir(-blockBlaster.getBallXdir());
                                blockBlaster.eraseBrick(i, j);
                                blockBlaster.incScore();
                                blockBlaster.decTotalBricks();
                            }
                            //hit bottom right of brick
                            if (new Rectangle((blockBlaster.getBall().x - 1) * SCALE, (blockBlaster.getBall().y - 1) * SCALE, SCALE, SCALE).intersects(new Rectangle(j * 11 * BBRunner.SCALE + 85, i * 7 * BBRunner.SCALE + 50, 7 * BBRunner.SCALE, 5 * BBRunner.SCALE))
                                    && blockBlaster.getBallXdir() < 0 && blockBlaster.getBallYdir() < 0 && blockBlaster.getOneBrick(i, j) == 1) {
                                blockBlaster.setBallYdir(-blockBlaster.getBallYdir());
                                blockBlaster.setBallXdir(-blockBlaster.getBallXdir());
                                blockBlaster.eraseBrick(i, j);
                                blockBlaster.incScore();
                                blockBlaster.decTotalBricks();
                            }
                        }
                    }
                }


                //this is constant to all levels
                if (blockBlaster.getTotalBricks() != 0) {
                    blockBlaster.getBall().x += blockBlaster.getBallXdir();
                    blockBlaster.getBall().y += blockBlaster.getBallYdir();
                }

                if (blockBlaster.getBall().x >= 78)  //right border
                {
                    blockBlaster.setBallXdir(-blockBlaster.getBallXdir());
                }
                if (blockBlaster.getBall().x <= 0)   //left border
                {
                    blockBlaster.setBallXdir(-blockBlaster.getBallXdir());
                }
                if (blockBlaster.getBall().y <= 0)   //top border
                {
                    blockBlaster.setBallYdir(-blockBlaster.getBallYdir());
                }

                //bottom border
                if (blockBlaster.getBall().y + 1 >= 66) {
                    if(getBlockBlaster().getScore() > GameRunner.getHighScore().getBbHighScore())
                    {
                        GameRunner.getHighScore().setBbHighScore(getBlockBlaster().getScore());
                        GameRunner.getHsSaver().SerializeHS(GameRunner.getHighScore());
                    }
                    over = true;
                }

                //if (blockBlaster.getTotalBricks() < getBlockBlaster().getRow() * getBlockBlaster().getCol() - 1) {
                    if (blockBlaster.getTotalBricks() ==0) {
                    if(blockBlaster.getLevel() == nonExistentLevel - 1) {
                        blockBlaster.setBallXdir(0);
                        blockBlaster.setBallYdir(0);
                        if(getBlockBlaster().getScore() > GameRunner.getHighScore().getBbHighScore())
                        {
                            GameRunner.getHighScore().setBbHighScore(getBlockBlaster().getScore());
                            GameRunner.getHsSaver().SerializeHS(GameRunner.getHighScore());
                        }
                        won = true;
                    }
                    else{
                        levelingUp = true;
                        blockBlaster.setLevel(blockBlaster.getLevel() + 1);
                    }
                }
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent e)
    {
        int i = e.getKeyCode();

        if (i == KeyEvent.VK_LEFT)
        {
            if (blockBlaster.getPaddle().x - 1 >= 0 && paused == false && !over && !won) //has not hit left edge
            {
                blockBlaster.setPaddle(blockBlaster.getPaddle().x - 1, blockBlaster.getPaddle().y);
            }

            //blockBlaster.setDirection(0);
        }
        if (i == KeyEvent.VK_RIGHT)
        {
            if (blockBlaster.getPaddle().x + 1 < 73 && paused == false && !over && !won) //has not hit right edge
            {
                blockBlaster.setPaddle(blockBlaster.getPaddle().x + 1, blockBlaster.getPaddle().y);
            }

            //blockBlaster.setDirection(1);
        }

        if (i == KeyEvent.VK_SPACE)
        {
            if (over || won) {
                newG = true;
                newLevel = false;
                startGame();
            }

            else if (levelingUp){
                started = true;
                levelingUp = false;
                load = false;
                newG = false;
                newLevel = true;

                startGame();
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
                levelingUp = false;
                newG = true;
                startGame();
            }
        }
        if (i == KeyEvent.VK_S)
        {
            if (paused)
            {
                bbs.SerializeBB(blockBlaster);
            }
            else if (levelingUp)
            {
                levelUpBB = new BlockBlaster();

                levelUpBB.setScore(blockBlaster.getScore());
                levelUpBB.setTime(blockBlaster.getTime());
                levelUpBB.setTotalBricks(blockBlaster.getTotalBricks());
                levelUpBB.setCol(blockBlaster.getCol());
                levelUpBB.setRow(blockBlaster.getRow());
                levelUpBB.setPaddle(blockBlaster.getPaddle().x, blockBlaster.getPaddle().y);
                levelUpBB.setBall(blockBlaster.getBall().x, blockBlaster.getBall().y);
                levelUpBB.setBricks(blockBlaster.getBricks());
                levelUpBB.setBallYdir(blockBlaster.getBallYdir());
                levelUpBB.setBallXdir(blockBlaster.getBallXdir());
                levelUpBB.setLevel(blockBlaster.getLevel() - 1);

                bbs.SerializeBB(levelUpBB);
            }
        }
        if (i == KeyEvent.VK_L)
        {
            if(!started || over || won)
            {
                over = false;
                won = false;
                started = true;
                newG = false;
                levelingUp = false;
                load = true;
                loadGame();
            }
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}


}
